package com.mills.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.mills.main.Game;

public class InputHandler implements KeyListener, MouseListener{
	
	protected long last = System.nanoTime();
	protected double delta = 0;
	
	public InputHandler(Game game)
	{
		game.addKeyListener(this);
		game.addMouseListener(this);
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
		if(keyCode == KeyEvent.VK_ESCAPE)	// ESCAPE
		{
			System.exit(0);  //TODO: Replace this with a method that gracefully shuts the game down
		}
	}
}
