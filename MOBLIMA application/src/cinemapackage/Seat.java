package cinemapackage;
import java.io.Serializable;

import customerpackage.Customer;


public class Seat implements Serializable{

	private boolean isBook;
	private int x;
	private int y;
	private Customer customer;

	public Seat(int x, int y){
		this.x = x;
		this.y = y;
		isBook = false;
		customer = null;
	}

	public boolean IsBooked() {
		return isBook;
	}

	public void SetBooked() {
		isBook = true;
		System.out.println("Seat " + x + "," + y + " is booked.");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}