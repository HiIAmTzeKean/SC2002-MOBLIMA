package showtimepackage;

public interface IShowtime {
    void bookSeat(int showtimeID, String seatRow, int seatCol, int customerID);
	/**
	 * Check if the requested seat is booked
	 * @return
	 */
	boolean isBooked(int showtimeID, String seatRow, int seatCol);
	/**
	 * Gets price of the cost of watching the showtime
	 * Mulitpliers are obtained from composited objects and final price is obtained
	 * via encapsulated calculations
	 */
	float getPrice(int showtimeID);
    /**
     * Prints all showtimes that are still showing
     */
	void printShowtimes();
    /**
     * Prints the seat layout of the showtime
     * @param showtimeID
     */
	void printSeats(int showtimeID);
    /**
     * Prints movie object stored in showtime object
     * @param showtimeID
     */
    void printMovie(int showtimeID);
    void printCinema(int showtimeID);
    void printCineplex(int showtimeID);
    /**
     * Returns the cinema code of the showtime requested
     * @param showtimeID
     */
    String getCinemaCode(int showtimeID);
    String getCineplexName(int showtimeID);
    String getCineplexLocation(int showtimeID);
    /**
     * Returns Date string "DDMMYYYY"
     * @param showtimeID
     * @return
     */
    String getDate(int showtimeID);
    /**
     * Returns time string "2359" 24H clock format
     * @param showtimeID
     */
    String getTime(int showtimeID);
}