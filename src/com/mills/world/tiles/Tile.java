package com.mills.world.tiles;

import java.awt.Color;

import com.mills.world.World;

public abstract class Tile {

	protected String name;
	protected TileType type;
	protected int x;
	protected int y;
	protected int oX;
	protected int oY;
	protected int tileX;
	protected int tileY;
	protected World world;
	protected java.awt.Color TILECOLOR;
	public static final int TILEWIDTH = 50;
	public static final int TILEHEIGHT = 50;
	
	public Tile(TileType type, World world, int x, int y, boolean wasPlaced)
	{
		this.type = type;
		this.world = world;
		if(wasPlaced)
		{
			this.oX = x * TILEWIDTH;
			this.oY = y * TILEHEIGHT;
		} else
		{
			this.oX = x;
			this.oY = y;
		}
		tileX = x / TILEWIDTH;
		tileY = y / TILEHEIGHT;
	}

	public boolean contains(int x, int y)
	{
		if((x >= this.x && x < Tile.TILEWIDTH) && (y >= this.y && y < Tile.TILEHEIGHT))
				return true;
		return false;
	}
	
	public void setType(TileType type)
	{
		this.type = type;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getTileX()
	{
		return tileX;
	}
	
	public int getY()
	{
		return y;
	}

	public int getTileY()
	{
		return tileY;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public TileType getType()
	{
		return type;
	}
	
	public void render(java.awt.Graphics g)
	{
		g.setColor(TILECOLOR);
		g.fillRect(x, y, TILEWIDTH, TILEHEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, TILEWIDTH, TILEHEIGHT);
	}
	public abstract void tick();
	
}
