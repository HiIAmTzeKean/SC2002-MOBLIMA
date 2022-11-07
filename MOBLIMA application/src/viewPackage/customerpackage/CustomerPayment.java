package viewPackage.customerpackage;
import java.util.*;

import agepackage.Age;
import agepackage.IAge;
import cinemapackage.CinemaType;
import customerpackage.Customer;
import customerpackage.DiscountCode;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;


	//public static void confirmPayment(){
		//TODO: Confirmation of Payment (yes/no)
	//}	
public class CustomerPayment {
	private static Scanner scan = new Scanner(System.in);
	private Customer c = null;
	private CustomerBook cb = null;
	private int bookingOption = 0;
	private boolean isCoupleSeat = false;
	private String discountEntered = null;
	private float price = 0;
	
	public Customer getCustomer() {
		return c;
	}
	
	public void setCustomerDetails(String name) {
		name = null;
		IAge age = null;
		int mobile = 0;
		String email = null;
		
		c.setName(name);
		System.out.println();
		
		System.out.print("age :");
		int ageInt = scan.nextInt();
		age.setAgeNumber(ageInt);
		c.setAge((Age)age);
		System.out.println();
		
		System.out.print("Mobile :");
		mobile = scan.nextInt();
		c.setMobile(mobile);
		System.out.println();
		
		System.out.print("Email :");
		email = scan.nextLine();
		c.setEmail(email);
		System.out.println();
		
		//customerID = c.getID();
	}//edd of setCustomerDetails()
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * internally modify the variables booking option, isCoupleSeat, discountEntered. 
	 * Used by getProjectedBookingPrice
	 */
	public void setBookingOption(CinemaType cType, String seatRow){
		DiscountCode dc = DiscountCode.getInstance();
		boolean discountValid = false;
		
		//couple seating is only allowed for Platinum Class in Row C
		if(cType.equals("Platinum") && seatRow.compareTo("C") == 0 ) {
			System.out.println("If you wish to book as couple seat, enter 1. Else, enter any other character: ");
			String coupleSeatChoice = scan.next();
			if(coupleSeatChoice.compareTo("1") == 0) {
				isCoupleSeat = true;
				bookingOption = 2; 
			}
		}
		
		discountEntered = "0";
		do {
			System.out.println("If you wish to use discount code, enter code. Else, enter 0: ");
			discountEntered = scan.next();
			if(dc.checkValid(discountEntered)) {
				discountValid = true;
			}
		}while(discountEntered.compareTo("0") != 0 || !discountValid); //exits when user enters 0 or enters a valid discount code
		
		if(discountValid) {
			bookingOption = 3;
			if(isCoupleSeat) {
				bookingOption = 4;
			}//endif
		}//endif
	}//end setBookingOption()
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public float getProjectedBookingPrice(CinemaType cType, String seatRow, int selectedShowtimeID) {
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		setBookingOption(cType, seatRow);
		
		try { //CHECK - handle specific exceptions for each call separately?
			switch(bookingOption) {
				case 1: 
					price = showtimeHandler.getPrice(selectedShowtimeID, c);
					break;
				case 2:
					price = showtimeHandler.getPrice(selectedShowtimeID, c, isCoupleSeat);
					break;
				case 3:
					price = showtimeHandler.getPrice(selectedShowtimeID, c, discountEntered);
					break;
				case 4:
					price = showtimeHandler.getPrice(selectedShowtimeID, c, isCoupleSeat, discountEntered);
					break;
				default: 
					System.out.println("Error in getting price.");
					price = -1;
					break;
			}//endswitch
		}//endtry
		catch(Exception eprice) {
			System.out.println("Error in getting price.");
			return -1; 
		}
		return price;
	}//end getProjectedBookingPrice
}

