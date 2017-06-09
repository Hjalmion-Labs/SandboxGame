package com.mills.world.tiles;

import java.awt.Color;
import java.awt.Graphics;

import com.mills.main.Game;
import com.mills.world.World;

public class StoneTile extends Tile
{
	public StoneTile(World world, int x, int y)
	{
		super("Stone", world, (byte)02, x, y);
		TILECOLOR = Color.GRAY;
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
