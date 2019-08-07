package finalLab;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class GenerateOutput {
	 public static void main(String[] args) throws InputMismatchException    
	 {
         final int SIZE=1000000;
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
        	 input = FileUtilities.readLineFromFile(candDataFilePath);
              //input = inputFile.readLine();
        	 String candidateList = "";
              for (int index=0;index<activeCandidates;index++) 
              {
                   candidates[index]=new String(input); // store file contents into array
                   candidateList += input + "\n";
                   //System.out.println(input); //display
                   input = FileUtilities.readLineFromFile(candDataFilePath);
                   //input = inputFile.readLine();
              }
              //Display candData contents
              MessageBox.displayMessage(candidateList);
              
              // Write the random votes to the file.
              for(int currentEntry=0; currentEntry<SIZE; currentEntry++)
              {
            	  FileUtilities.appendLineToFile(candidates[rand.nextInt(activeCandidates)]+" "+rand.nextInt(5)+ " " + rand.nextInt(50), voteDataFilePath);
                  //outputFile.println(candidates[rand.nextInt(activeCandidates)]+" "+rand.nextInt(5)+ " " + rand.nextInt(50));
              }
              // Close the file.
              //outputFile.close();
              MessageBox.displayMessage("Data written to the file.");
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
         
         System.exit(0);
    }
}
