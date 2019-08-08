package finalLab;

import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
	 * Checks if the given file path contents are empty.
	 * @param path
	 * @return boolean for whether the path contents are empty or not.
	 * @throws IOException, FileNotFoundException
	 */
	public static boolean isEmpty(String path) throws IOException, FileNotFoundException
	{
		File file = new File(path);
		Scanner scanner = getScannerForFile(path);
		if (readLineFromFile(path, scanner) == null)
		{
			scanner.close();
			return true;
		}
		else 
			{
				scanner.close();
				return false;
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
	 * This method creates a Scanner object for a given file name. If the file with that name doesn't exist 
	 * this method will return null instead.
	 * @param fileName
	 * @return Scanner
	 * @throws FileNotFoundException 
	 */
	public static Scanner getScannerForFile (String fileName) throws IOException
	{
		FileReader fileToOpen = new FileReader (fileName); // closes when closing scanner
		Scanner scanner = new Scanner(fileToOpen);
		return scanner;
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
	
	   /**
	    * Tokenizes a String and returns an ArrayList of type String, made up of tokens.
	    * @param stringToTokenize
	    * @param delimiter
	    * @return ArrayList<String> containing the tokens of the passed in String.
	    */
		public static ArrayList<String> tokenizeString(String stringToTokenize, String delimiter)
		{
		 StringTokenizer tokenizer;
		 if (delimiter != null)
		 {
			 tokenizer = new StringTokenizer(stringToTokenize, delimiter); 
		 }
		 else
		 {
			 tokenizer = new StringTokenizer(stringToTokenize); //in case null was passed in
		 }
		 
		 ArrayList<String> tokens = new ArrayList<String>();
		 while (tokenizer.hasMoreTokens())
		 {
		  tokens.add(tokenizer.nextToken());
		 }
		 return tokens;
		}
}
