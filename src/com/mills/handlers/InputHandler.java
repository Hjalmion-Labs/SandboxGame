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
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key k = new Key();
	
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
			up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S)	// DOWN
		{
			down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A)	// LEFT
		{
			left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D)	// RIGHT
		{
			right.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_K)	// K
		{
			k.toggle(isPressed);
			long now = System.currentTimeMillis();
			delta += (now - last);
			if(delta >= 500)
			{
				if(Game.currentWorld == Game.overWorld)
					Game.currentWorld = Game.underWorld;
				else
					Game.currentWorld = Game.overWorld;
				System.out.println("Swapped to " + Game.currentWorld);
			}
			delta = 0;
		}
	}
}
