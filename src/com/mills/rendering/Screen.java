package com.mills.rendering;

public class Screen {

	public static final int MAPWIDTH = 64;
	public static final int MAPWIDTHMASK = MAPWIDTH - 1;
	
	public int[] pixels;
	
	public int[] colors = new int[MAPWIDTH * MAPWIDTH * 4];
	
	public static int xOffset = 0;
	public static int yOffset = 0;
	
	public int width;
	public int height;
	
	public SpriteSheet sheet;
	
	public Screen(int width, int height, SpriteSheet spriteSheet)
	{
		this.width = width;
		this.height = height;
		this.sheet = spriteSheet;
		
		pixels = new int[width * height];
	}
	
	public void render(int xPos, int yPos, int tile, int color)
	{
		xPos -= xOffset;
		yPos -= yOffset;
		
		int xTile = tile % 32;
		int yTile = tile / 32;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;
		for(int y = 0; y < 8; y++)
		{
			if(y + yPos < 0 || y + yPos >= height) continue;
			int ySheet = y;
			for(int x = 0; x < 8; x++)
			{
				if(y + yPos < 0 || y + yPos >= height) continue;
				int xSheet = x;
				int col = (color >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;	// Get color data for where we are on the tile
				if(col < 255) pixels[(x + xPos) + (y + yPos) * width] = col;
			}
		}
	}
	
}
