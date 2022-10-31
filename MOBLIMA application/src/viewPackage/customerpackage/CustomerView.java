package viewPackage.customerviewpackage;

import java.util.Scanner;
import customerpackage.BookingManager; 
import cineplexpackage.ICineplex;

public class CustomerView  extends View{
	public static Scanner scan = new Scanner(System.in);
	
	public static void start() {
		System.out.println("Welcome to the MOBLIMA moviegoer view!");
		System.out.println();
		boolean contd = true;
		do {
			System.out.println("Enter a number from the following options:");
			System.out.println("1 -> Browse movies and make a new booking");
			System.out.println("2 -> See your booking history and add reviews");
			System.out.println("3 -> Leave this menu");
			int choice = scan.nextInt();
			System.out.println();
			
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
				}//end default case
			}//end switch case
			
			System.out.println();
		} while(contd); //end do while loop 
	}
	
	public static void bookMenu() {
		//STEP 1 - CHOOSE CINEPLEX
		//check if other imports needed from cineplex package
		ICineplex cx, selected_cx;
		System.out.println("The Cineplexes are:");
		cx.printAllCineplexes();
		System.out.println();
		System.out.println("Enter the name of the cineplex you have chosen:");
		String selectedCineplexName = scan.next();
		selected_cx = cx.getCineplex(selectedCineplexName); 
		
		//STEP 2 - CHOOSE MOVIE
		CustomerMovieList movSelectObj;
		movSelectObj.movieSelection();
			//get name of selected movie here or later?
		
		//STEP 3 - CHOOSE DAY
		
		//STEP 4 - Display showtimes + cinema
		
		//STEP 5 - choose showtime
		// ---> goes into customerbooking instance
	}
	
	public static void historyAndReview() {
		BookingManager hist = BookingManager.getInstance();
		System.out.println("Enter your email to see booking history");
		String email = scan.next();
		hist.printAllTransactionsForCustomer(email);
		System.out.println();
		
		//continue adding reviews until exit
		boolean review_contd = true;
		do {
			System.out.println("If you wish to add review for a movie, enter the movie name, else enter 0");
			String reviewMovieName = scan.next();
			if(reviewMovieName != "0") {
				CustomerReview r = null; 
				r.setReviewAndRating(reviewMovieName);
				System.out.println("thanks for your review!");
				System.out.println();
			}
			else {
				review_contd = false;
			}
		}while(review_contd);
	}
}
