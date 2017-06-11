package com.mills.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.mills.entities.Player;
import com.mills.handlers.EntityHandler;
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

	private int tickCount = 0;
	
	public static final String osName = System.getProperty("os.name");

	private static final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private static JFrame frame;

	public static World currentWorld;
	
	private final InputHandler inputHandler = new InputHandler(this);
	private final EntityHandler entityHandler = new EntityHandler();
	private final WorldHandler worldHandler = new WorldHandler();
	
	public Player player;
	
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
		
		/* Add Entities to the world's entity handler */
		currentWorld.addEntity(player);
		
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
//		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		requestFocus();
		
//		screen = new Screen(currentWorld.WIDTH, currentWorld.HEIGHT, new SpriteSheet("/SpriteSheet.png"));
		
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
				System.out.println("X: " + (currentWorld.xOffset / Tile.TILEWIDTH) + "\nY: " + (currentWorld.yOffset / Tile.TILEHEIGHT));

				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick()
	{
		tickCount++;
		
		worldHandler.tick();
		
		if(currentWorld.yOffset > 0)
			currentWorld.yOffset = 0;
		else if(currentWorld.yOffset > currentWorld.getHeight())
			currentWorld.yOffset = currentWorld.getHeight();
		if(currentWorld.xOffset > 0)
			currentWorld.xOffset = 0;
		else if(currentWorld.xOffset > currentWorld.getWidth())
			currentWorld.xOffset = currentWorld.getWidth();
		
		if(inputHandler.UP.isPressed())
			currentWorld.yOffset += player.getSpeed();
		if(inputHandler.DOWN.isPressed())
			currentWorld.yOffset -= player.getSpeed();
		if(inputHandler.LEFT.isPressed())
			currentWorld.xOffset += player.getSpeed();
		if(inputHandler.RIGHT.isPressed())
			currentWorld.xOffset -= player.getSpeed();
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
		Graphics2D g2 = (Graphics2D) g;
		
		/* START DRAWING */
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, null);
		worldHandler.render(g);
		/* Set Rendering Hints so we can draw the player nice and smooth */
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		entityHandler.render(g);
		/* Turn AntiAlias off so it doesn't affect any other objects being drawn */
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		/*  */
		/* END DRAWING */
		
		g.dispose();
		bs.show();
	}
	
}
