package com.mills.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import com.mills.main.Game;
import com.mills.rendering.gui.toolbar.Toolbar;
import com.mills.rendering.gui.toolbar.ToolbarBox;
import com.mills.world.World;
import com.mills.world.tiles.Tile;
import com.mills.world.tiles.TileType;

/**
 * Instantiates the InputHandler and adds this to the {@link Game} as the {@link KeyListener} and {@link MouseListener}
 * @param game - Game to add this to
 */
public class InputHandler implements KeyListener, MouseListener
{
	
	protected long last = System.nanoTime();
	
	private Game game;
	
	public InputHandler(Game game)
	{
		this.game = game;
		game.addKeyListener(this);
		game.addMouseListener(this);
	}
	
	/**
	 * Class that represents a Key on the keyboard.
	 * @author Nick Mills
	 *
	 */
	public class Key
	{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public int keyCode;
		
		/**
		 * <strong>Getter</strong><br>
		 * Gets the number of times this Key was pressed
		 * @return an int of how many times this Key has been pressed
		 */
		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}
		
		/**
		 * Returns true if {@link Key#pressed} is true, false otherwise
		 * @return whether or not this Key is pressed
		 */
		public boolean isPressed()
		{
			return pressed;
		}
		
		/**
		 * Set this Key to the supplied state (true or false), and increment {@link Key#numTimesPressed}
		 * @param isPressed - state that this Key should be in
		 */
		public void toggle(boolean isPressed)
		{
			pressed = isPressed;
			if(isPressed) numTimesPressed++;
		}
	}
	
	/**
	 * The current {@link Tile} that is selected in the {@link Toolbar}. Defaults to {@link TileType#STONE}
	 */
	private static TileType currentType = TileType.STONE;
	
	/* Arrow Keys */
	public final Key UP = new Key();
	public final Key DOWN = new Key();
	public final Key LEFT = new Key();
	public final Key RIGHT = new Key();
	
	/* Modifier Keys */
	public final Key ESCAPE = new Key();
	public final Key SHIFT = new Key();
	
	/* Number Keys (0-9) */
	public final Key KEY_0 = new Key();
	public final Key KEY_1 = new Key();
	public final Key KEY_2 = new Key();
	public final Key KEY_3 = new Key();
	public final Key KEY_4 = new Key();
	public final Key KEY_5 = new Key();
	public final Key KEY_6 = new Key();
	public final Key KEY_7 = new Key();
	public final Key KEY_8 = new Key();
	public final Key KEY_9 = new Key();
	
	/* Movement Keys */
	public final Key KEY_W = new Key();
	public final Key KEY_A = new Key();
	public final Key KEY_S = new Key();
	public final Key KEY_D = new Key();
	
	/* Other Keys */
	public final Key KEY_K = new Key();
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		int button = e.getButton();
		if(button == MouseEvent.BUTTON1)
		{
			int x = e.getX() / Tile.TILEWIDTH - (int)Math.ceil((((WorldHandler)(Game.handlers.get("world"))).getCurrentWorld().xOffset / Tile.TILEWIDTH));	// Puts the x as a x Tile coordinate with consideration to any offset
			int y = e.getY() / Tile.TILEHEIGHT - (int)Math.ceil((((WorldHandler)(Game.handlers.get("world"))).getCurrentWorld().yOffset / Tile.TILEHEIGHT));	// Puts the y as a y Tile coordinate with consideration to any offset
			
			System.out.println("Clicked at (" + x + ", " + y + ")");
			
			/* Loops over the World */
			for(int i = 0; i < Game.currentWorld.getSize(); i++)
			{
				World currentWorld = ((WorldHandler)(Game.handlers.get("world"))).getCurrentWorld();	// Get the current World
				Tile currentTile = currentWorld.getTile(i);	// Get the tile at this position
				if(currentTile.getTileX() == x && currentTile.getTileY() == y)	// If user clicked in the Tile
				{
					System.out.println("Replace " + currentTile.getType() + " with " + currentType);
					int tileX = currentTile.getTileX();
					int tileY = currentTile.getTileY();
					switch(currentType)
					{
						case DIRT:
							currentWorld.replaceTile(i, TileType.DIRT);
							System.out.println("Placed a DIRT tile at (" + tileX + ", " + tileY + ")");
							break;
						case GRASS:
							currentWorld.replaceTile(i, TileType.GRASS);
							System.out.println("Placed a GRASS tile at (" + tileX + ", " + tileY + ")");
							break;
						case STONE:
							currentWorld.replaceTile(i, TileType.STONE);
							System.out.println("Placed a STONE tile at (" + tileX + ", " + tileY + ")");
							break;
						case WATER:
							currentWorld.replaceTile(i, TileType.WATER);
							System.out.println("Placed a WATER tile at (" + tileX + ", " + tileY + ")");
							break;
						case LAVA:
							currentWorld.replaceTile(i, TileType.LAVA);
							System.out.println("Placed a LAVA tile at (" + tileX + ", " + tileY + ")");
							break;
						default:	// TileType.NULL ; Place a Stone Tile instead
							currentWorld.replaceTile(i, TileType.STONE);
					}
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void keyPressed(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), false);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		toggleKey(e.getKeyCode(), false);
	}
	
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if(keyCode == KeyEvent.VK_W)	// UP
		{
			UP.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S)	// DOWN
		{
			DOWN.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A)	// LEFT
		{
			LEFT.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D)	// RIGHT
		{
			RIGHT.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_K)
		{
			if(isPressed)
			{
				/* Save the game to the HD */
				FileHandler.prepareGame((GUIHandler)Game.handlers.get("gui"), game, this, (WorldHandler)Game.handlers.get("world"));
				FileHandler.saveGame(game);
			}
		}
		if(keyCode == KeyEvent.VK_1)
		{
			KEY_1.toggle(isPressed);
			
			/* Loop through all of the ToolbarBoxes and set each box's state to inactive*/
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			/* Set the ToolbarBox at the first position to active */
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(0).setActive(true);
			/* Set the currentType equal to the Tile that the ToolbarBox represents */
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(0).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_2)
		{
			KEY_2.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(1).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(1).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_3)
		{
			KEY_3.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(2).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(2).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_4)
		{
			KEY_4.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(3).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(3).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_5)
		{
			KEY_5.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(4).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(4).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_6)
		{
			KEY_6.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(5).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(5).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_7)
		{
			KEY_7.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(6).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(6).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_8)
		{
			KEY_8.toggle(isPressed);
		
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(7).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(7).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_9)
		{
			KEY_9.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(8).setActive(true);
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(8).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
		}
		if(keyCode == KeyEvent.VK_0)
		{
			KEY_0.toggle(isPressed);
		}
		
/*			GUIHandler guiHandler = (GUIHandler) Game.handlers.get("gui");
			List<Object> list = guiHandler.getItems();
			Toolbar bar = (Toolbar) list.get(0);
			bar.getBox(0).setActive(true);
*/			
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(9).setActive(true);	// This is so ugly, but works, so it stays ¯\_(ツ)_/¯
			currentType = ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get("gui")).getItems()).get(0)).getBox(9).getTile();
			if(isPressed)	// If we pressed (not released) the key
				System.out.println(currentType);
			
		if(keyCode == KeyEvent.VK_ESCAPE)	// ESCAPE
		{
			System.exit(0);  //TODO: Replace this with a method that gracefully shuts the game down
		}
	}
}
