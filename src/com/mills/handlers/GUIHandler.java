package com.mills.handlers;

import com.mills.rendering.gui.Toolbar;

public class GUIHandler
{
	public static Toolbar toolbar;
	
	public GUIHandler()
	{
		toolbar = new Toolbar();
	}
	
	public void render(java.awt.Graphics g)
	{
		toolbar.render(g);
	}
	
	public void tick()
	{
		toolbar.tick();
	}
}
