package com.mills.entities;

import com.mills.world.World;
import com.mills.world.tiles.Tile;

public abstract class Entity {

	protected String name;
	protected int x;
	protected int y;
	protected int speed;
	protected int width;
	protected int height;
	protected World world;
	protected java.awt.Color color;
	
	public Entity(String name, World world, int x, int y)
	{
		this.name = name;
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(java.awt.Graphics g);
	
	public String getName()
	{
		return name;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getTileX()
	{
		return x / Tile.TILEWIDTH;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getTileY()
	{
		return y / Tile.TILEHEIGHT;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public void keepInBounds()
	{
		if(x < 0) x = 0;
		if(x > world.getWidth()) x = world.getWidth();
		if(y < 0) y = 0;
		if(y > world.getHeight()) y = world.getHeight();
	}
}
