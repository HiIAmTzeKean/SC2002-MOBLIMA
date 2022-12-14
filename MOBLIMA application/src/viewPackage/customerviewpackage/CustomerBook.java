package viewpackage.customerviewpackage;
import java.util.*;
import agepackage.Age;
import cinemapackage.CinemaType;
import customerpackage.BookingManager;
import customerpackage.Customer;
import customerpackage.CustomerNullException;
import daypackage.Day;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import viewpackage.View;
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
		Day customerDay = new Day();
		Showtime customerShowtime = null;
		Customer customerObject = null;
		Boolean customerCoupleSeat = false;
		Boolean customerIsStudent = false;
		String customerDiscountCode = null;
		float customerBookingPrice = 0f;
		Boolean customerBookingDone = false;
		System.out.print("\033[H\033[2J");
		System.out.println("-------------------------------------");
		while (!complete) {
			switch (state) {
				case SELECTCINEPLEX:
					System.out.println("Choose a Cineplex");
					System.out.print("\033[H\033[2J");
					cs.printCineplexes();
					System.out.println("[Enter 0 to Return]");
					try {
						cineplexName = scan.nextLine();
						if (cineplexName.compareTo("0") == 0) {
							return;
						}
						if(!cs.isValidCineplexName(cineplexName)){
							System.out.printf("Please Enter a Valid Cineplex Name.");
							waitForEnter(null);
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
							System.out.printf("Please Enter a Valid Movie Name.");
							waitForEnter(null);
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
						System.out.printf("Only Preview and Now Showing Movies are Available for Booking");
						waitForEnter(null);
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
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.DISPLAYSHOWTIMES;
							break;
						}
					}
					catch(IllegalArgumentException e){
						System.out.println("No Showtimes Available for Current Movie at Selected Cineplex.");
						System.out.printf("Rerouting you to Select Cineplex Menu.");
						waitForEnter(null);
						state = bookMenuState.SELECTCINEPLEX;
						break;
					}
				case SELECTCINEMATYPE:
					//Select a Cinema Class
					System.out.println("Choose a Cinema Class");
					System.out.print("Available Options Are : ");
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
							if(cinemaType == null){
								System.out.printf("Cinema Type Entered is Invalid. Please Try Again.");
								waitForEnter(null);
								state = bookMenuState.SELECTCINEMATYPE;
								break;	
							}
						}catch(IllegalArgumentException e){
							System.out.printf("Cinema Type Entered is Invalid. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.SELECTCINEMATYPE;
							break;
						}
					}
				case SELECTDATETIME:
					try{
						System.out.println("Enter the Date of the Showtime");
						System.out.println("Format: YYYYMMDD");
						System.out.println("[Enter 0 to Return]");
						dateString = sc.next();
						if(dateString.compareTo("0") == 0){
							state = bookMenuState.SELECTCINEMATYPE;
							break;
						}
						customerDay.setDate(dateString);
						System.out.println("Enter the Time of the Showtime");
						System.out.println("Format: 24 Hours MMHH");
						System.out.println("[Enter 0 to Return]");
						timeString = sc.next();
						if(timeString.compareTo("0") == 0){
							state = bookMenuState.SELECTCINEMATYPE;
							break;
						}
						customerDay.setTime(timeString);
					}
					catch(IllegalArgumentException e){
						System.out.printf("Invalid Date or Time Entered. Please Try Again.");
						waitForEnter(null);	
						state = bookMenuState.SELECTDATETIME;
						break;
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
						state = bookMenuState.SELECTDATETIME;
						break;
					}
				case GETSHOWTIME:
					try{
						System.out.println();
						customerShowtime = cs.getShowtime(movieName, customerDay, cineplexName, cinemaType);
						System.out.print("\033[H\033[2J");	                                                             
						System.out.println("|-----------------------------------------------------------Selected Showtime----------------------------------|");
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
						System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
										"Movie Status","Movie Name","Cinema Type","Date","Time","Holiday");
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
						customerShowtime.printShowtime();
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
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
							System.out.printf("Invalid Input Entered. Please Try Again.");
							state = bookMenuState.GETSHOWTIME;
							waitForEnter(null);
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
						state = bookMenuState.GETSHOWTIME;
						break;
					}
				case SELECTSEAT:
					try{
						System.out.print("\033[H\033[2J");	
						System.out.println("Layout for Selected Showtime:");
						customerShowtime.printLayout();
						System.out.println("Enter the Row and Column of the Seat you Wish to Book");
						System.out.println("Row Letter: ");
						customerRow = sc.next();
						System.out.println("Column Number:");
						customerColumn = sc.nextInt();
						if(cs.isValidSeatSelection(customerShowtime, customerRow, customerColumn)){
							System.out.printf("Your Selected Seat is: Row %s Column %s\n", customerRow, customerColumn);
							if(cinemaType == CinemaType.PLATINUM && customerRow.compareTo("C") == 0){
								System.out.println("You Have Selected A Couple Seat.");
								customerCoupleSeat = true;
							}
							else{
								customerCoupleSeat = false;
							}
							System.out.println("[Enter 1 to Confirm Your Seat Selection]");
							System.out.println("[Enter 0 to Return]");
							String seatContinue = sc.next();
							if(seatContinue.compareTo("0") == 0){
								state = bookMenuState.SELECTSEAT;
								sc.nextLine();
								break;
							}	
							else{
								state = bookMenuState.CUSTOMERDETAILS;
								sc.nextLine();
								break;
							}
						}
						else{
							System.out.println("Seat Selected Has Already Been Booked. Please Try Again.");
							state = bookMenuState.SELECTSEAT;
							waitForEnter(null);
							break;
						}
					}catch(IllegalArgumentException e){
						System.out.println("Invalid Row and Column Entered. Please try again");
						waitForEnter(null);
						state = bookMenuState.SELECTSEAT;
						break;
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
						state = bookMenuState.SELECTSEAT;
						break;
					}
				case CUSTOMERDETAILS:
					System.out.print("\033[H\033[2J");	
					System.out.println("Please Enter your Personal Details for Booking");
					try{
						System.out.println("Are you a Student? [Y/N]");
						String studentChoice = sc.nextLine();
						if(studentChoice.compareTo("Y")==0){
							customerIsStudent = true;
						}
						else if(studentChoice.compareTo("N")==0){
							customerIsStudent = false;
						}
						else{
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CUSTOMERDETAILS;
							break;	
						}
						System.out.println("Name");
						String customerName = sc.nextLine();
						if(customerName.compareTo("")==0 || customerName.compareTo(" ")==0){
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CUSTOMERDETAILS;
							break;		
						}
						System.out.println("Mobile No.");
						int customerMobile = sc.nextInt();
						if(String.valueOf(customerMobile).length() != 8){
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CUSTOMERDETAILS;
							break;		
						}
						System.out.println("Email");
						sc.nextLine();
						String customerEmail = sc.nextLine();
						if(customerEmail.compareTo("")==0 || customerEmail.compareTo(" ")==0){
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CUSTOMERDETAILS;
							break;		
						}
						System.out.println("Age");
						int customerAgeInteger = sc.nextInt();
						if(customerAgeInteger <= 0){
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CUSTOMERDETAILS;
							break;		
						}
						Age customerAge = new Age(customerAgeInteger);
						customerObject = new Customer(customerName, customerMobile, customerEmail, customerAge, customerIsStudent);
					}
					catch(IllegalArgumentException e){
						System.out.println("Invalid Customer Details Entered. Please Try Again.");
						waitForEnter(null);
						state = bookMenuState.CUSTOMERDETAILS;
						break;
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
						state = bookMenuState.CUSTOMERDETAILS;
						break;
					}
				case CONFIRMCUSTOMERDETAILS:
					try {
						System.out.print("\033[H\033[2J");	
						System.out.println("You Have Entered the Following Details:");
						if(customerIsStudent){
							System.out.println("-Student Account-");
						}
						else{
							System.out.println("-Regular Account-");
						}
						System.out.printf("Name : %s\n",customerObject.getName());
						System.out.printf("Mobile No. : %d\n",customerObject.getMobile());
						System.out.printf("Email : %s\n",customerObject.getEmail());
						System.out.printf("Age: %d\n", customerObject.getAge().getAgeNumber());
						System.out.println("[Enter 0 to Change Your Details]");
						System.out.println("[Enter 1 to Confirm Your Details]");
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
							System.out.println("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.CONFIRMCUSTOMERDETAILS;
							break;
						}	
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = bookMenuState.CONFIRMCUSTOMERDETAILS;
						break;
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
								System.out.println("Invalid Discount Code Entered. Please Try Again.");
								waitForEnter(null);
								state = bookMenuState.DISPLAYPRICE;
								break;
							}
							customerBookingPrice = cp.getBookingPrice(customerObject, cinemaType, customerRow, customerColumn, customerShowtime, customerCoupleSeat, customerDiscountCode);
							System.out.printf("Your Final Price with Discount is %.2f\n",customerBookingPrice);
						}
						else if(discountCodeIndication.compareTo("N") == 0){
							customerDiscountCode = null;
							customerBookingPrice = cp.getBookingPrice(customerObject, cinemaType, customerRow, customerColumn, customerShowtime, customerCoupleSeat, customerDiscountCode);
							System.out.printf("Your Final Price is %.2f\n",customerBookingPrice);	
						}
						System.out.println("[Enter 0 to Return to Seat Selection]");
						System.out.println("[Enter 1 to Proceed to Payment]");
						System.out.println("[Enter 2 to Change Discount Code]");
						String displayPriceChoice = sc.next();
						if(displayPriceChoice.compareTo("0") == 0){
							state = bookMenuState.SELECTSEAT;
							break;
						}
						else if(displayPriceChoice.compareTo("1") == 0){
							state = bookMenuState.PAYMENT;
							break;
						}
						else if(displayPriceChoice.compareTo("2") == 0){
							state = bookMenuState.DISPLAYPRICE;
							break;
						}
						else{
							System.out.printf("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.DISPLAYPRICE;
							break;
						}
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
						state = bookMenuState.DISPLAYPRICE;
						break;
					}
					catch(IllegalArgumentException e){
						System.out.println("Invalid Discount Code Entered. Please Try Again.");
						state = bookMenuState.DISPLAYPRICE;
						waitForEnter(null);
						break;
					}
				case PAYMENT:
					try{
						System.out.print("\033[H\033[2J");
						System.out.println("Booking Summary:");
						System.out.println("|-----------------------------------------------------------Selected Showtime----------------------------------|");
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
						System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
										"Movie Status","Movie Name","Cinema Type","Date","Time","Holiday");
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
						customerShowtime.printShowtime();
						System.out.println("|--------------------------------------------------------------------------------------------------------------|");
						System.out.printf("Cineplex Name : %s\n",cineplexName);
						System.out.printf("Seat Number : %s %d\n",customerRow, customerColumn);	
						System.out.printf("Seat Type Booked : %s\n", customerCoupleSeat ?  "Couple Seat" : "Regular Seat");
						System.out.printf("Customer Age : %d\n", customerObject.getAge().getAgeNumber());
						System.out.printf("Final Price : %.2f \n",customerBookingPrice);
						System.out.printf("Discount Code Used : %s\n", customerDiscountCode == null ? "No" : "Yes");
						System.out.printf("Student Pricing Used : %s\n\n", customerIsStudent ? "Yes" : "No");
						System.out.println("Would You Like to Confirm Payment?");
						System.out.println("[Enter 0 to Cancel Payment and Select Another Movie]");
						System.out.println("[Enter 1 to Confirm Payment]");
						System.out.println("[Enter 2 to Cancel Payment and Return to Main Menu]");
						String paymentChoice = sc.next();
						if(paymentChoice.compareTo("0") == 0){
							state = bookMenuState.SELECTMOVIE;
							System.out.println("Cancelling Payment.");
							System.out.println("Please Select Another Movie at the Same Cineplex");
							waitForEnter(null);
							break;
						}
						else if(paymentChoice.compareTo("1") == 0){
							IShowtime bookingHandler = ShowtimeManager.getInstance();
							if(customerCoupleSeat){
								bookingHandler.bookCoupleSeat(customerShowtime.getID(),customerRow,customerColumn,customerObject,customerCoupleSeat, customerDiscountCode);
							}
							if(!customerCoupleSeat){
								bookingHandler.bookSeat(customerShowtime.getID(),customerRow,customerColumn,customerObject, customerDiscountCode);
							}
							customerBookingDone = true;
						}
						else if(paymentChoice.compareTo("2") == 0){
							state = bookMenuState.END;
							break;
						}
						else{
							System.out.println("Invalid Input Entered. Please Try Again.");
							waitForEnter(null);
							state = bookMenuState.PAYMENT;
							break;
						}
					}
					catch(InputMismatchException e){
						inputMismatchHandler();
						state = bookMenuState.PAYMENT;
						break;
					}
					catch(CustomerNullException e){
						System.out.println("Error with creating customer credentials.");
						System.out.println("Redirecting to entering customer details");
						state = bookMenuState.CUSTOMERDETAILS;
						waitForEnter(null);
						break;
					}
					catch(IllegalArgumentException e){
						System.out.println("Error with Creating Booking.");
						System.out.println("Redirecting to Seat Selection");
						state = bookMenuState.SELECTSEAT;
						waitForEnter(null);
						break;
					}
				case END:
					System.out.print("\033[H\033[2J");
					if(customerBookingDone){
						System.out.println("Booking Completed.");
						System.out.println("Thank You For Using MOBLIMA!");
					}
					System.out.println("Leaving Customer View");
					complete = true;
					break;
			}// end switch
		} // end while
	}// end bookMenu()

	public static void history() {
		try{
			Scanner scan = new Scanner(System.in);
			BookingManager hist = BookingManager.getInstance();
			System.out.println("Enter your Email to view your booking history");
			String email = scan.nextLine();
			hist.printAllTransactionsForCustomer(email);
			System.out.println();
		}catch(IllegalArgumentException e){
			System.out.println("There are no Booking Records Found for This Email.");
		}
	}
}
