package finalLab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * TestProgElectionResult has methods for generating data, and testing that data.
 * @author carlo
 *
 */
public class TestProgElectionResult 
{
		/** This method generates entries equal to numOfEntries into a voteData txt file using a candData txt file.
		 *  A candData.txt file must be in the project directory with a list of candidates.
		 * @param int numOfEntries
		 * @return void
		 * @throws none
		 */
	     public static void generateOutput(int numOfEntries)
	     {   
	         String candDataFilePath, input, voteDataFilePath="voteData5.txt", friendName, candidates[];
	         
	         int activeCandidates = 0;
	         
	         Random rand=new Random();
	         
	         // Get the number of candidates
	         activeCandidates = Integer.parseInt(MessageBox.getInput("How many candidates are active at this time?"));

	         candidates=new String[activeCandidates];

	         candDataFilePath="candData.txt";
	             
	         // Attempt to open and read the file.
	         try {
	              // Make sure the file does not exist.
	        	 FileUtilities.deleteFile(voteDataFilePath);
	        	 FileGenerator.GenerateNewTextFile(voteDataFilePath);
	        	 FileGenerator.GenerateNewTextFile(candDataFilePath);
	              
	              // Read and display the candData file's contents line by line.
	        	 Scanner candFileScanner = FileUtilities.getScannerForFile(candDataFilePath);
	        	 input = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
	        	 String candidateList = "";
	              for (int index=0;index<activeCandidates;index++) 
	              {
	                   candidates[index]=new String(input); // store file contents into array
	                   candidateList += input + "\n";
	                   input = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
	              }
	              //Display candData contents
	              MessageBox.displayMessage(candidateList);
	              candFileScanner.close();
	              
	              MessageBox.displayMessage("Starting to write data to the vote file... ");
	              
	              // Write the random votes to the file.
	              // Generates results in O(n) = O(1,000,000) time 
	              for(int currentEntry=0; currentEntry<numOfEntries; currentEntry++)
	              {
	            	  String output = candidates[rand.nextInt(activeCandidates)]+" "+rand.nextInt(5)+ " " + rand.nextInt(50);
	            	  FileUtilities.appendLineToFile(output, voteDataFilePath);
	            	  System.out.println("Added: " + output);
	              }
	              MessageBox.displayMessage("Data written to the vote file.");
	              FileUtilities.openFileOnDesktop(voteDataFilePath);
	              FileUtilities.openFileOnDesktop(candDataFilePath);
	         }
	         catch (FileNotFoundException e){
	              MessageBox.displayMessage("File not found."); 
	         }
	         catch (IOException e){
	              MessageBox.displayMessage(e.getMessage());
	         }
	         catch (NullPointerException n)
	         {
	        	 MessageBox.displayMessage("The candidate data file is empty. Please input candidate information into " + FileUtilities.getFileDirectory(candDataFilePath));
	         }
	     }
}
