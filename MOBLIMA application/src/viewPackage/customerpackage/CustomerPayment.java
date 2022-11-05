package viewPackage.customerpackage;
import java.util.*;

import agepackage.Age;
import agepackage.IAge;
import customerpackage.Customer;

public class CustomerPayment {
	private static Scanner scan = new Scanner(System.in);
	private Customer c = null;
	int customerID = 0;
	
	public void setCustomerDetails() {
		String name = null;
		IAge age = null;
		int mobile = 0;
		String email = null;
		
		System.out.println("\nPlease enter your personal details: ");
		System.out.print("Name :");
		name = scan.next();
		c.setName(name);
		System.out.println();
		
		System.out.print("age :");
		int ageInt = scan.nextInt();
		age.setAgeNumber(ageInt);
		c.setAge((Age)age);
		System.out.println();
		
		System.out.print("Mobile :");
		mobile = scan.nextInt();
		c.setMobile(mobile);
		System.out.println();
		
		System.out.print("Email :");
		email = scan.next();
		c.setEmail(email);
		System.out.println();
		
		customerID = c.getID();
	}
	
	public int getCustomerID() {
		return customerID;
	}

}
