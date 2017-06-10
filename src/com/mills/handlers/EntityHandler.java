package com.mills.handlers;

import java.util.ArrayList;
import java.util.List;

import com.mills.entities.Entity;

public class EntityHandler
{

	private static List<Entity> entities = new ArrayList<Entity>();
	
	public void tick()
	{
		for(Entity e : entities)
		{
			e.tick();
		}
	}
	
	public void render(java.awt.Graphics g)
	{
		for(Entity e : entities)
		{
			e.render(g);
		}
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
}
