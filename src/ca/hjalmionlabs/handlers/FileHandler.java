package ca.hjalmionlabs.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ca.hjalmionlabs.main.Game;

public class FileHandler
{
	private static File file;
	private static ObjectOutputStream output;
	private static FileOutputStream outStream;
	private static ObjectInputStream input;
	private static FileInputStream inputStream;
	
	private static List<Object> save = new ArrayList<Object>();
	private static List<Object> load = new ArrayList<Object>();
	
	/**
	 * Puts what needs to be saved into a {@link List} for easy save and load
	 * @param gui - GUIHandler
	 * @param wh - WorldHandler
	 * @return a List<Object> that holds the stuff we need to save the game
	 */
	public static List<Object> prepareGame(GUIHandler gui, WorldHandler wh)
	{
		save.add(gui);
		save.add(wh);
		return save;
	}
	
	/**
	 * Save the game to the hard drive
	 * @param game
	 */
	public static void saveGame(Game game)
	{
		try
		{	
			File dir = new File("D:\\Programming\\Java\\workspace\\CompSciFinal\\worlds\\");
			
			if(!dir.exists())
			{
				System.out.println("Directory doesn't exist.. Creating it now!");
				dir.mkdirs();
			}
			
			file = new File(dir, "test.world");
			if(!file.exists())
			{
				System.out.println("File doesn't exist... Creating it now!");
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
	
	public List<Object> loadGame(Game game)
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
			
			load = (List<Object>) input.readObject();
			
			System.out.println("World successfully loaded!");
			
			return load;
		} catch(IOException ioe)
		{
			System.err.println(ioe.getMessage());
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
