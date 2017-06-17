package com.mills.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import com.mills.main.Game;
import com.mills.rendering.gui.toolbar.Toolbar;
import com.mills.rendering.gui.toolbar.ToolbarBox;

public class InputHandler implements KeyListener, MouseListener{
	
	protected long last = System.nanoTime();
	protected double delta = 0;
	private Game game;
	
	public InputHandler(Game game)
	{
		this.game = game;
		this.game.addKeyListener(this);
		this.game.addMouseListener(this);
	}
	
	public class Key
	{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public int keyCode;
		
		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}
		
		public boolean isPressed()
		{
			return pressed;
		}
		
		public void toggle(boolean isPressed)
		{
			pressed = isPressed;
			if(isPressed) numTimesPressed++;
		}
	}
	
//	public List<Key> keys = new ArrayList<Key>();
	
	
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
	public void mouseClicked(MouseEvent e) {
		
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
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			game.player.resetSpeed();
		}
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
		if(keyCode == KeyEvent.VK_SHIFT)	// SHIFT
		{
			game.player.mulSpeed(2);
		}
		if(keyCode == KeyEvent.VK_0)
		{
			KEY_0.toggle(isPressed);
/*			GUIHandler guiHandler = (GUIHandler) Game.handlers.get(3);
			List<Object> list = guiHandler.getItems();
			Toolbar bar = (Toolbar) list.get(0);
			bar.getBox(0).setActive(true);
*/			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(9).setActive(true);	// This is so ugly, but works, so it stays ¯\_(ツ)_/¯
				
		}
		if(keyCode == KeyEvent.VK_1)
		{
			KEY_1.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(0).setActive(true);
		}
		if(keyCode == KeyEvent.VK_2)
		{
			KEY_2.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(1).setActive(true);
		}
		if(keyCode == KeyEvent.VK_3)
		{
			KEY_3.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(2).setActive(true);
		}
		if(keyCode == KeyEvent.VK_4)
		{
			KEY_4.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(3).setActive(true);
		}
		if(keyCode == KeyEvent.VK_5)
		{
			KEY_5.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(4).setActive(true);
		}
		if(keyCode == KeyEvent.VK_6)
		{
			KEY_6.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(5).setActive(true);
		}
		if(keyCode == KeyEvent.VK_7)
		{
			KEY_7.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(6).setActive(true);
		}
		if(keyCode == KeyEvent.VK_8)
		{
			KEY_8.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(7).setActive(true);
		}
		if(keyCode == KeyEvent.VK_9)
		{
			KEY_9.toggle(isPressed);
			
			for(ToolbarBox box : ((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBoxes())
			{
				box.setActive(false);
			}
			
			((Toolbar)((List<Object>)((GUIHandler) Game.handlers.get(3)).getItems()).get(0)).getBox(8).setActive(true);
		}
		if(keyCode == KeyEvent.VK_ESCAPE)	// ESCAPE
		{
			System.exit(0);  //TODO: Replace this with a method that gracefully shuts the game down
		}
	}
}
