package com.mills.world;

import com.mills.handlers.EntityHandler;
import com.mills.handlers.TileHandler;
import com.mills.world.tiles.Tile;
import com.mills.world.tiles.TileType;

public class DefaultWorld extends World
{

	public DefaultWorld(String name) {
		super(name, 160, 160 / 12 * 9);
		this.tileHandler = new TileHandler();
		this.entityHandler = new EntityHandler();
		
	}

	@Override
	public void createWorld()
	{
		for(int i = 0; i < this.WIDTH; i++)
			for(int j = 0; j < this.HEIGHT; j++)
				this.tileHandler.addTile(new Tile(TileType.DIRT, this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
	}

}
