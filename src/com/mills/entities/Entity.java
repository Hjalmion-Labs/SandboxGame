package com.mills.entities;

import com.mills.world.World;
import com.mills.world.tiles.Tile;

/**
 * Abstract class that represents an Entity in the game. Contains basic information 
 * that can be accessed by any instantiated Entities.
 * @author Nick Mills
 */
public abstract class Entity {

	protected String name;
	protected int x;
	protected int y;
	protected int tileX;
	protected int tileY;
	protected int speed;
	protected int width;
	protected int height;
	protected World world;
	protected java.awt.Color color;
	
	/**
	 * Creates a new Entity.
	 * @param name - The name of the Entity
	 * @param world - The {@link World} that this Entity lives in
	 * @param x - X coord to spawn this Entity at
	 * @param y - Y coord to spawn this Entity at
	 */
	public Entity(String name, World world, int x, int y)
	{
		this.name = name;
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Any logic calculations (movement, AI, damage, etc) should be done in here. Must be overridden by the child Entity class (like {@link Zombie} or {@link Player})
	 */
	public abstract void tick();
	
	/**
	 * Draw the Entity to the current {@link Display} using the provided {@link Graphics} object
	 * @param g - {@link Graphics} object to draw this Entity with
	 */
	public abstract void render(java.awt.Graphics g);
	
	/**
	 * Gets the name of this Entity
	 * @return String representation of the name of this Entity
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Set this Entity's current position to the provided {@link Tile} coordinates
	 * @param x - 
	 * @param y
	 */
	public void setPos(Tile tile)
	{
		x = tile.getX();
		y = tile.getY();
	}
	
	/**
	 * <strong>Setter</strong><br>
	 * Set this Entity's x-coord to the specified value
	 * @param x - The value to set this Entity's x-value to
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Get this Entity's x-coord
	 * @return the x-coord of this Entity
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Get this Entity's xTile-coord <br><br>
	 * Takes this Entity's x-value and divides it by {@link Tile#TILEWIDTH} to get a xTile-coord
	 * @return the xTile-coord of this Entity
	 */
	public int getTileX()
	{
		return x / Tile.TILEWIDTH;
	}
	
	/**
	 * <strong>Setter</strong><br>
	 * Set this Entity's xTile-coord to the specified value
	 * @param tile - The xTile-coord to set this Entity's xTile-coord to
	 */
	public void setTileX(Tile tileX)
	{
		this.tileX = tileX.getTileX();
	}
	
	/**
	 * <strong>Setter</strong><br>
	 * Set this Entity's y-coord to the specified value
	 * @param y - The y-value to set this Entity's y-coord to
	 */
	public void setY(int y)
	{
		this.y = y;
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Gets this Entity's y-coord
	 * @return This Entity's y-coord
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Gets this Entity's yTile-coord
	 * @return This Entity's yTile-coord
	 */
	public int getTileY()
	{
		return y / Tile.TILEHEIGHT;
	}
	
	/**
	 * <strong>Setter</strong><br>
	 * Set this Entity's yTile-coord to the specified value
	 * @param tile - The yTile-coord to set this Entity's yTile-coord to
	 */
	public void setTileY(Tile tileY)
	{
		this.tileY = tileY.getTileY();
	}
	
	/**
	 * <strong>Getter</strong><br>
	 * Gets this Entity's speed value
	 * @return This Entity's speed value, as an integer
	 */
	public int getSpeed()
	{
		return speed;
	}
	
	/**
	 * Makes sure that this Entity does not accidentally (or purposely) get out of the world. Should be called every {@link Entity#tick()}
	 */
	public void keepInBounds()
	{
		if(x < 0) x = 0;
		if(x > world.getWidth()) x = world.getWidth();
		if(y < 0) y = 0;
		if(y > world.getHeight()) y = world.getHeight();
	}
}
