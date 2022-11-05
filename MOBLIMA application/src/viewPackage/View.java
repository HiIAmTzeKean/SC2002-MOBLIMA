package viewPackage;

import java.util.Scanner;
import java.io.Console;

/**
 * Super class for the views. Each view will inherit from view class
 * and will implement displayMenu and start function
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class View {
	/**
	 * Scanner object that is global in each sub-view class
	 */
	public static Scanner sc = new Scanner(System.in);

	/**
	 * Function to prompt user to press enter key before continuing with the program
	 * Forces an I/O message to interrupt program execution
	 * @param message message to be printied in the function
	 * @param args
	 */
	public static void waitForEnter(String message, Object... args) {
		Console c = System.console();
		if (c != null) {
			// printf-like arguments
			if (message != null)
				c.format(message, args);
			c.format("\nPress ENTER to proceed.\n");
			c.readLine();
		}
	}
	/**
	 * Handles an InputMismatchException by clearing the buffer
	 * then prints a error statement for user
	 * To be called when there is an InputMismatchException
	 */
	public static void inputMismatchHandler() {
		sc.nextLine();
		System.out.println("Illegal input value");
	}
	/**
	 * Menu to be printed for navigation
	 */
	public static void displayMenu(){};
	/**
	 * First function to be called for the view
	 * Sets up the logic follow within class through switch cases
	 */
	public static void start(){};
}
