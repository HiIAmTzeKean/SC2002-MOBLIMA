package cinemapackage;
import cineplexpackage.ICineplex;

/**
 * Interface for CinemaManger interaction
 * @apiNote ICineplex
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public interface ICinema {
	/**
	 * Create a new cinema in cinema manager. Performs check if size of code is 3.
	 * Performs check if type is valid.
	 * @param code of length 3
	 * @param type ["Platinum", "Gold", "Silver"]
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
	 * Prints all cinema under CinemaManger.
	 * Format printed is {Code,Type}
	 */
	void printCinemas();
	/**
	 * Prints all cinema under CinemaManger in admin view.
	 * Format printed is {Id,Code,Type}
	 */
	void printCinemasAdmin();
	/**
	 * Finds the requested cinemaID and returns the cinema object. Function should be used with care and only
	 * by staff object to modify Cinema template
	 * Used in showtime class.
	 * @param cinemaID
	 * @return Cinema
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	Cinema getCinema(int cinemaID) throws IllegalArgumentException;
	/**
	 * Finds requested cinemaID in and returns the cinema code of target
	 * @param cinemaID
	 * @return String of cinema code
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	String getCinemaCode(int cinemaID) throws IllegalArgumentException;
	/**
	 * Sets cinema code of target cinema. Finds the ID of the cinema in array
	 * and updates the Cinema code.
	 * @param cinemaID
	 * @return String of cinema code
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	void setCinemaCode(int cinemaID, String code) throws IllegalArgumentException;
	CinemaType getCinemaType(int cinemaID) throws IllegalArgumentException;
	void setCinemaType(int cinemaID,CinemaType type) throws IllegalArgumentException;
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
	Cinema cloneCinemaByID(int id);
}