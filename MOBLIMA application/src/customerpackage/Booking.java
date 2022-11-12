package customerpackage;

import java.io.Serializable;

import showtimepackage.Showtime;

/**
 * Booking object that stores a single entry of booking from a customer
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Booking implements Serializable {
    private String transactionID;
    private Showtime showtime;
    private float price;
    private Customer customer;
    private String seatRow;
    private int seatCol;
    private boolean coupleSeating = false;

    /**
     * Base constuctor for Booking class
     * 
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
     * 
     * @param transactionID
     * @param showtime
     * @param price
     * @param customer
     * @param seatRow
     * @param seatCol
     * @param coupleSeating
     */
    Booking(String transactionID, Showtime showtime, float price, Customer customer, String seatRow, int seatCol,
            boolean coupleSeating) {
        this(transactionID, showtime, price, customer, seatRow, seatCol);
        this.coupleSeating = coupleSeating;
    }

    /**
     * Getter for transaction ID of booking
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Setter for transaction ID of booking
     * 
     * @param transactionID
     */
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Returns the price paid by customer
     * 
     * @return
     */
    public float getPrice() {
        return price;
    }

    /**
     * Setter for price in booking object
     * 
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns the customer object that made the booking
     * 
     * @return
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the customer object to point to customer object passed in
     * 
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    
    /** 
     * @return String
     */
    public String getCustomerEmail() {
        return customer.getEmail();
    }

    /**
     * Function to print booking in a formatted manner
     */
    public void print() {
        System.out.printf("|   %-15s   |  %-40s  |  %-15s  | %-8s |  %-5.2f | %-15s |\n",
                transactionID,
                showtime.getMovieName(),
                showtime.getCinemaClass(),
                showtime.getDate(),
                price,
                coupleSeating ? "YES" : "NO");
    }
}
