package com.mills.entities;

import java.awt.Color;
import java.awt.Graphics;

import com.mills.world.World;
import com.mills.world.tiles.Tile;

/**
 * The Player is the Entity that the user controls
 * @author Nick Mills
 *
 */
public class Player extends Entity
{
	protected static final int radius = 30;
	protected static final Color color = Color.BLUE;
	
	public Player(String name, World world, int x, int y, int speed)
	{
		super(name, world, x, y);
		this.speed = speed;
		this.width = radius;
		this.height = radius;
	}

	@Override
	public void tick()
	{
		keepInBounds();
	}

	@Override
	public void render(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	/**
	 * Place this Player at the specified Tile. 
	 * @param tile - The Tile to place this Player on
	 */
	public void placeAt(Tile tile)
	{
		setTileX(tile);
		setTileY(tile);
	}
}
