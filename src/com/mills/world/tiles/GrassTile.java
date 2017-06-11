package com.mills.world.tiles;

import java.awt.Color;
import java.awt.Graphics;

import com.mills.world.World;

public class GrassTile extends Tile
{

	public GrassTile(World world, int x, int y) {
		super(TileType.GRASS, world, x, y);
		TILECOLOR = type.getColor();
	}

	@Override
	public void render(Graphics g)
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
