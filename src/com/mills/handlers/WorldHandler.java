package com.mills.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mills.world.DefaultWorld;
import com.mills.world.World;

public class WorldHandler {

	private List<World> worlds = new ArrayList<World>();
	protected World currentWorld;
	
	public void render(java.awt.Graphics g)
	{
		currentWorld.renderAllHandlers(g);
	}
	
	public void tick()
	{
		currentWorld.tickAllHandlers();
	}
	
	public void add(World world)
	{
		worlds.add(world);
	}
	
	/**
	 * Get the World from this WorldHandler's List of Worlds that has the specified name
	 * @param name - Name of the World to get
	 * @return The World matching the specified name. If it cannot be found, a DefaultWorld is returned
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
	
	public World getCurrentWorld()
	{
		return currentWorld;
	}
	
}
