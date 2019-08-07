package finalLab;

import java.io.File;
import java.io.IOException;

public class TextFileGenerator{
	
	//generates a new text file and places it in the desired path
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
