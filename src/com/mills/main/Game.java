package com.mills.main;

import static java.lang.System.err;
import static java.lang.System.out;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.mills.main.rendering.Display;
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

	public static final String osName = System.getProperty("os.name");

	private static final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private static JFrame frame;

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
		/* Set up the level */
		System.out.println("Set up the World");
		int x = 0;
		int y = 0;
		for(int i = 0; i < World.WIDTH; i++)
		{
			x++;
			for(int j = 0; j < World.HEIGHT; j++)
			{
				World.tileHandler.addTile(new DirtTile(i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));

				y++;
			}
		}

		System.out.println("Set up the main window");

		if(osName.contains("nux"))
			frame = Display.create("Factory Game", LINUXWIDTH, LINUXHEIGHT);
		else
			frame = Display.create("Factory Game", WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this);
		frame.requestFocus();
		
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
		World.tileHandler.tick();
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/* START DRAWING */
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, null);
		World.tileHandler.render(g);
		/* END DRAWING */
		
		g.dispose();
		bs.show();
	}
	
}
