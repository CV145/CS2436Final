package finalLab;

import java.io.FileNotFoundException;
import java.io.IOException;

import MessageBoxes.Greeting;
import MessageBoxes.MessageBox;

/**
 * Description: This program tests methods for a presidential election program.
 * Class: Summer II COSC 2436.86400
 * Assignment: Final Project
 * Date: 08/08/2019
 * @author carlo
 *@version 0.0.0
 */
public class Driver {
	 public static void main(String[] args)  
	 {
		 Greeting.welcome();

		 try
		 {
		 if (!TestProgElectionResult.verifyCandidateFile()) exit();
		 if (!TestProgElectionResult.generateCandidateArray()) exit();
		 if (!TestProgElectionResult.generateVotes(Integer.parseInt(MessageBox.getInput("Please input the number of vote entries to use as data:")))) exit();
		 TestProgElectionResult.distributeVotes();
		 TestProgElectionResult.determineWinner();
		 }
		 catch (FileNotFoundException f)
		 {
			 MessageBox.displayMessage("One of the files is missing.");
		 }
		 catch (IOException io)
		 {
			 MessageBox.displayMessage("There's been an IO exception.");
		 }
		 
		 exit();
    }

	private static void exit() {
		Greeting.thanks();
		 System.exit(0);
	}
}
