package ca.hjalmionlabs.world;

import ca.hjalmionlabs.entities.Zombie;
import ca.hjalmionlabs.handlers.EntityHandler;
import ca.hjalmionlabs.handlers.TileHandler;
import ca.hjalmionlabs.main.Game;
import ca.hjalmionlabs.world.tiles.Tile;
import ca.hjalmionlabs.world.tiles.TileType;

public class OverWorld extends World 
{
	
	public OverWorld(String name)
	{
		super(name, 250, 200);
		this.tileHandler = new TileHandler();
		this.entityHandler = new EntityHandler();
	}
	
	public void createWorld()
	{
		int temp = 0;
		for(int i = 0; i < WIDTH; i++)
		{
			for(int j = 0; j < HEIGHT; j++)
			{
				tileHandler.addTile(new Tile(TileType.GRASS, this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
				temp++;
			}
		}
		System.out.println(temp + " tiles added to " + this.name + " (size of: " + tileHandler.size() + ")");
	}
	
	public void populateWorld(int numEntities)
	{
		for(int i = 0; i < numEntities; i++)
			entityHandler.addEntity(new Zombie("Zombie", this, Game.WIDTH/2, Game.HEIGHT/2));
	}
}
