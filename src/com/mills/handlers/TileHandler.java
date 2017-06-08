package com.mills.handlers;

import java.util.ArrayList;
import java.util.List;

import com.mills.world.tiles.Tile;

public class TileHandler {

	private List<Tile> tiles = new ArrayList<Tile>();
	
	public void render(java.awt.Graphics g)
	{
		for(Tile tile : tiles)
		{
			tile.render(g);
		}
	}
	
	public void tick()
	{
		for(Tile tile : tiles)
		{
			tile.tick();
		}
	}
	
	public void addTile(Tile t)
	{
		// Check to see if any IDs are duplicates
		for(Tile tile : tiles)
		{
			if(t.getID() == t.getID() && !(t.getName().equalsIgnoreCase(tile.getName())))
				throw new RuntimeException("Cannot have duplicate Tile IDs!\n\n" + tile.getName() + ":" + tile.getID() + "\n" + t.getName() + ":" + t.getID());
		}
		// Actually add the tile to the list
		tiles.add(t);
	}
	
}
