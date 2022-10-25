package cineplexpackage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import cinemapackage.Cinema;

public class Cineplex implements Serializable{

	private String location;
	private String name;
	private int id;
	private ArrayList<Cinema> cinemas;

	public Cineplex(int id, String name, String location){
		this.id = id;
		this.location = location;
		this.name = name;
		cinemas = new ArrayList<Cinema>();
	}
	public void addCinema(Cinema cinema){
		cinemas.add(cinema);
		System.out.println("===== Target is =====");
		System.out.println("Cinema ID: " + cinema.getID() + " Cinema Type: " + cinema.getCinemaType());
		System.out.println("Cinema has been added");
	}

	public void removeCinema(Cinema cinema) {
		int target=0;
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			if (it.next().getID() == cinema.getID()) {
				cinemas.remove(target);
				System.out.println("Target in cinplex is:");
				System.out.println("Cinema ID: " + cinema.getID() + " Cinema Type: " + cinema.getCinemaType());
				System.out.println("===== Cinema is deleted =====");
				return;
			}
			target++;
		}
		System.out.println("Cinema to be deleted is not found");
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}

	public ArrayList<Cinema> getCinemas(){
		return cinemas;
	}
	public void printCineplexCinemas() {
		for (Cinema c: cinemas){
			c.printCinema();
		}
	}
	public void printCineplex() {
		System.out.println("Cineplex ID: " + id + " Cineplex Name: " + name + " Cineplex Location: "+ location);
	}
}