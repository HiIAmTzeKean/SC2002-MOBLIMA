package customerpackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import showtimepackage.Showtime;

/**
 * Controller class for bookings
 * Maintains an array of bookings for Staff or Customer to use
 * 
 * @apiNote Booking
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class BookingManager {
	private static ArrayList<Booking> bookings;
	private static BookingManager bookingManager;

	private BookingManager() {
		// Deseralise all objects here
		bookings = new ArrayList<Booking>();

	}

	private BookingManager(ArrayList<Booking> bookings) {
		BookingManager.bookings = bookings;
	}

	
	/** 
	 * @param filename
	 * @return ArrayList<Booking>
	 */
	private static ArrayList<Booking> deseraliseBookings(String filename) {
		ArrayList<Booking> c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Booking>) in.readObject();
			in.close();
		} catch (IOException i) {
			// i.printStackTrace();
			// The reason why i return an empty list is because this error occurs
			// when the binary file is empty (No object state as it is a fresh file)
			// or the file cannot be read for whatever reason
			return new ArrayList<Booking>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	
	/** 
	 * @param filename
	 * @param c
	 */
	private static void seraliseBookings(String filename, ArrayList<Booking> c) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	
	/** 
	 * @return BookingManager
	 */
	public static BookingManager getInstance() {
		if (BookingManager.bookingManager == null) {
			ArrayList<Booking> c = BookingManager.deseraliseBookings("./MOBLIMA application/data/booking/booking.dat");
			BookingManager.bookingManager = new BookingManager(c);
			return BookingManager.bookingManager;
		}
		return BookingManager.bookingManager;
	}

	public static void close() {
		BookingManager.seraliseBookings("./MOBLIMA application/data/booking/booking.dat", bookings);
		BookingManager.bookingManager = null;
	}

	/**
	 * Iterates booking entries and prints the entrys that matches
	 * customer email. We assume email to be unique idetifier for customer
	 * 
	 * @param customerEmail
	 */
	public void printAllTransactionsForCustomer(String customerEmail) throws IllegalArgumentException{
		System.out.println("|---------------------------------------------------- Transaction History ---------------------------------------------------|");
		System.out.printf("| Customer email: %-30s                                                                             |\n",
				customerEmail);
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-15s   |  %-40s  |  %-15s  | %-8s |  %-5s | %-15s |\n",
				"Transaction ID", "Movie Name", "Cinema Type",
				"Date", "Price", "Couple Seating");
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------|");
		boolean found = false;
		for (Iterator<Booking> it = bookings.iterator(); it.hasNext();) {
			Booking b = it.next();
			if (b.getCustomerEmail().equals(customerEmail)) {
				b.print();
				found = true;
			}
		}
		System.out.println("|--------------------------------------------------------- END --------------------------------------------------------------|");
		if (!found){
			throw new IllegalArgumentException("Email provided does not have any booking history!");
		}
	}

	/**
	 * Add a new booking entry into manager
	 * 
	 * @param transactionID
	 * @param day
	 * @param price
	 * @param customer
	 */
	public void addBooking(String transactionID, Showtime showtime, float price, Customer customer, String seatRow,
			int seatCol) {
		bookings.add(new Booking(transactionID, showtime, price, customer, seatRow, seatCol));
	}
	public void addBooking(String transactionID, Showtime showtime, float price, Customer customer, String seatRow,
			int seatCol, boolean coupleSeating) {
		bookings.add(new Booking(transactionID, showtime, price, customer, seatRow, seatCol,coupleSeating));
	}

	/**
	 * Delete booking function should only be used once other classes
	 * has removed the booking of the seat
	 * 
	 * @param transactionID
	 */
	public void deleteBooking(String transactionID) {
		for (Iterator<Booking> it = bookings.iterator(); it.hasNext();) {
			Booking b = it.next();
			if (b.getCustomerEmail() == transactionID) {
				b.print();
			}
		}
		throw new IllegalAccessError("Booking is not found");
	}
}
