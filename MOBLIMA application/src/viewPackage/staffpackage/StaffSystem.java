package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import daypackage.Day;

import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;
import viewPackage.View;

public class StaffSystem extends View {

    private static IShowtimeSystem ssHandler = ShowtimeManager.getInstance(); 
  

 
    public static void displayMenu(){ 
        System.out.println("--------------------------------------");
        System.out.println("Update System Settings");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Set new BasePrice");
		System.out.println("choice 2 : Set new  Multiplier");
        System.out.println("choice 3 : Set new Holiday"); 

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

				case  1 : setBasePrice();
                        
				break;
				case  2 : setMultiplier();
                         
                break;
                case  3 : setHoliday();
                break;
                case  4 : 
                         System.out.println("--------------------------------------");
                         System.out.println("Exiting staff system menu"); 
                         System.out.println("--------------------------------------");
                         ShowtimeManager.close(); 
                         return;
				default : System.out.println("Enter valid choice");
						  choice = 0;		
			}
			
		}while(choice<4 && choice>=0);
    }

    public static void setBasePrice(){ 
        System.out.println("--------------------------------------");
        System.out.println("Setting new base price"); 
        System.out.println("-------------------------------------"); 
        

        float BasePrice;
        while(true){
                try {
                    System.out.println("Enter the new base price"); 
                    BasePrice = sc.nextFloat();
                    break;
                    
                } catch ( InputMismatchException e){ 
                    System.out.println(e.toString());
                }
        }
        // !! baseprice doesnt throw error so no need for exception handling ?
        // !! confirm and remove pls 

       // try{
        ssHandler.setBasePrice(BasePrice);
        // } catch ( IllegalArgumentException e ){ 
        //         System.out.println(e.toString());
        // }
         

        System.out.println("--------------------------------------");
        System.out.println("\t\tNew base Price Set! "); 
        System.out.println("--------------------------------------");

    }

    public static void setMultiplier(){ 
        System.out.println("--------------------------------------");
        System.out.println("Setting new multiplier"); 
        System.out.println("--------------------------------------"); 
        

        float multiplier;
        while(true){
                try {
                    System.out.println("Enter the new multiplier"); 
                    multiplier = sc.nextFloat();
                    break;
                    
                } catch ( InputMismatchException e){ 
                    System.out.println(e.toString());
                }
        }
        // !! mutltiplier doesnt throw error so no need for exception handling ?
        // !! confirm and remove pls 

       // try{
        Day.setMultiplier(multiplier);
        // } catch ( IllegalArgumentException e ){ 
        //         System.out.println(e.toString());
        // }
         

        System.out.println("--------------------------------------");
        System.out.println("\t\tNew multiplier Set! "); 
        System.out.println("--------------------------------------");

    }


    public static void setHoliday(){ 

        System.out.println("--------------------------------------");
        System.out.println("Setting new day as holiday"); 
        System.out.println("--------------------------------------"); 

        enum dayEnum {DATE, TIME};
        dayEnum  state = dayEnum.DATE;
        String date, time; 

        boolean completed = false; 
        


        while(!completed){
                switch(state){ 
                   
                    case DATE :  
                    
                    try {
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter Full Date (format:YYYYMMDD): ");
                        date = sc.next();
                        if (date.equals("0")) return;
                        state = dayEnum.TIME;
                    }
                    catch(InputMismatchException e){
                        sc.nextLine(); 
                        state = dayEnum.DATE;
                        break;
                    }

                    case TIME :  
                    
                    try {
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter TIME (24HR E.G. 1300): ");
                        time = sc.next();
                        if (date.equals("0")) { 
                            state = dayEnum.DATE;
                            break;
                        }
                        completed = true; 
                    }
                    catch(InputMismatchException e){
                        sc.nextLine(); 
                        state = dayEnum.TIME;
                        break;
                    }


                    default:
                    state = dayEnum.DATE;
                    break;
                    
                }
        }
        
       
       
       try{
            Day day = new Day(date,time); 
            ssHandler.setHoliday(day);
        } catch ( IllegalArgumentException e ){ 
                System.out.println(e.toString());
                return; 
        }
         

        System.out.println("--------------------------------------");
        System.out.println("\t\tNew Holiday Set! "); 
        System.out.println("--------------------------------------");

    }
}
