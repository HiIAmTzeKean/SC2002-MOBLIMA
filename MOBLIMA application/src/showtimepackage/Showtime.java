package showtimepackage;

import java.io.Serializable;

import cinemapackage.CinemaType;
import cinemapackage.ICinemaBooking;
import cineplexpackage.CineplexManager;
import customerpackage.Customer;
import customerpackage.CustomerNullException;
import customerpackage.DiscountCode;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;


/**
 * Showtime class that stores a showtime instance
 * @apiNote IBooking
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Showtime implements IBooking, Serializable{
	private static final long serialVersionUID = 6266710308272298089L;
	private Movie movie;
	private ICinemaBooking cinema;
	private static float basePrice = 8.5f;
	private IDay day;
	private int id;
	
	Showtime() {
		if (Showtime.basePrice == 0f){
			Showtime.basePrice = 8.5f;
		}
	}
	Showtime(Movie movie, ICinemaBooking cinema, IDay day, int id) {
		this();
		this.movie = movie;
		this.cinema = cinema;
		this.day = day;
		this.id = id;
	}
	
	/** 
	 * @param id
	 */
	public void setID(int id){
		this.id = id;
	}
	@Override
	public void printShowtime(){
		System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
						movie.getMovieStatus().toString(),
						movie.getMovieTitle(),
						cinema.getCinemaClass(),
						day.getDate(),
						day.getTime(),
						day.isHoliday()?"YES":"NO");
	}
	public void printShowtimeAdmin(){
		System.out.printf("|  %-12s|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
						id,				
						movie.getMovieStatus().toString(),
						movie.getMovieTitle(),
						cinema.getCinemaClass(),
						day.getDate(),
						day.getTime(),
						day.isHoliday()?"YES":"NO");
	}
	
	/** 
	 * @return String
	 */
	public String getTime(){
		return day.getTime();
	}
	public void setHoliday(){
		day.setHoliday();
	}
	public void unsetHoliday(){
		day.removeHoliday();
	}
	
	/** 
	 * @return boolean
	 */
	public boolean isHoliday(){
		return day.isHoliday();
	}
	
	/** 
	 * @return String
	 */
	public String getDate() {
		return day.getDate();
	}
	
	/** 
	 * @return int
	 */
	public int getID() {
		return id;
	}
	
	/** 
	 * @return Day
	 */
	public Day getDayObject(){
		return (Day)day;
	}
	
	/** 
	 * @return CinemaType
	 */
	public CinemaType getCinemaType(){
		return cinema.getCinemaType();
	}
	
	/** 
	 * @return int
	 */
	public int getCineplexID(){
		return cinema.getCineplexID();
	}
	
	/** 
	 * @return Movie
	 */
	public Movie getMovieObject(){
		return movie;
	}
	public void printMovie() {
		movie.printMovieComplete();
	}
	public void printLayout() {
		// Cinema has this interface
		cinema.printCinemaLayout();
	}
	public void printCineplex(){
		int cineplexID = cinema.getCineplexID();
		CineplexManager cm = CineplexManager.getInstance();
		cm.getCineplex(cineplexID).printCineplex();
	}
	
	/** 
	 * @return String
	 */
	public String getCinemaCode(){
		return cinema.getCinemaCode();
	}
	
	/** 
	 * @return String
	 */
	public String getCinemaClass(){
		return cinema.getCinemaClass();
	}
	
	/** 
	 * @return int
	 */
	public int getMovieID() {
		return movie.getID();
	}
	
	/** 
	 * @return MovieStatus
	 */
	public MovieStatus getMovieStatus(){
		return movie.getMovieStatus();
	}
	
	/** 
	 * @param status
	 */
	public void setMovieStatus(MovieStatus status){
		movie.setMovieStatus(status);
	}
	
	/** 
	 * @param cinemaID
	 * @param seatRow
	 * @param seatCol
	 */
	public void removeBooking(int cinemaID, String seatRow, int seatCol){
		cinema.removeBooking(cinemaID, seatRow, seatCol);
	}
	
	@Override
	public boolean isBooked(String seatRow, int seatCol){
		return cinema.isBooked(seatRow, seatCol);
	}
	
	/** 
	 * @return float
	 */
	public static float getBasePrice(){
		return Showtime.basePrice;
	}
	
	/** 
	 * @param basePrice
	 */
	public static void setBasePrice(float basePrice){
		Showtime.basePrice = basePrice;
	}
	
	@Override
	public String getMovieName()
	{
		return movie.getMovieTitle();
	}
	
	@Override
	public void changeDay(IDay day) throws IllegalArgumentException{
		if (day==null) throw new IllegalArgumentException("Day object null");
		this.day = day;
	}
	
	@Override
	public void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		System.out.println("===== Seat booking in progress =====");

		if (cinema.isBooked(seatRow, seatCol)){
			System.out.println("Seat is already booked, please choose another seat");
			throw new IllegalArgumentException("Seat is already booked");
		}
		else{
			cinema.bookSeat(seatRow, seatCol, customerID);
			System.out.println("Successfully booked seat");
		}
		System.out.println("===== Seat booking finish =====");
	}
	
	@Override
	public void bookCoupleSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		System.out.println("===== Seat booking in progress =====");

		if (cinema.isBooked(seatRow, seatCol)){
			System.out.println("Seat is already booked, please choose another seat");
			throw new IllegalArgumentException("Seat is already booked");
		}
		else if (cinema.getCinemaType() != CinemaType.PLATINUM) {
			throw new IllegalArgumentException("Only Platinum class allow for couple seat booking");
		}
		else{
			cinema.bookSeat(seatRow, seatCol, customerID);
			System.out.println("Successfully booked seat");
		}
		System.out.println("===== Seat booking finish =====");
	}
	
	@Override
	public float getPrice(Customer customer) throws IllegalArgumentException, CustomerNullException {
		if (customer == null) throw new CustomerNullException();
		try{
			float movieMultiplier = (float)movie.getMultiplier();
			float customerMulitplier = customer.getMultiplier();
			float cinemaMultiplier = cinema.getMultiplier();
			float dayMultiplier = day.getDayMultiplier();
			return basePrice * (movieMultiplier * cinemaMultiplier * customerMulitplier * dayMultiplier);
		}
		catch (IllegalArgumentException e){
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public float getPrice(Customer customer, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException{
		try{
			DiscountCode manager = DiscountCode.getInstance();
			float discountMultiplier = manager.getMultiplier(discountCodeTicket);
			DiscountCode.close();
			return getPrice(customer) * discountMultiplier;
		}
		catch (IllegalArgumentException e){
			//e.printStackTrace();
			throw e;
		}
		catch (CustomerNullException ex){
			throw ex;
		}
	}
	
	@Override
	public float getPrice(Customer customer, boolean isCoupleSeat, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException{
		if (isCoupleSeat) {
			return getPrice(customer, discountCodeTicket) * 2;
		}
		else{
			return getPrice(customer, discountCodeTicket);
		}
		
	}
	
	/** 
	 * @param customer
	 * @param isCoupleSeat
	 * @return float
	 * @throws IllegalArgumentException
	 * @throws CustomerNullException
	 */
	@Override
	public float getPrice(Customer customer, boolean isCoupleSeat) throws IllegalArgumentException, CustomerNullException{
		if (isCoupleSeat)
			return getPrice(customer) * 2;
		else
			return getPrice(customer);
	}
	@Override
	public void printSeat() {
		cinema.printCinemaLayout();
	}
	
}
