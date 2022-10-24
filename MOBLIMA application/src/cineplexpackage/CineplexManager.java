package cineplexpackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import cinemapackage.Cinema;

public class CineplexManager implements ICineplex {

	private ArrayList<Cineplex> cineplexes;
	private Cineplex cineplex;
	public CineplexManager() {
		cineplexes = new ArrayList<Cineplex>();
	}

	public CineplexManager(ArrayList<Cineplex> c) {
		this.cineplexes = c;
	} 

	public static ArrayList<Cineplex> deseraliseCineplexes(String filename){
		ArrayList<Cineplex>  c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Cineplex>) in.readObject();
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

	public static void seraliseCineplexes(String filename, ArrayList<Cineplex> c) {
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


	public int findCineplex(int id){
		int target=0;
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			if (it.next().getID() == id) {
				return target;
			}
			target++;
		}
		// Not found
		throw new IllegalArgumentException("Cineplex is not found");
	}

	@Override
	public void createCineplex(int id, String name, String location) {
		System.out.printf("===== Creating new Cinplex =====\n");
		Cineplex c=new Cineplex(id, name, location);
		cineplexes.add(c);
		c.printCineplex();
		System.out.printf("===== Cinplex created =====\n");
	}

	@Override
	public void deleteCineplex(int id) {
		try{
			int target = findCineplex(id);
			cineplexes.remove(target);
			System.out.println("Cineplex has been deleted");
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public ArrayList<Cineplex> getCineplexes() {
		return cineplexes;
	}

	public void printCineplexes() {
		for (Cineplex c:cineplexes){
			c.printCineplex();
		}
	}
	
	@Override
	public String getName(int id) {
		try{
			int target = findCineplex(id);
			return cineplexes.get(target).getName();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void setName(int id, String name) {
		try{
			int target = findCineplex(id);
			cineplexes.get(target).setName(name);
			System.out.println("Cineplex new name updated");
			System.out.println("Cinplex updated information as follows: ");
			cineplexes.get(target).printCineplex();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}
	
	@Override
	public void setLocation(int id, String location) {
		try{
			int target = findCineplex(id);
			cineplexes.get(target).setLocation(location);
			System.out.println("Cineplex new location updated");
			System.out.println("Cinplex updated information as follows: ");
			cineplexes.get(target).printCineplex();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public String getLocation(int id) {
		try{
			int target = findCineplex(id);
			return cineplexes.get(target).getLocation();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void addCinema(int id, Cinema cinema) {
		try{
			int target = findCineplex(id);
			cineplexes.get(target).addCinema(cinema);
			System.out.println("Cineplex new Cinema updated");
			System.out.println("The list of Cinemas under Cinema " + cineplexes.get(target).getName() + " is:");
			cineplexes.get(target).printCineplexCinemas();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}
	
	@Override
	public void removeCinema(int id, Cinema cinema) {
		try{
			int target = findCineplex(id);
			cineplexes.get(target).removeCinema(cinema);
			System.out.println("Cineplex has been updated");
			System.out.println("The list of Cinemas under Cinema " + cineplexes.get(target).getName() + " is:");
			cineplexes.get(target).printCineplexCinemas();
		}
		catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void removeCinemaWithoutID(Cinema cinema) {
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			Cineplex cineplex = it.next();
			ArrayList<Cinema> cinemas = cineplex.getCinemas();
			for (Cinema c:cinemas){
				if (c.getID() == cinema.getID()){
					// Found the cinema in Cinplex
					// Continue to do removal
					System.out.println("Cinema to be remove also found in a Cineplex");
					System.out.println("Cineplex affected:");
					cineplex.printCineplex();
					cineplex.removeCinema(cinema);
					return;
				}
			}
		}
		// Not found
		// Do not need to throw error since cinema might not exist
	}

	@Override
	public Cineplex getCineplex(int id) {
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			Cineplex c = it.next();
			if (c.getID() == id) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cineplex is not found");
	}

}