package viewpackage.staffviewpackage;

import java.util.InputMismatchException;
import java.util.Scanner;
import cinemapackage.CinemaManager;
import cinemapackage.CinemaType;
import cinemapackage.ICinema;
import cineplexpackage.CineplexManager;
import cineplexpackage.ICineplex;
import viewpackage.View;

/**
 * Staff cinema view
 * Allows modification of cinema and cinplex stored in data file
 * Staff must be logged in to use this class
 * 
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class StaffCinema extends View {
    /**
     * Function that displays possible options for Staff interactions with
     * Cineplexes
     * 
     */
    public static void displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------------------");
        System.out.println("        Update Cineplex/Cineplex Menu");
        System.out.println("-------------------------------------------------");
        System.out.println("Choice 1 : Create Cineplex");
        System.out.println("Choice 2 : Delete Cineplex");
        System.out.println("Choice 3 : Update Cineplex and Cinemas");
        System.out.println("Choice 4 : Return");
        System.out.println("-------------------------------------------------");
    }

    /**
     * Uses an instantiated CineplexManager to create a cineplex.
     * Uses enumeration values to enable staff to backtrack through the attribute
     * input process if required.
     * 
     */
    private static void createCineplex() {
        enum createCineplexState {
            NAME, LOCATION, CREATE
        }
        ;
        ICineplex cineplexHandler = CineplexManager.getInstance();
        createCineplexState state = createCineplexState.NAME;
        String name = null;
        String location = null;
        boolean complete = false;
        sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("\tCreating Cineplex");
        System.out.println("-------------------------------------");

        while (!complete) {
            switch (state) {
                case NAME:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter Cineplex Name");
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
                        System.out.println("Enter Cineplex Location");
                        location = sc.nextLine();
                        if (location.equals("0")) {
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
        System.out.println("\tNew Cineplex Created");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function that deletes the cineplex object of the specified cineplexID.
     */
    public static void deleteCineplex() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        Scanner sc = new Scanner(System.in);
        int id = 0;
        boolean complete = false;
        int state = 1;

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("\tDeleting  Cineplex");
        System.out.println("-------------------------------------");
        cineplexHandler.printCineplexesAdmin();
        while (!complete) {
            switch (state) {
                case 1:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter Cineplex ID");
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
        System.out.println("\tDeleted  Cineplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Main function of StaffCinema class that takes user inputs and calls
     * subsequent functions.
     */
    public static void start() {
        sc = new Scanner(System.in);

        int choice = 0;
        while (true) {
            displayMenu();
            try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
                if (choice > 5 || choice < 1) {
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
                    System.out.println("-------------------------------------");
                    System.out.println("\tExiting Staff Cinema Menu");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }
        }
    }

    /**
     * Function that lists possible options for the update cinema functionality for
     * Staff.
     */
    private static void updateCinplexMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("        Update Cinema Menu ");
        System.out.println("--------------------------------------");
        System.out.println("Choice 1 : Create Cinema");
        System.out.println("Choice 2 : Delete Cinema");
        System.out.println("Choice 3 : Update Cinema Code");
        System.out.println("Choice 4 : Update Cinema Type");
        System.out.println("Choice 5 : Set Cineplex Name");
        System.out.println("Choice 6 : Set Cineplex Location");
        System.out.println("Choice 7 : Add Cinema to Cineplex");
        System.out.println("Choice 8 : Remove Cinema from Cineplex");
        System.out.println("Choice 9 : Return");
        System.out.println("--------------------------------------");
    }

    /**
     * Function to provide UI to create a new cinema object for staff view
     */
    private static void createCinema() {
        enum createCinemaState {
            CODE, TYPE, CREATE
        }
        ;
        ICinema cinemaHandler = CinemaManager.getInstance();
        createCinemaState state = createCinemaState.CODE;
        String name = null;
        String type = null;
        boolean complete = false;
        sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("\tCreating Cinema");
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
                        else {
                            if (name.length() != 3) {
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
                        } else if (!type.equals("Platinum") && !type.equals("Gold") && !type.equals("Silver")) {
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
        System.out.println("\tNew Cinema Created");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to delete a cinema object for staff view
     */
    private static void deleteCinema() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        ICinema cinemaHandler = CinemaManager.getInstance();
        int ID = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("\tDeleting  Cinema");
        System.out.println("-------------------------------------");
        cinemaHandler.printCinemasAdmin();

        while (true) {
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Enter cinema ID to be deleted");
                ID = sc.nextInt();
                if (ID == 0)
                    return;
                else
                    break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                continue;
            }
        }

        try {
            cinemaHandler.deleteCinema(ID, cineplexHandler);
        } catch (IllegalArgumentException e) {
            System.out.println("Unable to delete new cineplex!");
            System.out.println("Exiting function!");
            waitForEnter(null);
            return;
        }
        System.out.println("-------------------------------------");
        System.out.println("\tCinema Deleted");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to create update cineplex name for staff view
     */
    private static void setCineplexName() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        enum setCineplexState {
            ID, NAME, CREATE
        }
        ;
        boolean complete = false;
        Scanner sc = new Scanner(System.in);
        int ID = 0;
        String name = null;
        setCineplexState state = setCineplexState.ID;

        System.out.println("-------------------------------------");
        System.out.println("   Setting New Cineplex Name");
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
                        if (ID == 0)
                            return;
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
        System.out.println("       New Cineplex Name Set");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to update cineplex locatino for staff view
     */
    private static void setCineplexLocation() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        enum setCineplexLocation {
            ID, LOCATION, CREATE
        }
        ;
        boolean complete = false;
        Scanner sc = new Scanner(System.in);
        int ID = 0;
        String name = null;
        setCineplexLocation state = setCineplexLocation.ID;

        System.out.println("-------------------------------------");
        System.out.println("  Setting New Cineplex Location ");
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
                        if (ID == 0)
                            return;
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
        System.out.println("     New Cineplex Location Set");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to create add a cinema to cineplex for staff view
     */
    private static void addCinemaToCineplex() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        ICinema cinemaHandler = CinemaManager.getInstance();
        enum addCinemaToCineplexState {
            CINEMA, CINEPLEX, SET
        }
        addCinemaToCineplexState state = addCinemaToCineplexState.CINEPLEX;
        int cinemaID = 0, cineplexID = 0;
        boolean complete = false;
        sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("     Adding Cinema to Cinplex");
        System.out.println("-------------------------------------");
        while (!complete) {
            switch (state) {
                case CINEPLEX:
                    try {
                        cineplexHandler.printCineplexesAdmin();
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter Cineplex ID to add to:");
                        cineplexID = sc.nextInt();
                        if (cineplexID == 0) {
                            return;
                        }
                        cineplexHandler.getCineplex(cineplexID);
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = addCinemaToCineplexState.CINEPLEX;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Cineplex ID entered was not valid!");
                        System.out.println("Try again!");
                        state = addCinemaToCineplexState.CINEPLEX;
                        break;
                    }
                case CINEMA:
                    try {
                        System.out.println("List of Cinemas under selected Cineplex below:");
                        cineplexHandler.printCinemasUnderCineplex(cineplexID);
                        System.out.println("Select which cinema you want to add below:");
                        cinemaHandler.printCinemasAdmin();
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter CinemaID to add:");
                        cinemaID = sc.nextInt();
                        if (cinemaID == 0) {
                            return;
                        }
                        cinemaHandler.getCinema(cinemaID);
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = addCinemaToCineplexState.CINEMA;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Cinema ID entered was not valid!");
                        System.out.println("Try again!");
                        state = addCinemaToCineplexState.CINEMA;
                        break;
                    }

                case SET:
                    try {
                        cineplexHandler.addCinema(cineplexID, cinemaHandler.getCinema(cinemaID));
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Not able to add cinema to cineplex requested");
                        state = addCinemaToCineplexState.CINEMA;
                        waitForEnter(null);
                        break;
                    }
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("      Cinema added to Cinplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to remoce a cinema object under cineplex for staff
     * view
     */
    private static void removeCinemaFromCineplex() {
        ICineplex cineplexHandler = CineplexManager.getInstance();
        ICinema cinemaHandler = CinemaManager.getInstance();
        enum removeCinemaFromCineplexSTATE {
            CINEMA, CINEPLEX, REMOVE
        }
        removeCinemaFromCineplexSTATE state = removeCinemaFromCineplexSTATE.CINEPLEX;
        int cinemaID = 0, cineplexID = 0;
        boolean complete = false;
        sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.println("-------------------------------------");
        System.out.println("    Removing Cinema from Cinplex");
        System.out.println("-------------------------------------");
        while (!complete) {
            switch (state) {
                case CINEPLEX:
                    try {
                        cineplexHandler.printCineplexesAdmin();
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter Cineplex ID to remove cinema from:");
                        cineplexID = sc.nextInt();
                        if (cineplexID == 0) {
                            return;
                        }
                        cineplexHandler.getCineplex(cineplexID);
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = removeCinemaFromCineplexSTATE.CINEPLEX;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Cineplex ID entered was not valid!");
                        System.out.println("Try again!");
                        state = removeCinemaFromCineplexSTATE.CINEPLEX;
                        break;
                    }
                case CINEMA:
                    try {
                        cineplexHandler.printCinemasUnderCineplex(cineplexID);
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter CinemaID to remove:");
                        cinemaID = sc.nextInt();
                        if (cinemaID == 0) {
                            state = removeCinemaFromCineplexSTATE.CINEPLEX;
                            break;
                        }
                        cinemaHandler.getCinema(cinemaID);
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = removeCinemaFromCineplexSTATE.CINEMA;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Cinema ID entered was not valid!");
                        System.out.println("Try again!");
                        state = removeCinemaFromCineplexSTATE.CINEMA;
                        break;
                    }
                case REMOVE:
                    try {
                        cineplexHandler.removeCinema(cineplexID, cinemaHandler.getCinema(cinemaID));
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Not able to remove cinema to cineplex requested!");
                        System.out.println("Cinema is not under Cineplex!\nTry again.");
                        state = removeCinemaFromCineplexSTATE.CINEMA;
                        waitForEnter(null);
                        break;
                    }
            }
        }
        System.out.println("-------------------------------------");
        System.out.println("    Cinema removed from Cinplex");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to create a update cinema object for staff view
     */
    private static void updateCinemaCode() {
        enum updateCinema {
            ID, CODE, SET
        }
        ;
        ICinema cinemaHandler = CinemaManager.getInstance();
        int ID = 0;
        String code = null;
        boolean complete = false;
        updateCinema state = updateCinema.ID;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("\tUpdate Cinema Code");
        System.out.println("-------------------------------------");

        cinemaHandler.printCinemasAdmin();
        while (!complete) {
            switch (state) {
                case ID:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cinema ID to be modify:");
                        ID = sc.nextInt();
                        if (ID == 0)
                            return;
                        cinemaHandler.getCinema(ID);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                        state = updateCinema.ID;
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to find request cinemaID!\n");
                        state = updateCinema.ID;
                        continue;
                    }
                case CODE:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter new cinema code [3 letters]:");
                        code = sc.next();
                        if (code.equals("0")) {
                            state = updateCinema.ID;
                            break;
                        } else if (code.length() != 3) {
                            System.out.println("Length not 3!");
                            state = updateCinema.CODE;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                        state = updateCinema.CODE;
                        continue;
                    }
                case SET:
                    try {
                        cinemaHandler.setCinemaCode(ID, code);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to find request cinemaID!\n");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }

        }
        cinemaHandler.printCinemasAdmin();
        System.out.println("-------------------------------------");
        System.out.println("\tCinema Updated");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to create a update cinema object for staff view
     */
    private static void updateCinemaType() {
        enum updateCinemaType {
            ID, TYPE, SET
        }
        ;
        ICinema cinemaHandler = CinemaManager.getInstance();
        int ID = 0;
        String input = null;
        boolean complete = false;
        CinemaType type = null;
        updateCinemaType state = updateCinemaType.ID;
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("\tUpdate Cinema Code");
        System.out.println("-------------------------------------");

        cinemaHandler.printCinemasAdmin();
        while (!complete) {
            switch (state) {
                case ID:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter cinema ID to be modify");
                        ID = sc.nextInt();
                        if (ID == 0)
                            return;
                        cinemaHandler.getCinema(ID);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                        state = updateCinemaType.ID;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to find request cinemaID!\n");
                        state = updateCinemaType.ID;
                        break;
                    }
                case TYPE:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter type to update to [Platinum, Gold, Silver]");
                        input = sc.next();
                        if (input.equals("0")) {
                            state = updateCinemaType.ID;
                            break;
                        } else if (!input.equals("Platinum") && !input.equals("Gold") && !input.equals("Silver")) {
                            System.out.println("Invalid cinema type");
                            System.out.println("Try again");
                            state = updateCinemaType.TYPE;
                            break;
                        }
                        type = CinemaType.valueOf(input.toUpperCase());
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                        state = updateCinemaType.TYPE;
                        continue;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to convert input to cinema type!");
                        state = updateCinemaType.TYPE;
                        continue;
                    }
                case SET:
                    try {
                        cinemaHandler.setCinemaType(ID, type);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to find request cinemaID!\n");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }

        }
        cinemaHandler.printCinemasAdmin();
        System.out.println("-------------------------------------");
        System.out.println("\tCinema Updated");
        System.out.println("-------------------------------------");
        waitForEnter(null);
    }

    /**
     * Function to provide UI to create a update cineplex object for staff view
     */
    private static void updateCineplex() {
        sc = new Scanner(System.in);
        int ch = 0;
        while (true) {
            updateCinplexMenu();

            try {
                System.out.println("Enter choice");
                ch = sc.nextInt();
                if (ch > 9 || ch < 1) {
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
                    updateCinemaCode();
                    break;
                case 4:
                    updateCinemaType();
                    break;
                case 5:
                    setCineplexName();
                    break;
                case 6:
                    setCineplexLocation();
                    break;
                case 7:
                    addCinemaToCineplex();
                    break;
                case 8:
                    removeCinemaFromCineplex();
                    break;
                case 9:
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
