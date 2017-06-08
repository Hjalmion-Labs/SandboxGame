package com.mills.world.tiles;

public abstract class Tile {

	protected String name;
	protected byte id;
	protected int x;
	protected int y;
	protected java.awt.Color TILECOLOR;
	public static final int TILEWIDTH = 50;
	public static final int TILEHEIGHT = 50;
	
	public Tile(String name, byte id, int x, int y)
	{
		this.name = name;
		this.id = id;
		this.x = x;
		this.y = y;
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
	
	public byte getID()
	{
		return id;
	}
	
	public abstract void render(java.awt.Graphics g);
	public abstract void tick();
	
}
