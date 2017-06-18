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
	
	public Tile getTileAt(int index)
	{
		return tiles.get(index);
	}
	
	public void replaceTile(int index, Tile tile)
	{
		tiles.set(index, tile);
	}
	
	public int size()
	{
		return tiles.size();
	}
	
	public boolean isEmpty()
	{
		return tiles.isEmpty();
	}
	
	public void empty()
	{
		tiles.clear();
	}
	
	public void addTile(Tile t)
	{
		tiles.add(t);
	}
	
}
