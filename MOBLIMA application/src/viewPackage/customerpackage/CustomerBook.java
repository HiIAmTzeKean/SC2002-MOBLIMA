package viewPackage.customerpackage;

import java.util.*;

import agepackage.Age;
import cinemapackage.CinemaType;
import customerpackage.BookingManager;
import customerpackage.Customer;
import daypackage.Day;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import viewPackage.View;
import showtimepackage.Showtime;

public class CustomerBook extends View {
	public static void start(){
		displayMenu();
	}
	public static void displayMenu() {
		//Initialize enum for backtracking
		enum bookMenuState {
			SELECTCINEPLEX, SELECTMOVIE, DISPLAYSHOWTIMES, SELECTCINEMATYPE, SELECTDATETIME, GETSHOWTIME, SELECTSEAT, CUSTOMERDETAILS, CONFIRMCUSTOMERDETAILS, DISPLAYPRICE,
			PAYMENT, END};
		CustomerShowtime cs = new CustomerShowtime();
		CustomerPayment cp = new CustomerPayment();
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		Scanner scan = new Scanner(System.in);
		
		bookMenuState state = bookMenuState.SELECTCINEPLEX;
		boolean complete = false;
		//Initializing Input Strings from User
		String cineplexName = null;
		String movieName = null;
		String cinemaTypeInput = null;
		CinemaType cinemaType = null;
		String dateString = null;
		String timeString = null;
		String customerRow = null;
		int customerColumn = 0;
		Day customerDay = null;
		Showtime customerShowtime = null;
		Customer customerObject = null;
		Boolean customerCoupleSeat = false;
		Boolean customerIsStudent = false;
		String customerDiscountCode = null;
		float customerBookingPrice = 0f;
		System.out.print("\033[H\033[2J");
		System.out.println("-------------------------------------");
		while (!complete) {
			switch (state) {
				case SELECTCINEPLEX:
					System.out.print("\033[H\033[2J");
					cs.printCineplexes();
					System.out.println("Choose a Cineplex");
					System.out.println("[Enter 0 to Return]");
					try {
						cineplexName = scan.nextLine();
						if (cineplexName.compareTo("0") == 0) {
							return;
						}
						if(!cs.isValidCineplexName(cineplexName)){
							System.out.println("Please Enter a Valid Cineplex Name");
							state = bookMenuState.SELECTCINEPLEX;
							break;
						}
					} catch (InputMismatchException e) {
						System.out.println(e);
						state = bookMenuState.SELECTCINEPLEX;
					}
				case SELECTMOVIE:
					try {
						System.out.print("\033[H\033[2J");
						System.out.println("Choose a Movie");
						CustomerMovieListing.printMovieTitles();
						System.out.println("[Enter 0 to Return]");
						movieName = scan.nextLine();
						if(movieName.compareTo("0") == 0) {
							state = bookMenuState.SELECTCINEPLEX;
							break;
						} 
						else if(!CustomerMovieListing.isValidMovieName(movieName)){
							System.out.println("Please Enter a Valid Movie Name.");
							state = bookMenuState.SELECTMOVIE;
							break;
						}
						else{
							state = bookMenuState.DISPLAYSHOWTIMES;
							break;
						}
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = bookMenuState.SELECTMOVIE;
						break;
					} catch (IllegalArgumentException e) {
						state = bookMenuState.SELECTMOVIE;
						break;
					}
				case DISPLAYSHOWTIMES:
					try{
						System.out.print("\033[H\033[2J");
						cs.displayShowtimes(movieName, cineplexName);
						System.out.println("[Enter 0 to Return]");
						System.out.println("[Enter 1 to Select a Showtime]");
						String choice = sc.next();
						if(choice.compareTo("0") == 0){
							state = bookMenuState.SELECTMOVIE;
							break;
						}
						else if(choice.compareTo("1") == 0){
							state = bookMenuState.SELECTCINEMATYPE;
							break;
						}
						else{
							System.out.println("Enter Valid Input!");
							state = bookMenuState.DISPLAYSHOWTIMES;
							break;
						}
					}
					catch(IllegalArgumentException e){
						System.out.println("No showtimes available for the current movie at the current cineplex.");
						System.out.println("Rerouting you to select cineplex again.");
						state = bookMenuState.SELECTCINEPLEX;
						break;
					}
				case SELECTCINEMATYPE:
					//Select a Cinema Class
					System.out.println("Choose a Cinema Class");
					System.out.print("Available Options : ");
					for(CinemaType type : CinemaType.values()){
						System.out.print(type.toString() + " ");
					}
					System.out.println();
					System.out.println("[Enter 0 to Return]");
					cinemaTypeInput = sc.next();
					if(cinemaTypeInput.compareTo("0")==0){
						state = bookMenuState.DISPLAYSHOWTIMES;
						break;
					}
					else{
						try{
							cinemaType = cs.isValidCinemaType(cinemaTypeInput);
						}catch(IllegalArgumentException e){
							System.out.println("Cinema Type Entered is Invalid. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.SELECTCINEMATYPE;
							break;
						}
					}
				case SELECTDATETIME:
					System.out.println("Enter the Date of the Showtime");
					System.out.println("Format: YYYYMMDD");
					System.out.println("[Enter 0 to Return]");
					dateString = sc.next();
					if(dateString.compareTo("0") == 0){
						state = bookMenuState.SELECTCINEMATYPE;
					}
					System.out.println("Enter the Time of the Showtime");
					System.out.println("Format: 24 Hours MMHH");
					System.out.println("[Enter 0 to Return]");
					timeString = sc.next();
					if(dateString.compareTo("0") == 0){
						state = bookMenuState.SELECTCINEMATYPE;
					}
					try{
						customerDay = cs.isValidDateTime(dateString, timeString); 
					}catch(IllegalArgumentException e){
						System.out.println("Invalid Date and Time Entered. Please try again.");
						waitForEnter(null);	
						state = bookMenuState.SELECTDATETIME;
						break;
					}
				case GETSHOWTIME:
					try{
						customerShowtime = cs.getShowtime(movieName, customerDay, cineplexName, cinemaType);
						System.out.print("\033[H\033[2J");	
						customerShowtime.printShowtime();
						System.out.println("[Enter 0 to Return]");
						System.out.println("[Enter 1 to Confirm Showtime and Proceed with Seat Booking]");
						String showtimeChoice = sc.next();
						if(showtimeChoice.compareTo("0")==0){
							state = bookMenuState.DISPLAYSHOWTIMES;
						}	
						else if(showtimeChoice.compareTo("1")==0){
							state = bookMenuState.SELECTSEAT;
						}
						else{
							System.out.println("Invalid Input!");
							state = bookMenuState.GETSHOWTIME;
							return;
						}
					}
					catch(IllegalArgumentException e){
						System.out.println("Showtime Not Found for the Requested Movie and Cineplex.");
						waitForEnter(null);
						state = bookMenuState.DISPLAYSHOWTIMES;
						break;
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
					}
				case SELECTSEAT:
					try{
						System.out.println("Layout for Selected Showtime:");
						customerShowtime.printLayout();
						System.out.println("Enter the Row and Column of the Seat you Wish to Book");
						System.out.println("Row Letter: ");
						customerRow = sc.next();
						System.out.println("Column Number:");
						customerColumn = sc.nextInt();
						if(cs.isValidSeatSelection(customerShowtime, customerRow, customerColumn)){
							System.out.printf("Your Selected Seat is: Row %s Column %s\n", customerRow, customerColumn);
							if(cinemaType == CinemaType.PLATINUM){
								System.out.println("Would You Like to Book a Couple Seat? [Y/N]");
								String coupleSeatChoice = sc.next();
								if(coupleSeatChoice.compareTo("Y") == 0){
									customerCoupleSeat = true;
								}
							}
							System.out.println("[Enter 1 to Confirm Your Seat Selection]");
							System.out.println("[Enter 0 to Return]");
							String seatContinue = sc.next();
							if(seatContinue.compareTo("0") == 0){
								state = bookMenuState.SELECTSEAT;
								break;
							}	
							else{
								state = bookMenuState.CUSTOMERDETAILS;
								break;
							}
						}
					}catch(IllegalArgumentException e){
						System.out.println("Invalid Row and Column Entered. Please try again");
						waitForEnter(null);
						state = bookMenuState.SELECTSEAT;
						break;
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
					}
					
				case CUSTOMERDETAILS:
					System.out.print("\033[H\033[2J");	
					System.out.println("Please Enter your Personal Details for Booking");
					sc.nextLine();
					try{
						System.out.println("Are you a Student? [Y/N]");
						String studentChoice = sc.nextLine();
						if(studentChoice.compareTo("Y")==0){
							customerIsStudent = true;
						}
						else if(studentChoice.compareTo("N")==0){
							customerIsStudent = false;
						}
						System.out.println("Name");
						String customerName = sc.nextLine();
						System.out.println("Mobile No.");
						int customerMobile = sc.nextInt();
						System.out.println("Email");
						sc.nextLine();
						String customerEmail = sc.nextLine();
						System.out.println("Age");
						int customerAgeInteger = sc.nextInt();
						Age customerAge = new Age(customerAgeInteger);
						customerObject = new Customer(customerName, customerMobile, customerEmail, customerAge, customerIsStudent);
					}
					catch(InputMismatchException e){
						System.out.println("Invalid Customer Details Entered. Please try again.");
						state = bookMenuState.CUSTOMERDETAILS;
						break;
					}
				case CONFIRMCUSTOMERDETAILS:
					try {
						System.out.print("\033[H\033[2J");	
						System.out.println("You Have Entered the Following Details");
						if(customerIsStudent){
							System.out.println("-Student Account-");
						}
						else{
							System.out.println("-Regular Account-");
						}
						System.out.printf("Name : %s\n",customerObject.getName());
						System.out.printf(" Mobile No. : %d\n",customerObject.getMobile());
						System.out.printf("Email : %s\n",customerObject.getEmail());
						System.out.printf("Age: %d\n", customerObject.getAge().getAgeNumber());
						System.out.println("[To Confirm Your Details, Enter 1]");
						System.out.println("[Enter 0 to Change Your Details]");
						String customerDetailsChoice = sc.next();
						if(customerDetailsChoice.compareTo("0")==0){
							state = bookMenuState.CUSTOMERDETAILS;
							break;
						}
						else if(customerDetailsChoice.compareTo("1")==0){
							state = bookMenuState.DISPLAYPRICE;
							break;
						}
						else{
							System.out.println("Invalid Input.");
							state = bookMenuState.CONFIRMCUSTOMERDETAILS;
							break;
						}	
					} catch (InputMismatchException e) {
						inputMismatchHandler();
					}
				case DISPLAYPRICE:
					try{
						System.out.print("\033[H\033[2J");		
						System.out.println("Do You Have a Discount Code? [Y/N]");
						String discountCodeIndication = sc.next();
						if(discountCodeIndication.compareTo("Y") == 0){
							System.out.println("Please Enter Your Discount Code:");
							customerDiscountCode = sc.next();	
							if(!cp.isValidDiscountCode(customerDiscountCode)){
								System.out.println("Invalid Discount Code Entered.");
								state = bookMenuState.DISPLAYPRICE;
							}
							System.out.printf("Your Final Price with Discount is %.2f\n",cp.getBookingPrice(customerObject, cinemaType, customerRow, customerColumn, customerShowtime, customerCoupleSeat, customerDiscountCode));
						}
						else if(discountCodeIndication.compareTo("N") == 0){
							customerBookingPrice = cp.getBookingPrice(customerObject, cinemaType, customerRow, customerColumn, customerShowtime, customerCoupleSeat, customerDiscountCode);
							System.out.printf("Your Final Price is %.2f\n",cp.getBookingPrice(customerObject, cinemaType, customerRow, customerColumn, customerShowtime, customerCoupleSeat, customerDiscountCode));	
						}
						System.out.println("[Enter 0 to Return to Seat Selection]");
						System.out.println("[Enter 1 to Proceed to Payment]");
						String displayPriceChoice = sc.next();
						if(displayPriceChoice.compareTo("0") == 0){
							state = bookMenuState.SELECTSEAT;
							break;
						}
						else if(displayPriceChoice.compareTo("1") == 0){
							state = bookMenuState.PAYMENT;
							break;
						}
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
					}
				case PAYMENT:
					try{
						System.out.println("Would you like to confirm payment? Currently supported methods are:");
						System.out.println("[Enter 1 to Confirm Payment]");
						System.out.println("[Enter 0 to Return]");
						String paymentChoice = sc.next();
						if(paymentChoice.compareTo("0") == 0){
							state = bookMenuState.SELECTMOVIE;
							break;
						}
						else if(paymentChoice.compareTo("1") == 1){
							IShowtime bookingHandler = ShowtimeManager.getInstance();
							if(customerCoupleSeat){
								bookingHandler.bookCoupleSeat(customerShowtime.getID(),customerRow,customerColumn,customerObject);
							}
							if(!customerCoupleSeat){
								bookingHandler.bookSeat(customerShowtime.getID(),customerRow,customerColumn,customerObject);
							}
						}
						else{
							System.out.println("Invalid Input");
							state = bookMenuState.PAYMENT;
							break;
						}
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
					}
				case END:
					System.out.println("Leaving movigoer view!");
					complete = true;
					break;
			}// end switch
		} // end while
	}// end bookMenu()

	public static void history() {
		Scanner scan = new Scanner(System.in);
		BookingManager hist = BookingManager.getInstance();
		System.out.println("Enter your Email to view your booking history");
		String email = scan.nextLine();
		hist.printAllTransactionsForCustomer(email);
		System.out.println();
	}
}
