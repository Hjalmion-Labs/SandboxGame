package com.mills.entities;

public abstract class Mob extends Entity 
{

	public Mob(String name, int x, int y)
	{
		super(name, x, y);
		this.color = java.awt.Color.BLUE;
	}

	public abstract void tick();
	public abstract void render(java.awt.Graphics g);

}
