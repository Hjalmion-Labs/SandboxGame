package com.mills.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.mills.world.World;
import com.mills.world.tiles.Tile;

public class Player extends Entity
{
	protected static final int radius = 30;
	protected static final Color color = Color.BLUE;
	protected int originalSpeed;
	
	public Player(String name, World world, int x, int y, int speed)
	{
		super(name, world, x, y);
		this.originalSpeed = speed;
		this.width = radius;
		this.height = radius;
		this.speed = originalSpeed;
	}

	@Override
	public void tick()
	{
		keepInBounds();
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	public void resetSpeed()
	{
		speed = originalSpeed;
	}
	
	public void mulSpeed(int factor)
	{
		speed *= factor;
	}
}
