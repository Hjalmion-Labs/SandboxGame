package ca.hjalmionlabs.entities;

import ca.hjalmionlabs.world.World;

/**
 * Base class for all passive and hostile mobs
 */
public abstract class Mob extends Entity 
{

	/**
	 * Create a new Mob in the specified {@link World}
	 * @param name - String representation of the name of this Mob
	 * @param world - The {@link World} that this Mob belongs in
	 * @param x - The x-coord that this Mob should be created at
	 * @param y - The y-coord that this Mob should be created at
	 */
	public Mob(String name, World world, int x, int y)
	{
		super(name, world, x, y);
		this.color = java.awt.Color.RED;
	}

	@Override
	public abstract void tick();
	@Override
	public abstract void render(java.awt.Graphics g);

}
