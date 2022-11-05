package showtimepackage;

import cinemapackage.ICinemaBooking;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;
import moviepackage.MovieType;

public interface IShowtimeSystem extends IShowtime {

	/**
	 * Set the base price of Showtime class
	 */
	void setBasePrice(float basePrice);
	/**
	 * Returns base price of Showtime class
	 */
	float getBasePrice();
	/**
	 * Create a new showtime object
	 * Checks if movie is "Preview" or "Now Showing" before creating entry
	 */
	void addShowtime(Movie movie, ICinemaBooking cinema, IDay day);
	/**
	 * When a movie status changes to "End of Showing" invoke this method to
	 * update all movie entires in showtime to stop booking for a showtime.
	 * Pass in the movieID which status has changed, Manager will set all showtimes with
	 * that MovieID to "End of Showing"
	 */
	void movieShowtimeEnd(int movieID);
	Day getDay(String dateString) throws IllegalArgumentException;

	void setMovieType(int movieID, MovieType type) throws IllegalArgumentException;
	void setMovieStatus(int movieID, MovieStatus status) throws IllegalArgumentException;
	void setMovieDirector(int movieID, String director) throws IllegalArgumentException;

	void setHoliday(Day day) throws IllegalArgumentException;
	void unsetHoliday(Day day) throws IllegalArgumentException;
	// TODO
	void changeShowtimeDay(int showtimeID, Day day) throws IllegalArgumentException;
}