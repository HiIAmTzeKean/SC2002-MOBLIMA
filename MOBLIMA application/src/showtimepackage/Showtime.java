package showtimepackage;
import java.util.Date;

import javax.naming.directory.SearchControls;

import cinemapackage.ICinemaBooking;
import daypackage.IDay;
import moviepackage.Movie;

public class Showtime implements IBooking{
	

	private Movie movie;
	private ICinemaBooking cinema;
	private static float basePrice;
	private IDay day;
	
	Showtime() {
		basePrice = 10;
	}
	
	public void printLayout() {
		// Cinema has this interface
		cinema.printCinemaLayout();
	}	
	public void bookSeat(int cinemaID, String seatRow, int seatCol, Customer customer){
		cinema.bookSeat(cinemaID, seatRow, seatCol, customer);
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

}
