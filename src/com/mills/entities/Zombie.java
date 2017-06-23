package com.mills.entities;

import com.mills.world.World;

public class Zombie extends Mob 
{

	public Zombie(String name, World world, int x, int y)
	{
		super(name, world, x, y);
		width = 50;
		height = 50;
	}

	@Override
	public void tick()
	{
		x = oX + world.xOffset;
		y = oY + world.yOffset;
	}
	
	@Override
	public void render(java.awt.Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
}
