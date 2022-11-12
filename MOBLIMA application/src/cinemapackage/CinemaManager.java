package cinemapackage;

import java.util.ArrayList;
import java.util.Iterator;
import cineplexpackage.ICineplex;
import java.io.*;

/**
 * Controller for Cinema class
 * 
 * @apiNote ICineplex
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
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
		lastID = getLargestID();
	}

	/**
	 * Helper function to obtain the largest ID in list for maintaining a unique ID
	 * for
	 * each cinema
	 * 
	 * @return int largest ID in the list
	 */
	private int getLargestID() {
		if (cinemas == null) {
			return 0;
		}
		int largest = 0;
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			int current = it.next().getID();
			if (current > largest) {
				largest = current;
			}
		}
		return largest;
	}

	/**
	 * Method to deseralise all cinema object from .dat file
	 * Method called in singleton getInstance helper.
	 * 
	 * @param filename
	 * @return
	 */
	private static ArrayList<Cinema> deseraliseCinemas(String filename) {
		ArrayList<Cinema> c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Cinema>) in.readObject();
			in.close();
		} catch (IOException i) {
			// i.printStackTrace();
			// The reason why i return an empty list is because this error occurs
			// when the binary file is empty (No object state as it is a fresh file)
			// or the file cannot be read for whatever reason
			return new ArrayList<Cinema>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Serialise an array of cinema for Manager to control
	 * 
	 * @param filename
	 * @param c
	 */
	private static void seraliseCinemas(String filename, ArrayList<Cinema> c) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Singleton design pattern implementation to obtain instance of CinemaManager
	 * 
	 * @return
	 */
	public static CinemaManager getInstance() {
		if (cinemaManager == null) {
			ArrayList<Cinema> c = CinemaManager.deseraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat");
			CinemaManager.cinemaManager = new CinemaManager(c);
			return CinemaManager.cinemaManager;
		}
		return CinemaManager.cinemaManager;
	}

	/**
	 * Closes and saves the array state of cinemas
	 * Allows java to automatically delete Manager object after setting static
	 * variable to null
	 */
	public static void close() {
		CinemaManager.seraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat", cinemas);
		CinemaManager.cinemaManager = null;
	}

	/**
	 * Private method used within manager to format printing of a cinema
	 * 
	 * @param cinema
	 */
	private void printCinema(Cinema cinema) {
		printCinemaHeader();
		cinema.printCinemaAdmin();
		printCinemaEnder();
	}

	/**
	 * Funcion to be used within package level
	 * Other classes that are not related to Cinema should not be able to access
	 * this method
	 * 
	 * @return
	 */
	protected ArrayList<Cinema> getCinemas() {
		return cinemas;
	}

	/**
	 * Private function to print cinema header for print formatting
	 */
	private void printCinemaHeader() {
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
		System.out.printf("|   %-15s   |       %-15s        |    %-30s     |\n",
				"CinemaID", "Cinema Code", "Cinema Type");
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
	}

	/**
	 * Private function to print cinema footer for print formatting
	 */
	private void printCinemaEnder() {
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
	}

	@Override
	public void deleteCinema(int id, ICineplex cineplexManager) {
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			Cinema c = it.next();
			if (c.getID() == id) {
				cineplexManager.removeCinema(c);
				it.remove();
				return;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cinema is not found");
	}

	@Override
	public void createCinema(String code, String type) {
		try {
			if (code.length() != 3) {
				System.out.println("Invalid code supplied");
				System.out.println("Exiting cinema creation function");
				System.out.println("===== Cinema creation finished =====");
				return;
			}
			this.getCinema(code);
			System.out.println("Cinema already created");
		} catch (IllegalArgumentException ex) {
			Cinema c = null;
			if (CinemaType.PLATINUM.equals(type))
				c = new PlatinumMovieSuit(code, ++lastID);
			else if (CinemaType.GOLD.equals(type))
				c = new GoldMovieSuit(code, ++lastID);
			else if (CinemaType.SILVER.equals(type))
				c = new SilverMovieSuit(code, ++lastID);
			else {
				System.out.println("Invalid Cinema type supplied");
				System.out.println("Exiting cinema creation function");
				return;
			}
			printCinema(c);
			cinemas.add(c);
		}
	}

	@Override
	public void printCinemas() {
		System.out.printf("|-------------------------------------------------------------------|\n");
		System.out.printf("|   %-15s   |       %-30s        |\n",
				"Cinema Code", "Cinema Type");
		System.out.printf("|-------------------------------------------------------------------|\n");
		for (Cinema cinema : cinemas) {
			cinema.printCinema();
		}
		System.out.printf("|-------------------------------------------------------------------|\n");
	}

	@Override
	public void printCinemasAdmin() {
		printCinemaHeader();
		for (Cinema cinema : cinemas) {
			cinema.printCinemaAdmin();
		}
		printCinemaEnder();
	}

	@Override
	public Cinema getCinema(int id) {
		if (cinemas == null || cinemas.size() == 0) {
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

	/**
	 * @param code
	 * @return Cinema
	 */
	public Cinema getCinema(String code) {
		if (cinemas == null || cinemas.size() == 0) {
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
		try {
			return getCinema(cinemaID).getCode();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public void setCinemaCode(int cinemaID, String code) {
		try {
			getCinema(cinemaID).setCode(code);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public CinemaType getCinemaType(int cinemaID) {
		try {
			return getCinema(cinemaID).getCinemaType();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public void setCinemaType(int cinemaID, CinemaType type) throws IllegalArgumentException {
		try {
			getCinema(cinemaID).setCinemaType(type);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public int getCineplexID(int cinemaID) {

		try {
			return getCinema(cinemaID).getCineplexID();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public int getCineplexID(String code) {

		try {
			return getCinema(code).getCineplexID();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public void setCineplexID(int cinemaID, int cineplexID) {

		try {
			getCinema(cinemaID).setCineplexid(cineplexID);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public void printCinemaLayout(int cinemaID) {

		try {
			getCinema(cinemaID).printLayout();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public void printCinemaLayout(String code) {

		try {
			getCinema(code).printLayout();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cinema is not found");
		}
	}

	@Override
	public Cinema cloneCinemaByID(int id) {
		return getCinema(id).cloneCinema();
	}
}