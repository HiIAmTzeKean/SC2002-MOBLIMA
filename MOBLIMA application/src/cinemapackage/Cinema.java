package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cinema  implements Serializable, ICinemaBooking{
	private static final long serialVersionUID = 6266710308272298089L;
	protected char colList[] = {'A', 'B', 'C', 'D', 'E'};
	protected ArrayList<ArrayList<Seat>> seats;
	private CinemaType cinemaType;
	private String code;
	private int Cineplexid;
	private int id;

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
	private int convertSeatRowToInt(String seatRow) {
		char comparator = seatRow.charAt(0);
		int i = 0;
		boolean found = false; 
		for (i=0; i<colList.length && comparator!=colList[i]; i++){
			found = true;
		}
		if (found) return i;
		else return -1;
	}
	public void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalAccessError{
		if (isBooked(seatRow,seatCol)) {
			int row = convertSeatRowToInt(seatRow);
			seats.get(row).get(seatCol).setBooked(customerID);
		}
		else throw new IllegalAccessError("Seat was not booked");
	}
    public boolean isBooked(String seatRow, int seatCol){
		int row = convertSeatRowToInt(seatRow);
		if (seats.get(row).get(seatCol).isBooked()) return false;
		else return true;
	}
    public void removeBooking(int cinemaID, String seatRow, int seatCol){
		if (isBooked(seatRow,seatCol)) {
			int row = convertSeatRowToInt(seatRow);
			seats.get(row).get(seatCol).setUnBooked();
		}
		else throw new IllegalAccessError("Seat was not booked");
	}
    public void printCinemaLayout(){
		printLayout();
	}
	/**
	 * Abstract method declared here as we have 3 different subclass of cinema
	 * which will perform differently due to different seat layout
	 */
	public abstract void printLayout();
}