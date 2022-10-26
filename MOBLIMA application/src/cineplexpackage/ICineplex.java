package cineplexpackage;

import java.util.ArrayList;

import cinemapackage.Cinema;

public interface ICineplex {
	
	ArrayList<Cineplex> getCineplexes();

	void createCineplex(int id, String name, String location) throws IllegalArgumentException;

	void deleteCineplex(int id) throws IllegalArgumentException;
	
	String getName(int id) throws IllegalArgumentException;
	
	void setName(int id, String name) throws IllegalArgumentException;
	
	String getLocation(int id) throws IllegalArgumentException;

	void setLocation(int id, String Location) throws IllegalArgumentException;

	void addCinema(int id, Cinema cinema) throws IllegalArgumentException;

	/**
	 * Finds cineplex object by id specified
	 * Removes specified cinema from cineplex object
	 * If not found raise IllegalArgumentException
	 * @param id
	 * @param cinema
	 */
	void removeCinema(int id, Cinema cinema) throws IllegalArgumentException;

	/**
	 * Uses the id stored in cinema to find the cineplex object
	 * Used when id of cineplex is not known
	 * @param cinema
	 */
	void removeCinema(Cinema cinema) throws IllegalArgumentException;

	/**
	 * Finds cineplex object by id specified
	 * If not found raise IllegalArgumentException
	 * @param id
	 * @return Cineplex object
	 */
	Cineplex getCineplex(int id) throws IllegalArgumentException;
}