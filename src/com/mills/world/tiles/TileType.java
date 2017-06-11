package com.mills.world.tiles;

import java.awt.Color;

public enum TileType
{
	DIRT("DIRT", (byte)01, new Color(139, 69, 19)),
	STONE("STONE", (byte)02, Color.GRAY),
	GRASS("GRASS", (byte)03, Color.GREEN.darker());
	
	private String name;
	private byte id;
	private Color tileColor;
	
	TileType(String name, byte id, Color color)
	{
		this.name = name;
		this.id = id;
		this.tileColor = color;
	}
	
	public String getName()
	{
		return name;
	}
	
	public byte getID()
	{
		return id;
	}
	
	public Color getColor()
	{
		return tileColor;
	}
}
