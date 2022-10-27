package cinemapackage;

import customerpackage.Customer;

public interface ICinemaBooking {
    void bookSeat(int cinemaID, String seatRow, int seatCol, Customer customer);
    boolean IsBooked(String seatRow, int seatCol);
    void removeBooking(int cinemaID, String seatRow, int seatCol);
}
