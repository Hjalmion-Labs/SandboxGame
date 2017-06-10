package com.mills.world;

import com.mills.handlers.EntityHandler;
import com.mills.handlers.TileHandler;

public abstract class World {

	protected String name;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public World(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	protected TileHandler tileHandler;
	protected EntityHandler entityHandler;
	
	protected abstract void createWorld();
	
	/**
	 * Runs the render method for each of this World's handlers
	 * @param g - Graphics object to draw with
	 */
	public void renderAllHandlers(java.awt.Graphics g)
	{
		tileHandler.render(g);
		entityHandler.render(g);
	}
	
	/**
	 * Runs the tick method for each of this World's handlers
	 */
	public void tickAllHandlers()
	{
		tileHandler.tick();
		entityHandler.tick();
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
