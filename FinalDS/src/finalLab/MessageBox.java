package finalLab;

import javax.swing.*;

//creates message boxes for printing strings and 
//taking in input
public class MessageBox {
	
	//method for displaying a string in a box
	public static void displayMessage(String message)
	{
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, message);
		
		//Pass in a string message
		
		//Create a frame object
		
		//Create a message box using the passed in message and
		//the frame just built
	}
	/*
	 * @param String message
	 * @return void
	 * @throws none
	 */
	
	//method for getting user input using an input dialog
	public static String getInput(String message)
	{
		return JOptionPane.showInputDialog(message);
	}
	/*
	 * @param String message
	 * @return String
	 * @throws none
	 */
}
