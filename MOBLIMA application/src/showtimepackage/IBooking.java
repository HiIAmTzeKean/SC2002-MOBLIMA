package showtimepackage;

import customerpackage.Customer;
import customerpackage.CustomerNullException;
import daypackage.IDay;

/**
 * Implemented by Showtime class
 * Used for interfacing by other classes with showtime object
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
	void bookSeat(String seatRow, int seatCol, int customerID)throws IllegalArgumentException;
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

	float getPrice(Customer customer, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException;

	float getPrice(Customer customer, boolean isCoupleSeat) throws IllegalArgumentException, CustomerNullException;

	float getPrice(Customer customer, boolean isCoupleSeat, String discountCodeTicket) throws IllegalArgumentException, CustomerNullException;

	/**
	 * Prints the seats of the cinema object composited within showtime
	 */
	void printSeat();
	void printShowtime();
	boolean isHoliday();
	String getMovieName();
	void changeDay(IDay day) throws IllegalArgumentException;
}