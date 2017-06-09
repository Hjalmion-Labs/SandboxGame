package com.mills.main;

import static java.lang.System.err;
import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.mills.handlers.InputHandler;
import com.mills.rendering.Display;
import com.mills.rendering.Screen;
import com.mills.rendering.SpriteSheet;
import com.mills.world.OverWorld;
import com.mills.world.UnderWorld;
import com.mills.world.World;

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
	private static int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private static int[] colors = new int[6 * 6 * 6];
	private Screen screen;
	
	private static JFrame frame;

	public static World overWorld = new OverWorld("OVER");
	public static World underWorld = new UnderWorld("UNDER");
	public static World currentWorld;
	
	private final InputHandler inputHandler = new InputHandler(this);
	
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
		
		int index = 0;
		for(int r = 0; r < 6; r++)
		{
			for(int g = 0; g < 6; g++)
			{
				for(int b = 0; b < 6; b++)
				{
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colors[index++] = rr << 16 | gg << 8 | bb;
				}
			}
			
		}
		
		/* Set up the level */
		System.out.println("Set up the World");
		currentWorld = underWorld;
		currentWorld.createWorld();
		System.out.println("Set up the main window");

		if(osName.contains("nux"))
			frame = Display.create("Factory Game", LINUXWIDTH, LINUXHEIGHT);
		else
			frame = Display.create("Factory Game", WIDTH, HEIGHT);
		setPreferredSize(new Dimension(World.WIDTH, World.HEIGHT));
		setMaximumSize(new Dimension(World.WIDTH, World.HEIGHT));
		setMinimumSize(new Dimension(World.WIDTH, World.HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
//		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		requestFocus();
		
		screen = new Screen(World.WIDTH, World.HEIGHT, new SpriteSheet("/SpriteSheet.png"));
		
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
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick()
	{
		tickCount++;
		
		currentWorld.tileHandler.tick();
		
		if(inputHandler.up.isPressed())
			currentWorld.yOffset++;
		if(inputHandler.down.isPressed())
			currentWorld.yOffset--;
		if(inputHandler.left.isPressed())
			currentWorld.xOffset++;
		if(inputHandler.right.isPressed())
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
		currentWorld.tileHandler.render(g);
		/* END DRAWING */
		
		g.dispose();
		bs.show();
	}
	
}
