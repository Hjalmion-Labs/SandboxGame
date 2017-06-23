package com.mills.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.mills.main.Game;

public class FileHandler
{
	private File file;
	private ObjectOutputStream output;
	private FileOutputStream outStream;
	private ObjectInputStream input;
	private FileInputStream inputStream;
	
	private static List<Object> save = new ArrayList<Object>();
	
	public static List<Object> prepareGame(GUIHandler gui, InputHandler ih, WorldHandler wh)
	{
		save.add(gui);
		save.add(ih);
		save.add(wh);
		return save;
	}
	
	public void saveGame(Game game)
	{
		try
		{	
			File dir = new File(FileHandler.class.getClassLoader().getResource(File.separator).getPath());
			
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			
			System.out.println(dir.getAbsolutePath());
			
			file = new File(dir, File.separator + "worlds" + File.separator + "test.world");
			
			if(!file.exists())
			{
				System.out.println("File doesn't exist.. Creating it now!");
				file.createNewFile();
			}
			
			outStream = new FileOutputStream(file);
			output = new ObjectOutputStream(outStream);
			
			output.writeObject(save);
			
			System.out.println("Object written to " + file.getName() + "!");
			
			output.close();
			outStream.close();
			
		} catch(NullPointerException e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch(IOException ioe)
		{
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
		}
	}
	
	public void loadGame(Game game)
	{
		try
		{
			File dir = new File(game.getClass().getClassLoader().getResource(File.separator).getPath());
			
			file = new File(dir, File.separator + "worlds" + File.separator + "test.world");
			
			System.out.println(file.getAbsolutePath());
			
			if(!file.exists())
			{
				System.out.println("Can't load a world that doesn't exist!");
			}
			
			inputStream = new FileInputStream(file);
			input = new ObjectInputStream(inputStream);
			
			List<Object> world = (List<Object>) input.readObject();
			
			System.out.println("World successfully loaded!");
			
		} catch(IOException ioe)
		{
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
