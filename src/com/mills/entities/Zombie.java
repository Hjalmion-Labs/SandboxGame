package com.mills.entities;

import com.mills.world.World;

/**
 * Zombie is the first hostile {@link Mob} added to the game.
 * @author Nick Mills
 *
 */
public class Zombie extends Mob 
{
	/**
	 * Create a new Zombie in the specified {@link World}
	 * @param name - String representation of this Zombie's name
	 * @param world - The {@link World} that this Zombie belongs in
	 * @param x - The x-coord that this Zombie should be created at
	 * @param y - The y-coord that this Zombie should be created at
	 */
	public Zombie(String name, World world, int x, int y)
	{
		super(name, world, x, y);
		width = 25;
		height = 25;
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
