package com.mills.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mills.rendering.gui.toolbar.Toolbar;

public class GUIHandler implements Serializable
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
	
	public List<Object> getItems()
	{
		List<Object> items = new ArrayList<Object>();
		items.add(toolbar);
		
		return items;
	}
	
	public void tick()
	{
		toolbar.tick();
	}
}