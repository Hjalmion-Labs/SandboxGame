package com.mills.rendering.gui;

import java.awt.Color;

import com.mills.main.Game;

public class Toolbar
{

	private int x;
	private int y;
	private int width;
	private int height;
	
	public Toolbar()
	{
		x = 250;
		y = Game.HEIGHT - 103;
		width = 451;
		height = 51;
	}
	
	public void tick()
	{
		
	}
	
	public void render(java.awt.Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(250, Game.HEIGHT - 103, 451, 51);
	}
	
}
