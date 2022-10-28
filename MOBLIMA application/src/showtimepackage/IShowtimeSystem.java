package showtimepackage;

import java.util.Date;

import cinemapackage.ICinemaBooking;
import daypackage.IDay;
import moviepackage.Movie;

public interface IShowtimeSystem extends IShowtime {

	/**
	 * Set the base price of Showtime class
	 */
	void setBasePrice(float basePrice);
	/**
	 * Returns base price of Showtime class
	 */
	void getBasePrice();
	/**
	 * Create a new showtime object
	 * Checks if movie is "Preview" or "Now Showing" before creating entry
	 */
	void addShowtime(int movieID, int CinemaID,IDay day);
	/**
	 * When a movie status changes to "End of Showing" invoke this method to
	 * update all movie entires in showtime to stop booking for a showtime
	 */
	void movieShowtimeEnd(int movieID);
}