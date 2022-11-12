package cinemapackage;

import cineplexpackage.ICineplex;

/**
 * Interface for CinemaManger interaction
 * 
 * @apiNote ICineplex
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public interface ICinema {
	/**
	 * Create a new cinema in cinema manager. Performs check if size of code is 3.
	 * Performs check if type is valid.
	 * 
	 * @param code of length 3
	 * @param type ["Platinum", "Gold", "Silver"]
	 */
	void createCinema(String code, String type);

	/**
	 * Search through array of cinemas and locate the id to be delete. If the ID is
	 * found, success
	 * message will be printed else
	 * 
	 * @param cinemaID integer variable
	 * @param cineplexManager ICineplex object
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
	 * Finds the requested cinemaID and returns the cinema object. Function should
	 * be used with care and only
	 * by staff object to modify Cinema template
	 * Used in showtime class.
	 * 
	 * @param cinemaID integer variable
	 * @return Cinema
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	Cinema getCinema(int cinemaID) throws IllegalArgumentException;

	/**
	 * Finds requested cinemaID in and returns the cinema code of target
	 * 
	 * @param cinemaID integer variable
	 * @return String of cinema code
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	String getCinemaCode(int cinemaID) throws IllegalArgumentException;

	/**
	 * Sets cinema code of target cinema. Finds the ID of the cinema in array
	 * and updates the Cinema code.
	 * 
	 * @param cinemaID integer variable
	 * @return String of cinema code
	 * @throws IllegalArgumentException if cinemaID not found
	 */
	void setCinemaCode(int cinemaID, String code) throws IllegalArgumentException;

	/**
	 * Returns an enum of the type of cinema requested
	 * 
	 * @param cinemaID integer variable
	 * @return CinemaType enum type
	 * @throws IllegalArgumentException
	 */
	CinemaType getCinemaType(int cinemaID) throws IllegalArgumentException;

	/**
	 * Setter to modify the cinema type
	 * 
	 * @param cinemaID integer variable
	 * @param type CinemaType variable
	 * @throws IllegalArgumentException
	 */
	void setCinemaType(int cinemaID, CinemaType type) throws IllegalArgumentException;

	/**
	 * Returns the cineplex ID the cinema belongs to.
	 * Returns -1 if there is no valid cineplex
	 * 
	 * @param cinemaID integer variable
	 * @return integer of the cineplex cinema belongs to
	 * @throws IllegalArgumentException
	 */
	int getCineplexID(int cinemaID) throws IllegalArgumentException;

	/**
	 * Returns the cineplex ID of a cinema
	 * 
	 * @param cinemaCode integer variable
	 * @return integer CineplexID target
	 * @throws IllegalArgumentException
	 */
	int getCineplexID(String cinemaCode) throws IllegalArgumentException;

	/**
	 * Setter for a cinema object to change the cineplex it belongs to
	 * 
	 * @param cinemaID integer variable
	 * @param cineplexID integer variable
	 * @throws IllegalArgumentException
	 */
	void setCineplexID(int cinemaID, int cineplexID) throws IllegalArgumentException;

	/**
	 * Prints the target Cinema layout
	 * 
	 * @param cinemaID integer variable
	 */
	void printCinemaLayout(int cinemaID) throws IllegalArgumentException;

	/**
	 * Prints the target Cinema layout
	 * 
	 * @param cinemaCode integer variable
	 * @throws IllegalArgumentException
	 */
	void printCinemaLayout(String cinemaCode) throws IllegalArgumentException;

	/**
	 * Clones a cinema object within manager
	 * Allows showtime object to store another cinema object through deep clone
	 * 
	 * @param ID integer variable
	 * @return Cinema object target
	 */
	Cinema cloneCinemaByID(int ID);
}