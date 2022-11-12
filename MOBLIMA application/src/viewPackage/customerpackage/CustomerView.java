//THIS CLASS WILL BE ENTIRELY REPLACED BY NEWCUSTOMER VIEW, MOST OF THE CODE HERE HAS BEEN
//REFORMATTED AND ADDED TO CUSTOMERBOOK

package viewPackage.customerpackage;
import java.util.*;

import cineplexpackage.ICineplex;
import cineplexpackage.CineplexManager;
import cineplexpackage.Cineplex;

import viewPackage.View;

import customerpackage.BookingManager;
import customerpackage.Customer;
import moviepackage.IMovie;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import viewPackage.View;
import showtimepackage.Showtime;
import cinemapackage.CinemaType;
import daypackage.Day; 
import agepackage.IAge;
import agepackage.Age;

public class CustomerView  extends View{
	private static Scanner scan = new Scanner(System.in); 
	
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void bookMenu() {
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		
		int selectedShowtimeID = 0;
		CinemaType cType = null;
		String seatRow = null;
		int seatCol = 0;
		boolean bookingDone = false;
		//int customerID = 0;
		Customer c = null;
		boolean leaveBookMenu = false;
		int bookingOption = 0;
		float price = 0;
		
		//CHECK
		CustomerShowtime cs = null;
		CustomerPayment cp = null;
		
		//STEP 1 - Choosing from cineplexes and movies to view all showtimes for the combination
		cs.displayShowtimes();
		
		//STEP 2 - Select a showtime from those available for movie and cineplex combination
		System.out.println("Choose a row from the above table and enter these details: ");
		cs.selectShowtime();
		selectedShowtimeID = cs.getSelectedShowtimeID();
		cType = cs.getCinemaType();
		
		do {//until available seats selected and booked 
		
			//STEP 3 - Seat selection
			cs.selectSeat();
			seatRow = cs.getseatRow();
			seatCol = cs.getseatCol();
			
			//STEP 4 - Get customer details — check for DiscountCode and CoupleSeat
			//cp.setCustomerDetails();
			c = cp.getCustomer();
			
			//STEP 5 - select booking option and get projected price
			price = cp.getProjectedBookingPrice(cType, seatRow, selectedShowtimeID);
			System.out.printf("The cost of your booking is: %f %n", price);
			
			//STEP 6 - Confirm booking seat
			System.out.println("Enter 1 to confirm booking, 0 to change selected seat, or -1 to moviegoer main menu");
			String bookConfirm = scan.next();
			
			switch(bookConfirm) {
				case "1":{
					try { 
						if(bookingOption == 2 || bookingOption == 4) {
							// showtimeHandler.bookCoupleSeat(selectedShowtimeID, seatRow, seatCol, c);
						}
						else {
							// showtimeHandler.bookSeat(selectedShowtimeID, seatRow, seatCol, c);
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
		
		
		ShowtimeManager.close();
		
	}//End of Book Menu Function
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
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
				//r.setReviewAndRating(reviewMovieName);
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
