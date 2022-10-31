package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Cinema  implements Serializable, ICinemaBooking{
	private static final long serialVersionUID = 6266710308272298089L;
	protected char colList[] = {'A', 'B', 'C', 'D', 'E'};
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
	public Cinema(String code,int id) {
		this();
		this.code = code;
		this.id = id;
		this.Cineplexid = -1;
	}
	public Cinema(String code,int id, CinemaType type, int Cineplexid, ArrayList<ArrayList<Seat>> seats){
		this.code = code;
		this.id = id;
		this.cinemaType = type;
		this.Cineplexid = Cineplexid;
		this.seats = seats;
	}
	public abstract Cinema cloneCinema();
	/**
	 * Returns an ArrayList of seat objects
	 * @return
	 */
	public ArrayList<ArrayList<Seat>> getSeats() {
		return seats;
	}
	/**
	 * Returns code of Cinema
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Alias for getCode() function
	 */
	public String getCinemaCode(){
		return code;
	}
	public void setCinemaCode(String code){
		this.code = code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	public CinemaType getCinemaType(){
		return cinemaType;
	}
	public void setCinemaType(CinemaType cinemaType){
		this.cinemaType = cinemaType;
	}
	public void printCinema(){
		System.out.println("Cinema ID: " + id + " Cinema code: " + code + " Cinema Type: "+ cinemaType);
	}
	public int getCineplexID(){
		return this.Cineplexid;
	}
	public void setCineplexid(int cineplexID) {
		this.Cineplexid = cineplexID;
	}
	/**
	 * Given a String, fetch the first character of the String to be used to indicate the row number
	 * of the seat to be booked
	 * We assume that error checking has been done before seatRow is passed into Cinema class, where
	 * seatRow should be String of size 1
	 * @param seatRow
	 * @return
	 * @throws IllegalArgumentException
	 */
	private int convertSeatRowToInt(String seatRow) throws IllegalArgumentException{
		char comparator = seatRow.charAt(0);
		int i = 0;
		boolean found = false; 
		for (i=0; i<colList.length; i++){
			if (comparator==colList[i]) return i;
		}
	
		
		System.out.println(seatRow + " Cannot be converted");
		throw new IllegalArgumentException("Invalid Seat conversion");
	}
	
	public void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Seat input not valid");
		}
		
		if (!isBooked(seatRow,seatCol)) {
			seats.get(row).get(seatCol).setBooked(customerID);
		}
		else throw new IllegalArgumentException("Seat was not booked");
	}
    public boolean isBooked(String seatRow, int seatCol) throws IllegalArgumentException{
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Seat input not valid");
		}
		try{
			if (seats.get(row).get(seatCol).isBooked()== false) return false;
			else return true;
		}
		catch(IndexOutOfBoundsException ex){
			System.out.println(seatRow+seatCol+getCinemaType());
			throw new IllegalArgumentException("Seat input not valid");
		}
	}
    public void removeBooking(int cinemaID, String seatRow, int seatCol){
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Seat input not valid");
		}
		if (isBooked(seatRow,seatCol)) {
			row = convertSeatRowToInt(seatRow);
			seats.get(row).get(seatCol).setUnBooked();
		}
		else throw new IllegalArgumentException("Seat was not booked");
	}
    public void printCinemaLayout(){
		this.printLayout();
	}
	/**
	 * Method declared here as we have 3 different subclass of cinema
	 * which will perform differently due to different seat layout
	 */
	public abstract void printLayout();
}