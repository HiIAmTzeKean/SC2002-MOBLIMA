package cinemapackage;

import java.util.ArrayList;
import java.util.Iterator;

import cineplexpackage.ICineplex;

import java.io.*;

public class CinemaManager implements ICinema {
	private static CinemaManager cinemaManager = null;
	private static ArrayList<Cinema> cinemas;
	private static int lastID;

	private CinemaManager() {
		// Deseralise all objects here
		cinemas = new ArrayList<Cinema>();
		lastID = 0;
	}
	private CinemaManager(ArrayList<Cinema> cinemas) {
		CinemaManager.cinemas = cinemas;
		lastID = cinemas.size();
	}
	private static ArrayList<Cinema> deseraliseCinemas(String filename){
		ArrayList<Cinema>  c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Cinema>) in.readObject();
			in.close();
		} 
		catch (IOException i) {
			// i.printStackTrace();
			// The reason why i return an empty list is because this error occurs
			// when the binary file is empty (No object state as it is a fresh file)
			// or the file cannot be read for whatever reason
			return new ArrayList<Cinema>();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static void seraliseCinemas(String filename, ArrayList<Cinema> c) {
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

	public static CinemaManager getInstance(){
		if (cinemaManager == null){
			ArrayList<Cinema> c = CinemaManager.deseraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat");
			CinemaManager.cinemaManager = new CinemaManager(c);
			return CinemaManager.cinemaManager;
		}
		return CinemaManager.cinemaManager;
	}

	public static void close() {
		CinemaManager.seraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat",cinemas);
		CinemaManager.cinemaManager = null;
	}

	@Override
	public void createCinema(String code, String type) {
		System.out.println("===== Cinema being created =====");
		try{
			if (code.length() != 3) {
				System.out.println("Invalid code supplied");
				System.out.println("Exiting cinema creation function");
				System.out.println("===== Cinema creation finished =====");
				return;
			}
			this.getCinema(code);
			System.out.println("Cinema already created");
		}
		catch (IllegalArgumentException ex) {
			Cinema c = null;
			if (CinemaType.PLATINUM.equals(type))
				c = new PlatinumMovieSuit(code,++lastID);
			else if (CinemaType.GOLD.equals(type))
				c = new GoldMovieSuit(code,++lastID);
			else if (CinemaType.SLIVER.equals(type))
				c = new SliverMovieSuit(code, ++lastID);
			else {
				System.out.println("Invalid Cinema type supplied");
				System.out.println("Exiting cinema creation function");
				System.out.println("===== Cinema creation finished =====");
				return;
			}
			System.out.println("Cinema has been created");
			c.printCinema();
			cinemas.add(c);
		}
		System.out.println("===== Cinema creation finished =====");
	}

	@Override
	public void deleteCinema(int id, ICineplex cineplexManager) {
		
		System.out.println("===== Cinema being deleted =====");
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getID() == id) {
				System.out.println("Target is:");
				c.printCinema();
				cineplexManager.removeCinema(c);
				System.out.println("===== Cinema has been deleted =====");
				it.remove();
				return;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}

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
		if (cinemas== null || cinemas.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}

		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getID() == id) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}
	public Cinema getCinema(String code) {
		if (cinemas== null || cinemas.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}

		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getCinemaCode() == code) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}
	@Override
	public String getCinemaCode(int cinemaID) {
		return getCinema(cinemaID).getCode();
	}
	@Override
	public void setCinemaCode(int cinemaID, String code) {
		getCinema(cinemaID).setCode(code);;
	}
	@Override
	public CinemaType getCinemaType(int cinemaID) {
		return getCinema(cinemaID).getCinemaType();
		
	}
	@Override
	public int getCineplexID(int cinemaID) {
		return getCinema(cinemaID).getCineplexID();
	}
	@Override
	public void setCineplexID(int cinemaID, int cineplexID) {
		getCinema(cinemaID).setCineplexid(cineplexID);
	}
	@Override
	public void printCinemaLayout(int cinemaID) {
		getCinema(cinemaID).printLayout();
	}
}