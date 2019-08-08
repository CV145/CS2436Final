package finalLab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import MessageBoxes.MessageBox;

/**
 * TestProgElectionResult has methods for generating data, and testing that data.
 * @author carlo
 *
 */
public class TestProgElectionResult 
{
	
		static String voteDataFilePath = "voteData5.txt";
		static String candDataFilePath = "candData.txt";
		static Candidate[] candidates;
		static int activeCandidates;
	     
	     /**
	      * Checks to see if a candData.txt file exists and, if it doesn't, create it, and if it's empty, prompt
	      * the user to fill it up by displaying where it is stored.
	      * @return Boolean for whether or not the candidate file is ready for use or not.
	     * @throws IOException 
	     * @throws FileNotFoundException 
	      */
	     public static boolean verifyCandidateFile() throws FileNotFoundException, IOException
	     {
	    	 /**
	    	  * Check if a candData.txt file exists.
	    	  * If it doesn't, create a new candData.txt file.
	    	  * 
	    	  * Check if candData.txt is empty.
	    	  * If it is, create a message box prompting the user to put information in it. Return false.
	    	  * Otherwise, return true.
	    	  */
	    	 
	    	 if (!FileGenerator.exists(candDataFilePath))
	    	 {
	    	  FileGenerator.generateNewTextFile(candDataFilePath); 
	    	 }
	    	  
				if (FileUtilities.isEmpty(candDataFilePath))
				 {
					 MessageBox.displayMessage("The candidate data file is empty. Please put information"
							 + " into the file path: " + FileUtilities.getFileDirectory(candDataFilePath) + " and "
							 + "try again.");
					 FileUtilities.openFileOnDesktop(candDataFilePath);
					 return false;
				 }
	    	 
	    	 return true;
	     }
	     
	     /**
	      * Initializes the candidate array based off the contents of the candidate data file.
	      * @return boolean for whether the operation was successful or not.
	      * @throws IOException
	      */
	     public static boolean generateCandidateArray() throws IOException
	     {
	    	 /**
	    	  * Input the size of the candidate array.
	    	  * 
	    	  * Begin Loop
	    	  * For each element in the candidate array:
	    	  * If the next line in the candidate file is not null, set it into String input.
	    	  * If it is null, display an error message and return false.
	    	  * Initialize a new Candidate and set its name to input.
	    	  * End Loop
	    	  * 
	    	  * At this point, return true. The candidate array was filled successfully.
	    	  */
	    	 
	    	  activeCandidates = Integer.parseInt(MessageBox.getInput("Please input the number of candidates:"));
	         
	         candidates=new Candidate[activeCandidates];
	         
	         Scanner candFileScanner = FileUtilities.getScannerForFile(candDataFilePath);
        	 
	         String name = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
        	 
        	 for (int index=0;index<activeCandidates;index++) 
             {
        		 if (name != null)
        		 {
        			 candidates[index]=new Candidate(name); // store file contents into array and make new candidates
        			 name = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
        		 }
        		 else
        		 {
        			 MessageBox.displayMessage("Error: The candidate file has less candidates than what was requested. ");
        			 FileUtilities.openFileOnDesktop(candDataFilePath);
        			 MessageBox.displayMessage("Candidates on file: " + index + "\n" + "Candidates requested: " + activeCandidates);
        			 return false;
        		 }
             }
        	 
        	 return true;
	     }
	     
