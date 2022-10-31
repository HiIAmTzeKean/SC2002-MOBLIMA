package cinemapackage;
import cineplexpackage.ICineplex;

/**
 * Interface for CinemaManger interaction
 */
public interface ICinema {
	/**
	 * Create a new cinema in cinema manager. Performs check if size of code is 3.
	 * Performs check if type is valid.
	 * @param code of length 3
	 * @param type ["Platinum", "Gold", "Sliver"]
	 */
	void createCinema(String code, String type);
	/**
	 * Search through array of cinemas and locate the id to be delete. If the ID is found, success
	 * message will be printed else
	 * 
	 * @param cinemaID
	 * @param cineplexManager
	 */
	void deleteCinema(int cinemaID, ICineplex cineplexManager) throws IllegalArgumentException;
	/**
	 * Print all cinema under CinemaManger
	 */
	void printCinemas();
	/**
	 * Finds the requested cinemaID and returns the cinema object
	 * Used in showtime class
	 * @param cinemaID
	 * @return
	 */
	Cinema getCinema(int cinemaID) throws IllegalArgumentException;
	String getCinemaCode(int cinemaID) throws IllegalArgumentException;
	void setCinemaCode(int cinemaID, String code) throws IllegalArgumentException;
	CinemaType getCinemaType(int cinemaID) throws IllegalArgumentException;
	int getCineplexID(int cinemaID) throws IllegalArgumentException;
	int getCineplexID(String cinemaCode) throws IllegalArgumentException;
	void setCineplexID(int cinemaID, int cineplexID) throws IllegalArgumentException;
	/**
	 * Prints the target Cinema layout
	 * @param cinemaID
	 */
	void printCinemaLayout(int cinemaID) throws IllegalArgumentException;
	/**
	 * Prints the target Cinema layout
	 * @param cinemaCode
	 * @throws IllegalArgumentException
	 */
	void printCinemaLayout(String cinemaCode) throws IllegalArgumentException;
}