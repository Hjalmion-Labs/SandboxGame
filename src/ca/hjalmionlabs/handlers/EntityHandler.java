package ca.hjalmionlabs.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.hjalmionlabs.entities.Entity;

public class EntityHandler implements Serializable
{
	/**
	 * ArrayList holding a bunch of Entities
	 */
	private static List<Entity> entities = new ArrayList<Entity>();
	
	/**
	 * Used to tick all of the {@link Entity} that are in the {@link EntityHandler#entities} ArrayList
	 */
	public void tick()
	{
		for(Entity e : entities)
		{
			e.tick();
		}
	}
	
	/**
	 * Draw all of the {@link Entity} that are in the {@link EntityHandler#entities} ArrayList by using the supplied {@link Graphics} object
	 * @param g - Graphics object to draw the Entities with
	 */
	public void render(java.awt.Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
	}
	
	/**
	 * Add an {@link Entity} to this handler's {@link EntityHandler#entities} ArrayList
	 * @param e - The Entity to add to the ArrayList
	 */
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
}
