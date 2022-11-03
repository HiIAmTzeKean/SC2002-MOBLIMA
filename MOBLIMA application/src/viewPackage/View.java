package viewPackage;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import viewPackage.customerpackage.CustomerView;


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

	public static void start() throws InputMismatchException {
		// TODO - implement View.start
		
		
		Scanner sc = new Scanner(System.in);
		int choice = 0; 

		try { 
			do { 
				displayMenu(); 
				System.out.println("\nEnter choice : ");

			
				choice = sc.nextInt(); 

				switch(choice) { 
				case 1: viewPackage.staffpackage.StaffAuth.login(); break; 
				case 2: CustomerView.start(); break; 
				case 3: return; 
				default : System.out.println("Enter valid choice");
				choice = 0;

				}
			} while (choice>=0 && choice <=3);
		}catch( NoSuchElementException e){
			System.out.println(e.toString());
			sc.nextLine();
			View.start();
		}
		
		sc.close();
	}

}
