package showtimepackage;

import cinemapackage.ICinemaBooking;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;
import moviepackage.MovieType;

/**
 * Subclass that inherits from IShowtime.
 * Implemented by ShowtimeManager
 * @apiNote IShowtime
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public interface IShowtimeSystem extends IShowtime {
	/**
	 * Set the base price of Showtime class
	 * @param basePrice
	 */
	void setBasePrice(float basePrice);
	/**
	 * Returns base price of Showtime class
	 * @return float baseprice of showtime
	 */
	float getBasePrice();
	/**
	 * Create a new showtime object
	 * Checks if movie is "Preview" or "Now Showing" before creating entry
	 * @param movie
	 * @param cinema
	 * @param day
	 * @throws IllegalArgumentException
	 */
	void addShowtime(Movie movie, ICinemaBooking cinema, IDay day) throws IllegalArgumentException;
	/**
	 * When a movie status changes to "End of Showing" invoke this method to
	 * update all movie entires in showtime to stop booking for a showtime.
	 * Pass in the movieID which status has changed, Manager will set all showtimes with
	 * that MovieID to "End of Showing"
	 * @param movieID
	 */
	void movieShowtimeEnd(int movieID);
	Day getDay(String dateString) throws IllegalArgumentException;
	/**
	 * Updates a MovieType in showtime
	 * Function should be invoked with update MovieType from MovieManager
	 * @param movieID
	 * @param type
	 */
	void setMovieType(int movieID, MovieType type);
	void setMovieStatus(int movieID, MovieStatus status);
	void setMovieDirector(int movieID, String director);
	/**
	 * Sets all showtimes with the given day object to a holiday
	 * Only takes in the date of the day provdied for checking
	 * @param day
	 * @throws IllegalArgumentException
	 */
	void setHoliday(Day day) throws IllegalArgumentException;
	/**
	 * Unsets holiday in all showtimes with the given day object
	 * Only takes in the date of the day provdied for checking
	 * @param day
	 * @throws IllegalArgumentException
	 */
	void unsetHoliday(Day day) throws IllegalArgumentException;
	/**
	 * Changes a given ShowtimeID Day attribute
	 * Allows for update to the date or time of the movie
	 * @param showtimeID
	 * @param day
	 * @throws IllegalArgumentException
	 */
	void changeShowtimeDay(int showtimeID, Day day) throws IllegalArgumentException;
}