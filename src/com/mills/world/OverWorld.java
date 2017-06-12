package com.mills.world;

import com.mills.entities.Zombie;
import com.mills.handlers.EntityHandler;
import com.mills.handlers.TileHandler;
import com.mills.main.Game;
import com.mills.world.tiles.GrassTile;
import com.mills.world.tiles.Tile;

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
				tileHandler.addTile(new GrassTile(this, i * Tile.TILEWIDTH, j * Tile.TILEHEIGHT));
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