package finalLab;

import javax.swing.*;

//creates message boxes for printing strings and 
//taking in input
public class MessageBox {
	
	/**This method displays a passed in string in 
	 * a message box.
	 * @param String message
	 * @return void
	 * @throws none
	 */
	public static void displayMessage(String message)
	{
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, message);
	}
	
	
	/** This method creates a dialog box asking the user for
	 * String input, and returns it. A message is passed in to 
	 * help prompt the user.
	 * @param String message
	 * @return Whatever input the user put into the dialog box.
	 * @throws none
	 */
	public static String getInput(String message)
	{
		return JOptionPane.showInputDialog(message);
	}
	
}
