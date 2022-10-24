package cinemapackage;

import java.util.ArrayList;
import java.util.Iterator;

import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;

import java.io.*;

public class CinemaManager implements ICinema {

	private ArrayList<Cinema> cinemas;

	public CinemaManager() {
		// Deseralise all objects here
		cinemas = new ArrayList<Cinema>();

	}
	public CinemaManager(ArrayList<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	public static ArrayList<Cinema> deseraliseCinemas(String filename){
		ArrayList<Cinema>  c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Cinema>) in.readObject();
			in.close();
		 } 
		 catch (IOException i) {
			i.printStackTrace();
		 } 
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		 }
		 return c;
	}

	public static void seraliseCinemas(String filename, ArrayList<Cinema> c) {
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}

	@Override
	public void createCinema(int id, String name, String type) {
		Cinema c = null;
		if (CinemaType.PLATINUM.equals(type))
			c = new PlatinumMovieSuit(name,id);
		else if (CinemaType.GOLD.equals(type))
			c = new GoldMovieSuit(name,id);
		else
			c = new SliverMovieSuit(name, id);
		System.out.println("===== Cinema has been created =====");
		c.printCinema();
		cinemas.add(c);
	}

	@Override
	public void deleteCinema(int id, ICineplex cineplexManager) {
		
		System.out.println("===== Cinema being deleted =====");
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getID() == id) {
				System.out.println("Target is:");
				c.printCinema();
				cineplexManager.removeCinemaWithoutID(c);
				System.out.println("===== Cinema has been deleted from Cinplex =====");
				it.remove();
				return;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}

	@Override
	public ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	@Override
	public void printCinemas() {
		for (Cinema cinema: cinemas){
            cinema.printCinema();
        }
	}
	@Override
	public Cinema getCinema(int id) {
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getID() == id) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}
}