package cinemapackage;

import customerpackage.Customer;

public interface ICinemaBooking {
    void bookSeat(String seatRow, int seatCol, Customer customer);
    boolean isBooked(String seatRow, int seatCol);
    void removeBooking(int cinemaID, String seatRow, int seatCol);
}
