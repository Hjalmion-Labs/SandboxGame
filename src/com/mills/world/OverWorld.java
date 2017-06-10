package com.mills.world;

import com.mills.entities.Zombie;
import com.mills.handlers.EntityHandler;
import com.mills.handlers.TileHandler;
import com.mills.main.Game;
import com.mills.world.tiles.DirtTile;
import com.mills.world.tiles.Tile;

public class OverWorld extends World 
{

	public OverWorld(String name)
	{
		super(name);
		tileHandler = new TileHandler();
		entityHandler = new EntityHandler();
		createWorld();
	}
	
	protected void createWorld()
	{
		for(int i = 0; i < World.WIDTH; i++)
		{
			for(int j = 0; j < World.HEIGHT; j++)
			{
				tileHandler.addTile(new DirtTile(this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
			}
		}
	}
	
	public void populateWorld(int numEntities)
	{
		for(int i = 0; i < numEntities; i++)
			entityHandler.addEntity(new Zombie("Zombie", Game.WIDTH/2, Game.HEIGHT/2));
	}
}
