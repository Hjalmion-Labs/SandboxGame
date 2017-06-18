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
	
	private ToolbarBox box1 = new ToolbarBox();	// First Box on the Toolbar (furthest left)
	private ToolbarBox box2 = new ToolbarBox();
	private ToolbarBox box3 = new ToolbarBox();
	private ToolbarBox box4 = new ToolbarBox();
	private ToolbarBox box5 = new ToolbarBox();
	private ToolbarBox box6 = new ToolbarBox();
	private ToolbarBox box7 = new ToolbarBox();
	private ToolbarBox box8 = new ToolbarBox();
	private ToolbarBox box9 = new ToolbarBox();
	private ToolbarBox box0 = new ToolbarBox();	// Last Box on the Toolbar (furthest right)
	
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
		boxes.add(box1);
		boxes.add(box2);
		boxes.add(box3);
		boxes.add(box4);
		boxes.add(box5);
		boxes.add(box6);
		boxes.add(box7);
		boxes.add(box8);
		boxes.add(box9);
		boxes.add(box0);
		
		for(int i = 0; i < boxes.size(); i++)
		{
			assignTile(i);
		}
	}
	
	private void assignTile(int i)
	{
		switch(i)
		{
			case 0:
				boxes.get(0).setTile(TileType.DIRT);
				break;
			case 1:
				boxes.get(1).setTile(TileType.GRASS);
				break;
			case 2:
				boxes.get(2).setTile(TileType.STONE);
				break;
			case 3:
				boxes.get(3).setTile(TileType.WATER);
				break;
			case 4:
				boxes.get(4).setTile(TileType.LAVA);
				break;
			default:
				boxes.get(i).setTile(TileType.NULL);
		}
	}
	
	public TileType getActiveTile()
	{
		for(ToolbarBox box : boxes)
		{
			if(box.isActive())
				return box.getTile();
		}
		return TileType.NULL;
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
		
		// Draw a rectangle at this :
		// x value, but make sure it is spaced out by 2 pixels from each box and the sides of the bar.
		// y value, but make sure it is spaced out by 2 pixels from the top and bottom of the bar.
		// width, which is just a smaller version of the Tile's Width.
		// height, which is just a smaller versio of the Tile'es Height.
		// All of these combined space the boxes out
		
		int i = 0;
		
		box1.setX(x + (i++ * 50) + 3);
		box1.setY(y + 2);
		box1.setWidth(Tile.TILEWIDTH - 10);
		box1.setHeight(Tile.TILEHEIGHT - 10);
		
		box2.setX(x + (i++ * 50) + 3);
		box2.setY(y + 2);
		box2.setWidth(Tile.TILEWIDTH - 10);
		box2.setHeight(Tile.TILEHEIGHT - 10);
		
		box3.setX(x + (i++ * 50) + 3);
		box3.setY(y + 2);
		box3.setWidth(Tile.TILEWIDTH - 10);
		box3.setHeight(Tile.TILEHEIGHT - 10);
		
		box4.setX(x + (i++ * 50) + 3);
		box4.setY(y + 2);
		box4.setWidth(Tile.TILEWIDTH - 10);
		box4.setHeight(Tile.TILEHEIGHT - 10);
		
		box5.setX(x + (i++ * 50) + 3);
		box5.setY(y + 2);
		box5.setWidth(Tile.TILEWIDTH - 10);
		box5.setHeight(Tile.TILEHEIGHT - 10);
		
		box6.setX(x + (i++ * 50) + 3);
		box6.setY(y + 2);
		box6.setWidth(Tile.TILEWIDTH - 10);
		box6.setHeight(Tile.TILEHEIGHT - 10);
		
		box7.setX(x + (i++ * 50) + 3);
		box7.setY(y + 2);
		box7.setWidth(Tile.TILEWIDTH - 10);
		box7.setHeight(Tile.TILEHEIGHT - 10);
		
		box8.setX(x + (i++ * 50) + 3);
		box8.setY(y + 2);
		box8.setWidth(Tile.TILEWIDTH - 10);
		box8.setHeight(Tile.TILEHEIGHT - 10);
		
		box9.setX(x + (i++ * 50) + 3);
		box9.setY(y + 2);
		box9.setWidth(Tile.TILEWIDTH - 10);
		box9.setHeight(Tile.TILEHEIGHT - 10);
		
		box0.setX(x + (i++ * 50) + 3);
		box0.setY(y + 2);
		box0.setWidth(Tile.TILEWIDTH - 10);
		box0.setHeight(Tile.TILEHEIGHT - 10);
		
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
