package com.mills.rendering.gui.toolbar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.mills.main.Game;
import com.mills.world.tiles.Tile;
import com.mills.world.tiles.TileType;

public class Toolbar
{

	private int x;
	private int y;
	private int width;
	private int height;
	
	/** When using this value, make sure to use it in a loop like so: <br> boxX = x + (i * 50) + 3; */
	private int boxX;
	private int boxY;
	private int boxWidth;
	private int boxHeight;
	
	private List<ToolbarBox> boxes = new ArrayList<ToolbarBox>();
	
	public Toolbar()
	{
		x = 250;
		y = Game.HEIGHT - 103;
		width = 501;
		height = 51;
		
		boxY = y + 2;
		boxWidth = Tile.TILEWIDTH - 5;
		boxHeight = Tile.TILEWIDTH - 5;
		
		initialize();
	}
	
	private void initialize()
	{
		for(int i = 0; i < 10; i++)
		{
			boxes.add(i, new ToolbarBox());
			assignTile(i);
		}
	}
	
	private void assignTile(int i)
	{
		switch(i)
		{
			case 0:
				boxes.get(i).setTile(TileType.DIRT);
				break;
			case 1:
				boxes.get(i).setTile(TileType.GRASS);
				break;
			case 2:
				boxes.get(i).setTile(TileType.STONE);
				break;
			default:
				boxes.get(i).setTile(TileType.NULL);
		}
	}
	
	public List<ToolbarBox> getBoxes()
	{
		return boxes;
	}
	
	public void tick()
	{
		
	}
	
	public void render(java.awt.Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		for(int i=0;i<10;i++)
		{
			// Draw a rectangle at this :
			// x value, but make sure it is spaced out by 2 pixels from each box and the sides of the bar.
			// y value, but make sure it is spaced out by 2 pixels from the top and bottom of the bar.
			// width, which is just a smaller version of the Tile's Width.
			// height, which is just a smaller versio of the Tile'es Height.
			// All of these combined space the boxes out
			boxX = x + (i * 50) + 3;	// Needed to properly place the boxes
			boxes.get(i).setX(boxX);
			boxes.get(i).setY(boxY);
			boxes.get(i).setWidth(boxWidth);
			boxes.get(i).setHeight(boxHeight);
		}
		
		/* Example of how to set the TileType of a ToolbarBox */
//		ToolbarBox box1 = boxes.get(0);
//		box1.setTile(TileType.DIRT);
		
		for(ToolbarBox box : boxes)	// Render all the ToolbarBoxes that are in the List of ToolbarBoxes
		{
			box.render(g);
		}
	}

	public ToolbarBox getBox(int index)
	{
		return boxes.get(index);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getBoxX() {
		return boxX;
	}

	public void setBoxX(int boxX) {
		this.boxX = boxX;
	}

	public int getBoxY() {
		return boxY;
	}

	public void setBoxY(int boxY) {
		this.boxY = boxY;
	}

	public int getBoxWidth() {
		return boxWidth;
	}

	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	public int getBoxHeight() {
		return boxHeight;
	}

	public void setBoxHeight(int boxHeight) {
		this.boxHeight = boxHeight;
	}
	
}
