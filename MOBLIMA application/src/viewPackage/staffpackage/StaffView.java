package viewPackage.staffpackage;

import viewPackage.View;

import java.util.InputMismatchException;
import java.util.Scanner;


public class StaffView extends View {

    public static void displayMenu(){ 
        System.out.println("\t\t\tStaff Main Menu");
		System.out.println("---------------------------------------------------");
		System.out.println("Choice 1 : Create/Update/Remove Movie listing");
		System.out.println("Choice 2 : Create/Update/Remove Cinemas");
		System.out.println("Choice 3 : Create/Update/Remove Showtimes");
		System.out.println("Choice 4 : Configure System Settings");
		System.out.println("Choice 5 : logout");
        System.out.println("---------------------------------------------------");
    } 



    public static void start(){
		
        Scanner sc = new Scanner(System.in); 
        int choice = 0 ; 
		
		 do {	
			displayMenu();
            try {
                System.out.println("Enter choice"); 
                choice = sc.nextInt();
            } catch(InputMismatchException e ){ 
                System.out.println(e.toString());
            } 
			//clearing the console
			System.out.print("\033[H\033[2J");
			
			switch (choice) { 
				case  1 : StaffMovie.start(); 
				break;
				case  2 : StaffCinema.start(); 
				break;
				case  3 : StaffShowtime.start(); 
				break;
				case  4 : StaffSystem.start(); 
				break;
				case  5 : StaffAuth.logout();
				break;
				default : System.out.println("Enter valid choice");
						  choice = 0;		
			}

		}while(choice<6 && choice>=0);
        
		System.out.println("\f");
        sc.close(); 
	}

}
    

