package com.mills.rendering.gui.toolbar;

import java.awt.Color;

import com.mills.world.tiles.TileType;

public class ToolbarBox
{

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private TileType tile;
	private boolean isActive;
	
	/**
	 * Empty constructor. Must manually define the x, y, width, and height values for this object to work properly
	 */
	public ToolbarBox() {}
	
	public ToolbarBox(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		tile = TileType.NULL;
	}

	public void render(java.awt.Graphics g)
	{
		if(!isActive)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.YELLOW);
		g.drawRect(x, y, width, height);
		if(color != null)
		{
			g.setColor(color);
			g.fillRect(x, y, width, height);
		}
	}
	
	public void setTile(TileType type)
	{
		tile = type;
		color = tile.getColor();
	}
	
	public TileType getTile()
	{
		return tile;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public void setActive(boolean b)
	{
		isActive = b;
	}
	
}
