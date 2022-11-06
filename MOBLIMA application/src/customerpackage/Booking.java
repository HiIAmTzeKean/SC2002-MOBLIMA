package customerpackage;

import java.io.Serializable;

import showtimepackage.Showtime;

/**
 * Booking object that stores a single entry of booking from a customer
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Booking implements Serializable{
    private String transactionID;
    private Showtime showtime;
    private float price;
    private Customer customer;
    private String seatRow;
    private int seatCol;
    private boolean coupleSeating = false;

    /**
     * Base constuctor for Booking class
     * @param transactionID
     * @param showtime
     * @param price
     * @param customer
     * @param seatRow
     * @param seatCol
     */
    Booking(String transactionID, Showtime showtime, float price, Customer customer, String seatRow, int seatCol) {
        this.transactionID = transactionID;
        this.showtime = showtime;
        this.price = price;
        this.customer = customer;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }
    /**
     * Overide the base constructor to indicate couple seating
     * @param transactionID
     * @param showtime
     * @param price
     * @param customer
     * @param seatRow
     * @param seatCol
     * @param coupleSeating
     */
    Booking(String transactionID, Showtime showtime, float price, Customer customer, String seatRow, int seatCol, boolean coupleSeating) {
        this(transactionID, showtime, price, customer, seatRow, seatCol);
        this.coupleSeating = coupleSeating;
    }
    public String getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getCustomerEmail(){
        return customer.getEmail();
    }
    public void print() {
        System.out.printf("|   %-15s   |  %-30s  |  %-15s  | %-8s |  %-5.2f | %-15s |\n",
                        transactionID,
                        showtime.getMovieName(),
                        showtime.getCinemaClass(),
                        showtime.getDate(),
                        price,
                        coupleSeating?"YES":"NO");
    }
}
