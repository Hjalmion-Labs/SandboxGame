package com.mills.world.tiles;

import java.awt.Graphics;

import com.mills.world.World;

public class GrassTile extends Tile
{

	public GrassTile(World world, int x, int y, boolean wasPlaced) {
		super(TileType.GRASS, world, x, y, wasPlaced);
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
