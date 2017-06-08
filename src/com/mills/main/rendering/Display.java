package com.mills.main.rendering;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Display
{

	public static List<JFrame> frames = new ArrayList<JFrame>();
	
	/**
	 * Create a new JFrame. The new JFrame is added to the list of JFrames, then returned so it can be used. Not visible when returned. Defaults to JFrame.HIDE_ON_CLOSE
	 * @param name - Name of the JFrame
	 * @param width - Width of the JFrame
	 * @param height - Height of the JFrame
	 * @return a new JFrame object
	 */
	public static JFrame create(String name, int width, int height)
	{
		JFrame frame = new JFrame(name);
		frame.setSize(width, height);
		frames.add(frame);
		return frame;
	}
	
}
