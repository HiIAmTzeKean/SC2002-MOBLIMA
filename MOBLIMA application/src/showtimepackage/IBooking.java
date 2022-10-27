package showtimepackage;

public interface IBooking{

	void bookSeat(String seatRow, int seatCol, Customer customer);
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