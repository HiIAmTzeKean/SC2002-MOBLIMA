package viewPackage.customerpackage;

import java.util.*;
import agepackage.Age;
import agepackage.IAge;
import cinemapackage.CinemaType;
import customerpackage.Customer;
import customerpackage.DiscountCode;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import showtimepackage.Showtime;

public class CustomerBook {	
	public static void bookMenu(){
		int selectedCineplexID = 0;
		int selectedShowtimeID = 0;
		Showtime selectedShowtime = null;
		String MovieName = null;
		CinemaType cType = null;
		String seatRow = null;
		int seatCol = 0;
		boolean bookingDone = false;
		//int customerID = 0;
		Customer c = null;
		boolean leaveBookMenu = false;
		int bookingOption = 0;
		float price = 0;
		String return_choice = null;
		
		CustomerShowtime cs = new CustomerShowtime();
		CustomerPayment cp =  new CustomerPayment();
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		Scanner scan = new Scanner(System.in);
         
		enum bookMenuState{
             SELECTCINEPLEX, SELECTMOVIE, DISPLAYSHOWTIMES, SELECTSHOWTIME, SELECTSEAT, CUSTOMERDETAILS, DISPLAYPRICE, CONFIRMBOOK, END
         };
         
         bookMenuState state = bookMenuState.SELECTCINEPLEX;
         boolean complete = false;
         System.out.print("\033[H\033[2J");
         System.out.println("-------------------------------------");
         
         while(!complete){
             switch(state){
                 case SELECTCINEPLEX:
                	 System.out.println("[Enter 0 to return]");
                	 return_choice = scan.next();
                	 if(return_choice.compareTo("0")==0) {
                		 return;
                	 }
                	 selectedCineplexID = cs.chooseCineplex(); //function returns -1 in case of error
                	 if(selectedCineplexID == -1) {
                		 selectedCineplexID = 0; //reset value;
                		 state = bookMenuState.SELECTCINEPLEX; //restart 
                		 break;
                	 }
                 case SELECTMOVIE:
                     try{
                         System.out.println("[Enter 0 to return]"); 
                    	 return_choice = scan.next();
                    	 if(return_choice.compareTo("0")==0) {
                    		 state = bookMenuState.SELECTCINEPLEX; //go-back a step
                    		 break;
                    	 }
                         CustomerMovieListing movieListing = new CustomerMovieListing();
                         movieListing.movieSelection();
                     }
                     catch(InputMismatchException e){
                         //inputMismatchHandler(); -> gave error, check for something missing
                         state = bookMenuState.SELECTMOVIE;
                         break;
                     }
                 case DISPLAYSHOWTIMES:
                	 System.out.println("[Enter 0 to return]"); 
                	 return_choice = scan.next();
                	 if(return_choice.compareTo("0")==0) {
                		 state = bookMenuState.SELECTMOVIE; //go-back a step
                		 break;
                	 }
                	 boolean displayOk = cs.displayShowtimes();
                	 if(!displayOk) {
                		 state = bookMenuState.SELECTCINEPLEX; //go-back to select a different cineplex/combination
                		 break;
                	 }
                	 //else if displayOk is true -> goes to next step
                	 //Do not clear console between display showtimes and select showtime
                 case SELECTSHOWTIME:
			     System.out.println("Select a row from showtimes display and enter details to select it.");
			     System.out.print("Enter Cinema Class, from 'Platinum', 'Gold', 'Sliver'");
			     System.out.println("[Enter 0 to Return]");
			     try{
 				String cinemaTypeName = scan.nextLine();
 				if(cinemaTypeName.compareTo("0")==0){
					state = bookMenuState.DISPLAYSHOWTIMES; //go-back a step
 	                		 break;
 					}
				cs.setCinemaTypeName(cinemaTypeName);
 				selectedShowtime = cs.getSelectedShowtime();
 				if(selectedShowtime == null) { //function returns null object when showtime not found
					state = bookMenuState.SELECTSHOWTIME;
					System.out.println("Error in selecting the showtime, please retry.");
					break;
                   		 }
 				else {
					selectedShowtimeID = cs.getSelectedShowtimeID();
					cType = cs.getCinemaType(); 
 					}
 				}
                	 catch(Exception eselectshow) {
                		 state = bookMenuState.SELECTSHOWTIME; 
                		 break;
                	 }
                 case SELECTSEAT:
                	 System.out.println("[Enter 0 to return]"); 
                	 return_choice = scan.next();
                	 if(return_choice.compareTo("0")==0) {
                		 state = bookMenuState.SELECTSHOWTIME; //go-back a step
                		 break;
                	 }
                	 try {
                		cs.selectSeat();
             			seatRow = cs.getseatRow();
             			seatCol = cs.getseatCol();
                	 }
                	 catch(Exception eselectseat) {
                		 state = bookMenuState.SELECTSEAT; 
                		 break;
                	 }
                 case CUSTOMERDETAILS:
	                System.out.println("[Enter 0 to return]"); 
	            	 return_choice = scan.next();
	            	 if(return_choice.compareTo("0")==0) {
	            		 state = bookMenuState.SELECTSEAT; //go-back a step
	            		 break;
	            	 }
	            	 try {
	            		cp.setCustomerDetails();
	         			c = cp.getCustomer();
	            	 }
	            	 catch(Exception ecust) {
	            		 System.out.println("Error entering customer details, please try again"); 
	            		 state = bookMenuState.CUSTOMERDETAILS; 
	            		 break;
	            	 }
                 case DISPLAYPRICE:
                	 System.out.println("[Enter 0 to return]"); 
	            	 return_choice = scan.next();
	            	 if(return_choice.compareTo("0")==0) {
	            		 state = bookMenuState.CUSTOMERDETAILS; //go-back a step
	            		 break;
	            	 }
	            	 try {
	            		 price = cp.getProjectedBookingPrice(cType, seatRow, selectedShowtimeID);
	         			System.out.printf("The cost of your booking is: %f %n", price);
	            	 }
	            	 catch(Exception eprice) {
	            		 System.out.println("Error in displaying, please try again"); 
	            		 state = bookMenuState.DISPLAYPRICE; 
	            		 break;
	            	 }
                 case CONFIRMBOOK:
                	 System.out.println("[Enter 0 to return]"); 
	            	 return_choice = scan.next();
	            	 if(return_choice.compareTo("0")==0) {
	            		 state = bookMenuState.DISPLAYPRICE; //go-back a step
	            		 break;
	            	 }
	            	try {
	                	System.out.println("Enter 1 to confirm booking, 0 to change selected seat, or -1 to moviegoer main menu");
	         			String bookConfirm = scan.nextLine();
	         			
	         			switch(bookConfirm) {
	         				case "1":{
	         					try { 
	         						if(bookingOption == 2 || bookingOption == 4) {
	         							showtimeHandler.bookCoupleSeat(selectedShowtimeID, seatRow, seatCol, c);
	         						}
	         						else {
	         							showtimeHandler.bookSeat(selectedShowtimeID, seatRow, seatCol, c);
	         						}
	         					}
	         					catch(Exception ebook) {//specific exceptions
	         						System.out.println("Error in booking seat.");
	         						continue; //to start of bookingDone do-while loop
	         					}
	         					bookingDone = true;
	         					System.out.println("\nYour booking has been made!\n");
	         					break;//only breaks out of inner switch case
	         				}
	         				case "0": 
	         					state = bookMenuState.SELECTSEAT;
	         					break;
	         				case "-1": 
	         					state = bookMenuState.END;
	         					break;
	         				default: 
	         					System.out.println("Erroneous value entered, going back to book options.");
	         					state = bookMenuState.CONFIRMBOOK;
	         					break;
	         				}//end switch
         			
	         			if(!bookingDone) {
		            		//state set to another value, break out of switch-case for next iteration
		            		break;
		            	}
	            	}//end try
	            	catch(Exception ebook) {
	            		 System.out.println("Error in booking, please try again"); 
	            		 state = bookMenuState.CONFIRMBOOK; 
	            		 break;
	            	 }
                 case END:
                	 System.out.println("Leaving movigoer view!");
                	 complete = true;
                	 break;
             }//end switch
         }//end while
     }//end bookMenu()
	
		
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void history() {
		Scanner scan = new Scanner(System.in);
		BookingManager hist = BookingManager.getInstance();
		System.out.println("Enter your email to see booking history");
		String email = scan.next();
		hist.printAllTransactionsForCustomer(email);
		System.out.println();
	}
}

