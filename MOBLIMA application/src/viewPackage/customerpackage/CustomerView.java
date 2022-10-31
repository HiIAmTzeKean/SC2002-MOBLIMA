package viewPackage.customerviewpackage;

import java.util.Scanner;
import customerpackage.BookingManager; 
import cineplexpackage.ICineplex;
//change to select by movie name

public class CustomerView extends View {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Welcome to the MOBLIMA moviegoer menu");
		System.out.println();
		boolean contd = true;
		do {
			System.out.println("Enter a number from the following options:");
			System.out.println("1 -> browse movies and make a new booking");
			System.out.println("2 -> see your booking history and add reviews");
			System.out.println("3 -> leave this menu");
			int choice = scan.nextInt();
			
			switch(choice) {
			case 1:{
				bookMenu();
				break;
			}
			case 2:{
				historyAndReview();
				break;
			}
			case 3:{
				System.out.println("Leaving moviegoer view!");
				contd = false;
				break;
			}
			default:{
				System.out.println("Incorrect value entered, please try again.");
				break;
			}
			}
		} while(contd);
	}
	
	public static void bookMenu() {
		ICineplex cx;
		System.out.println("The Cineplexes are:");
		cx.printAllCineplexes();
		System.out.println();
		System.out.println("Enter the name of the cineplex you have chosen:");
		String selectedCineplex = scan.next();
		//cx.getCineplex(selectedCineplex);
		
		//selecting cinema and showtime
	}
	
	public static void historyAndReview() {
		BookingManager hist = BookingManager.getInstance();
		System.out.println("Enter your email to see booking history");
		String email = scan.next();
		hist.printAllTransactionsForCustomer(email);
		System.out.println();
		
		System.out.println("If you wish to add review for a movie, enter the MovieID, else enter 0.");
		int movieIDReview = scan.nextInt();
		if(movieIDReview != 0) {
			CustomerReview r = null; //define constructor instead?
			r.setReviewAndRating(movieIDReview);
		}
	}
}
