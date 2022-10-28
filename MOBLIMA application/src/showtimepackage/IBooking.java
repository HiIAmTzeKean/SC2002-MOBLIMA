package showtimepackage;

public interface IBooking{

	/**
	 * Books seat in showtime object
	 * @param seatRow
	 * @param seatCol
	 * @param customerID
	 */
	void bookSeat(String seatRow, int seatCol, int customerID);
	/**
	 * Check if the requested seat is booked
	 * @return
	 */
	boolean isBooked(String seatRow, int seatCol);
	/**
	 * Gets price of the cost of watching the showtime
	 * Mulitpliers are obtained from composited objects and final price is obtained
	 * via encapsulated calculations
	 */
	float getPrice();
	void printSeats();
}