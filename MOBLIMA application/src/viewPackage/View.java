package viewPackage;
import java.util.InputMismatchException;
import java.util.Scanner;

import viewPackage.customerpackage.CustomerView;

// !! has not been tested yet

public class View {

	public static void displayMenu() {
		System.out.println("WELCOME TO MOBLIMA");
		System.out.println("--------------------------------------");
		System.out.println("\t\tMenu\t\t");
		System.out.println("--------------------------------------");
		System.out.println("Choice 1 : Staff");
		System.out.println("Choice 2 : Customer"); 
		System.out.println("choice 3 : exit MOBLIMA"); 
		System.out.println("--------------------------------------");
	}

	public static void start() {
		// TODO - implement View.start
		
		
		Scanner sc = new Scanner(System.in);
		int choice = 0; 

		

		// if ( choice==1 || choice==2 ){ 
		do { 
			displayMenu(); 
			System.out.println("\nEnter choice : ");

			try { 
				choice = sc.nextInt(); 
				} catch (InputMismatchException e){ 
					System.out.println(e.toString());
			}

			switch(choice) { 
			case 1: viewPackage.staffpackage.StaffAuth.login(); break; 
			case 2: CustomerView.start(); break; 
			case 3: return; 
			default : System.out.println("Enter valid choice");
			choice = 0;

			}
		} while (choice>=0 && choice <=3);
		// }
		// else throw new UnsupportedOperationException("Invalid Value");
		
		sc.close();
	}

}
