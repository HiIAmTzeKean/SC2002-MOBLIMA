package showtimepackage;

import java.io.Serializable;

import cinemapackage.ICinemaBooking;
import cineplexpackage.CineplexManager;
import customerpackage.Customer;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;

public class Showtime implements IBooking, Serializable{
	private static final long serialVersionUID = 6266710308272298089L;
	private Movie movie;
	private ICinemaBooking cinema;
	private static float basePrice;
	private IDay day;
	private int id;
	
	Showtime() {
		Showtime.basePrice = 10;
	}
	Showtime(Movie movie, ICinemaBooking cinema, IDay day, int id) {
		this.movie = movie;
		this.cinema = cinema;
		this.day = day;
		this.id = id;
	}
	public void setID(int id){
		this.id = id;
	}
	public void printShowtime(){
		System.out.println("Movie is: " + movie.getMovieTitle() + "Cinema Code is: " + cinema.getCinemaCode() + "Day and time is: " + day.getDate());
	}
	public void printShowtimeAdmin(){
		System.out.println("ShowtimeID: "+ id +"\tMovie is: " + movie.getMovieTitle() + "\tCinema Code is: " + cinema.getCinemaCode() + "\tDate: " + day.getDate() + "\tTime: "+day.getTime());
	}
	public String getTime(){
		return day.getTime();
	}
	public String getDate() {
		return day.getDate();
	}
	public int getID() {
		return id;
	}
	public Day getDayObject(){
		return (Day)day;
	}
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
	public String getCinemaCode(){
		return cinema.getCinemaCode();
	}
	public int getMovieID() {
		return movie.getID();
	}
	public void setMovieStatus(MovieStatus status){
		movie.setMovieStatus(status);
	}
	public void removeBooking(int cinemaID, String seatRow, int seatCol){
		cinema.removeBooking(cinemaID, seatRow, seatCol);
	}
	public boolean isBooked(String seatRow, int seatCol){
		return cinema.isBooked(seatRow, seatCol);
	}
	public static float getBasePrice(){
		return Showtime.basePrice;
	}
	public static void setBasePrice(float basePrice){
		Showtime.basePrice = basePrice;
	}
	public String getMovieName()
	{
		return movie.getMovieTitle();
	}
	@Override
	public void bookSeat(String seatRow, int seatCol, int customerID) {
		System.out.println("===== Seat booking in progress =====");

		if (cinema.isBooked(seatRow, seatCol)){
			System.out.println("Seat is already booked, please choose another seat");
		}
		else{
			cinema.bookSeat(seatRow, seatCol, customerID);
			System.out.println("Successfully booked seat");
		}
		System.out.println("===== Seat booking finish =====");
	}
	@Override
	public float getPrice(Customer customer) {
		// get multiplier from Movie
		float movieMultiplier = (float)movie.getMultiplier();
		// get multipler from customer
		float customerMulitplier = customer.getMultiplier();
		// get multiplier from Cinema
		float cinemaMultiplier = cinema.getMultiplier();
		return basePrice * (movieMultiplier + cinemaMultiplier + customerMulitplier);
	}
	@Override
	public void printSeat() {
		cinema.printCinemaLayout();
	}
}
