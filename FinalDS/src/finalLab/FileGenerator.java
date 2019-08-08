package finalLab;

import java.io.File;
import java.io.IOException;

/**
 * A generator for making new files.
 * @author carlo
 *
 */
public class FileGenerator{
	
	/**Generates a new text file and places it in the desired path.
	 * @param path
	 * @return boolean for whether the path was successfully created or not
	 */
	public static boolean generateNewTextFile(String path) 
	{
			File file = new File(path);
			try { file.createNewFile(); }
			catch (IOException io) { return false; }
			return true;
	}
	
	/**
	 * This method checks if a file path exists.
	 * @param path
	 * @return boolean for whether the file exists or not.
	 */
	public static boolean exists(String path)
	{
		File file = new File(path);
		if (!file.exists())
		{
			return false;
		}
		return true;
	}
}
