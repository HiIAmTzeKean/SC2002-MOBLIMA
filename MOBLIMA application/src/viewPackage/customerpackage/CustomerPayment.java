package viewPackage.customerpackage;
import java.util.*;

import agepackage.Age;
import agepackage.IAge;
import cinemapackage.CinemaType;
import customerpackage.Customer;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;

public class CustomerPayment {
	private static Scanner scan = new Scanner(System.in);
	private Customer c = null;
	//private int customerID = 0;
	private CustomerBook cb = null;
	private int bookingOption = 0;
	private float price = 0;
	
	public Customer getCustomer() {
		return c;
	}
	
	public void setCustomerDetails() {
		String name = null;
		IAge age = null;
		int mobile = 0;
		String email = null;
		
		System.out.println("\nPlease enter your personal details: ");
		System.out.print("Name :");
		name = scan.next();
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
		email = scan.next();
		c.setEmail(email);
		System.out.println();
		
		//customerID = c.getID();
	}//edd of setCustomerDetails()
	
	public float getProjectedBookingPrice(CinemaType cType, String seatRow, int selectedShowtimeID) {
		cb.setBookingOption(cType, seatRow);
		bookingOption = cb.getBookingOption();
		boolean isCoupleSeat = cb.getIsCoupleSeat();
		String discountEntered = cb.getDiscountEntered();
		
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		
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
			}//endswitch
		}//endtry
		catch(Exception eprice) {
			System.out.println("Error in getting price.");
			return -1; 
		}
		
		return price;
	}//end getProjectedBookingPrice
	
}
