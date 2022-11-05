package viewPackage;

import java.util.NoSuchElementException;
import java.util.Scanner;

import viewPackage.customerpackage.CustomerView;
import viewPackage.staffpackage.StaffAuth;
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

	public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tMenu\t\t");
		System.out.println("--------------------------------------");
		System.out.println("Choice 1 : Staff");
		System.out.println("Choice 2 : Customer");
		System.out.println("choice 3 : exit MOBLIMA");
		System.out.println("--------------------------------------");
	}

	public static void start() {
		sc = new Scanner(System.in);
		int choice = 0;

		try {
			do {
				displayMenu();
				System.out.println("\nEnter choice : ");

				choice = sc.nextInt();
				switch (choice) {
					case 1:
						StaffAuth.login();
						break;
					case 2:
						CustomerView.start();
						break;
					case 3:
						return;
					default:
						System.out.println("Enter valid choice");
						choice = 0;
				}
			} while (choice >= 0 && choice <= 3);
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
			sc.nextLine();
			View.start();
		}
		sc.close();
	}

}
