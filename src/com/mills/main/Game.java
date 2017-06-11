package com.mills.main;

import static java.lang.System.err;
import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.mills.handlers.EntityHandler;
import com.mills.handlers.InputHandler;
import com.mills.handlers.TileHandler;
import com.mills.handlers.WorldHandler;
import com.mills.rendering.Display;
import com.mills.rendering.Screen;
import com.mills.world.DefaultWorld;
import com.mills.world.OverWorld;
import com.mills.world.UnderWorld;
import com.mills.world.World;
import com.mills.world.tiles.DirtTile;
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
//	private static int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
//	private static int[] colors = new int[6 * 6 * 6];
	private Screen screen;
	
	private static JFrame frame;

	public static World currentWorld;
	
	private final InputHandler inputHandler = new InputHandler(this);
	private final EntityHandler entityHandler = new EntityHandler();
	private final WorldHandler worldHandler = new WorldHandler();
	TileHandler tiles = new TileHandler();
	
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
			out.println("Error encountered while stopping thread!");
			e.printStackTrace();
			err.println(e.getMessage());
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
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick()
	{
		tickCount++;
		
		worldHandler.tick();
		
		if(inputHandler.UP.isPressed())
			currentWorld.yOffset++;
		if(inputHandler.DOWN.isPressed())
			currentWorld.yOffset--;
		if(inputHandler.LEFT.isPressed())
			currentWorld.xOffset++;
		if(inputHandler.RIGHT.isPressed())
			currentWorld.xOffset--;
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
		/* END DRAWING */
		
		g.dispose();
		bs.show();
	}
	
}
