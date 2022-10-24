package cinemapackage;

import java.util.ArrayList;

import cineplexpackage.ICineplex;

public interface ICinema {

	/**
	 * Create a new cinema in cinema manager
	 * @param id
	 * @param name
	 * @param type
	 */
	void createCinema(int id, String name, String type);

	/**
	 * Search through array of cinemas and locate the id to be delete. If the ID is found, success
	 * message will be printed else
	 * 
	 * @param id
	 * @param cineplexManager
	 */
	void deleteCinema(int id, ICineplex cineplexManager);

	/**
	 * Returns an array list of objects(cinema)
	 * @return
	 */
	ArrayList<Cinema> getCinemas();

	/**
	 * Print all cinema that cinema manager has
	 */
	void printCinemas();

	Cinema getCinema(int id);
}