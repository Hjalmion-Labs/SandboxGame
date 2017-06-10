package com.mills.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends Entity
{
	protected static final int radius = 30;
	protected static final Color color = Color.GREEN;
	
	public Player(String name, int x, int y, int speed)
	{
		super(name, x, y);
		this.speed = speed;
		this.width = radius;
		this.height = radius;
	}

	@Override
	public void tick()
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
}
