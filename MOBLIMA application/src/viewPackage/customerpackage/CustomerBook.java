package viewPackage.customerpackage;
import cinemapackage.CinemaType;
import customerpackage.DiscountCode;
import java.util.*;

public class CustomerBook {
	/*
	 * Internal variable bookingOption keeps track of the booking being a 
	 * 1- Standard booking for 1 person
	 * 2- Couple Seat
	 * 3- Discount code
	 * 4- Couple seat + Discount code
	 */

	private int bookingOption = 1;
	private boolean isCoupleSeat = false;
	private boolean discountValid = false;
	private String discountEntered = null;
	
	private static Scanner scan = new Scanner(System.in); 
	
	public int getBookingOption() {
		return bookingOption;
	}
	
	public boolean getIsCoupleSeat() {
		return isCoupleSeat;
	}
	
	public String getDiscountEntered() {
		return discountEntered;
	}
	
	public void setBookingOption(CinemaType cType, String seatRow){
		DiscountCode dc = DiscountCode.getInstance();
		
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
}
