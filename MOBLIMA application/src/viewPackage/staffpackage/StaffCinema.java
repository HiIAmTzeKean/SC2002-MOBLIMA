package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import  cinemapackage.CinemaManager;
import  cinemapackage.ICinema;
import  cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import viewPackage.View;


public class StaffCinema extends View {

    private static ICinema cinemaHandler = CinemaManager.getInstance();
    private static ICineplex cineplexHandler = CineplexManager.getInstance(); 

    public static void displayMenu(){ 

        System.out.println("\t\tCineplex Menu");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : create cineplex");
		System.out.println("choice 2 : delete cineplex");
		System.out.println("choice 3 : update cineplex and cinemas");
        System.out.println("choice 5 : Go Back to Staff Main Menu");
        System.out.println("--------------------------------------");
   }

   public static void start(){ 
        int  choice = 0, id; 
        Scanner sc = new Scanner(System.in);

        do {	
			displayMenu();
			System.out.println("Enter choice"); 
            try {
                choice = sc.nextInt(); 
                System.out.println("\f");
            } catch(InputMismatchException e ){ 
                System.out.println(e.toString());
            } 
			
			switch (choice) { 
				case  1 : 

                        System.out.println("\t\tCreating Cineplex"); 
                        System.out.println("-------------------------------------");
                        // System.out.println("Enter cineplex ID to be created");
                        // id = sc.nextInt();
                        System.out.println("Enter cineplex name to be created");
                        String name = new String (); 
                        try {
                        name  = sc.next();
                        }  catch(InputMismatchException e ){ 
                            System.out.println(e.toString());
                        } 
                        System.out.println("Enter cineplex location to be created");
                        String location = new String();
                        try {
                            location = sc.next();
                        }  catch(InputMismatchException e ){ 
                                System.out.println(e.toString());
                        } 

                        cineplexHandler.createCineplex( name, location);

                        System.out.println("\t\tNew Cineplex Created"); 
                        break;

				case  2 : 
                        cineplexHandler.printCineplexes();
                        System.out.println("\t\tDeleting  Cineplex"); 
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID to be deleted");
                        

                        try {
                            id = sc.nextInt();
                        }  catch(InputMismatchException e ){ 
                                System.out.println(e.toString());
                        } 
                        
                        cineplexHandler.deleteCineplex(id);
                        System.out.println("\t\tCineplex Deleted"); 
				break;

                case  3 : 
                
                         updateCineplex();
				break;
				case  4 : 
                        CinemaManager.close(); 
                        System.out.println("\t\tExiting Staff Cinema Menu");
                        System.out.println("-------------------------------------");
                        StaffAuth.start();
				break;
				default : System.out.println("Enter valid choice");
						  choice = 0;		
			}
			
		}while(choice<=4 && choice>=0);
        sc.close();
   }

   private static void updateCineplex() {
		
	Scanner sc = new Scanner(System.in);
    int ch = 0 , ID; 
    String name = new String();
		
    do {

        System.out.println("\f");
        System.out.println("Update Cineplex Menu ");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : create cinema");
        System.out.println("choice 2 : remove cinema");
        // System.out.println("choice 3 : !! Set Cineplex ID/update cinema to cineplex ID/cinema code");
        System.out.println("choice 3 : Set Cineplex Name");
        System.out.println("choice 4 : Set Cineplex Location");
        System.out.println("choice 5 : Go Back to Cineplex Menu");
        System.out.println("--------------------------------------");
        
        System.out.println("Enter choice"); 
        
        ch = sc.nextInt(); 
        

        try { 
            switch (ch) { 
                case  1 :
                        //scanning arguments 
                        System.out.println("\t\tCreating Cinema"); 
                        System.out.println("-------------------------------------");
                    //     System.out.println("Enter cinema ID to be created");
                    //     ID = sc.nextInt();
                        System.out.println("Enter cinema name to be created");
                        name  = sc.next();
                        System.out.println("Enter cinema type to be created");
                        String type = new String();
                        type = sc.next();

                        // creating
                        cinemaHandler.createCinema(name, type);
                        System.out.println("\t\tNew Cinema Created"); 
                break;
                case  2 : 
                        //Scanning argument
                        cinemaHandler.printCinemas();
                        System.out.println("\t\tDeleting  Cineplex"); 
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID to be deleted");
                        ID = sc.nextInt();

                        // Deleting
                        cinemaHandler.deleteCinema(ID, cineplexHandler);
                        System.out.println("\t\tCinema Deleted"); 
                break;

                case  3 : 
                        cineplexHandler.printCineplexes();
                        System.out.println("\t\tSetting New Name for Cineplex"); 
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID");
                        ID = sc.nextInt();
                        System.out.println("Enter new cinema name");
                        name  = sc.next();

                
                        cineplexHandler.setName(ID, name);
                        System.out.println("\t\tnew name set for cineplex"); 

                break;
                case  4 : 
                        cineplexHandler.printCineplexes();
                        System.out.println("\t\tSetting New Name for Cineplex"); 
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID");
                        ID = sc.nextInt();
                        System.out.println("Enter new cinema location");
                        name  = sc.next();

                
                
                        cineplexHandler.setLocation(ID, name);
                        System.out.println("\t\tnew location set for cineplex"); 
                break;
                case  5 : // back to StaffCinema menu  
                        start();
                break;
                default : System.out.println("Enter valid choice");
                        ch = 0;		
            }
        } catch (InputMismatchException e ){ 
            System.out.println(e.toString());
        }
        
        
    } while(ch<=5 && ch>=0);
    sc.close(); 
   }

  

}// class bracket 





