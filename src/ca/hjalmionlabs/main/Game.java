package ca.hjalmionlabs.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import ca.hjalmionlabs.entities.Player;
import ca.hjalmionlabs.entities.Zombie;
import ca.hjalmionlabs.handlers.GUIHandler;
import ca.hjalmionlabs.handlers.InputHandler;
import ca.hjalmionlabs.handlers.WorldHandler;
import ca.hjalmionlabs.rendering.Display;
import ca.hjalmionlabs.world.OverWorld;
import ca.hjalmionlabs.world.UnderWorld;
import ca.hjalmionlabs.world.World;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 8125218200279626336L;	// Needed for Runnable

	/* The Thread to run this Game with */
	private Thread thread;
	/* Whether or not the Game is running */
	private static boolean isRunning;

	/* Width of the screen, in pixels */
	public static final int WIDTH = 1080;
	/* Height of the screen, in pixels. The height is calculated by using the Width and generating a 16:9 aspect ratio */
	public static final int HEIGHT = WIDTH / 16 * 9;
	/* Width of the screen, in pixels, if the OS running the Game is Linux / Unix based */
	public static final int LINUXWIDTH = 700;
	/* Height of the screen, in pixels, if the OS running the Game is Linux / Unix based. */
	public static final int LINUXHEIGHT = 400;

	/* The name of the OS running the Game.  */
	public static final String osName = System.getProperty("os.name");

	/* This image is the background of the game */
	private static final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	/* The window that the Game lives in. Instantiated by Display.create(...) */
	private static JFrame frame;

	/* The current World that we are in */
	public static World currentWorld;
	
	/* Handles all of the input for the Game */
	private final InputHandler inputHandler = new InputHandler(this);
	/* Handles all of the World for the Game */
	private final WorldHandler worldHandler = new WorldHandler();
	/* Handles any UI elements for the Game */
	private final GUIHandler guiHandler = new GUIHandler();
	
	public static final Map<String, Object> handlers = new HashMap<String, Object>();
	
	/* The Player for the game */
	public static Player player;
	
	/**
	 * Starts the game
	 */
	public synchronized void start()
	{
		if(isRunning) return;
		thread = new Thread(this, "MAIN_GAME_LOOP");
		isRunning = true;
        if (osName.contains("Mac"))
        	thread.run();
        else
        	thread.start();
	}

	/**
	 * Stops the game
	 */
	public synchronized void stop()
	{
		if(!isRunning) return;
		try
		{
			thread.join();
			isRunning = false;
		} catch(InterruptedException e)
		{
			System.out.println("Error encountered while stopping thread!");
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Helper method that instantiates anything that is needed and wasn't instantiated already
	 */
	private void init()
	{
		System.out.println("Initialize..");
		
		/* Set up the World */
		System.out.println("Set up the World");
		worldHandler.add(new OverWorld("Overworld"));
		worldHandler.add(new UnderWorld("Underworld"));
		
		currentWorld = worldHandler.get("Overworld");
		currentWorld.createWorld();
		
		/* Instantiate the Player */
		player = new Player("Player1", currentWorld, WIDTH / 2, HEIGHT / 2, 5);
		
		/* Create a test Zombie */
		Zombie zombie = new Zombie("George", currentWorld, 50, 50);
		zombie.setPos(currentWorld.getTile(2 * currentWorld.getWidth() + 1));
		
		/* Add Entities to the world's entity handler */
		currentWorld.addEntity(player);
		currentWorld.addEntity(zombie);
		
		/* Map the handlers to the string keys, so we can access them in other classes */
		handlers.put("input", inputHandler);
		handlers.put("world", worldHandler);
		handlers.put("gui", guiHandler);
		
		System.out.println("Set up the main window");

		if(osName.contains("nux"))
			frame = Display.create("Top Down 2D Game", LINUXWIDTH, LINUXHEIGHT);
		else
			frame = Display.create("Top Down 2D Game", WIDTH, HEIGHT);
		setPreferredSize(new Dimension(currentWorld.getWidth(), currentWorld.getHeight()));
		setMaximumSize(new Dimension(currentWorld.getWidth(), currentWorld.getHeight()));
		setMinimumSize(new Dimension(currentWorld.getWidth(), currentWorld.getHeight()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		requestFocus();
		
		
		System.out.println("Done Initialization");
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();
		double nsPerTick = 1.0E9D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		System.out.println("Entering Game Loop...");
		
		while(isRunning)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -=1;
				shouldRender = true;
			}
			try
			{
				Thread.sleep(2);
			} catch(InterruptedException e)
			{
				System.err.println("Error while trying to sleep thread!");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			if(shouldRender)
			{
				frames++;
				render();
			}
			if(System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				System.out.println("Current World: " + worldHandler.getCurrentWorld());
				System.out.println("X: " + player.getTileX() + "\nY: " + player.getTileY());

				frames = 0;
				ticks = 0;
			}
		}
	}
	
	/**
	 * Runs usually about 60 times a second. All updates go here
	 */
	public void tick()
	{
		worldHandler.tick();
		
		if(inputHandler.UP.isPressed())
		{
			if(currentWorld.yOffset < 0 && player.getTileY() <= 4)	// If the Player is in the World (not in the void) and within the 4 Tile "square", move the World
				currentWorld.yOffset += player.getSpeed();
			else													// Otherwise just move the Player
				player.setY(player.getY() - player.getSpeed());
		}
		if(inputHandler.DOWN.isPressed())
		{
			if(currentWorld.yOffset < currentWorld.getHeight() && player.getTileY() >= 4)
				currentWorld.yOffset -= player.getSpeed();
			else if(player.getTileY() <= 4)						// If Player is less than or at 4 tiles away from the edge, move the Player
				player.setY(player.getY() + player.getSpeed());	
		}
		if(inputHandler.LEFT.isPressed())
		{
			if(currentWorld.xOffset < 0 && player.getTileX() <= 4)	// If the Player is in the World (not in the void) and within the 4 Tile "square". move the World
				currentWorld.xOffset += player.getSpeed();
			else
				player.setX(player.getX() - player.getSpeed());		// Otherwise just move the Player
		}
		if(inputHandler.RIGHT.isPressed())
		{
			if(currentWorld.xOffset < currentWorld.getWidth() && player.getTileX() >= 4)	// If the Player is less than the width of the World and outside the 4 Tile "square", move the World
				currentWorld.xOffset -= player.getSpeed();
			else if(player.getTileX() <= 4)													// If the player is less than or at 4 Tiles away from the edge, move the Player
				player.setX(player.getX() + player.getSpeed());
		}
		
		guiHandler.tick();
			
	}
	
	/**
	 * Draws everything to the screen
	 */
	public void render()
	{		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/* START DRAWING */
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, null);
		worldHandler.render(g);
		guiHandler.render(g);
		/* END DRAWING */
		
		g.dispose();
		bs.show();
	}
}
