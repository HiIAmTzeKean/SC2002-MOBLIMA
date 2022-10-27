package cinemapackage;

import java.util.ArrayList;

import cineplexpackage.ICineplex;

public interface ICinema {
	/**
	 * Create a new cinema in cinema manager
	 * @param cinemaID
	 * @param name
	 * @param type
	 */
	void createCinema(int cinemaID, String name, String type);

	/**
	 * Search through array of cinemas and locate the id to be delete. If the ID is found, success
	 * message will be printed else
	 * 
	 * @param cinemaID
	 * @param cineplexManager
	 */
	void deleteCinema(int cinemaID, ICineplex cineplexManager);


	/**
	 * Print all cinema under CinemaManger
	 */
	void printCinemas();

	/**
	 * Finds the requested cinemaID and returns the cinema object
	 * @param cinemaID
	 * @return
	 */
	Cinema getCinema(int cinemaID);
	void getCinemaCode(int cinemaID);
	void setCinemaCode(int cinemaID, int code);
	void getCinemaType(int cinemaID);
	void getCineplexID(int cinemaID);
	void setCineplexID(int cinemaID, int cineplexID);
	void printCinemaLayout(int cinemaID);
}