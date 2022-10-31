package cinemapackage;

public interface ICinemaBooking {
    void bookSeat(String seatRow, int seatCol, int customerID);
    boolean isBooked(String seatRow, int seatCol);
    void removeBooking(int cinemaID, String seatRow, int seatCol);
    float getMultiplier();
    void printCinemaLayout();
    String getCinemaCode();
    int getCineplexID();
}
