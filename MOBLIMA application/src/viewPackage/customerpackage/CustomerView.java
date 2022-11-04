package viewPackage.customerpackage;

import java.util.Scanner;
import java.util.
import viewPackage.View;
import cineplexpackage.ICineplex;
import cineplexpackage.CineplexManager;
import cineplexpackage.Cineplex;
//import cinemapackage.ICinema;
//import cinemapackage.CinemaManager;
import customerpackage.BookingManager;
import moviepackage.IMovie;
import moviepackage.MovieManager; 
import moviepackage.Movie; 
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import showtimepackage.Showtime;
import cinemapackage.CinemaType;
import daypackage.Day; 

public class CustomerView  extends View{
	private static Scanner scan = new Scanner(System.in);
	private static ICineplex cineplexHandler = CineplexManager.getInstance();
	private static IMovie movieFindHandler = MovieManager.getInstance(); 
	private static IShowtime showtimeHandler = ShowtimeManager.getInstance(); 
	//private static ICinema cinemaHandler = CinemaManager.getInstance(); 
	
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
		String selectedCineplexName;
		Cineplex selectedCineplexObj;
		String selectedMovieName;
		Movie selectedMovieObj;
		String selectedCinemaTypeName = null;
		String selectedDate = null;
		String selectedTime = null;
		CinemaType selectedCinemaType = null;
		//IDay selectedDay;
		Showtime selectedShowtime;
		
		boolean showtime_selection_contd = true;
		//loop until a user selects a cineplex and movie combination for which non-zero showtimes exist.
		do {
			//STEP 1 - CHOOSE CINEPLEX
			System.out.println("The Cineplexes are:");
			cineplexHandler.printCineplexes();
			System.out.println();
			System.out.println("Enter the name of the cineplex you have chosen:");
			selectedCineplexName = scan.next();
			System.out.println();
			try {
				selectedCineplexObj = cineplexHandler.getCineplex(selectedCineplexName); 
			}
			catch(IllegalArgumentException ecineplex) {
				System.out.println("Cineplex is not found — please try again");
				continue; //to start of outer do-while loop
			}
			int selectedCineplexID = selectedCineplexObj.getID();
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//STEP 2 - CHOOSE MOVIE
			CustomerMovieListing movSelectObj = null;
			movSelectObj.movieSelection(); //runs the movie selection menu
			selectedMovieName = movSelectObj.getSelectedMovieName();
			try {
				selectedMovieObj =  movieFindHandler.findMoviebyName(selectedMovieName); 
			}
			catch(IllegalArgumentException e) {
				System.out.println("Movie is not found — please try again");
				continue; //to start of outer do-while loop
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//STEP 3 - Display showtimes for the movie and cineplex combination (atleast one showtime should exist)
			
			try {
				System.out.printf("Following are the showtimes available for movie %s at cineplex %s: ", selectedMovieName, selectedCineplexName);
				showtimeHandler.printShowtimesByMovieNameAndCineplexID(selectedMovieName, selectedCineplexID);
				System.out.println();
				
				/*
				 * If user does not like any of the showtimes available, 
				 * allow to re-enter and movie and cinplex combination
				 * and display another set of showtimes
				 */
				
				System.out.println("To pick cineplex and movie again, for another set of showtimes, enter 0. Else enter any other character:");
				String re_enter = scan.next();
				if(re_enter.compareTo("0") == 0) {
					continue; //to start of outer do-while loop
				}
			}
			catch(IllegalArgumentException e) {
				System.out.println("No Showtimes exist for the Movie and Cineplex combination — please try again");
				continue; //to start of outer do-while loop
			}
		}while(showtime_selection_contd);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//STEP 4 - Select a showtime from those available for movie and cineplex combination

		
		System.out.println("Choose a row from the above table and enter these details: ");
		
		boolean cinemaTypeScanContd = true;
		do {
			System.out.print("Cinema Class, from 'Platinum', 'Gold', 'Sliver' : "); 
				//display header is class but attribute is CinemaType
				//Silver misspelled as Sliver in enum definition - UPDATE if changed
			selectedCinemaTypeName = scan.next();
			System.out.println();
					
			switch(selectedCinemaTypeName) {
			case "Platinum": 
				selectedCinemaType = CinemaType.PLATINUM;
				cinemaTypeScanContd = false;
				break;
			case "Gold": 
				selectedCinemaType = CinemaType.GOLD;
				cinemaTypeScanContd = false;
				break;
			case "Sliver": 
				selectedCinemaType = CinemaType.PLATINUM;
				cinemaTypeScanContd = false;
				break;
			default:
				System.out.println("Erroneous value entered, please try again");
				break;
			}
	}while(cinemaTypeScanContd);//endCinemaScan 		
				
	//keep prompting until correct format for date
	do {
		System.out.print("Date (length 8): ");
		selectedDate = scan.next();
	}while(selectedDate.length() != 8); 
	System.out.println();
	//convert string date to required format for Day constructor
	int dateInt = 0, dayNumber = 0, monthNumber = 0,yearNumber = 0;
	try {
		dateInt = Integer.parseInt(selectedDate);
		// reversing fullDate = Integer.toString(this.yearNumber) + monthString + dayString;
		dayNumber = dateInt%100;
		dateInt/=100;
		monthNumber = dateInt%100;
		dateInt/=100;
		yearNumber = dateInt;
	}//endtry
	catch (NumberFormatException edate){
        ex.printStackTrace();
    }					
	
	//implement input check if needed
	System.out.print("Time: ");
	selectedTime = scan.next();
	System.out.println();
	
	//CHECK for bugs
	try {			
	Day selectedDay(dayNumber, monthNumber, yearNumber, selectedTime);
	}
	catch(IllegalArgumentException eday) {
		System.out.println("Invalid date string supplied");
	}
				
	try {
		selectedShowtime = showtimeHandler.getShowtime(selectedMovieName, selectedDay, selectedCineplexID, CinemaType cinemaType);
	}
	catch(IllegalArgumentException e) {
		System.out.println("Showtime is not found for details entered");
	}
			
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//STEP 5 - Seat selection
		int showtimeID = selectedShowtime.getID();
		try{
			showtimeHandler.printCinemaLayout(showtimeID);
		}
		catch(IllegalArgumentException eshowtime){
			System.out.println("Showtime is not found");
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//STEP 6 - Get customer details — check for DiscountCode and CoupleSeat
		
		//STEP 7 - Display price (+other details) and confirm from customer
		
		//STEP 8 - bookSeat (internal calls booking in customer package) Call appropriate overloaded function. 
		
		CineplexManager.close();
		
	}//End of Book Menu Function
	
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
			if(reviewMovieName.compareTo("0") == 0) { //if string entered equals 0
				CustomerReview r = null; 
				r.setReviewAndRating(reviewMovieName);
				System.out.println("thanks for your review!");
				System.out.println();
			}
			else {
				review_contd = false;
			}
		}while(review_contd);
		BookingManager.close();
	}
}
