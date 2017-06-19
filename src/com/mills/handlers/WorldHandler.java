package com.mills.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mills.world.DefaultWorld;
import com.mills.world.World;

/**
 * Handles all of the Worlds in the Game, allowing them to be easily updated and rendered
 * @author Nick Mills
 *
 */
public class WorldHandler {
	
	private List<World> worlds = new ArrayList<World>();
	/* Reference to the last World that was retrieved by WorldHandler#get() */
	protected World currentWorld;
	
	/**
	 * Draw all of the {@link World}s with the supplied {@link Graphics} object
	 * @param g - Graphics object to draw this World (and its handlers) with
	 */
	public void render(java.awt.Graphics g)
	{
		currentWorld.renderAllHandlers(g);
	}
	
	/**
	 * Updates all of the {@link World}s in the {@link ArrayList} of Worlds
	 */
	public void tick()
	{
		currentWorld.tickAllHandlers();
	}
	
	/**
	 * Add the supplied {@link World} to the end of this handler's {@link ArrayList} of Worlds
	 * @param world
	 */
	public void add(World world)
	{
		worlds.add(world);
	}
	
	/**
	 * Get the World from this WorldHandler's List of Worlds that has the specified name
	 * @param name - Name of the World to get
	 * @return The World matching the specified name. If it cannot be found, a DefaultWorld with name <code>"Default"</code> is returned
	 */
	public World get(String name)
	{
		Iterator<World> it = worlds.iterator();
		while(it.hasNext())
		{
			World world = it.next();
			if(world.getName().equals(name))
			{
				currentWorld = world;
				return world;
			}
		}
		currentWorld = new DefaultWorld("Default");
		return currentWorld;
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Gets the last {@link World} that was retrieved with {@link WorldHandler#get()}
	 * @return the most recent World retrieved
	 */
	public World getCurrentWorld()
	{
		return currentWorld;
	}
	
}
