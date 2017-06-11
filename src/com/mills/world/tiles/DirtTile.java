package com.mills.world.tiles;

import java.awt.Color;

import com.mills.world.World;

public class DirtTile extends Tile
{

	public DirtTile(World world, int x, int y)
	{
		super(TileType.DIRT, world, x, y);
		TILECOLOR = type.getColor();
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
		x = oX + world.xOffset;
		y = oY + world.yOffset;
	}
	
}
