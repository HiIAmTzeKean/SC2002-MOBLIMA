package cinemapackage;

public interface ICinemaBooking {
    /**
     * Book a seat with given row and col. Additional information such as customerID is also stored 
     * @param seatRow
     * @param seatCol
     * @param customerID
     */
    void bookSeat(String seatRow, int seatCol, int customerID);
    boolean isBooked(String seatRow, int seatCol);
    void removeBooking(int cinemaID, String seatRow, int seatCol);
    float getMultiplier();
    void printCinemaLayout();
    String getCinemaCode();
    int getCineplexID();
}
