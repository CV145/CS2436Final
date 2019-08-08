package finalLab;
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

/**
 * Entry-point for the program.
 * @author carlo
 *
 */
public class Driver {
	 public static void main(String[] args) throws InputMismatchException    
	 {
		 Greeting.welcome();
		 TestProgElectionResult.generateOutput(Integer.parseInt(MessageBox.getInput("Please input the number of vote entries to use as data:")));

		 Greeting.thanks();
         System.exit(0);
    }
}
