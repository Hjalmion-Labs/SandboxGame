package com.mills.world;

import com.mills.handlers.TileHandler;
import com.mills.world.tiles.DirtTile;
import com.mills.world.tiles.StoneTile;
import com.mills.world.tiles.Tile;

public class World {

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
	
	public World createWorld()
	{
		int x = 0;
		int y = 0;
		switch(name)
		{
			case "OVER":
				for(int i = 0; i < World.WIDTH; i++)
				{
					x++;
					for(int j = 0; j < World.HEIGHT; j++)
					{
						tileHandler.addTile(new DirtTile(this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
						y++;
					}
				}
				return this;
			case "UNDER":
				for(int i = 0; i < World.WIDTH; i++)
				{
					x++;
					for(int j = 0; j < World.HEIGHT; j++)
					{
						tileHandler.addTile(new StoneTile(this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
						y++;
					}
				}
				return this;
			default:
				return new World("DEFAULT");
		}
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
