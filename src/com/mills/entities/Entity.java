package com.mills.entities;

import com.mills.world.World;

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
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getSpeed()
	{
		return speed;
	}
}
