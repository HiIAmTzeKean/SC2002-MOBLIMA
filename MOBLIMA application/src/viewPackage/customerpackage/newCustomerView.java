package viewPackage.customerpackage;
import java.util.*;

import javax.swing.Icon;
import javax.swing.plaf.InputMapUIResource;

import daypackage.*;
import cineplexpackage.*;
import customerpackage.*;
import moviepackage.*;
import viewPackage.*;

public class newCustomerView extends View {
    public static void start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("MOBLIMA Customer View\n");
        int choice = 0;
        while(true){
            displayMenu();
            try{
                System.out.println("Enter Choice");
                choice = sc.nextInt();
                if(choice<1 || choice>3){
                    System.out.println("Invalid input!");
                    waitForEnter(null);
                    continue;
                }
            }
            catch(InputMismatchException e){
                inputMismatchHandler();
                waitForEnter(null);
                continue;
            }
            switch(choice){
                case 1:
                    bookMenu();
                    break;
                case 2:
                    seeBookingHistory();
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Staff Cinema Menu");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("Enter valid choice!");
                    choice = 0;
    
            }
        }   
    }
    public static void displayMenu(){
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Customer Main Menu");
        System.out.println("--------------------------------------");
        System.out.println("Choice 1 : Browse Movies");
        System.out.println("Choice 2 : Make a New Booking");
        System.out.println("Choice 3 : See Booking History and Add Reviews");
        System.out.println("Choice 4 : Return");
        System.out.println("--------------------------------------");
    }
    public static void bookMenu(){
        Scanner sc = new Scanner(System.in);
        enum bookMenuState{
            SELECTCINEPLEX, BROWSEMOVIE
        };
        bookMenuState state = bookMenuState.SELECTCINEPLEX;
        ICineplex cineplexHander = CineplexManager.getInstance();
        String cineplexChoice = null;
        Cineplex selectedCineplexObj = null;
        String movieChoice = null;
        boolean complete = false;
        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        while(!complete){
            switch(state){
                case SELECTCINEPLEX:
                    try{
                        System.out.println("[Enter 0 to return]");
                        cineplexHander.printCineplexes();
                        System.out.println("Choose a Cineplex from the Above Options:");
                        cineplexChoice = sc.nextLine();
                        if(cineplexChoice.equals("0")){
                            return;
                        }
                        selectedCineplexObj = cineplexHander.getCineplex(cineplexChoice);
                        state = bookMenuState.SELECTMOVIE;
                    }
                    catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = bookMenuState.SELECTCINEPLEX;
                        break;
                    }
                    catch(IllegalArgumentException e){
                        System.out.println(e);
                    }
                break;
                case BROWSEMOVIE:
                    try{
                        System.out.println("[Enter 0 to return]"); 
                        CustomerMovieListing movieListing = new CustomerMovieListing();
                        movieListing.movieSelection();
                        

                    }
                    catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = bookMenuState.SELECTMOVIE;
                    }
                
            }
        }

    }
}
