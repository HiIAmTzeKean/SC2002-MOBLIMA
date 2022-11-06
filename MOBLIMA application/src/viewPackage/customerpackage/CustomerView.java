package viewPackage.customerpackage;
import java.util.*;

import cineplexpackage.ICineplex;
import cineplexpackage.CineplexManager;
import cineplexpackage.Cineplex;
import customerpackage.BookingManager;
import customerpackage.Customer;
import moviepackage.IMovie;
import moviepackage.MovieManager; 
import moviepackage.Movie; 
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import viewPackage.View;
import showtimepackage.Showtime;
import cinemapackage.CinemaType;
import daypackage.Day; 
import agepackage.IAge;
import agepackage.Age;

public class CustomerView extends View{
	private static Scanner scan = new Scanner(System.in);
	//private static Scanner scan = new Scanner(System.in);	
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
			}
			//end switch case
			System.out.println();
		} while(contd); //end do while loop 
	}
	
	public static void bookMenu() {
		ICineplex cineplexHandler = CineplexManager.getInstance();
		IMovie movieFindHandler = MovieManager.getInstance(); 
		IShowtime showtimeHandler = ShowtimeManager.getInstance(); 
		String selectedCineplexName;
		Cineplex selectedCineplexObj;
		int selectedCineplexID = 0;
		String selectedMovieName = null;
		Movie selectedMovieObj;
		String selectedCinemaTypeName = null;
		String selectedDate = null;
		String selectedTime = null;
		CinemaType selectedCinemaType = null;
		Day selectedDay;
		Showtime selectedShowtime = null;
		int showtimeID;
		String seatRow;
		int seatCol;
		boolean bookingDone = false;
		int customerID;
		boolean leaveBookMenu = false;
		
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
			selectedCineplexID = selectedCineplexObj.getID();
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//STEP 2 - CHOOSE MOVIE
			CustomerMovieListing movSelectObj = new CustomerMovieListing();
			movSelectObj.movieSelection(); //runs the movie selection menu
			selectedMovieName = movSelectObj.getSelectedMovieName();
			//System.out.println(selectedMovieName);
			try {
				selectedMovieObj =  movieFindHandler.findMoviebyName(selectedMovieName); 
				selectedMovieObj.printMovieComplete();
			}
			catch(IllegalArgumentException e) {
				System.out.println("Movie is not found — please try again");
				continue; //to start of outer do-while loop
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//STEP 3 - Display showtimes for the movie and cineplex combination (atleast one showtime should exist)
			
			try {
				
				System.out.printf("\nFollowing are the showtimes available for movie %s at cineplex %s:\n", selectedMovieName, selectedCineplexName);
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
		/*
		 * Loop 
		 */
		do {
			//#1
			System.out.println("Choose a row from the above table and enter these details: ");
			
			//A) CinemaType
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
				
			//B) Date - prompt until length 8
			do {
				System.out.print("Date (length 8): ");
				selectedDate = scan.next();
			}while(selectedDate.length() != 8); 
			System.out.println();
			
			//convert string date to required format for Day constructor
			int dateInt = 0, dayNumber = 0, monthNumber = 0,yearNumber = 0;
			try {
				dateInt = Integer.parseInt(selectedDate);
			}
			catch (NumberFormatException edate){
		        edate.printStackTrace();
		        continue;
		    }	
			
			// reversing fullDate = Integer.toString(this.yearNumber) + monthString + dayString;
			dayNumber = dateInt%100;
			dateInt/=100;
			monthNumber = dateInt%100;
			dateInt/=100;
			yearNumber = dateInt;		
							
			//C) Time - implement input check if needed
			System.out.print("Time: ");
			selectedTime = scan.next();
			System.out.println();	
				
				//CHECK for bugs
				try {			
				selectedDay = new Day(dayNumber, monthNumber, yearNumber, selectedTime);
				}
				catch(IllegalArgumentException eday) {
					System.out.println("Invalid date string supplied");
					continue; 
				}
							
				try {
					selectedShowtime = showtimeHandler.getShowtime(selectedMovieName, selectedDay, selectedCineplexID, selectedCinemaType);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Showtime is not found for details entered");
					System.out.println();
				}
		}while(selectedShowtime == null);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//STEP 5 - Seat selection
		showtimeID = selectedShowtime.getID();
		try{
			showtimeHandler.printCinemaLayout(showtimeID);
			System.out.println();
		}
		catch(IllegalArgumentException eshowtime){ //unlikely as check for showtime object has be done already
			System.out.println("Showtime is not found");
		}
		
		
		do {//until available seats selected and booked 
			
		//INPUT CHECKS for row and column at this end? — range of 'allowed' values depends on layout
		System.out.println("Based on the layout shown, enter the row and column for the seat you wish to select");
		System.out.print("Row(letter) :");
		seatRow = scan.next();
		System.out.println();
		System.out.print("Column(number) :");
		seatCol = scan.nextInt();
		System.out.println();
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//STEP 6 - Get customer details — check for DiscountCode and CoupleSeat
		Customer c = null;
		System.out.println("Please enter your personal details: ");
		System.out.print("Name :");
		String name = scan.next();
		c.setName(name);
		System.out.println();
		
		IAge age = null;
		System.out.print("age :");
		int ageInt = scan.nextInt();
		age.setAgeNumber(ageInt);
		c.setAge((Age)age);
		System.out.println();
		
		System.out.print("Mobile :");
		int mobile = scan.nextInt();
		c.setMobile(mobile);
		System.out.println();
		
		System.out.print("Email :");
		String email = scan.next();
		c.setEmail(email);
		System.out.println();
		
		customerID = c.getID();
		
		/*
		 * Internal variable bookingOption keeps track of the booking being a 
		 * 1- Standard booking for 1 person
		 * 2- Couple Seat
		 * 3- Discount code
		 * 4- Couple seat + Discount code
		 */
		int bookingOption = 1; 
		
		boolean isCoupleSeat = false;
		System.out.println("If you wish to book as couple seat, enter 1. Else, enter any other character: ");
		String coupleSeatChoice = scan.next();
		if(coupleSeatChoice.compareTo("1") == 0) {
			isCoupleSeat = true;
			bookingOption = 2; 
		}
		
		boolean discountValid = false;
		String discEntered = "0";
		do {
			System.out.println("If you wish to use discount code, enter code. Else, enter 0: ");
			discEntered = scan.next();
		}while(discEntered.compareTo("0") != 0 || !discountValid); //exits when user enters 0 or enters a valid discount code
		if(discountValid) {
			bookingOption = 3;
			if(isCoupleSeat) {
				bookingOption = 4;
			}
		}
		
		//STEP 7 - Display price (+other details) and confirm from customer
		float price = 0;
		try { //CHECK - handle specific exceptions for each call separately?
			switch(bookingOption) {
				case 1: 
					price = showtimeHandler.getPrice(showtimeID, c);
					break;
				case 2:
					price = showtimeHandler.getPrice(showtimeID, c, isCoupleSeat);
					break;
				case 3:
					price = showtimeHandler.getPrice(showtimeID, c, discEntered);
					break;
				case 4:
					price = showtimeHandler.getPrice(showtimeID, c, isCoupleSeat, discEntered);
					break;
				default: 
					System.out.println("Error in getting price.");
			}
		}
		catch(Exception eprice) {
			System.out.println("Error in getting price.");
			continue; //to start of bookingDone do-while loop
		}
		System.out.printf("The cost of your booking is: %f %n", price);
		System.out.println("Enter 1 to confirm booking, 0 to change booking option, or -1 to moviegoer main menu");
		String bookConfirm = scan.next();
		
		//CHECK - Need another prompt to 'make payment'?
		
		//STEP 8 - bookSeat (internal calls booking in customer package) Call appropriate overloaded function. 
		//set bookingdone
		switch(bookConfirm) {
			case "1":{
				try { 
					if(bookingOption == 2 || bookingOption == 4) {
						showtimeHandler.bookCoupleSeat(showtimeID, seatRow, seatCol, c);
					}
					else {
						showtimeHandler.bookSeat(showtimeID, seatRow, seatCol, c);
					}
				}
				catch(Exception ebook) {//specific exceptions
					System.out.println("Error in booking seat.");
					continue; //to start of bookingDone do-while loop
				}
				bookingDone = true;
				System.out.println("Your booking has been made!");
				break;
			}
			case "0":continue;
			case "-1": 
				leaveBookMenu = true;
				break;
			default: 
				System.out.println("Erroneous value entered, going back to book options.");
				break;
			}
		
		}while(!bookingDone || leaveBookMenu);
		
		MovieManager.close();
		ShowtimeManager.close();
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
