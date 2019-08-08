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
	 * @return A String message of whether the file was successfully created or not.
	 * @throws IOException
	 */
	public static String GenerateNewTextFile(String path) throws IOException
	{
		File file = new File(path);
		if (file.exists())
		{
			return "The file path  " + path + " already exists.";
		}
		file.createNewFile();
		return "The file path " + path + " was successfully created.";
	}
}
