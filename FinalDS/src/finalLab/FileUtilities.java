package finalLab;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

public class FileUtilities {

	
	//method that writes a line to a file
	public static boolean appendLineToFile (String lineToAppend, String fileName) throws IOException
	{
		File file = new File (fileName); // creates an instance of File class
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		if (!file.exists())
		{
			file.createNewFile();
		}

		try { fileWriter = new FileWriter (file, true); }// append to file
		catch (IOException e) { return false; } // there was an error writing to file.getName()

		printWriter = new PrintWriter(fileWriter);
		printWriter.println(lineToAppend); // writes the line to the file using the fileWriter

		printWriter.close();
		fileWriter.close();
		return true; 
	}
	
	//method that reads a line from a file and returns it as a string
	public static String readLineFromFile (String fileName) throws IOException
	{
		Scanner scanner = getScannerForFile(fileName);
		if (scanner.hasNext())
		{
			return scanner.nextLine();
		}
		else
		{
			return null;
		}
	}


	//method for automatically opening a file using Desktop
	public static String openFileOnDesktop (String fileName) throws IOException 
	{
		File file = new File(fileName);
		Desktop desktop;
		String errorMessage = null;
		
		if (!Desktop.isDesktopSupported())
		{ 
			errorMessage = "Desktop class is not supported.";
		}
		else
		{
			desktop = Desktop.getDesktop();
			if (file.exists()) { desktop.open(file); }
			else 
			{ 
				errorMessage = file.getName() + " file does not exist.";
			}
		}
		
		return errorMessage;
	}

	//method that looks for and gets the directory of where a file is located
	public static String getFileDirectory (String filePath)
	{
		String fileDirectory;
		File file = new File(filePath);
		fileDirectory = file.getAbsolutePath();
		if (fileDirectory != null) { return fileDirectory; }
		else return null;
	}

	//method returns a scanner for a given file name
	public static Scanner getScannerForFile (String fileName) throws FileNotFoundException, IOException
	{
		FileReader fileToOpen = new FileReader (fileName);
		return new Scanner(fileToOpen);
	}

}
