package showtimepackage;
import java.util.Date;

import javax.naming.directory.SearchControls;

import cinemapackage.ICinemaBooking;
import customerpackage.Customer;
import daypackage.IDay;
import moviepackage.Movie;

public class Showtime implements IBooking{
	private Movie movie;
	private ICinemaBooking cinema;
	private static float basePrice;
	private IDay day;
	private int id;
	
	Showtime() {
		Showtime.basePrice = 10;
	}
	public void setID(int id){
		this.id = id;
	}
	public void printShowtime(){
		System.out.println("Movie is: " + movie.getMovieTitle() + "Cinema Code is: " + cinema.getCinemaCode() + "Day and time is: " + day.getDate());
	}
	public int getID() {
		return id;
	}
	public void printLayout() {
		// Cinema has this interface
		cinema.printCinemaLayout();
	}	
	public void removeBooking(int cinemaID, String seatRow, int seatCol){
		cinema.removeBooking(cinemaID, seatRow, seatCol);
	}
	public boolean isBooked(String seatRow, int seatCol){
		return cinema.isBooked(seatRow, seatCol);
	}
	public float getBasePrice(){
		return basePrice;
	}
	public void setBasePrice(float basePrice){
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
	public float getPrice() {
		// get multiplier from Movie
		float movieMultiplier = movie.getMultiplier();
		// get multiplier from Cinema
		float cinemaMultiplier = cinema.getMultiplier();
		return movieMultiplier + cinemaMultiplier;
	}
	@Override
	public void printSeats() {
		cinema.printCinemaLayout();
	}

}
