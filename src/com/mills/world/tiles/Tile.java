package com.mills.world.tiles;

import com.mills.world.World;

public abstract class Tile {

	protected String name;
	protected TileType type;
	protected int x;
	protected int y;
	protected int oX;
	protected int oY;
	protected World world;
	protected java.awt.Color TILECOLOR;
	public static final int TILEWIDTH = 50;
	public static final int TILEHEIGHT = 50;
	
	public Tile(TileType type, World world, int x, int y)
	{
		this.type = type;
		this.world = world;
		this.oX = x;
		this.oY = y;
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}

	public World getWorld()
	{
		return world;
	}
	
	public TileType getType()
	{
		return type;
	}
	
	public abstract void render(java.awt.Graphics g);
	public abstract void tick();
	
}
