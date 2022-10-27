package showtimepackage;
import javax.naming.directory.SearchControls;

import cinemapackage.ICinemaBooking;
import cinemapackage.Seat;
public class Showtime{
	

	private Movie movie;
	private ICinemaBooking cinema;
	private Date time;
	private float basePrice;
	private Day day;
	
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
	public boolean IsBooked(String seatRow, int seatCol){
		cinema.IsBooked(seatRow, seatCol);
	}
	public float getBasePrice(){
		return basePrice;
	}
	public float setBasePrice(float basePrice){
		this.basePrice = basePrice;
	}
	
	public String getMovieName()
	{
		return movie.getMovieTitle();
	}
	
	public Date time()
	{
		return this.time;
	}

}
