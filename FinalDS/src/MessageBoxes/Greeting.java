package MessageBoxes;

public class Greeting  {

	/** This method creates a quick welcome message for the user.
	 * @param none
	 * @return void
	 * @throws none
	 */
		public static void welcome()
		{
			MessageBox.displayMessage("Welcome to the program!"  + "\n" + "Press OK to continue.");
		}
		
		/** This method creates a quick thank you message for the user.
		 * @param none
		 * @return void
		 * @throws none
		 */
		public static void thanks()
		{
			MessageBox.displayMessage("Thank you for using the program!");
		}	
}
