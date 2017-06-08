package com.mills.world.tiles;

import java.awt.Color;

public class DirtTile extends Tile
{

	public DirtTile(int x, int y)
	{
		super("DIRT", (byte)01, x, y);
		TILECOLOR = new Color(139, 69, 19);
	}
	
	@Override
	public void render(java.awt.Graphics g)
	{
		g.setColor(TILECOLOR);
		g.fillRect(x, y, TILEWIDTH, TILEHEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, TILEWIDTH, TILEHEIGHT);
	}
	
	@Override
	public void tick()
	{
		
	}
	
}
