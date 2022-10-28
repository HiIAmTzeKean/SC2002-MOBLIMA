package customerpackage;
import agepackage.*;
import showtimepackage.*;
import java.util.Scanner;

//todo: implement booking history

public class Customer {
	public static Scanner scan = new Scanner(System.in);
	private String name;
	private int age;
	private long mobile;
	private String email;
	
	public void setCustomerDetails() {
		System.out.println("Enter the following customer details:- ");
		System.out.print("Enter name: ");
		name = scan.next();
		System.out.print("Enter age: ");
		age = scan.nextInt();
		System.out.print("Enter mobile: ");
		mobile = scan.nextLong();
		System.out.print("Enter email: ");
		email = scan.next();
	}
	
	public int getAge() {
		return age;
	}
}
