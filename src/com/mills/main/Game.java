package com.mills.main;

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

import com.mills.entities.Player;
import com.mills.entities.Zombie;
import com.mills.handlers.FileHandler;
import com.mills.handlers.GUIHandler;
import com.mills.handlers.InputHandler;
import com.mills.handlers.WorldHandler;
import com.mills.rendering.Display;
import com.mills.world.OverWorld;
import com.mills.world.UnderWorld;
import com.mills.world.World;
import com.mills.world.tiles.Tile;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 8125218200279626336L;

	private Thread thread;
	private static boolean isRunning;

	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int LINUXWIDTH = 700;
	public static final int LINUXHEIGHT = 400;

	public static final String osName = System.getProperty("os.name");

	private static final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private static JFrame frame;

	public static World currentWorld;
	
	private final InputHandler inputHandler = new InputHandler(this);
	private final WorldHandler worldHandler = new WorldHandler();
	private final GUIHandler guiHandler = new GUIHandler();
	private final FileHandler fileHandler = new FileHandler();
	
	public static final Map<String, Object> handlers = new HashMap<String, Object>();
	
	public static Player player;
	
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
		player.setPos(currentWorld.getTile(currentWorld.getWidth() / 2), currentWorld.getTile(currentWorld.getHeight() / 2));
		
		/* Create a test Zombie */
		Zombie zombie = new Zombie("George", currentWorld, 50, 50);
		
		/* Add Entities to the world's entity handler */
		currentWorld.addEntity(player);
		currentWorld.addEntity(zombie);
		
		/* Map the handlers to the integer keys, so we can access them in other classes */
		handlers.put("input", inputHandler);
		handlers.put("world", worldHandler);
		handlers.put("gui", guiHandler);
		handlers.put("file", fileHandler);
		
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
				System.out.println("X: " + (player.getX() / Tile.TILEWIDTH) + "\nY: " + (player.getY() / Tile.TILEHEIGHT));

				frames = 0;
				ticks = 0;
			}
		}
	}
	
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
	
	public List<Object> save()
	{
		List<Object> list = new ArrayList<Object>();
		list.add(handlers);
		list.add(currentWorld);
		
		return list;
	}
	
}
