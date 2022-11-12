package showtimepackage;

import customerpackage.Customer;
import customerpackage.CustomerNullException;
import daypackage.IDay;

/**
 * Implemented by Showtime class
 * Used for interfacing by other classes with showtime object
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public interface IBooking {
	/**
	 * Books seat in showtime object
	 * 
	 * @param seatRow
	 * @param seatCol
	 * @param customerID
	 */
	void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException;

	/**
	 * Allows for booking of couple seat instead of a single seat booking
	 * 
	 * @param seatRow
	 * @param seatCol
	 * @param customerID
	 * @throws IllegalArgumentException
	 */
	void bookCoupleSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException;

	/**
	 * Check if the requested seat is booked
	 * 
	 * @return
	 */
	boolean isBooked(String seatRow, int seatCol);

	/**
	 * Gets price of the cost of watching the showtime
	 * Mulitpliers are obtained from composited objects and final price is obtained
	 * through summing the multipiers with the customer multiplier in customer
	 * object
	 * 
	 * @param customer Objects which provides the multiplier
	 * @throws IllegalArgumentException
	 */
	float getPrice(Customer customer) throws IllegalArgumentException, CustomerNullException;

	/**
	 * Another get price mechanism used when there is a discount code available
	 * 
	 * @param customer
	 * @param discountCodeTicket
	 * @return
	 * @throws IllegalArgumentException
	 * @throws CustomerNullException
	 */
	float getPrice(Customer customer, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException;

	/**
	 * Another get price mechanism when couple seating is chosen
	 * 
	 * @param customer
	 * @param isCoupleSeat
	 * @return
	 * @throws IllegalArgumentException
	 * @throws CustomerNullException
	 */
	float getPrice(Customer customer, boolean isCoupleSeat) throws IllegalArgumentException, CustomerNullException;

	/**
	 * Another get price mechanism for when couple seating and discount code ticket
	 * is available.
	 * 
	 * @param customer
	 * @param isCoupleSeat
	 * @param discountCodeTicket
	 * @return
	 * @throws IllegalArgumentException
	 * @throws CustomerNullException
	 */
	float getPrice(Customer customer, boolean isCoupleSeat, String discountCodeTicket)
			throws IllegalArgumentException, CustomerNullException;

	/**
	 * Prints the seats of the cinema object composited within showtime
	 */
	void printSeat();

	/**
	 * Prints the showtime in a formatted manner
	 * 
	 */
	void printShowtime();

	/**
	 * Returns boolean if the showtime falls on a holiday
	 * 
	 * @return boolean
	 */
	boolean isHoliday();

	/**
	 * Returns the movie name of the showtime object
	 * 
	 * @return String movie name
	 */
	String getMovieName();

	/**
	 * Changes the day object in the showtime object. Allows for update to the date and time of showtime.
	 * 
	 * @param day
	 * @throws IllegalArgumentException
	 */
	void changeDay(IDay day) throws IllegalArgumentException;
}