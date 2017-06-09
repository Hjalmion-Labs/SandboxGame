package com.mills.world;

import com.mills.world.tiles.DirtTile;
import com.mills.world.tiles.Tile;

public class OverWorld extends World 
{

	public OverWorld(String name)
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
				tileHandler.addTile(new DirtTile(this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
				y++;
			}
		}
		return this;
	}
}
