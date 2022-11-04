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
		
		boolean showtime_selection_contd = true;
		//loop until a user selects a cineplex and movie combination for which non-zero showtimes exist.
		do {
			//STEP 1 - CHOOSE CINEPLEX
			System.out.println("The Cineplexes are:");
			cineplexHandler.printCineplexes();
			System.out.println();
			System.out.println("Enter the name of the cineplex you have chosen:");
			String selectedCineplexName = scan.next();
			System.out.println();
			Cineplex selectedCineplexObj;
			try {
				selectedCineplexObj = cineplexHandler.getCineplex(selectedCineplexName); 
			}
			catch(IllegalArgumentException e) {
				System.out.println("Cineplex is not found — please try again");
				continue; //to start of outer do-while loop
			}
			int selectedCineplexID = selectedCineplexObj.getID();
			
			//STEP 2 - CHOOSE MOVIE
			CustomerMovieListing movSelectObj = null;
			movSelectObj.movieSelection(); //runs the movie selection menu
			String selectedMovieName = movSelectObj.getSelectedMovieName();
			Movie selectedMovieObj;
			try {
				selectedMovieObj =  movieFindHandler.findMoviebyName(selectedMovieName); 
			}
			catch(IllegalArgumentException e) {
				System.out.println("Movie is not found — please try again");
				continue; //to start of outer do-while loop
			}
			
			//STEP 3 - Select from showtimes available for chosen cineplex and movie
			String selectedCinemaTypeName;
			String selectedDate;
			String selectedTime;
			Showtime selectedShowtime; //CHECK 
			System.out.printf("Following are the showtimes available for movie %s at cineplex %s: ", selectedMovieName, selectedCineplexName);
			//CHECK WITH METHOD DECLARATION
			try {
				showtimeHandler.getShowtimeByMovieAndDateAndCineplex(selectedMovieName, selectedCineplexID);
				System.out.println();
				System.out.println("Choose a row from the above table and enter these details: ");
				System.out.print("Cinema Class: "); //display header is class but attribute is CinemaType
				selectedCinemaTypeName = scan.next();
				//convert to object of cinema type - interface?
				//CinemaType selectedCinemaType;selectedCinemaType.setType(selectedCinemaTypeName);
				CinemaType selectedCinemaType(selectedCinemaTypeName); //MAKE CONSTRUCTOR PUBLIC
				System.out.println();
				
				//date and time are strings as per print showtime method definition 
				System.out.print("Date: ");
				selectedDate = scan.next();
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
				}
				catch (NumberFormatException ex){
		            ex.printStackTrace();
		        }
				System.out.print("Time: ");
				selectedTime = scan.next();
				System.out.println();
				
				Day selectedDay = Day(dayNumber,monthNumber, yearNumber, selectedTime);
				try {
					selectedShowtime = showtimeHandler.getShowtime(selectedMovieName, selectedDay, selectedCineplexID, CinemaType cinemaType);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Showtime is not found");
					continue;//to start of outer do-while loop
				}
			}//try end
			//CHECK IF EXCEPTIONS HANDLED CORRECTLY
			catch(Exception e) {
				System.out.println("No Showtimes exist for the Movie and Cineplex combination — please try again");
				continue; //to start of outer do-while loop
			}
		}while(showtime_selection_contd);
		
		//STEP 4 - Seat selection
		int showtimeID = selectedShowtime.getID();
		try{
			showtimeHandler.printCinemaLayout(showtimeID);
		}
		catch(IllegalArgumentException ex){
			System.out.println("Showtime is not found");
		}
		
		//STEP 5 - Get customer details — check for DiscountCode and CoupleSeat
		
		//STEP 6 - Display price (+other details) and confirm from customer
		
		//STEP 7 - bookSeat (internal calls booking in customer package) Call appropriate overloaded function. 
		
		CineplexManager.close();
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
