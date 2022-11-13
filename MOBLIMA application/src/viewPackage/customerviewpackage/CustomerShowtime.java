package viewpackage.customerviewpackage;

import java.util.*;
import cinemapackage.CinemaType;
import cineplexpackage.Cineplex;
import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import cinemapackage.CinemaType;
import cinemapackage.SeatRowException;
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
	
	
	/** 
	 * @return String
	 */
	public String getseatRow() {
		return seatRow;
	}
	
	/** 
	 * @return int
	 */
	public int getseatCol() {
		return seatCol;
	}
	
	
	/** 
	 * @return CinemaType
	 */
	public CinemaType getCinemaType() {
		return selectedCinemaType;
	}
	
	
	/** 
	 * @return Showtime
	 */
	public Showtime getSelectedShowtime() {
		return selectedShowtime;
	}
	
	/** 
	 * @return int
	 */
	public int getSelectedShowtimeID() {
		return showtimeID;
	}

	
	/** 
	 * @param movieName
	 */
	public void setMovieName(String movieName){
		this.selectedMovieName = movieName;
	}
	
	public void printCineplexes(){
		ICineplex cineplexHandler = CineplexManager.getInstance();
		cineplexHandler.printCineplexes();
	}
	
	
	/** 
	 * @param ctname
	 */
	public void setCinemaTypeName(String ctname) {
		selectedCinemaTypeName = ctname;
	}
	
	
	/** 
	 * @param cineplexName
	 * @return int
	 */
	//returns cineplexID or -1 in case of an error
	
	public int chooseCineplexfromString(String cineplexName){
		ICineplex cineplexHandler = CineplexManager.getInstance();
		try {
			selectedCineplexObj = cineplexHandler.getCineplex(cineplexName); 
		}
		catch(IllegalArgumentException e) {
			selectedCineplexID = 0; //reset value
			return -1;
		}
		selectedCineplexID = selectedCineplexObj.getID();
		selectedCineplexName = selectedCineplexObj.getName();
		return selectedCineplexID;	
	}

	
	/** 
	 * @param cineplexName
	 * @return Boolean
	 */
	public Boolean isValidCineplexName(String cineplexName){
		ICineplex cineplexHandler = CineplexManager.getInstance();
		Cineplex toCheck = null;
		try{
			toCheck = cineplexHandler.getCineplex(cineplexName);
			if(toCheck != null) return true;
		}
		catch(IllegalArgumentException e){
			//System.out.println("Invalid Cineplex Name entered. Please try again");
			return false;
		}
		return false;
	}
	
	
	/** 
	 * @return int
	 */
	public int chooseCineplex() {
		ICineplex cineplexHandler = CineplexManager.getInstance();
		System.out.println("The Cineplexes are:");
		cineplexHandler.printCineplexes();
		System.out.println();
		System.out.println("Enter the name of the cineplex you have chosen:");
		try {
			selectedCineplexName = scan.nextLine();
		}
		catch(InputMismatchException e) {
			selectedCineplexID = 0; //reset value
			return -1;
		}
		System.out.println();
		
		try {
			selectedCineplexObj = cineplexHandler.getCineplex(selectedCineplexName); 
		}
		catch(IllegalArgumentException e) {
			selectedCineplexID = 0; //reset value
			return -1;
		}
		
		selectedCineplexID = selectedCineplexObj.getID();
		return selectedCineplexID;
	}
	
	
	
	/** 
	 * @param movieName
	 * @param cineplexName
	 */
	public void displayShowtimes(String movieName, String cineplexName){
		IShowtime showtimeHandler = ShowtimeManager.getInstance(); 	
		ICineplex cineplexHandler = CineplexManager.getInstance();		
		int cineplexID = cineplexHandler.getCineplex(cineplexName).getID();
		try{
			System.out.printf("Showtimes Available at %s for Movie %s:\n", cineplexName,movieName);
			//System.out.printf("\nFollowing are the showtimes available for movie %s at cineplex %s:\n", selectedMovieName, selectedCineplexName);
			showtimeHandler.printShowtimesByMovieNameAndCineplexID(movieName, cineplexID);
			System.out.println();	
		}
		catch(IllegalArgumentException e){
			throw e;
		}	
	}

	
	/** 
	 * @param cinemaType
	 * @return CinemaType
	 */
	public CinemaType isValidCinemaType(String cinemaType){
		CinemaType toReturn = null;
		try{
			toReturn = CinemaType.valueOf(cinemaType);
		}catch(IllegalArgumentException e){
			//System.out.println("Cinema Type Entered is Not Valid.");
		}
		return toReturn;
	}

	
	/** 
	 * @param dateString
	 * @param timeString
	 * @return Day
	 */
	public Day isValidDateTime(String dateString, String timeString){
		Day toReturn = null;
		try{
			toReturn = new Day(dateString,timeString);
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid Date Format Entered. Please try again.");
		}
		return toReturn;
	}


	
	/** 
	 * @throws IllegalArgumentException
	 */
	public void displayShowtimes() throws IllegalArgumentException{
		IShowtime showtimeHandler = ShowtimeManager.getInstance(); 	
			try {
				//selectedCineplexName = 
				System.out.print("\033[H\033[2J");
				System.out.printf("Showtimes Available at %s for Movie %s:\n", selectedCineplexName,selectedMovieName);
				//System.out.printf("\nFollowing are the showtimes available for movie %s at cineplex %s:\n", selectedMovieName, selectedCineplexName);
				showtimeHandler.printShowtimesByMovieNameAndCineplexID(selectedMovieName, selectedCineplexID);
				System.out.println();
			}
			catch(IllegalArgumentException e) {
				throw new IllegalArgumentException("No Showtimes exist for the Movie and Cineplex combination — please try again");
			}
	}// end of displayShowtimes
	
	
	/** 
	 * @return Showtime
	 */
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* sets selectedShowtime attribute by taking input of CinemaType, Date, and Time
	 * as viewed from the displayShowtimes function
	 * Returns Showtime object which is null in case of unsuccessful 
	 */
	public Showtime selectShowtime() {
		IShowtime showtimeHandler = ShowtimeManager.getInstance();
		//A) CinemaType
		boolean cinemaTypeScanContd = false;
		do {
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
				selectedCinemaType = CinemaType.SILVER;
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
			selectedDate = scan.nextLine();
		}while(selectedDate.length() != 8); 
		System.out.println();
		
		//convert string date to required format for Day constructor
		int dateInt = 0, dayNumber = 0, monthNumber = 0,yearNumber = 0;
		try {
			dateInt = Integer.parseInt(selectedDate);
		}
		catch (NumberFormatException edate){
	        edate.printStackTrace();
	    }	
		
		// reversing fullDate = Integer.toString(this.yearNumber) + monthString + dayString;
		dayNumber = dateInt%100;
		dateInt/=100;
		monthNumber = dateInt%100;
		dateInt/=100;
		yearNumber = dateInt;		
						
		//C) Time - implement input check if needed
		System.out.print("Time: ");
		selectedTime = scan.nextLine();
		System.out.println();	
			
		//CHECK for bugs
		try {			
		selectedDay = new Day(dayNumber, monthNumber, yearNumber, selectedTime);
			try {
				//add getShowtime to IShowtime interface
				selectedShowtime = showtimeHandler.getShowtime(selectedMovieName, selectedDay, selectedCineplexID, selectedCinemaType);
				showtimeID =  selectedShowtime.getID();
			}
			catch(IllegalArgumentException e) {
				System.out.println("Showtime is not found for details entered\n");
			}
		}
		catch(IllegalArgumentException eday) {
			System.out.println("Invalid date string supplied");
		}
					
		return selectedShowtime;
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

	
	/** 
	 * @param movieName
	 * @param showtimeDay
	 * @param cineplexName
	 * @param cinemaType
	 * @return Showtime
	 */
	public Showtime getShowtime(String movieName, Day showtimeDay, String cineplexName, CinemaType cinemaType){
		Showtime toReturn = null;	
		try {
			IShowtime showtimeHandler = ShowtimeManager.getInstance();
			ICineplex cineplexHandler = CineplexManager.getInstance();
			int cineplexID = cineplexHandler.getCineplex(cineplexName).getID();
			toReturn = showtimeHandler.getShowtime(movieName, showtimeDay, cineplexID, cinemaType);	
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Showtime not found.");	
		}
		return toReturn;	
	}

	
	/** 
	 * @param customerShowtime
	 * @param seatRow
	 * @param seatCol
	 * @return Boolean
	 * @throws IllegalArgumentException
	 */
	 public Boolean isValidSeatSelection(Showtime customerShowtime, String seatRow, int seatCol) throws IllegalArgumentException{
		try{
			int showtimeID = customerShowtime.getID();
			IShowtime showtimeHandler = ShowtimeManager.getInstance();
			if(!showtimeHandler.isBooked(showtimeID, seatRow, seatCol)){
				return true;	
			}
		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Seat selection is invalid.");
		}
		return false;
	}

}

