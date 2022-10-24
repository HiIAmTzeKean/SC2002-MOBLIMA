package cineplexpackage;

import java.util.ArrayList;

import cinemapackage.Cinema;

public interface ICineplex {
	
	ArrayList<Cineplex> getCineplexes();

	void createCineplex(int id, String name, String location);

	void deleteCineplex(int id);
	
	String getName(int id);
	
	void setName(int id, String name);
	
	String getLocation(int id);

	void setLocation(int id, String Location);

	void addCinema(int id, Cinema cinema);

	/**
	 * Finds cineplex object by id specified
	 * Removes specified cinema from cineplex object
	 * If not found raise IllegalArgumentException
	 * @param id
	 * @param cinema
	 */
	void removeCinema(int id, Cinema cinema);

	void removeCinemaWithoutID(Cinema cinema);

	/**
	 * Finds cineplex object by id specified
	 * If not found raise IllegalArgumentException
	 * @param id
	 * @return Cineplex object
	 */
	Cineplex getCineplex(int id);
}