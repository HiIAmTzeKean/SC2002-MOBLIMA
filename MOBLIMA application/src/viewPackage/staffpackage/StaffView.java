package viewPackage.staffpackage;

import viewPackage.View;

import java.util.InputMismatchException;
import java.util.Scanner;


public class StaffView extends View {

    public static void displayMenu(){ 
		System.out.print("\033[H\033[2J");
		System.out.println("---------------------------------------------------");
        System.out.println("\t\tStaff Main Menu");
		System.out.println("---------------------------------------------------");
		System.out.println("Choice 1 : Create/Update/Remove Movie");
		System.out.println("Choice 2 : Create/Update/Remove Cinemas");
		System.out.println("Choice 3 : Create Showtimes");
		System.out.println("Choice 4 : Configure System Settings");
		System.out.println("Choice 5 : logout");
        System.out.println("---------------------------------------------------");
    } 

    public static void start(){
        int choice = 0 ; 
		
		while (true) {	
			displayMenu();
            try {
				System.out.println("Enter choice");
				sc = new Scanner(System.in); 
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
				case  1 : StaffMovie.start(); 
				break;
				case  2 : StaffCinema.start(); 
				break;
				case  3 : StaffShowtime.start(); 
				break;
				case  4 : StaffSystem.start(); 
				break;
				case  5 : return;
				//break;
				default : 
					System.out.println("Enter valid choice");
					choice = 0;		
			}

		}
	}
}
    

