package com.mills.world.tiles;

import com.mills.world.World;

public class DirtTile extends Tile
{

	public DirtTile(World world, int x, int y, boolean wasPlaced)
	{
		super(TileType.DIRT, world, x, y, wasPlaced);
		TILECOLOR = type.getColor();
	}
	
	@Override
	public void render(java.awt.Graphics g)
	{
		super.render(g);
	}
	
	@Override
	public void tick()
	{
		x = oX + world.xOffset;
		y = oY + world.yOffset;
	}
	
}
