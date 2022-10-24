package cinemapackage;
import customerpackage.Customer;


public class Seat {

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
		System.out.println("Seat " + x + "," + y + "booked.");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		customer = customer;
	}

}