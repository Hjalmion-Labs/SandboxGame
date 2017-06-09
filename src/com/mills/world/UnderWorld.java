package com.mills.world;

import com.mills.world.tiles.StoneTile;
import com.mills.world.tiles.Tile;

public class UnderWorld extends World {

	public UnderWorld(String name)
	{
		super(name);
	}

	public World createWorld()
	{
		int x = 0;
		int y = 0;
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
	}
	
}
