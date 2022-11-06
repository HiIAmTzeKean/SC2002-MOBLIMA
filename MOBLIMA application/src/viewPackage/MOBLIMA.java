package viewPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import cinemapackage.CinemaManager;
import customerpackage.BookingManager;
import customerpackage.DiscountCode;
import moviepackage.MovieManager;
import showtimepackage.ShowtimeManager;
import viewPackage.customerpackage.CustomerView;
import viewPackage.staffpackage.StaffAuth;

/**
 * Controller class for MOBLIMA application
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class MOBLIMA extends View {
    public static void main(String args []){ 
        System.out.print("\033[H\033[2J");
        System.out.println("-----------------------------------------");
        System.out.println("\tWELCOME TO MOBLIMA\t\t"); 
        System.out.println("-----------------------------------------");
		waitForEnter(null);

		startAllManagers();
        start();
		//closeAllManagers();

        System.out.println("-----------------------------------------");
        System.out.println("\tEXITING MOBLIMA\t\t"); 
        System.out.println("-----------------------------------------");
    }
	/**
	 * Menu to be printed for navigation
	 */
	private static void startAllManagers(){
		BookingManager.getInstance();
		DiscountCode.getInstance();
		MovieManager.getInstance();
		CinemaManager.getInstance();
		ShowtimeManager.getInstance();
	}
	private static void closeAllManagers(){
		BookingManager.close();
		DiscountCode.close();
		MovieManager.close();
		CinemaManager.close();
		ShowtimeManager.close();
	}
    public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tMenu\t\t");
		System.out.println("--------------------------------------");
		System.out.println("Choice 1 : Staff");
		System.out.println("Choice 2 : Customer");
		System.out.println("Choice 3 : Exit MOBLIMA");
		System.out.println("--------------------------------------");
	}
    /**
	 * First function to be called for the view
	 * Sets up the logic follow within class through switch cases
	 */
	public static void start() {
		sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			displayMenu();
			try {
				System.out.println("Enter Choice");
				choice = sc.nextInt();
				if (choice>5 || choice<1) {
					System.out.println("Invalid input!");
					waitForEnter(null);
					continue;
				}
			} catch (InputMismatchException e) {
				inputMismatchHandler();
				waitForEnter(null);
				continue;
			}
			
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
		}
	}
}
