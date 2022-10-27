package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Cinema  implements Serializable{
	private static final long serialVersionUID = 6266710308272298089L;
	protected char colList[] = {'A', 'B', 'C', 'D', 'E'};
	protected ArrayList<ArrayList<Seat>> seats;
	private CinemaType cinemaType;
	private String name;
	private int Cineplexid;
	private int id;

	/**
	 * default constructor to create the array for seat objects
	 */
	public Cinema() {
		seats = new ArrayList<ArrayList<Seat>>();
	}
	public Cinema(String name,int id) {
		this();
		this.name = name;
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
	 * Returns name of Cinema
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		System.out.println("Cinema ID: " + id + " Cinema Name: " + name + " Cinema Type: "+ cinemaType);
	}
	public int getCineplexID(){
		return this.Cineplexid;
	}
	public void setCineplexid(int cineplexID) {
		this.Cineplexid = cineplexID;
	}
	/**
	 * Abstract method declared here as we have 3 different subclass of cinema
	 * which will perform differently due to different seat layout
	 */
	public abstract void printLayout();
}