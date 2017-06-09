package com.mills.world;

import com.mills.handlers.TileHandler;
import com.mills.world.tiles.DirtTile;
import com.mills.world.tiles.StoneTile;
import com.mills.world.tiles.Tile;

public abstract class World {

	protected String name;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	public int xOffset = 0;
	public int yOffset = 0;
	
	public World(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public TileHandler tileHandler = new TileHandler();
	
	public abstract World createWorld();
	
	@Override
	public String toString()
	{
		return name;
	}
}
