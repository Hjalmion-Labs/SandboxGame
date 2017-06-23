package com.mills.entities;

import java.awt.Dimension;

import com.mills.world.World;
import com.mills.world.tiles.Tile;

public abstract class Entity {

	protected String name;
	protected int x;
	protected int y;
	protected int oX;
	protected int oY;
	protected int tileX;
	protected int tileY;
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
		this.oX = this.x;
		this.oY = this.y;
		this.tileX = x * Tile.TILEWIDTH;
		this.tileY = y * Tile.TILEHEIGHT;
	}
	
	public abstract void tick();
	public abstract void render(java.awt.Graphics g);
	
	public String getName()
	{
		return name;
	}
	
	public void detectCollision(Entity ent1, Entity ent2)
	{
		Dimension center1 = new Dimension(ent1.getX() + (ent1.getWidth() / 2), ent1.getY() + (ent1.getHeight() / 2));
		Dimension center2 = new Dimension(ent2.getX() + (ent2.getWidth() / 2), ent2.getY() + (ent2.getHeight() / 2));
		
//		center1.
	}
	
	public int center()
	{
		
		return 0;
	}
	
	public void setPos(Tile tile)
	{
		tileX = tile.getTileX();
		tileY = tile.getTileY();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
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
	
	public void setTileX(Tile tile)
	{
		tileX = tile.getTileX();
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
	
	public void setTileY(Tile tile)
	{
		tileY = tile.getTileY();
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
