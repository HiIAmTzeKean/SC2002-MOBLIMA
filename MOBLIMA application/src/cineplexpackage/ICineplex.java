package cineplexpackage;

import cinemapackage.Cinema;

public interface ICineplex {

	void createCineplex(String name, String location) throws IllegalArgumentException;

	void deleteCineplex(int CineplexID) throws IllegalArgumentException;
	
	String getName(int CineplexID) throws IllegalArgumentException;
	
	void setName(int CineplexID, String name) throws IllegalArgumentException;
	
	String getLocation(int CineplexID) throws IllegalArgumentException;
	/**
	 * Sets the location of a specified cineplex object
	 * @param CineplexID
	 * @param Location
	 * @throws IllegalArgumentException
	 */
	void setLocation(int CineplexID, String Location) throws IllegalArgumentException;
	/**
	 * Adds a new cinema object to a cineplex specified
	 * @param CineplexID
	 * @param cinema
	 * @throws IllegalArgumentException
	 */
	void addCinema(int CineplexID, Cinema cinema) throws IllegalArgumentException;
	/**
	 * Finds cineplex object by CineplexID specified
	 * Removes specified cinema from cineplex object
	 * If not found raise IllegalArgumentException
	 * @param CineplexID
	 * @param cinema
	 */
	void removeCinema(int CineplexID, Cinema cinema) throws IllegalArgumentException;

	/**
	 * Uses the CineplexID stored in cinema to find the cineplex object
	 * Used when CineplexID of cineplex is not known
	 * @param cinema
	 */
	void removeCinema(Cinema cinema) throws IllegalArgumentException;
	/**
	 * Prints all cineplex objects in CineplexManager
	 */
	void printCineplexes();
	/**
	 * Finds cineplex object by CineplexID specified
	 * If not found raise IllegalArgumentException
	 * @param CineplexID
	 * @return Cineplex object
	 */
	Cineplex getCineplex(int CineplexID) throws IllegalArgumentException;
}