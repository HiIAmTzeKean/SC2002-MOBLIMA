package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cinema object that stores an instance of Cinema details
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public abstract class Cinema  implements Serializable, ICinemaBooking{
	private static final long serialVersionUID = 6266710308272298089L;
	protected char rowList[] = {'A', 'B', 'C', 'D', 'E'};
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
	public String getCinemaClass(){
		return this.cinemaType.toString();
	}
	public int getID(){
		return this.id;
	}
	public void setID(int id){
		this.id = id;
	}
	@Override
	public CinemaType getCinemaType(){
		return cinemaType;
	}
	public void setCinemaType(CinemaType cinemaType){
		this.cinemaType = cinemaType;
	}
	public void printCinema(){
		System.out.printf("|   %-15s   |       %-30s        |\n",
						code,
						cinemaType);
	}
	public void printCinemaAdmin(){
		System.out.printf("|   %-15s   |       %-15s        |    %-30s     |\n",
						id,code,cinemaType);
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
	protected int convertSeatRowToInt(String seatRow) throws SeatRowException{
		char comparator = seatRow.charAt(0);
		for (int i=0; i<rowList.length; i++){
			if (comparator==rowList[i]) return i;
		}
		System.out.println(seatRow + " Cannot be converted");
		throw new SeatRowException();
	}
	
	public void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (!isBooked(seatRow,seatCol)) {
				seatCol = seatCol-1;
				seats.get(row).get(seatCol).setBooked(customerID);
			}
			else throw new IllegalArgumentException("Seat was not booked");
		}
		catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch (SeatRowException ex){
			ex.printStackTrace();
			throw new IllegalArgumentException("Seat row input not valid");
		}
	}
    public boolean isBooked(String seatRow, int seatCol) throws IllegalArgumentException{
		seatCol = seatCol-1;
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (seats.get(row).get(seatCol).isBooked()== false) return false;
			else return true;
		}
		catch (SeatRowException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Seat row input not valid");
		}
		catch(IndexOutOfBoundsException ex){
			System.out.println(seatRow+seatCol+getCinemaType());
			throw new IllegalArgumentException("Seat col/row input out of range");
		}
	}
    public void removeBooking(int cinemaID, String seatRow, int seatCol) throws IllegalArgumentException{
		int row = 0;
		try {
			row = convertSeatRowToInt(seatRow);
			if (isBooked(seatRow,seatCol)) {
				seatCol = seatCol-1;
				seats.get(row).get(seatCol).setUnBooked();
			}
			else throw new IllegalArgumentException("Seat was not booked");
		} 
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		}catch (IndexOutOfBoundsException ex){
			ex.printStackTrace();
			throw new IllegalArgumentException("Invalid seat col/row input");
		}
		catch (SeatRowException ex) {
			ex.printStackTrace();
			throw new IllegalArgumentException("Invalid seat row input");
		}
	}
    public void printCinemaLayout(){
		this.printLayout();
	}
	/**
	 * Method declared here as we have 3 different subclass of cinema
	 * which will perform differently due to different seat layout
	 */
	public void printLegend(){
		System.out.println("-----------------------------------");
		System.out.println("             Legend");
		System.out.println("-----------------------------------");
		System.out.printf("Normal seat booking: | X |\n");
		System.out.printf("Elite seat booking:  |-X-|\n");
		System.out.printf("Couple seat booking: | X    X |\n");
		System.out.println("-----------------------------------");
	}
	public abstract void printLayout();
	public abstract float getMultiplier();
}