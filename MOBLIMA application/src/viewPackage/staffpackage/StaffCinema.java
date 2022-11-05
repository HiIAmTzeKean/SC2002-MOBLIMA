package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;
import cinemapackage.CinemaManager;
import cinemapackage.ICinema;
import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import viewPackage.View;

public class StaffCinema extends View {

    public static void displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Cineplex Menu");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : create cineplex");
        System.out.println("choice 2 : delete cineplex");
        System.out.println("choice 3 : update cineplex and cinemas");
        System.out.println("choice 4 : Return");
        System.out.println("--------------------------------------");
    }

    private static void createCineplex() {
        enum createCineplexState {
            NAME, LOCATION, CREATE
        };
        ICineplex cineplexHandler = CineplexManager.getInstance();
        createCineplexState state = createCineplexState.NAME;
        String name = null;
        String location = null;
        boolean complete = false;
        sc = new Scanner(System.in);
        
        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("\t\tCreating Cineplex");
        System.out.println("-------------------------------------");

        while (!complete) {
            switch (state) {
                case NAME:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex name to be created");
                        name = sc.nextLine();
                        if (name.equals("0"))
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = createCineplexState.NAME;
                        break;
                    }
                case LOCATION:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex location to be created");
                        location = sc.nextLine();
                        if (location.equals("0")){
                            state = createCineplexState.NAME;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = createCineplexState.LOCATION;
                        break;
                    }
                case CREATE:
                    try {
                        cineplexHandler.createCineplex(name, location);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to create new cineplex!");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("\t\tCreation complete");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    public static void deleteCineplex() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        Scanner sc = new Scanner(System.in);
        int id = 0;
        boolean complete = false;
        int state = 1;

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("\t\tDeleting  Cineplex");
        System.out.println("-------------------------------------");
        cineplexHandler.printCineplexesAdmin();
        while (!complete) {
            switch (state) {
                case 1:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cineplex ID to be deleted");
                        id = sc.nextInt();
                        if (id == 0)
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = 1;
                        break;
                    }
                case 2:
                    try {
                        cineplexHandler.deleteCineplex(id);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to create new cineplex!");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("\t\tDeleted  Cineplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);

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
                    createCineplex();
                    break;
                case 2:
                    deleteCineplex();
                    break;
                case 3:
                    updateCineplex();
                    break;
                case 4:
                    CinemaManager.close();
                    CineplexManager.close();
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Staff Cinema Menu");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }

        }
    }

    private static void updateCinplexMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Update Cineplex Menu ");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : create cinema");
        System.out.println("choice 2 : remove cinema");
        System.out.println("choice 3 : Set Cineplex Name");
        System.out.println("choice 4 : Set Cineplex Location");
        System.out.println("choice 5 : Go Back to Cineplex Menu");
        System.out.println("--------------------------------------");
    }
    private static void createCinema(){
        enum createCinemaState {
            CODE, TYPE, CREATE
        };
        ICinema cinemaHandler = CinemaManager.getInstance();
        createCinemaState state = createCinemaState.CODE;
        String name = null;
        String type = null;
        boolean complete = false;
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("Creating Cinema");
        System.out.println("-------------------------------------");

        while (!complete) {
            switch (state) {
                case CODE:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cinema CODE (length 3) to be created");
                        name = sc.next();
                        if (name.equals("0"))
                            return;
                        else{
                            if (name.length() != 3){
                                System.out.println("Invalid cinema code");
                                System.out.println("Try again");
                                state = createCinemaState.CODE;
                                break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = createCinemaState.CODE;
                        break;
                    }
                case TYPE:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cinema type to be created");
                        System.out.println("[Platinum, Gold, Silver]");
                        type = sc.next();
                        if (type.equals("0")) {
                            state = createCinemaState.CODE;
                            break;
                        }
                        else if (!type.equals("Gold") || !type.equals("Gold")|| !type.equals("Gold")) {
                                System.out.println("Invalid cinema type");
                                System.out.println("Try again");
                                state = createCinemaState.TYPE;
                                break;
                            }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = createCinemaState.TYPE;
                        break;
                    }
                case CREATE:
                    try {
                        cinemaHandler.createCinema(name, type);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to create new Cinema!");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("Created Cinema");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }
    private static void deleteCinema() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        ICinema cinemaHandler = CinemaManager.getInstance();
        int ID = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("Deleting  Cinema");
        System.out.println("-------------------------------------");
        cinemaHandler.printCinemasAdmin();

        while(true){
            try{
                System.out.println("[Enter 0 to return]");
                System.out.println("Enter cinema ID to be deleted");
                ID = sc.nextInt();
                if (ID==0) return;
                else break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                continue;
            }
        }

        try{
            cinemaHandler.deleteCinema(ID, cineplexHandler);
        } catch(IllegalArgumentException e){
            System.out.println("Unable to delete new cineplex!");
            System.out.println("Exiting function!");
            waitForEnter(null);
            return;
        }
        System.out.println("-------------------------------------");
        System.out.println("Cinema Deleted");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }
    private static void setCineplexName(){
        ICineplex cineplexHandler = CineplexManager.getInstance();
        enum setCineplexState {
            ID, NAME, CREATE
        };
        boolean complete = false;
        Scanner sc = new Scanner(System.in);
        int ID = 0;
        String name = null;
        setCineplexState state = setCineplexState.ID;

        System.out.println("-------------------------------------");
        System.out.println("Setting New Name for Cineplex");
        System.out.println("-------------------------------------");
        cineplexHandler.printCineplexesAdmin();
        System.out.println("-------------------------------------");

        while (!complete) {
            switch (state) {
                case ID:
                try {
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter Cinplex ID:");
                    ID = sc.nextInt();
                    if (ID == 0) return;
                } catch (InputMismatchException e) {
                    inputMismatchHandler();
                    state = setCineplexState.ID;
                    break;
                }
                case NAME:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter new cineplex name");
                        name = sc.next();
                        if (name.equals("0")) {
                            state = setCineplexState.ID;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setCineplexState.NAME;
                        break;
                    }
                case CREATE:
                    try {
                        cineplexHandler.setName(ID, name);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to set cineplex name");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("New name set for cineplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }
    private static void setCineplexLocation(){
        ICineplex cineplexHandler = CineplexManager.getInstance();
        enum setCineplexLocation {
            ID, LOCATION, CREATE
        };
        boolean complete = false;
        Scanner sc = new Scanner(System.in);
        int ID = 0;
        String name = null;
        setCineplexLocation state = setCineplexLocation.ID;

        System.out.println("-------------------------------------");
        System.out.println("Setting New Name for Cineplex");
        System.out.println("-------------------------------------");
        cineplexHandler.printCineplexesAdmin();
        System.out.println("-------------------------------------");

        while (!complete) {
            switch (state) {
                case ID:
                try {
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter Cinplex ID:");
                    ID = sc.nextInt();
                    if (ID == 0) return;
                } catch (InputMismatchException e) {
                    inputMismatchHandler();
                    state = setCineplexLocation.ID;
                    break;
                }
                case LOCATION:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter new cineplex location");
                        name = sc.next();
                        if (name.equals("0")) {
                            state = setCineplexLocation.ID;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setCineplexLocation.LOCATION;
                        break;
                    }
                case CREATE:
                    try {
                        cineplexHandler.setLocation(ID, name);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to set cineplex location");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("new location set for cineplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }
    private static void updateCineplex() {
        Scanner sc = new Scanner(System.in);
        int ch = 0;
        while (true) {
            updateCinplexMenu();

            try {
                System.out.println("Enter choice");
                ch = sc.nextInt();
                if (ch>5 || ch<1) {
                    System.out.println("Invalid input!");
                    waitForEnter(null);
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                waitForEnter(null);
                continue;
            }

            switch (ch) {
                case 1:
                    createCinema();
                    break;
                case 2:
                    deleteCinema();
                    break;
                case 3:
                    setCineplexName();
                    break;
                case 4:
                    setCineplexLocation();
                    break;
                case 5:
                    CineplexManager.close();
                    CinemaManager.close();
                    return;
                default:
                    System.out.println("Enter valid choice");
                    ch = 0;
            }

        } 
    }
}
