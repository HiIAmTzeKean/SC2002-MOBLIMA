package cineplexpackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import cinemapackage.Cinema;

/**
 * Controller for Cineplex class
 * 
 * @apiNote ICineplex
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class CineplexManager implements ICineplex {

	private static ArrayList<Cineplex> cineplexes;
	private static CineplexManager cineplexManager;
	private static int lastID;

	private CineplexManager() {
		cineplexes = new ArrayList<Cineplex>();
		lastID = 0;
	}

	private CineplexManager(ArrayList<Cineplex> c) {
		CineplexManager.cineplexes = c;
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
		int largest = 0;
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			int current = it.next().getID();
			if (current > largest) {
				largest = current;
			}
		}
		return largest;
	}

	private static ArrayList<Cineplex> deseraliseCineplexes(String filename) {
		ArrayList<Cineplex> c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Cineplex>) in.readObject();
			in.close();
		} catch (IOException i) {
			// empty binary file
			// or file not found
			return new ArrayList<Cineplex>();
			// i.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static void seraliseCineplexes(String filename, ArrayList<Cineplex> c) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static CineplexManager getInstance() {
		if (cineplexManager == null) {
			ArrayList<Cineplex> c = CineplexManager
					.deseraliseCineplexes("./MOBLIMA application/data/cineplex/cineplex.dat");
			CineplexManager.cineplexManager = new CineplexManager(c);
			return CineplexManager.cineplexManager;
		}
		return CineplexManager.cineplexManager;
	}

	public static void close() {
		if (CineplexManager.cineplexManager != null) {
			CineplexManager.seraliseCineplexes("./MOBLIMA application/data/cineplex/cineplex.dat", cineplexes);
			CineplexManager.cineplexManager = null;
		}
	}

	/**
	 * Internal prviate function to return index location of the cineplex object in array.
	 * 
	 * @param id
	 * @return
	 */
	private int findCineplex(int id) {
		int target = 0;
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
	public void createCineplex(String name, String location) {
		System.out.printf("===== Creating new Cinplex =====\n");
		Cineplex c = new Cineplex(++lastID, name, location);
		cineplexes.add(c);
		c.printCineplex();
		System.out.printf("===== Cinplex created =====\n");
	}

	@Override
	public void deleteCineplex(int id) throws IllegalArgumentException {
		try {
			int target = findCineplex(id);
			cineplexes.remove(target);
			System.out.println("Cineplex has been deleted");
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void printCineplexes() {
		System.out.printf("|----------------------------------------------------------------------------------|\n");
		System.out.printf("|   %-30s   |       %-30s        |\n",
				"Cineplex Name", "Cineplex Location");
		System.out.printf("|----------------------------------------------------------------------------------|\n");
		for (Cineplex c : cineplexes) {
			c.printCineplex();
		}
		System.out.printf("|----------------------------------------------------------------------------------|\n");
	}

	@Override
	public void printCineplexesAdmin() {
		System.out.printf(
				"|------------------------------------------------------------------------------------------------------|\n");
		System.out.printf("|  %-15s  |   %-30s   |       %-30s        |\n", "ID", "Cineplex Name", "Cineplex Location");
		System.out.printf(
				"|------------------------------------------------------------------------------------------------------|\n");
		for (Cineplex c : cineplexes) {
			c.printCineplexAdmin();
		}
		System.out.printf(
				"|------------------------------------------------------------------------------------------------------|\n");
	}

	@Override
	public String getName(int id) {
		try {
			int target = findCineplex(id);
			return cineplexes.get(target).getName();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void setName(int id, String name) {
		try {
			int target = findCineplex(id);
			cineplexes.get(target).setName(name);
			System.out.println("Cineplex new name updated. View details below");
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
			System.out.printf("|  %-15s  |   %-30s   |       %-30s        |\n", "ID", "Cineplex Name",
					"Cineplex Location");
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
			cineplexes.get(target).printCineplexAdmin();
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void setLocation(int id, String location) throws IllegalArgumentException {
		try {
			int target = findCineplex(id);
			cineplexes.get(target).setLocation(location);
			System.out.println("Cineplex new location updated. View details below");
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
			System.out.printf("|  %-15s  |   %-30s   |       %-30s        |\n", "ID", "Cineplex Name",
					"Cineplex Location");
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
			cineplexes.get(target).printCineplexAdmin();
			System.out.printf(
					"|------------------------------------------------------------------------------------------------------|\n");
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public String getLocation(int id) throws IllegalArgumentException {
		try {
			int target = findCineplex(id);
			return cineplexes.get(target).getLocation();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
	}

	@Override
	public void addCinema(int id, Cinema cinema) throws IllegalArgumentException {
		int target = 0;
		try {
			target = findCineplex(id);
			// Check if the cinema exist in the cineplex
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
		ArrayList<Cinema> cinemas = cineplexes.get(target).getCinemas();
		for (Iterator<Cinema> it = cinemas.iterator(); it.hasNext();) {
			if (it.next().getID() == cinema.getID()) {
				System.out.printf("Cinema is not added as it already exist in Cineplex\n");
				throw new IllegalArgumentException("Cinema is not added as it already exist in Cineplex");
			}
		}
		cineplexes.get(target).addCinema(cinema);
		cinema.setCineplexid(cineplexes.get(target).getID());
		cinema.setCineplexid(id);
		System.out.println("The list of Cinemas under Cinema " + cineplexes.get(target).getName() + " is:");
		printCinemas(target);
	}

	private void printCinemas(int cineplexIndexPosition) {
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
		System.out.printf("|   %-15s   |       %-15s        |    %-30s     |\n", "ID", "Cinema Code", "Cinema Type");
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
		cineplexes.get(cineplexIndexPosition).printCineplexCinemasAdmin();
		System.out.printf(
				"|--------------------------------------------------------------------------------------------|\n");
	}

	public void printCinemasUnderCineplex(int cineplexID) {
		try {
			printCinemas(findCineplex(cineplexID));
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	@Override
	public void removeCinema(int id, Cinema cinema) throws IllegalArgumentException {
		int target = 0;
		try {
			target = findCineplex(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Cineplex is not found");
		}
		if (cineplexes.get(target).getID() != cinema.getCineplexID()) {
			// User trying to delete the cinema that does not exist in cineplex
			// raise an error
			throw new IllegalArgumentException("Cinema does not exist in the Cineplex");
		}
		cineplexes.get(target).removeCinema(cinema);
		cinema.setCineplexid(-1);
		System.out.println("The list of Cinemas under Cinema " + cineplexes.get(target).getName() + " is:");
		printCinemas(target);
	}

	@Override
	public void removeCinema(Cinema cinema) throws IllegalArgumentException {
		if (cinema.getCineplexID() != -1) {
			// There exist some cineplex that we need to modify
			int id = cinema.getCineplexID();
			try {
				int target = findCineplex(id);
				System.out.println("Cinema to be remove also found in a Cineplex");
				System.out.println("Cineplex affected:");
				cineplexes.get(target).printCineplexCinemas();
				cineplexes.get(target).removeCinema(cinema);
				cinema.setCineplexid(-1);
				System.out.println("Cinema has been removed from Cineplex");
			} catch (IllegalArgumentException ex) {
				throw new IllegalArgumentException("Cineplex is not found");
			}
		}
		System.out.println("Cinema to be remove not found in any Cineplex");
		// Not found
		// Do not need to throw error since cinema might not exist
	}

	@Override
	public Cineplex getCineplex(int id) throws IllegalArgumentException {
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			Cineplex c = it.next();
			if (c.getID() == id) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cineplex is not found");
	}

	@Override
	public Cineplex getCineplex(String name) throws IllegalArgumentException {
		for (Iterator<Cineplex> it = cineplexes.iterator(); it.hasNext();) {
			Cineplex c = it.next();
			if (c.getName().equals(name)) {
				return c;
			}
		}
		// Not found
		throw new IllegalArgumentException("Cineplex is not found");
	}
}