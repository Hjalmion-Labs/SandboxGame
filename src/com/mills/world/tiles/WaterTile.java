package com.mills.world.tiles;

import com.mills.world.World;

public class WaterTile extends Tile
{

	public WaterTile(World world, int x, int y, boolean wasPlaced)
	{
		super(TileType.WATER, world, x, y, wasPlaced);
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
		
	}
	
}
