package viewPackage;

import java.util.Scanner;

public class MOBLIMA extends View {
    public static void main(String args []){ 
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------");
        System.out.println("\t\tWELCOME TO MOBLIMA\t\t"); 
        System.out.println("----------------------------------");
        start(); 
        System.out.println("----------------------------------");
        System.out.println("\t\tEXITING MOBLIMA\t\t"); 
        System.out.println("----------------------------------");
    }
    public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tMenu\t\t");
		System.out.println("--------------------------------------");
		System.out.println("Choice 1 : Staff");
		System.out.println("Choice 2 : Customer");
		System.out.println("choice 3 : exit MOBLIMA");
		System.out.println("--------------------------------------");
	}
    
    public static void start() {
		sc = new Scanner(System.in);
		int choice = 0;
		while (true) {
			displayMenu();
			try {
				System.out.println("Enter choice");
				choice = sc.nextInt();
				if (choice>5 || choice<1) {
					System.out.println("Invalid input!");
					waitForEnter(null);
					continue;
				}
			} catch (InputMismatchException e) {
				inputMismatchHandler();
				waitForEnter(null);
				continue;
			}
			
			switch (choice) {
				case 1:
					StaffAuth.login();
					break;
				case 2:
					CustomerView.start();
					break;
				case 3:
					return;
				default:
					System.out.println("Enter valid choice");
					choice = 0;
			}
		}
	}
}
