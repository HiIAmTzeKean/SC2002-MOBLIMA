package cinemapackage;

/**
 * Interface to be implemented by Cinema class
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public interface ICinemaBooking {
    /**
     * Book a seat with given row and col. Additional information such as customerID
     * is also stored
     * 
     * @param seatRow
     * @param seatCol
     * @param customerID
     */
    void bookSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException;

    /**
     * Check if a seat is booked by a customer. Takes in coordinates of seat to
     * check
     * 
     * @param seatRow
     * @param seatCol
     * @return
     * @throws IllegalArgumentException if row or col not valid
     */
    boolean isBooked(String seatRow, int seatCol) throws IllegalArgumentException;

    /**
     * Removes a booking by a customer. Sets the isBook flag to 0 to indicate the
     * seat is avilable
     * 
     * @param cinemaID
     * @param seatRow
     * @param seatCol
     * @throws IllegalArgumentException if row or col not valid || if the seat was
     *                                  not booked.
     */
    void removeBooking(int cinemaID, String seatRow, int seatCol) throws IllegalArgumentException;

    /**
     * Returns a multiplier based on the type of the cinema
     * Each subclass will return a different multiplier
     * 
     */
    float getMultiplier();

    /**
     * Print layout. Each subclass will specialise and print a different format
     * 
     */
    void printCinemaLayout();

    /**
     * Accessor for cinema code
     * Alias for getCode() function
     * 
     * @return String cinema code
     */
    String getCinemaCode();

    /**
     * Setter to set cinema code.
     * Alais to setCode() function
     * 
     */
    void setCinemaCode(String code);

    /**
     * Accessor for cineplexID
     * 
     * @return int cineplexID
     */
    int getCineplexID();

    /**
     * Accessor to get cinemaType in String format
     * 
     * @return String cinema type
     */
    String getCinemaClass();

    /**
     * Accessor to get cinemaType in CinemaType enum
     * 
     * @return CinemaType enum
     */
    CinemaType getCinemaType();
}
