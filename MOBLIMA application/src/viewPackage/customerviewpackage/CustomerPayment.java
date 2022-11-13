package viewpackage.customerviewpackage;
import java.util.*;

import agepackage.Age;
import agepackage.IAge;
import cinemapackage.CinemaType;
import customerpackage.Customer;
import customerpackage.CustomerNullException;
import customerpackage.DiscountCode;
import showtimepackage.IShowtime;
import showtimepackage.Showtime;
import showtimepackage.ShowtimeManager;

public class CustomerPayment{
	private static Scanner scan = new Scanner(System.in);
	private Customer c = null;
	private CustomerBook cb = null;
	private int bookingOption = 0;
	private boolean isCoupleSeat = false;
	private String discountEntered = null;
	private float price = 0;
	
	public enum paymentMethod{
		CASH, VISA, MASTERCARD
	};

	
	/** 
	 * @return Customer
	 */
	public Customer getCustomer() {
		return c;
	}
	
	
	/** 
	 * @param name
	 */
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
	
	
	/** 
	 * @param cType
	 * @param seatRow
	 */
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
	
	
	/** 
	 * @param customerObject
	 * @param cinemaType
	 * @param customerRow
	 * @param customerColumn
	 * @param customerShowtime
	 * @param customerCoupleSeat
	 * @param discountCodeTicket
	 * @return float
	 */
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public float getBookingPrice(Customer customerObject, CinemaType cinemaType, String customerRow, int customerColumn, Showtime customerShowtime, Boolean customerCoupleSeat, String discountCodeTicket) throws IllegalArgumentException{
		float price = 0.0f;		
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		int showtimeID = customerShowtime.getID();
		try{
			if(discountCodeTicket==null){
				if(customerCoupleSeat){
					return(showtimeHandler.getPrice(showtimeID, customerObject,true));
				}
				else{
					return(showtimeHandler.getPrice(showtimeID,customerObject));
				}
			}
			else{
				if(customerCoupleSeat){
					return(showtimeHandler.getPrice(showtimeID, customerObject, true, discountCodeTicket));
				}
				else{
					return(showtimeHandler.getPrice(showtimeID, customerObject, discountCodeTicket));
				}
			}
		}
		catch(CustomerNullException e){
			System.out.println("An Error Occured. Please Try Again.");
		}
		catch(IllegalArgumentException e){
			throw e;
			//System.out.println("Invalid Discount Code Entered. Please Try Again.");
		}
		return price;
	}
	
	
	/** 
	 * @param discountCode
	 * @return Boolean
	 */
	public Boolean isValidDiscountCode(String discountCode){
		DiscountCode discountCodeHandler = DiscountCode.getInstance();
		return(discountCodeHandler.checkValid(discountCode));
	}

	
	/** 
	 * @param cType
	 * @param seatRow
	 * @param selectedShowtimeID
	 * @return float
	 */
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

