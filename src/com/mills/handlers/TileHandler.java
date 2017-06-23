package com.mills.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mills.world.tiles.Tile;
import com.mills.world.tiles.TileType;

public class TileHandler implements Serializable 
{

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
	
	public void replaceTile(int index, TileType type)
	{
		tiles.get(index).setType(type);
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
