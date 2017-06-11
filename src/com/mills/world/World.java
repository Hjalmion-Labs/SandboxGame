package com.mills.world;

import com.mills.handlers.EntityHandler;
import com.mills.handlers.TileHandler;

public abstract class World {

	protected String name;
	
	protected int WIDTH;
	protected int HEIGHT;
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public World(String name, int width, int height)
	{
		this.name = name;
		WIDTH = width;
		HEIGHT = height;
	}
	
	public String getName()
	{
		return name;
	}
	
	protected TileHandler tileHandler;
	protected EntityHandler entityHandler;
	
	public abstract void createWorld();
	
	public void destroyWorld()
	{
		tileHandler.empty();
	}
	
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
	
	public int getWidth()
	{
		return WIDTH;
	}
	
	public int getHeight()
	{
		return HEIGHT;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
