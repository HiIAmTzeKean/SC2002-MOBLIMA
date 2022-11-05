package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.LayoutStyle.ComponentPlacement;

import cinemapackage.CinemaManager;
import cinemapackage.ICinema;
import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import viewPackage.View;

public class StaffCinema extends View {

    public static void displayMenu() {
        System.out.println("--------------------------------------");
        System.out.println("Cineplex Menu");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : create cineplex");
        System.out.println("choice 2 : delete cineplex");
        System.out.println("choice 3 : update cineplex and cinemas");
        System.out.println("choice 5 : Go Back to Staff Main Menu");
        System.out.println("--------------------------------------");
    }

    public static void createCineplex() {
        enum createCineplexState {NAME, LOCATION, CREATE};
        ICineplex cineplexHandler = CineplexManager.getInstance();
        createCineplexState state = createCineplexState.NAME;
        String name = null;
        String location = null;
        boolean complete = false;

        System.out.println("\t\tCreating Cineplex");
        System.out.println("-------------------------------------");

        while (!complete) {
            switch(state){
                case NAME:
                    try{
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex name to be created");
                        name = sc.nextLine();
                        if (name.equals("0")) return;
                    }
                    catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = createCineplexState.NAME;
                        break;
                    }
                case LOCATION:
                    try{
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex location to be created");
                        location = sc.nextLine();
                        if (location.equals("0")) return;
                    }
                    catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = createCineplexState.LOCATION;
                        break;
                    }
                case CREATE:
                try{
                    cineplexHandler.createCineplex(name, location);
                    complete = true;
                }
                catch(IllegalArgumentException e){
                    System.out.println("Unable to create new cineplex!");
                    System.out.println("Exiting function!");
                    return;
                }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("\t\tCreation complete");
        System.out.println("-------------------------------------");
    }

    public static void deleteCineplex(){
        ICineplex cineplexHandler = CineplexManager.getInstance();
        int id = 0;
        boolean complete = false;
        int state = 1;

        cineplexHandler.printCineplexes();
        System.out.println("\t\tDeleting  Cineplex");
        System.out.println("-------------------------------------");

        while (!complete){
            switch(state){
                case 1:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex ID to be deleted");
                        id = sc.nextInt();
                        if (id==0) return;
                    }catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = 1;
                        break;
                    }
        }
        
        cineplexHandler.deleteCineplex(id);

        System.out.println("-------------------------------------");
    }
    public static void start() {
        ICinema cinemaHandler = CinemaManager.getInstance();
        ICineplex cineplexHandler = CineplexManager.getInstance();
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do {
            displayMenu();
            System.out.println("Enter choice");

            choice = sc.nextInt();
            System.out.println("\f");

            switch (choice) {
                case 1:
                    createCineplex();
                    break;
                case 2:
                    cineplexHandler.printCineplexes();
                    System.out.println("\t\tDeleting  Cineplex");
                    System.out.println("-------------------------------------");
                    System.out.println("Enter cineplex ID to be deleted");
                    
                    id = sc.nextInt();
                    cineplexHandler.deleteCineplex(id);

                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    updateCineplex();
                    break;
                case 4:
                    CinemaManager.close();
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Staff Cinema Menu");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }

        } while (choice <= 4 && choice >= 0);
    }

    private static void updateCineplex() {
        ICinema cinemaHandler = CinemaManager.getInstance();
        ICineplex cineplexHandler = CineplexManager.getInstance();
        Scanner sc = new Scanner(System.in);
        int ch = 0, ID;
        String name = new String();

        try {
            do {

                System.out.println("--------------------------------------");
                System.out.println("Update Cineplex Menu ");
                System.out.println("--------------------------------------");
                System.out.println("choice 1 : create cinema");
                System.out.println("choice 2 : remove cinema");
                // System.out.println("choice 3 : !! Set Cineplex ID/update cinema to cineplex
                // ID/cinema code");
                System.out.println("choice 3 : Set Cineplex Name");
                System.out.println("choice 4 : Set Cineplex Location");
                System.out.println("choice 5 : Go Back to Cineplex Menu");
                System.out.println("--------------------------------------");

                System.out.println("Enter choice");

                ch = sc.nextInt();

                switch (ch) {
                    case 1:

                        System.out.println("-------------------------------------");
                        System.out.println("Creating Cinema");
                        System.out.println("-------------------------------------");

                        System.out.println("Enter cinema CODE (length 3) to be created");
                        name = sc.next();
                        System.out.println("Enter cinema type to be created");
                        String type = new String();
                        type = sc.next();

                        cinemaHandler.createCinema(name, type);

                        break;
                    case 2:

                        System.out.println("-------------------------------------");
                        System.out.println("Deleting  Cinema");
                        System.out.println("-------------------------------------");
                        cinemaHandler.printCinemas();
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cinema ID to be deleted");
                        ID = sc.nextInt();

                        cinemaHandler.deleteCinema(ID, cineplexHandler);
                        System.out.println("-------------------------------------");
                        System.out.println("Cinema Deleted");
                        System.out.println("-------------------------------------");
                        break;

                    case 3:

                        System.out.println("-------------------------------------");
                        System.out.println("Setting New Name for Cineplex");
                        System.out.println("-------------------------------------");
                        cineplexHandler.printCineplexes();
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID");
                        ID = sc.nextInt();
                        System.out.println("Enter new cinema name");
                        name = sc.next();

                        cineplexHandler.setName(ID, name);
                        System.out.println("-------------------------------------");
                        System.out.println("new name set for cineplex");
                        System.out.println("-------------------------------------");

                        break;
                    case 4:

                        System.out.println("-------------------------------------");
                        System.out.println("Setting New Name for Cineplex");
                        System.out.println("-------------------------------------");
                        cineplexHandler.printCineplexes();
                        System.out.println("-------------------------------------");
                        System.out.println("Enter cineplex ID");
                        ID = sc.nextInt();
                        System.out.println("Enter new cineplex location");
                        name = sc.next();

                        cineplexHandler.setLocation(ID, name);
                        System.out.println("-------------------------------------");
                        System.out.println("new location set for cineplex");
                        System.out.println("-------------------------------------");
                        break;
                    case 5: // back to StaffCinema menu
                        return;
                    default:
                        System.out.println("Enter valid choice");
                        ch = 0;
                }

            } while (ch <= 5 && ch >= 0);
            sc.close();
        } catch (InputMismatchException e) {
            System.out.println(e.toString());
            sc.next();
            updateCineplex();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            sc.next();
            updateCineplex();
        }
    }

}
