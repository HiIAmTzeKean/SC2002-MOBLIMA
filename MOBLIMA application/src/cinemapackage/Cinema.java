package cinemapackage;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cinema  implements Serializable{
	protected ArrayList<ArrayList<Seat>> seats;
	protected char colList[] = {'A', 'B', 'C', 'D', 'E'};
	private CinemaType cinemaType;
	private String name;
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
	}

	public ArrayList<ArrayList<Seat>> getSeats() {
		return seats;
	}

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
	/**
	 * Abstract method declared here as we have 3 different subclass of cinema
	 * which will perform differently due to different seat layout
	 */
	public abstract void printLayout();
}