	     /**
	      * Fills up random vote entries in the voteData file. Also creates a new vote data file if it doesn't
	      * exist already. Pre-existing voteData files are overwritten with new data.
	      * @param numOfEntries
	      * @throws IOException
	      */
	     public static boolean generateVotes(int numOfEntries) throws IOException
	     {
	    	 /**
	    	  * Delete any existing voteData files to overwrite.
	    	  * Create a new voteData file.
	    	  * Create a random object.
	    	  * 
	    	  * Begin Loop
	    	  * For as many desired entries:
	    	  * Write a new entry with random contents into the voteData file
	    	  * End Loop
	    	  */
	    	 
        	 if (FileUtilities.deleteFile(voteDataFilePath))
        	 {
        		 MessageBox.displayMessage("Old vote data file deleted.");
        		 FileGenerator.generateNewTextFile(voteDataFilePath);
        	 }
        	 else
        	 {
        		 MessageBox.displayMessage("Unable to delete the old vote data file.");
        		 MessageBox.displayMessage("Terminate old runs of this program." + "\n"
        		 + "Any old runs will stop the file from closing.");
        		 return false;
        	 }

        	 Random rand = new Random();
        	 
        	 MessageBox.displayMessage("Starting to write data to the vote file... ");
             
             // Write the random votes to the file.
             // Generates results in O(n) = O(1,000,000) time 
             for(int currentEntry=0; currentEntry<numOfEntries; currentEntry++)
             {
           	  String output = candidates[rand.nextInt(activeCandidates)].getName()+" "+rand.nextInt(4)+ " " + rand.nextInt(50); //state values range [0, 4)
           	  FileUtilities.appendLineToFile(output, voteDataFilePath);
           	  System.out.println("Added: " + output);
             }
             MessageBox.displayMessage("Data written to the vote file.");
             FileUtilities.openFileOnDesktop(voteDataFilePath);
             return true;
	     }
	     
	     /**
	      * This method grabs the data from the voteData file and uses it to fill up the votes for each
	      * candidate in the candidate array.
	     * @throws IOException 
	      */
	     public static void distributeVotes() throws IOException
	     {
	    	 /**
	    	  * Sort the candidates array.
	    	  * 
	    	  * Begin Loop
	    	  * While the voteData continues to have input:
	    	  * Tokenize the input, separated by spaces, and grab a list of tokens.
	    	  * 
	    	  * Use the first token to search for the candidate in the array.
	    	  * Pass in the second token as the state for the candidate.
	    	  * Pass in the third token as the votes to store.
	    	  * End Loop
	    	  */
	    	 
	    	 Scanner scanner = FileUtilities.getScannerForFile(voteDataFilePath);
	    	 String input = FileUtilities.readLineFromFile(voteDataFilePath, scanner);
	    	 ArrayList<String> tokens;
	    	 String name;
	    	 int state;
	    	 int votes;
	    	 int index;
	    	 
	    	 while (input != null)
	    	 {
	    		 tokens = FileUtilities.tokenizeString(input, " ");
	    		 name = tokens.get(0);
	    		 state = Integer.parseInt(tokens.get(1));
	    		 votes = Integer.parseInt(tokens.get(2));
	    		 
	    		 index = ElectionResult.findCandidate(candidates, candidates.length, name);
	    		 candidates[index].updateVotes(state, votes);
	    		 
	    		 input = FileUtilities.readLineFromFile(voteDataFilePath, scanner);
	    	 }
	     }
	     
	     /**
	      * Sorts the candidates array in ascending order. The candidate with the highest votes is 
	      * determined as the winner, and output in a message box.
	      */
	     public static void determineWinner()
	     {
	    	 /**
	    	  * For each candidate, tally up all the votes they got for each state.
	    	  * Sort the candidate array by total votes.
	    	  * Retrieve the candidate at the very last index of the array and set as the winner.
	    	  * Output the winner in a message box.
	    	  */
	    	 
	    	 String results = "";
	    	 
	    	 for (Candidate candidate : candidates)
	    	 {
	    		 candidate.determineTotalVotes();
	    		 results += candidate.getName() + ": " + candidate.getTotalVotes() + " votes" + "\n";
	    	 }
	    	 
	    	 Candidate winner;
	    	 ElectionResult.sortResults(candidates, candidates.length);
	    	 winner = candidates[activeCandidates - 1];
	    	 
	    	 MessageBox.displayMessage("The winner of this year's presidential election is... ");
	    	 MessageBox.displayMessage("President " + winner.getName() + "!!!" + "\n" + "With a grand total of " + winner.getTotalVotes() + " votes!!!!");
	    	 MessageBox.displayMessage("-----Results-----" + "\n" + results);
	     }
	     
	     public static void debugShowArray()
	     {
	    	 String list = "";
	    	 for (Candidate candidate : candidates)
	    	 {
	    		 list += candidate.getName() + "\n";
	    	 }
	    	 MessageBox.displayMessage(list);
	     }
}
