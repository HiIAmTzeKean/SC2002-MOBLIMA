package viewPackage;

import java.util.Scanner;
import java.io.Console;

public class View {
	public static Scanner sc = new Scanner(System.in);

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

	public static void inputMismatchHandler() {
		sc.nextLine();
		System.out.println("Illegal input value");
	}

	public static void displayMenu(){};
	public static void start(){};
}
