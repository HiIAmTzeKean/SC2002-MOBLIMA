package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;


import daypackage.Day;
// import showtimepackage.IShowtime;
import showtimepackage.IShowtimeSystem;
// import showtimepackage.Showtime;
import showtimepackage.ShowtimeManager;
import viewPackage.View;

public class StaffSystem extends View {

    private static IShowtimeSystem ssHandler = ShowtimeManager.getInstance(); 
    //private static Day day = new Day(); 

 
    public static void displayMenu(){ 
        System.out.println("Update System Settings");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Set new BasePrice");
		System.out.println("choice 2 : Set new  Multiplier");
        System.out.println("choice 3 : Set new Holiday"); // showime obj calls setHoliday() no args
		System.out.println("choice 4 : Go Back");
        System.out.println("--------------------------------------");

    }
    
    public static void start(){ 
        Scanner sc = new Scanner(System.in); 
        int choice = 0; 
		
		do {	
			displayMenu();
			System.out.println("Enter choice"); 
            try { 
                choice = sc.nextInt(); 
                
            } catch(InputMismatchException e){
                System.out.println(e.toString());
            }
			
			switch (choice) { 

				case  1 : 
                        System.out.println("--------------------------------------");
                        System.out.println("Setting new base price"); 
                        System.out.println("-------------------------------"); 
                        float BasePrice;
                        try {
                            System.out.println("Enter the new base price"); 
                            BasePrice = sc.nextFloat();
                            ssHandler.setBasePrice(BasePrice);
                        } catch ( IllegalArgumentException e ){ 
                            System.out.println(e.toString());
                        }catch ( InputMismatchException e){ 
                            System.out.println(e.toString());
                        }

                        System.out.println("--------------------------------------");
                        System.out.println("\t\tNew base Price Set! "); 
                        System.out.println("--------------------------------------");
				break;
				case  2 : 
                        System.out.println("--------------------------------------");
                        System.out.println("Setting new  mutliplier ");         
                        System.out.println("-------------------------------"); 
                        float Multiplier;

                        try {
                            System.out.println("Enter the new  multiplier"); 
                            Multiplier = sc.nextFloat();
                            Day.setMultiplier(Multiplier);
                        } catch ( IllegalArgumentException e ){ 
                            System.out.println(e.toString());
                        }

    
                            
                            System.out.println("\t\tNew  multiplier Set! "); 
                        break;
				
                case  3 : 
                        System.out.println("--------------------------------------");
                        System.out.println("Setting Day as Holiday");         
                        System.out.println("-------------------------------"); 
 
                        
                        try {
                            System.out.println("Enter the full date <format>"); 
                            String date = sc.next();
                            
                            Day day = ssHandler.getDay(date);
                            day.setHoliday();

                        } catch ( IllegalArgumentException e ){ 
                            System.out.println(e.toString());
                        } catch(InputMismatchException e){ 
                            System.out.println(e.toString());
                        }
                            System.out.println("--------------------------------------");
                            System.out.println("\t\tHoliday Set! "); 
                            System.out.println("--------------------------------------");
                break;
                case  4 : 
                         System.out.println("--------------------------------------");
                         System.out.println("Exiting staff system menu"); 
                         System.out.println("--------------------------------------");
                         ShowtimeManager.close(); 
                         StaffView.start();
				break;
				default : System.out.println("Enter valid choice");
						  choice = 0;		
			}
			
		}while(choice<4 && choice>=0);
		
        sc.close(); 
    }
}
