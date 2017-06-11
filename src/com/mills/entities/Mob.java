package com.mills.entities;

import com.mills.world.World;

public abstract class Mob extends Entity 
{

	public Mob(String name, World world, int x, int y)
	{
		super(name, world, x, y);
		this.color = java.awt.Color.BLUE;
	}

	public abstract void tick();
	public abstract void render(java.awt.Graphics g);

}
