package com.mills.world.tiles;

import com.mills.world.World;

public abstract class Tile {

	protected String name;
	protected byte id;
	protected int x;
	protected int y;
	protected int oX;
	protected int oY;
	protected World world;
	protected java.awt.Color TILECOLOR;
	public static final int TILEWIDTH = 50;
	public static final int TILEHEIGHT = 50;
	
	public Tile(String name, World world, byte id, int x, int y)
	{
		this.name = name;
		this.world = world;
		this.id = id;
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
	
	public String getName()
	{
		return name;
	}

	public World getWorld()
	{
		return world;
	}
	
	public byte getID()
	{
		return id;
	}
	
	public abstract void render(java.awt.Graphics g);
	public abstract void tick();
	
}
