package viewPackage.customerpackage;

import java.util.*;
import cinemapackage.CinemaType;
import cineplexpackage.Cineplex;
import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import cinemapackage.CinemaType;
import daypackage.Day;
import moviepackage.IMovie;
import moviepackage.Movie;
import moviepackage.MovieManager;
import showtimepackage.IShowtime;
import showtimepackage.Showtime;
import showtimepackage.ShowtimeManager;

public class CustomerShowtime {
	//attributes used by displayShowtimes()
	private String selectedCineplexName = null;
	private Cineplex selectedCineplexObj = null;
	private int selectedCineplexID = 0;
	private String selectedMovieName = null;
	private Movie selectedMovieObj = null;
	
	//attributes used by selectShowtime()
	private String selectedCinemaTypeName = null;
	private String selectedDate = null;
	private String selectedTime = null;
	private CinemaType selectedCinemaType = null;
	private Day selectedDay = null;
	private Showtime selectedShowtime = null;
	private int showtimeID = 0;
	
	//attributes used by selectSeat()
	String seatRow = null;
	int seatCol = 0;
	
	private static Scanner scan = new Scanner(System.in);
	
	public String getseatRow() {
		return seatRow;
	}
	public int getseatCol() {
		return seatCol;
	}
	
	public CinemaType getCinemaType() {
		return selectedCinemaType;
	}
	
	public Showtime getSelectedShowtime() {
		return selectedShowtime;
	}
	public int getSelectedShowtimeID() {
		return showtimeID;
	}
	
	/* 
	 *display menu to interact with the cineplexes and movies available, and 
	 *view all possible showtime for a combination.
	 */
	public void displayShowtimes() {

		ICineplex cineplexHandler = CineplexManager.getInstance();
		IMovie movieFindHandler = MovieManager.getInstance(); 
		IShowtime showtimeHandler = ShowtimeManager.getInstance(); 
		
		boolean showtime_selection_contd = true;
		//loop until a user selects a cineplex and movie combination for which non-zero showtimes exist.
		do {
			//STEP A - CHOOSE CINEPLEX
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
			
			//STEP B - CHOOSE MOVIE
			CustomerMovieListing movSelectObj = new CustomerMovieListing();
			movSelectObj.movieSelection(); //runs the movie selection menu
			selectedMovieName = movSelectObj.getSelectedMovieName();
			try {
				selectedMovieObj =  movieFindHandler.findMoviebyName(selectedMovieName); 
				selectedMovieObj.printMovieComplete();
			}
			catch(IllegalArgumentException e) {
				System.out.println("Movie is not found — please try again");
				continue; //to start of outer do-while loop
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//STEP C - Display showtimes for the movie and cineplex combination (atleast one showtime should exist)
			
			try {
				System.out.printf("\nFollowing are the showtimes available for movie %s at cineplex %s:\n", selectedMovieName, selectedCineplexName);
				showtimeHandler.printShowtimesByMovieNameAndCineplexID(selectedMovieName, selectedCineplexID);
				System.out.println();
			}
			catch(IllegalArgumentException e) {
				System.out.println("No Showtimes exist for the Movie and Cineplex combination — please try again");
				continue; //to start of outer do-while loop
			}
			
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
			
		}while(showtime_selection_contd);
		
		CineplexManager.close();
		MovieManager.close();
		ShowtimeManager.close();
	}// end of displayShowtimes
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* sets selectedShowtime attribute by taking input of CinemaType, Date, and Time
	 * as viewed from the displayShowtimes function
	 */
	public void selectShowtime() {
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		
		do {
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
				case "Silver": 
					selectedCinemaType = CinemaType.SILVER; //update to SILVER in CinemaType
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
					//add getShowtime to IShowtime interface
					selectedShowtime = showtimeHandler.getShowtime(selectedMovieName, selectedDay, selectedCineplexID, selectedCinemaType);
					showtimeID =  selectedShowtime.getID();
				}
				catch(IllegalArgumentException e) {
					System.out.println("Showtime is not found for details entered");
					System.out.println();
				}
		}while(selectedShowtime == null);
		
		ShowtimeManager.close();
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* seat selection — picking row and column based on layout for selected showtime
	 */
	public void selectSeat() {
		IShowtime showtimeHandler = ShowtimeManager.getInstance(); 
		try{
			showtimeHandler.printCinemaLayout(showtimeID);
			System.out.println();
		}
		catch(IllegalArgumentException eshowtime){ //unlikely as check for showtime object has be done already
			System.out.println("Showtime is not found");
		}
			
		//INPUT CHECKS for row and column at this end? — range of 'allowed' values depends on layout
		System.out.println("Based on the layout shown, enter the row and column for the seat you wish to select");
		System.out.print("Row(letter) :");
		seatRow = scan.next();
		System.out.println();
		System.out.print("Column(number) :");
		seatCol = scan.nextInt();
		System.out.println();
	}
	
}
