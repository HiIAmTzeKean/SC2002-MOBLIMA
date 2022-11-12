package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cinema object that stores an instance of Cinema details
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public abstract class Cinema implements Serializable, ICinemaBooking {
	private static final long serialVersionUID = 6266710308272298089L;
	protected char rowList[] = { 'A', 'B', 'C', 'D', 'E' };
	protected ArrayList<ArrayList<Seat>> seats;
	protected CinemaType cinemaType;
	protected String code;
	protected int Cineplexid;
	protected int id;

	/**
	 * default constructor to create the array for seat objects
	 */
	public Cinema() {
		seats = new ArrayList<ArrayList<Seat>>();
	}

	/**
	 * Default constructor for class. To be called within subclass
	 * 
	 * @param code
	 * @param id
	 */
	public Cinema(String code, int id) {
		this();
		this.code = code;
		this.id = id;
		this.Cineplexid = -1;
	}

	/**
	 * Constructor to be used for cloning if needed
	 * @param code
	 * @param id
	 * @param type
	 * @param Cineplexid
	 * @param seats
	 */
	public Cinema(String code, int id, CinemaType type, int Cineplexid, ArrayList<ArrayList<Seat>> seats) {
		this.code = code;
		this.id = id;
		this.cinemaType = type;
		this.Cineplexid = Cineplexid;
		this.seats = seats;
	}

	
	
	/** 
	 * @return Cinema
	 */
	/** 
	 * @return Cinema
	 */
	/**
	 * Allows cloning of the subclass
	 * Returns a clone cinema object
	 * 
	 * @param Cinema
	 */
	public abstract Cinema cloneCinema();

	/**
	 * Returns an ArrayList of seat objects
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<Seat>> getSeats() {
		return seats;
	}

	/**
	 * Returns code of Cinema
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter function to set cinema code
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	
	/** 
	 * @return int
	 */
	public int getID() {
		return this.id;
	}

	
	/** 
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Setter for cinema type
	 * 
	 * @param cinemaType
	 */
	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	/**
	 * Prints cinema attribute in basic view.
	 * Used in customer view where admin rights not needed.
	 * 
	 */
	public void printCinema() {
		System.out.printf("|   %-15s   |       %-30s        |\n",
				code,
				cinemaType);
	}

	/**
	 * Prints cinema attributes in admin view.
	 * Function should be used only in staff view.
	 *
	 */
	public void printCinemaAdmin() {
		System.out.printf("|   %-15s   |       %-15s        |    %-30s     |\n",
				id, code, cinemaType);
	}

	/**
	 * Setter for cineplexID
	 * 
	 * @param cineplexID
	 */
	public void setCineplexid(int cineplexID) {
		this.Cineplexid = cineplexID;
	}

	/**
	 * Prints the legend of the cinema to indicate type of seating
	 * 
	 */
	public void printLegend() {
		System.out.println("-----------------------------------");
		System.out.println("             Legend");
		System.out.println("-----------------------------------");
		System.out.printf("Normal seat booking: | X |\n");
		System.out.printf("Elite seat booking:  |-X-|\n");
		System.out.printf("Couple seat booking: | X    X |\n");
		System.out.println("-----------------------------------");
	}

	
	
	/** 
	 * @param seatRow
	 * @throws SeatRowException
	 */
	/** 
	 * @param seatRow
	 * @throws SeatRowException
	 */
	/**
	 * Prints the layout of a cinema.
	 * Each subclass will specialise and print different formats.
	 * 
	 */
	public abstract void printLayout();

	
	
	/** 
	 * @param seatRow
	 * @return float
	 * @throws SeatRowException
	 */
	/** 
	 * @param seatRow
	 * @return float
	 * @throws SeatRowException
	 */
	@Override
	public abstract float getMultiplier();

	/**
	 * Given a String, fetch the first character of the String to be used to
	 * indicate the row number
	 * of the seat to be booked
	 * We assume that error checking has been done before seatRow is passed into
	 * Cinema class, where
	 * seatRow should be String of size 1
	 * 
	 * @param seatRow
	 * @return
	 * @throws IllegalArgumentException
	 */
	protected int convertSeatRowToInt(String seatRow) throws SeatRowException {
		char comparator = seatRow.charAt(0);
		for (int i = 0; i < rowList.length; i++) {
			if (comparator == rowList[i])
				return i;
		}
		System.out.println(seatRow + " Cannot be converted");
		throw new SeatRowException();
	}

	
	/** 
	 * @param seatRow
	 * @param seatCol
	 * @param customerID
	 * @throws IllegalArgumentException
	 */
	@Override
	public void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException {
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (!isBooked(seatRow, seatCol)) {
				seatCol = seatCol - 1;
				seats.get(row).get(seatCol).setBooked(customerID);
			} else
				throw new IllegalArgumentException("Seat was not booked");
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (SeatRowException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Seat row input not valid");
		}
	}

	
	/** 
	 * @param seatRow
	 * @param seatCol
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	@Override
	public boolean isBooked(String seatRow, int seatCol) throws IllegalArgumentException {
		seatCol = seatCol - 1;
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (seats.get(row).get(seatCol).isBooked() == false)
				return false;
			else
				return true;
		} catch (SeatRowException e) {
			//e.printStackTrace();
			throw new IllegalArgumentException("Seat row input not valid");
		} catch (IndexOutOfBoundsException ex) {
			//System.out.println(seatRow + seatCol + getCinemaType());
			throw new IllegalArgumentException("Seat col/row input out of range");
		}
	}

	
	/** 
	 * @param cinemaID
	 * @param seatRow
	 * @param seatCol
	 * @throws IllegalArgumentException
	 */
	@Override
	public void removeBooking(int cinemaID, String seatRow, int seatCol) throws IllegalArgumentException {
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (isBooked(seatRow, seatCol)) {
				seatCol = seatCol - 1;
				seats.get(row).get(seatCol).setUnBooked();
			} else
				throw new IllegalArgumentException("Seat was not booked");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Invalid seat col/row input");
		} catch (SeatRowException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Invalid seat row input");
		}
	}

	@Override
	public void printCinemaLayout() {
		this.printLayout();
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String getCinemaCode() {
		return code;
	}

	
	/** 
	 * @return int
	 */
	@Override
	public int getCineplexID() {
		return this.Cineplexid;
	}

	
	/** 
	 * @return CinemaType
	 */
	@Override
	public CinemaType getCinemaType() {
		return cinemaType;
	}

	
	/** 
	 * @param code
	 */
	@Override
	public void setCinemaCode(String code) {
		this.code = code;
	}

	
	/** 
	 * @return String
	 */
	@Override
	public String getCinemaClass() {
		return this.cinemaType.toString();
	}
}