package com.mills.world.tiles;

import java.awt.Graphics;

import com.mills.world.World;

public class LavaTile extends Tile
{

	public LavaTile(World world, int x, int y, boolean wasPlaced)
	{
		super(TileType.LAVA, world, x, y, wasPlaced);
		TILECOLOR = type.getColor();
	}

	@Override
	public void render(Graphics g)
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
