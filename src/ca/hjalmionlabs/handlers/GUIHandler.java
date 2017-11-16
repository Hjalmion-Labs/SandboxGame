package ca.hjalmionlabs.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.hjalmionlabs.rendering.gui.toolbar.Toolbar;

public class GUIHandler implements Serializable
{
	/**
	 * The Toolbar that is drawn at the bottom middle of the screen
	 */
	public static Toolbar toolbar;
	
	/**
	 *   Instantiates the Toolbar
	 */
	public GUIHandler()
	{
		toolbar = new Toolbar();
	}
	
	/**
	 * Render all of the UI elements with the supplied {@link Graphics} object
	 * @param g - The Graphics object to draw with
	 */
	public void render(java.awt.Graphics g)
	{
		toolbar.render(g);
	}
	
	/**
	 * Get all of the UI elements that this handler handles as a {@link List}
	 * @return List of all the UI elements
	 */
	public List<Object> getItems()
	{
		List<Object> items = new ArrayList<Object>();
		items.add(toolbar);
		
		return items;
	}
	
	/**
	 * Updates all of the UI elements that this handler handles
	 */
	public void tick()
	{
		toolbar.tick();
	}
}