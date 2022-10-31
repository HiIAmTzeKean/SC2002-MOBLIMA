package customerpackage;

import showtimepackage.Showtime;

public class Booking {
    private String transactionID;
    private Showtime showtime;
    private float price;
    private Customer customer;

    Booking(String transactionID, Showtime showtime, float price, Customer customer) {
        this.transactionID = transactionID;
        this.showtime = showtime;
        this.price = price;
        this.customer = customer;
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
        System.out.println("===== Booking Entry =====");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Movie Name: " + showtime.getMovieName());
        System.out.println("Date of movie: " + showtime.getDate());
        System.out.println("Price: " + price);
        System.out.println("===== End =====");
    }
}
