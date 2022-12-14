package showtimepackage;
import daypackage.Day;
import cinemapackage.CinemaType;
import customerpackage.Customer;
import customerpackage.CustomerNullException;

/**
 * Superclass interface for showtime handling
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public interface IShowtime {
    void bookSeat(int showtimeID, String seatRow, int seatCol, Customer customer, String discountCodeTicket)throws IllegalArgumentException, CustomerNullException;
    void bookCoupleSeat(int showtimeID, String seatRow, int seatCol, Customer customer, boolean isCoupleSeat, String discountCodeTicket)throws IllegalArgumentException, CustomerNullException;
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
	float getPrice(int showtimeID, Customer customer) throws IllegalArgumentException, CustomerNullException;
    float getPrice(int showtimeID, Customer customer, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException;
    float getPrice(int showtimeID, Customer customer, boolean isCoupleSeat) throws IllegalArgumentException, CustomerNullException;
    float getPrice(int showtimeID, Customer customer, boolean isCoupleSeat, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException;
    /**
     * Prints the showtimes in admin view
     * ID, Showtime, 
     */
    void printShowtimeAdmin();
    /**
     * Prints all showtimes that are still showing
     */
	void printShowtimes() throws IllegalArgumentException;
    void printShowtimesByMovieName(String movieName) throws IllegalArgumentException;
    void printShowtimesByMovieNameAndCineplexID(String movieName, int cineplexID) throws IllegalArgumentException;
    /**
     * Prints the seat layout of the showtime
     * @param showtimeID
     */
	void printSeats(int showtimeID) throws IllegalArgumentException;
    /**
     * Prints movie object stored in showtime object
     * @param showtimeID
     * @throws IllegalArgumentException
     */
    void printMovie(int showtimeID) throws IllegalArgumentException;
    /**
     * Finds showtime object target by ID
     * Prints the cinema object composited in object
     * @param showtimeID
     * @throws IllegalArgumentException
     */
    void printCinemaLayout(int showtimeID) throws IllegalArgumentException;
    /**
     * Prints which cineplex the cinema object belongs to by calling Cinema.printCineplex() method
     * @param showtimeID
     * @throws IllegalArgumentException
     */
    void printCineplex(int showtimeID) throws IllegalArgumentException;
    /**
     * Returns the cinema code of the showtime requested
     * @param showtimeID
     * @throws IllegalArgumentException
     */
    String getCinemaCode(int showtimeID) throws IllegalArgumentException;
    /**
     * Returns Date string "DDMMYYYY"
     * @param showtimeID
     * @throws IllegalArgumentException
     * @return String
     */
    String getDate(int showtimeID) throws IllegalArgumentException;
    /**
     * Returns time string "2359" 24H clock format
     * @param showtimeID
     * @throws IllegalArgumentException
     */
    String getTime(int showtimeID) throws IllegalArgumentException;
    /**
     * Returns showtime object with the provided paramters to make modification to object stored in data file
     * @param movieName
     * @param day
     * @param cineplexID
     * @param cinemaType
     * @return Showtime
     * @throws IllegalArgumentException
     */
    Showtime getShowtime(String movieName, Day day, int cineplexID, CinemaType cinemaType) throws IllegalArgumentException;
}