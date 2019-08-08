package finalLab;

import java.awt.Desktop;
import java.io.*;
import java.util.Scanner;

/**
 * This is a collection of re-usable file methods, such as writing and reading to files.
 * @author carlo
 *
 */
public class FileUtilities {

	
	/** This method writes a line into a given file name.
	 * @param lineToAppend
	 * @param fileName
	 * @return boolean
	 * @throws IOException
	 */
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
	
	/**
	 * This method reads a line from a file and returns it as a String.
	 * @param fileName
	 * @param scanner
	 * @return String
	 * @throws IOException
	 */
	public static String readLineFromFile (String fileName, Scanner scanner) throws IOException
	{
		//scanner needs to be passed in because if declared here it will reset each call
		if (scanner.hasNext())
		{
			return scanner.nextLine();
		}
		else
		{
			return null;
		}
	}


	/**
	 * This method opens a file using Desktop, if possible. Returns an error message if it can't.
	 * @param fileName
	 * @return String
	 * @throws IOException
	 */
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

	/**
	 * This method looks for and returns the directory of where a file is located.
	 * @param filePath
	 * @return String
	 */
	public static String getFileDirectory (String filePath)
	{
		String fileDirectory;
		File file = new File(filePath);
		fileDirectory = file.getAbsolutePath();
		if (fileDirectory != null) { return fileDirectory; }
		else return null;
	}

	/**
	 * This method creates a Scanner object for a given file name.
	 * @param fileName
	 * @return Scanner
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Scanner getScannerForFile (String fileName) throws FileNotFoundException, IOException
	{
		FileReader fileToOpen = new FileReader (fileName);
		return new Scanner(fileToOpen);
	}

	/**
	 * Deletes a file with the name fileName.
	 * @param fileName
	 * @return A boolean check for whether the file was actually deleted or not.
	 */
	public static boolean deleteFile (String fileName) 
	{
		File file = new File (fileName);
		if (file.delete()) return true;
		else return false;
	}
}
