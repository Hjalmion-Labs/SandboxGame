package ca.hjalmionlabs.world;

import ca.hjalmionlabs.handlers.EntityHandler;
import ca.hjalmionlabs.handlers.TileHandler;
import ca.hjalmionlabs.world.tiles.Tile;
import ca.hjalmionlabs.world.tiles.TileType;

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
