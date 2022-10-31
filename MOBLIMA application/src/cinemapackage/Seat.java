package cinemapackage;
import java.io.Serializable;

public class Seat implements Serializable{

	private boolean isBook;
	private int x;
	private int y;
	private int customerID;

	public Seat(int x, int y){
		this.x = x;
		this.y = y;
		isBook = false;
		customerID = -1;
	}

	public boolean isBooked() {
		return isBook;
	}
	public void setBooked(int customerID) {
		isBook = true;
		this.customerID = customerID;
		System.out.println("Seat " + x + "," + y + " is booked.");
	}
	public void setUnBooked() {
		isBook = false;
		System.out.println("Seat " + x + "," + y + " is unbooked.");
	}
	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomer(int customerID) {
		this.customerID = customerID;
	}

}