package showtimepackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import cinemapackage.CinemaType;
import cinemapackage.ICinemaBooking;
import customerpackage.BookingManager;
import customerpackage.Customer;
import customerpackage.CustomerNullException;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieManager;
import moviepackage.MovieStatus;
import moviepackage.MovieType;

/**
 * Controller for Showtime object
 * 
 * @apiNote IShowtimeSystem
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class ShowtimeManager implements IShowtimeSystem {
	private static ArrayList<Showtime> showtimes;
	private static ShowtimeManager showtimeManager;
	private static int lastID;

	private ShowtimeManager() {
		lastID = 0;
	}

	private ShowtimeManager(ArrayList<Showtime> showtimeArray) {
		ShowtimeManager.showtimes = showtimeArray;
		lastID = showtimes.size();
	}

	private static ArrayList<Showtime> deseraliseShowtimes(String filename) {
		ArrayList<Showtime> c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Showtime>) in.readObject();
			in.close();
		} catch (IOException i) {
			// i.printStackTrace();
			return new ArrayList<Showtime>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static void seraliseShowtimes(String filename, ArrayList<Showtime> c) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static ShowtimeManager getInstance() {
		if (ShowtimeManager.showtimeManager == null) {
			ArrayList<Showtime> c = ShowtimeManager
					.deseraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat");
			ShowtimeManager.showtimeManager = new ShowtimeManager(c);
			return ShowtimeManager.showtimeManager;
		}
		return ShowtimeManager.showtimeManager;
	}

	public static void close() {
		if (ShowtimeManager.showtimeManager != null) {
			ShowtimeManager.seraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat", showtimes);
		}
		ShowtimeManager.showtimeManager = null;
	}

	/**
	 * Function used to create test data. Used with admin rights to bypass all checks to add a new showtime object.
	 * 
	 * @param movie
	 * @param cinema
	 * @param day
	 */
	public void addShowtimeSystem(Movie movie, ICinemaBooking cinema, IDay day) {
		ShowtimeManager.showtimes.add(new Showtime(movie, cinema, day, ++lastID));
	}

	/**
	 * Function used to create test data. Used with admin rights to obtain showtime object via its ID.
	 * 
	 * @param showtimeID
	 * @return
	 */
	public Showtime getShowtimeByID(int showtimeID) {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getID() == showtimeID) {
				return s;
			}
		}
		throw new IllegalArgumentException("Showtime is not found");
	}

	@Override
	public Showtime getShowtime(String movieName, Day day, int cineplexID, CinemaType cinemaType)
			throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieName().equals(movieName) && s.getDayObject().equals(day) && s.getCineplexID() == cineplexID
					&& s.getCinemaType() == cinemaType) {
				return s;
			}
		}
		throw new IllegalArgumentException("Showtime is not found");
	}

	/**
	 * Returns the index of the showtime object in the array
	 * 
	 * @param showtimeID
	 * @return
	 * @throws IllegalArgumentException
	 */
	protected int getShowtimeIndex(int showtimeID) throws IllegalArgumentException {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No showtime exist");
		}
		int count = 0;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			if (it.next().getID() == showtimeID) {
				return count;
			}
			count++;
		}
		// Not found
		throw new IllegalArgumentException("Showtime is not found");
	}

	/**
	 * Allows booking of seat without normal functional checks that will be done for a normal user.
	 * Assumes there is privilege access given.
	 * 
	 * @param showtimeID
	 * @param seatRow
	 * @param seatCol
	 * @param customer
	 * @throws IllegalArgumentException
	 */
	public void bookSeatAdmin(int showtimeID, String seatRow, int seatCol, Customer customer)
			throws IllegalArgumentException {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customer.getID());
			BookingManager bookingManager = BookingManager.getInstance();

			bookingManager.addBooking(showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode() +
					showtimes.get(getShowtimeIndex(showtimeID)).getDate() +
					showtimes.get(getShowtimeIndex(showtimeID)).getTime(),
					showtimes.get(getShowtimeIndex(showtimeID)),
					showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer),
					customer,
					seatRow,
					seatCol);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in booking seat");
		} catch (CustomerNullException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void bookSeat(int showtimeID, String seatRow, int seatCol, Customer customer, String discountCodeTicket)
			throws IllegalArgumentException, CustomerNullException {
		try {
			Showtime s = showtimes.get(getShowtimeIndex(showtimeID));
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING ||
					s.getMovieStatus() != MovieStatus.COMING_SOON) {
				showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customer.getID());
				BookingManager bookingManager = BookingManager.getInstance();

				if (discountCodeTicket == null){
					bookingManager.addBooking(showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode() +
						showtimes.get(getShowtimeIndex(showtimeID)).getDate() +
						showtimes.get(getShowtimeIndex(showtimeID)).getTime(),
						showtimes.get(getShowtimeIndex(showtimeID)),
						showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer),
						customer,
						seatRow,
						seatCol);
				}
				else {
					bookingManager.addBooking(showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode() +
						showtimes.get(getShowtimeIndex(showtimeID)).getDate() +
						showtimes.get(getShowtimeIndex(showtimeID)).getTime(),
						showtimes.get(getShowtimeIndex(showtimeID)),
						showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer, discountCodeTicket),
						customer,
						seatRow,
						seatCol);
				}

			} else {
				throw new IllegalArgumentException("Error in booking seat");
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (CustomerNullException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public void bookCoupleSeat(int showtimeID, String seatRow, int seatCol, Customer customer, boolean isCoupleSeat, String discountCodeTicket)
			throws IllegalArgumentException, CustomerNullException {
		try {
			Showtime s = showtimes.get(getShowtimeIndex(showtimeID));
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING ||
					s.getMovieStatus() != MovieStatus.COMING_SOON) {
				showtimes.get(getShowtimeIndex(showtimeID)).bookCoupleSeat(seatRow, seatCol, customer.getID());
				
				BookingManager bookingManager = BookingManager.getInstance();
				
				if (discountCodeTicket == null){
					bookingManager.addBooking(showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode() +
					showtimes.get(getShowtimeIndex(showtimeID)).getDate() +
					showtimes.get(getShowtimeIndex(showtimeID)).getTime(),
					showtimes.get(getShowtimeIndex(showtimeID)),
					showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer,isCoupleSeat),
					customer,
					seatRow,
					seatCol,
					true);
				}
				else {
					bookingManager.addBooking(showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode() +
						showtimes.get(getShowtimeIndex(showtimeID)).getDate() +
						showtimes.get(getShowtimeIndex(showtimeID)).getTime(),
						showtimes.get(getShowtimeIndex(showtimeID)),
						showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer,isCoupleSeat,discountCodeTicket),
						customer,
						seatRow,
						seatCol,
						true);
				}
				
			} else {
				throw new IllegalArgumentException("Error in booking seat");
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (CustomerNullException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public boolean isBooked(int showtimeID, String seatRow, int seatCol) throws IllegalArgumentException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).isBooked(seatRow, seatCol);
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
	}

	@Override
	public float getPrice(int showtimeID, Customer customer) throws IllegalArgumentException, CustomerNullException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (CustomerNullException ex) {
			throw ex;
		}
	}

	@Override
	public float getPrice(int showtimeID, Customer customer, String discountCodeTicket)
			throws IllegalArgumentException, CustomerNullException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer, discountCodeTicket);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (CustomerNullException ex) {
			throw ex;
		}
	}

	@Override
	public float getPrice(int showtimeID, Customer customer, boolean isCoupleSeat)
			throws IllegalArgumentException, CustomerNullException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer, isCoupleSeat);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (CustomerNullException ex) {
			throw ex;
		}
	}

	@Override
	public float getPrice(int showtimeID, Customer customer, boolean isCoupleSeat, String discountCodeTicket)
			throws IllegalArgumentException, CustomerNullException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer, isCoupleSeat, discountCodeTicket);
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (CustomerNullException ex) {
			throw ex;
		}
	}

	@Override
	public void printShowtimes() throws IllegalArgumentException {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No showtime exist");
		}
		System.out.println(
				"|----------------------------------------------------------- Showtimes ----------------------------------------|");
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
				"Movie Status", "Movie Name", "Cinema Type", "Date", "Time", "Holiday");
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus() == MovieStatus.PREVIEW)
				s.printShowtime();
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus() == MovieStatus.NOW_SHOWING)
				s.printShowtime();
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus() == MovieStatus.COMING_SOON)
				s.printShowtime();
		}
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
	}

	@Override
	public void printShowtimesByMovieName(String movieName) throws IllegalArgumentException {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No showtime exist");
		}

		// check if the movie has already ended
		MovieManager MM = MovieManager.getInstance();
		if (MM.findMoviebyName(movieName).getMovieStatus() == MovieStatus.END_OF_SHOWING) {
			throw new IllegalArgumentException("Movie is no longer showing");
		}
		else if (MM.findMoviebyName(movieName).getMovieStatus() == MovieStatus.COMING_SOON){
			throw new IllegalArgumentException("Movie is coming soon cannot be booked");
		}

		// Check if movie exist in the showtime
		boolean found = false;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieName().equals(movieName))
				found = true;
		}

		MovieManager.close();
		System.out.println(
				"|----------------------------------------------------------- Showtimes ------------------------------------------------------|");
		System.out.println(
				"|----------------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
				"Movie Status", "Movie Name", "Cinema Type", "Date", "Time", "Holiday");
		System.out.println(
				"|----------------------------------------------------------------------------------------------------------------------------|");
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING & s.getMovieStatus() != MovieStatus.COMING_SOON && s.getMovieName().equals(movieName))
				s.printShowtime();
		}
		System.out.println(
				"|----------------------------------------------------------------------------------------------------------------------------|");
	}

	@Override
	public void printShowtimesByMovieNameAndCineplexID(String movieName, int cineplexID)
			throws IllegalArgumentException {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No showtime exist");
		}

		// check if the movie has already ended
		MovieManager MM = MovieManager.getInstance();
		if (MM.findMoviebyName(movieName).getMovieStatus() == MovieStatus.END_OF_SHOWING) {
			throw new IllegalArgumentException("Movie is no longer showing");
		}
		else if (MM.findMoviebyName(movieName).getMovieStatus() == MovieStatus.COMING_SOON){
			throw new IllegalArgumentException("Movie is coming soon cannot be booked");
		}

		// Check if movie exist with cineplex selection
		boolean found = false;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieName().equals(movieName) && s.getCineplexID() == cineplexID)
				found = true;
		}
		if (!found)
			throw new IllegalArgumentException("Movie is not showing in this cineplex!");
		MovieManager.close();

		System.out.println(
				"|---------------------------------------------- Showtimes -----------------------------------------------------|");
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
				"Movie Status", "Movie Name", "Cinema Type", "Date", "Time", "Holiday");
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus() != MovieStatus.COMING_SOON && s.getMovieName().equals(movieName)
					&& s.getCineplexID() == cineplexID)
				s.printShowtime();
		}
		System.out.println(
				"|--------------------------------------------------------------------------------------------------------------|");
	}

	@Override
	public void printShowtimeAdmin() {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}
		System.out.println(
				"|------------------------------------------------------ Showtimes ------------------------------------------------------------|");
		System.out.println(
				"|-----------------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|  %-12s|   %-15s   |  %-30s  |  %-15s  |   %-8s  |  %-5s |  %-7s |\n",
				"ShowtimeID", "Movie Status", "Movie Name", "Cinema Type", "Date", "Time", "Holiday");
		System.out.println(
				"|-----------------------------------------------------------------------------------------------------------------------------|");
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			it.next().printShowtimeAdmin();
		}
		System.out.println(
				"|-----------------------------------------------------------------------------------------------------------------------------|");
	}

	@Override
	public void printSeats(int showtimeID) throws IllegalArgumentException {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printSeat();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printMovie(int showtimeID) throws IllegalArgumentException {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printMovie();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printCinemaLayout(int showtimeID) throws IllegalArgumentException {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printLayout();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Unable to find layout for showtime chosen!");
		}
	}

	@Override
	public void printCineplex(int showtimeID) throws IllegalArgumentException {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printCineplex();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public String getCinemaCode(int showtimeID) throws IllegalArgumentException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void setBasePrice(float basePrice) {
		Showtime.setBasePrice(basePrice);
	}

	@Override
	public float getBasePrice() {
		return Showtime.getBasePrice();
	}

	@Override
	public void addShowtime(Movie movie, ICinemaBooking cinema, IDay day) throws IllegalArgumentException {
		if (movie.getMovieStatus() == MovieStatus.COMING_SOON ||
				movie.getMovieStatus() == MovieStatus.NOW_SHOWING ||
				movie.getMovieStatus() == MovieStatus.PREVIEW) {
			ShowtimeManager.showtimes.add(new Showtime(movie, cinema, day, ++lastID));
			return;
		}
		throw new IllegalArgumentException("Movie status is not Coming, Showing or Preview");
	}

	@Override
	public void movieShowtimeEnd(int movieID) {
		if (showtimes == null || showtimes.size() == 0) {
			// exit before any looping is done
			throw new IllegalArgumentException("No showitmes with this movie exist");
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID) {
				s.setMovieStatus(MovieStatus.END_OF_SHOWING);
			}
		}
	}

	@Override
	public String getTime(int showtimeID) throws IllegalArgumentException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getTime();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public String getDate(int showtimeID) throws IllegalArgumentException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getDate();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public Day getDay(String dateString) throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getDate().equals(dateString)) {
				return s.getDayObject();
			}
		}
		throw new IllegalArgumentException("No such date in showtimes");
	}

	@Override
	public void setMovieType(int movieID, MovieType type) {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {

			Showtime s = it.next();
			if (s.getMovieID() == movieID) {
				s.getMovieObject().setMovieType(type);
			}
		}
	}

	@Override
	public void setMovieStatus(int movieID, MovieStatus status) {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID) {
				s.getMovieObject().setMovieStatus(status);
			}
		}
	}

	@Override
	public void setMovieDirector(int movieID, String director) {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID) {
				s.getMovieObject().setMovieDirector(director);
			}
		}
	}

	@Override
	public void setHoliday(Day day) throws IllegalArgumentException {
		int flag = 0;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getDayObject().equalsDate(day)) {
				s.setHoliday();
				flag = 1;
			}
		}
		if (flag == 0)
			throw new IllegalArgumentException("Showtime with date and timing provided exist");
	}

	@Override
	public void unsetHoliday(Day day) throws IllegalArgumentException {
		int flag = 0;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getDayObject().equalsDate(day)) {
				s.unsetHoliday();
				flag = 1;
			}
		}
		if (flag == 0)
			throw new IllegalArgumentException("Showtime with date and timing provided exist");
	}

	@Override
	public void changeShowtimeDay(int showtimeID, Day day) throws IllegalArgumentException {
		try {
			getShowtimeByID(showtimeID).changeDay(day);
		} catch (IllegalArgumentException ex) {
			throw ex;
		}
	}

	@Override
	public void removeShowtime(int showtimeID) {
		try {
			showtimes.remove(getShowtimeIndex(showtimeID));
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (IndexOutOfBoundsException ex) {
			throw new IllegalArgumentException("Showtime not found!");
		}
	}
}
