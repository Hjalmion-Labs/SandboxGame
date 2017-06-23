package com.mills.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mills.world.tiles.Tile;
import com.mills.world.tiles.TileType;

<<<<<<< HEAD
/**
 * Handles the updating and rendering of {@link Tile}s which are stored as an {@link ArrayList}. Use this object
 * to interact with any Tiles.
 */
public class TileHandler {
=======
public class TileHandler implements Serializable 
{
>>>>>>> master

	/**
	 * {@link ArrayList} of {@link Tile}s.
	 */
	private List<Tile> tiles = new ArrayList<Tile>();
	
	/**
	 * Draw all of the {@link Tile}s in the {@link ArrayList} to the screen using the supplied {@link Graphics} object
	 * @param g - Graphics object to draw the Tiles with
	 */
	public void render(java.awt.Graphics g)
	{
		for(Tile tile : tiles)
		{
			tile.render(g);
		}
	}
	
	/**
	 * Update all of the {@link Tile}s in the {@link ArrayList} of Tiles
	 */
	public void tick()
	{
		for(Tile tile : tiles)
		{
			tile.tick();
		}
	}
	
	/**
	 * Gets the {@link Tile} that is at the specified position, <i>index</i>
	 * @param index - Where in the ArrayList to get the {@link Tile}
	 * @return the Tile at the specified position
	 */
	public Tile getTileAt(int index)
	{
		return tiles.get(index);
	}
	
<<<<<<< HEAD
	/**
	 * Replaces the {@link Tile} at the specified position, <i>index</i> with the supplied Tile
	 * @param index - Position to replace 
	 * @param tile - Tile to replace existing Tile with
	 */
	public void replaceTile(int index, Tile tile)
=======
	public void replaceTile(int index, TileType type)
>>>>>>> master
	{
		tiles.get(index).setType(type);
	}
	
	/**
	 * Gets the size of the {@link ArrayList} of {@link Tile}s
	 * @return int representing how many Tiles are in the ArrayList
	 */
	public int size()
	{
		return tiles.size();
	}
	
	/**
	 * Whether or not the {@link ArrayList} of {@link Tile}s is empty
	 * @return true if nothing is in the List, false otherwise
	 */
	public boolean isEmpty()
	{
		return tiles.isEmpty();
	}
	
	/**
	 * Empties the {@link ArrayList} of {@link Tile}s.
	 */
	public void empty()
	{
		tiles.clear();
	}
	
	/**
	 * Adds the supplied {@link Tile} to the end of the {@link ArrayList} of Tiles
	 * @param t - Tile to add to the end of the ArrayList
	 */
	public void addTile(Tile t)
	{
		tiles.add(t);
	}
	
}
