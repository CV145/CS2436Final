package finalLab;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TestProgElectionResult 
{
	     public static void GenerateOutput()
	     {
	    	 final int SIZE=1000;
	         //FileReader reader;
	         //BufferedReader inputFile;
	         
	         String candDataFilePath, input, voteDataFilePath="voteData5.txt", friendName, candidates[];
	         
	         int activeCandidates = 0;
	         
	         Random rand=new Random();
	         //Scanner keyboard=new Scanner(System.in);
	         
	         // Get the number of candidates
	         activeCandidates = Integer.parseInt(MessageBox.getInput("How many candidates are active at this time?"));

	         candidates=new String[activeCandidates];

	         candDataFilePath="candData.txt";
	             
	         // Attempt to open and read the file.
	         try {
	              // Open the file.
	              //reader = new FileReader(inFileName);
	              //inputFile = new BufferedReader(reader);
	              // Make sure the file does not exist.
	        	 TextFileGenerator.GenerateNewTextFile(voteDataFilePath);
	        	 TextFileGenerator.GenerateNewTextFile(candDataFilePath);
	              //File file = new File(outFileName);
	              //if (file.exists()){
	                 // Display an error message.
	                 //System.out.println("The file " + outFileName + " already exists.");
	                 // Exit the program.
	                 //System.exit(1);
	              //}
	        	 
	              // Open the file.
	              //PrintWriter outputFile = new PrintWriter(file);
	              
	              // Read and display the candData file's contents line by line.
	        	 Scanner candFileScanner = FileUtilities.getScannerForFile(candDataFilePath);
	        	 input = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
	              //input = inputFile.readLine();
	        	 String candidateList = "";
	              for (int index=0;index<activeCandidates;index++) 
	              {
	                   candidates[index]=new String(input); // store file contents into array
	                   candidateList += input + "\n";
	                   //System.out.println(input); //display
	                   input = FileUtilities.readLineFromFile(candDataFilePath, candFileScanner);
	                   //input = inputFile.readLine();
	              }
	              //Display candData contents
	              MessageBox.displayMessage(candidateList);
	              candFileScanner.close();
	              
	              MessageBox.displayMessage("Starting to write data to the vote file... May take a while.");
	              
	              // Write the random votes to the file.
	              // Generates results in O(n) = O(1,000,000) time 
	              for(int currentEntry=0; currentEntry<SIZE; currentEntry++)
	              {
	            	  String output = candidates[rand.nextInt(activeCandidates)]+" "+rand.nextInt(5)+ " " + rand.nextInt(50);
	            	  FileUtilities.appendLineToFile(output, voteDataFilePath);
	            	  System.out.println("Added: " + output);
	                  //outputFile.println(candidates[rand.nextInt(activeCandidates)]+" "+rand.nextInt(5)+ " " + rand.nextInt(50));
	              }
	              // Close the file.
	              //outputFile.close();
	              MessageBox.displayMessage("Data written to the vote file.");
	              FileUtilities.openFileOnDesktop(voteDataFilePath);
	              FileUtilities.openFileOnDesktop(candDataFilePath);
	              //System.out.println("Data written to the file.");
	              //inputFile.close();
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
