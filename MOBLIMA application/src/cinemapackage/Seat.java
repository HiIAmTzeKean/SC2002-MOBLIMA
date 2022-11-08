package cinemapackage;

import java.io.Serializable;

/**
 * Seat object that stores an instance of Seat details
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Seat implements Serializable {
	private boolean isBook;
	private int x;
	private int y;
	private int customerID;

	/**
	 * Default constructor for seat
	 * 
	 * @param x
	 * @param y
	 */
	public Seat(int x, int y) {
		this.x = x;
		this.y = y;
		isBook = false;
		customerID = -1;
	}

	/**
	 * Returns the row number
	 * 
	 * @return int
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the col number
	 * 
	 * @return int
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns if the seat has been booked
	 * 
	 * @return boolean
	 */
	public boolean isBooked() {
		return isBook;
	}

	/**
	 * Setter to set the seat to be booked. Stores the customer ID inforamtion for
	 * tracking.
	 * 
	 * @param customerID
	 */
	public void setBooked(int customerID) {
		isBook = true;
		this.customerID = customerID;
		System.out.println("Seat " + x + "," + y + " is booked.");
	}

	/**
	 * Removes booking made by unsetting variables
	 * 
	 */
	public void setUnBooked() {
		isBook = false;
		customerID = 0;
		System.out.println("Seat " + x + "," + y + " is unbooked.");
	}

	/**
	 * Returns the customerID. 0 will be returned if no customer has booked seat
	 * 
	 * @return
	 */
	public int getCustomerID() {
		return this.customerID;
	}

	/**
	 * Sets the customerID for the seat. Used when ID should be changed for the seat
	 * 
	 * @param customerID
	 */
	public void setCustomer(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Print function to print layout of seat.
	 * 
	 */
	public void print() {
		System.out.printf("| %d |", isBook ? 1 : 0);
	}

}