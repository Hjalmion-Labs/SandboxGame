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
			// Don't allow the tiles to have the same ID if they do not share the same name (same Tile Type)
			if(t.getID() == tile.getID() && !(t.getName().equalsIgnoreCase(tile.getName())))
				throw new RuntimeException("Cannot have duplicate Tile IDs!\n\n" + tile.getName() + ":" + tile.getID() + "\n" + t.getName() + ":" + t.getID());
			else
				tiles.add(t);	// Add the tile to the list
		}
		
	}
	
}
