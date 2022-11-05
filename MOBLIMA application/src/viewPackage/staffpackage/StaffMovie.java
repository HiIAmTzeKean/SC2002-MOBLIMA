package viewPackage.staffpackage;

import moviepackage.MovieManager;
import moviepackage.MovieStatus;
import moviepackage.MovieType;
import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;
import viewPackage.View;
import moviepackage.AgeRestriction;
import moviepackage.IMovie;
import moviepackage.ISales;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Staff movie view
 * Allows modification of movies data file
 * Staff must be logged in to use this class
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class StaffMovie extends View {
    public static void displayMenu() {
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Update Movie Listings");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : Create Movie");
        System.out.println("choice 2 : Delete Movie");
        System.out.println("choice 3 : Update Movie");
        System.out.println("choice 4 : View Top 5 Movies");
        System.out.println("choice 5 : Return");
        System.out.println("--------------------------------------");
    }
    /**
     * Provides UI to create new movie object which will be stored
     * on the data file
     * @apiNote IMovie,createMovieEnum,AgeRestriction,MovieStatus,MovieType
     */
    private static void createMovie() {
        IMovie MovieHandler = MovieManager.getInstance();
        enum createMovieEnum {
            TITLE, SYNOPSIS, DIRECTOR, CAST, AGERESTRICTION, STATUS, TYPE, DURATION, CREATE
        };
        boolean complete = false;
        String movieTitle = null;
        String movieSynopsis  = null;
        String movieDirector  = null;
        String movieCast  = null;
        MovieStatus movieStatus  = null;
        AgeRestriction movieAgeRestriction  = null;
        MovieType movieType  = null;
        int duration  = 0;

        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Creating New Movie"); 
        System.out.println("--------------------------------------");
        createMovieEnum state = createMovieEnum.TITLE;

        // clear buffer
        sc = new Scanner(System.in);

        while(!complete){
            switch(state){
            case TITLE:
                try{
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter the movie title:");
                    movieTitle = sc.nextLine();
                    if (movieTitle.equals("0")) return;
                }
                catch(InputMismatchException e){
                    System.out.println("Input not valid!");
                    sc.nextLine(); 
                    state = createMovieEnum.TITLE;
                    break;
                }
            case SYNOPSIS:
                try {
                    System.out.println("[Enter 0 to go back]");
                    System.out.println("Enter Synopsis: ");
                    movieSynopsis = sc.next();
                    if (movieSynopsis.equals("0")) {
                        state=createMovieEnum.TITLE;
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Input not valid!");
                    sc.nextLine(); 
                    state = createMovieEnum.SYNOPSIS;
                    break;
                }
            case DIRECTOR:
                try {
                    System.out.println("[Enter 0 to go back]");
                    System.out.println("Enter Director: ");
                    movieDirector = sc.next();
                    if (movieDirector.equals("0")) {
                        state=createMovieEnum.SYNOPSIS;
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Input not valid!");
                    state = createMovieEnum.DIRECTOR;
                    break;
                }
            case CAST:
                try {
                    System.out.println("[Enter 0 to go back]");
                    System.out.println("Enter Cast: ");
                    movieCast = sc.next();
                    if (movieCast.equals("0")) {
                        state=createMovieEnum.DIRECTOR;
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Input not valid!");
                    sc.nextLine(); 
                    state = createMovieEnum.CAST;
                    break;
                }
            case AGERESTRICTION:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Age Restriction: ");

                AgeRestriction[] arr = AgeRestriction.values();
                for(AgeRestriction ar:arr){ 
                    System.out.printf("choice %d : %s\n",(ar.ordinal()+1) ,ar.toString()); 
                }
                int input = sc.nextInt();
                if (input == 0) {
                    state=createMovieEnum.CAST;
                    break;
                }
                if (input > arr.length+1 || input < 0) {
                    state=createMovieEnum.AGERESTRICTION;
                    System.out.println("Enter a valid choice");
                    break;
                } 
                movieAgeRestriction = arr[input-1];
            }
            catch (IllegalArgumentException ex){
                state = createMovieEnum.AGERESTRICTION;
                System.out.println("Invalid age restriction given");
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Input not valid!");
                sc.nextLine(); 
                state = createMovieEnum.AGERESTRICTION;
                break;
            }
            case STATUS:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter movie status ");
                MovieStatus[] arr = MovieStatus.values();

                for(MovieStatus ar:arr){ 
                    System.out.printf("choice %d : %s\n",(ar.ordinal()+1) ,ar.toString()); 
                }
                int input = sc.nextInt();

                if (input==0) {
                    state=createMovieEnum.AGERESTRICTION;
                    break;
                }if (input > arr.length+1 || input < 0) {
                    state=createMovieEnum.STATUS;
                    System.out.println("Enter a valid choice");
                    break;
                } 

                movieStatus = arr[input-1]; 
            }
            catch (IllegalArgumentException ex){
                state = createMovieEnum.STATUS;
                System.out.println("Invalid movie status given");
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Input not valid!");
                sc.nextLine(); 
                state = createMovieEnum.STATUS;
                break;
            }
            case TYPE:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Type ");

                MovieType[] arr = MovieType.values();
                for(MovieType ar:arr){ 
                    System.out.printf("choice %d : %s\n",(ar.ordinal()+1) ,ar.toString()); 
                }
                int input = sc.nextInt();
                if (input==0) {
                    state=createMovieEnum.STATUS;
                    break;
                }
                if (input > arr.length+1 || input < 0) {
                    state=createMovieEnum.TYPE;
                    System.out.println("Enter a valid choice");
                    break;
                }
                movieType = arr[input-1]; 
            }
            catch(InputMismatchException e){
                System.out.println("Input not valid!");
                sc.nextLine(); 
                state = createMovieEnum.TYPE;
                break;
            }
            case DURATION:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Duration ");
                duration = sc.nextInt();
                if (duration==0) {
                    state=createMovieEnum.TYPE;
                    break;
                }
            }
            catch(InputMismatchException e){
                System.out.println("Input not valid!");
                sc.nextLine(); 
                state = createMovieEnum.DURATION;
                break;
            }
            case CREATE:
                try {
                    MovieHandler.createMovie(movieTitle, movieStatus, movieSynopsis,movieDirector, movieCast, movieAgeRestriction, movieType , duration);
                    complete = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Unable to create new movie!");
                    System.out.println("Exiting the function now!");
                    waitForEnter(null);
                    return;
                }
            default:
                state = createMovieEnum.TITLE;
                break;
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew Movie Created");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
    /**
      * Provides UI for setting movie type
      * @apiNote IMovie,IShowtimeSystem,setMovieTypeState,MovieType
      */
    private static void setMovieDirector(){
        IShowtimeSystem stHandler = ShowtimeManager.getInstance();
        IMovie MovieHandler = MovieManager.getInstance();
        enum setMovieDirectorState {ID,DIRECTOR,SETTING}
        setMovieDirectorState state = setMovieDirectorState.ID;
        int ID = 0;
        String director = null;
        boolean complete = false;
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Set new Movie Director");
        System.out.println("--------------------------------------");
        MovieHandler.printMovieAdmin();

        while (!complete){
            switch(state){
                case ID:
                try{
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter movie ID :");
                    ID = sc.nextInt();
                    if (ID==0) return;
                } catch(InputMismatchException e){
                    System.out.println("Invalid input");
                    sc.nextLine();
                    state = setMovieDirectorState.ID;
                    break;
                }
                case DIRECTOR:
                try {
                    System.out.println("Enter new movie Director ");
                    director = sc.next();
                    if (director.equals("0")){
                        state = setMovieDirectorState.DIRECTOR;
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                    sc.nextLine();
                    state = setMovieDirectorState.DIRECTOR;
                    break;
                }
                case SETTING:
                try {
                    MovieHandler.setMovieDirector(ID, director);
                    stHandler.setMovieDirector(ID, director);
                    complete = true;
                } catch (IllegalArgumentException e) {
                    // There should not be exception here unless ID not valid
                    System.out.println("ID is not valid, please retry");
                    state = setMovieDirectorState.ID;
                    break;
                }
            }
        }
        MovieHandler.printMovieAdmin();
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew movie director has been set");
        System.out.println("--------------------------------------");
    }
     /**
      * Provides UI for setting movie type
      * @apiNote IMovie,IShowtimeSystem,setMovieTypeState,MovieType
      */
    private static void setMovieType() {
        IMovie MovieHandler = MovieManager.getInstance();
        IShowtimeSystem stHandler = ShowtimeManager.getInstance();
        enum setMovieTypeState {ID,TYPE,SETTING};
        int ID = 0;
        MovieType movieType = null;
        boolean complete = false;
        setMovieTypeState state =  setMovieTypeState.ID;
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Set new Movie type");
        System.out.println("--------------------------------------");
        
        MovieHandler.printMovieAdmin();

        while (!complete) {
            switch(state){
                case ID:
                try{
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter movie ID :");
                    ID = sc.nextInt();
                    if (ID==0) return;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input");
                    sc.nextLine();
                    state = setMovieTypeState.ID;
                    break;
                }
                case TYPE:
                try{
                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter new Type :");
                    MovieType[] arr = MovieType.values();
    
                    for(MovieType ar:arr){ 
                        System.out.printf("choice %d : %s\n",(ar.ordinal()+1) ,ar.toString()); 
                    }
                    int input = sc.nextInt();

                    if (input == 0)  {
                        state = setMovieTypeState.ID;
                        break;
                    }
                    else if (input > arr.length || input < 1) {
                        System.out.println("Enter a valid choice");
                        state = setMovieTypeState.TYPE;
                        break;
                    } 
                    movieType = arr[input-1];

                } catch(InputMismatchException e){
                    System.out.println("Invalid input");
                    sc.nextLine();
                    state = setMovieTypeState.TYPE;
                    break;
                }
                case SETTING:
                    try{
                        MovieHandler.setMovieType(ID, movieType);
                        stHandler.setMovieType(ID, movieType);
                        complete = true;
                } catch(IllegalArgumentException e){
                    System.out.println("Invalid movie type input");
                    sc.nextLine();
                    state = setMovieTypeState.TYPE;
                    waitForEnter(null);
                    break;
                }
            }
        }
        MovieHandler.printMovieAdmin();
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew movie type has been set");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
    /**
     * Provides UI to soft delete movie stored in data file
     * Movie is not deleted but set to End Of Showing
     * @apiNote IMovie
     */
    private static void deleteMovie() {
        IMovie MovieHandler = MovieManager.getInstance();
        int ID = 0;
        boolean complete = false;
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Deleting Movie");
        System.out.println("--------------------------------------");

        MovieHandler.printMovieAdmin();

        while (!complete){
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Enter Movie ID to delete:");
                ID = sc.nextInt();
                if (ID==0) return;
                MovieHandler.deleteMovie(ID);
                complete=true;
            }
            catch (InputMismatchException e) {
                System.out.println("Input not valid!");
            } catch (IllegalArgumentException e) {
                System.out.println("No valid Movie ID found");
                System.out.println("Exiting function!");
                waitForEnter(null);
                return;
            }
        }
        
        System.out.println("--------------------------------------");
        System.out.println("\t\t Movie Deleted");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    public static void start() {
        sc = new Scanner(System.in);
        int choice = 0;

        while(true) {
            displayMenu();
            try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    createMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    updateMovie();
                    break;
                case 4:
                    showTopMovie();
                    break;
                case 5:
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Staff Movie Menu");
                    System.out.println("-------------------------------------");
                    MovieManager.close();
                    ShowtimeManager.close();
                    waitForEnter(null);
                    return;
                default:
                    System.out.println("Enter valid choice!");
                    choice = 0;
            }
        }
    }
    
    private static void updateMovieMenu(){
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Update Movie");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : set movie type");
        System.out.println("choice 2 : set movie status");
        System.out.println("choice 3 : Set movie director");
        System.out.println("choice 4 : Return");
        System.out.println("--------------------------------------");
    }
    /**
     * Provides UI to update the
     * movie status stored in the data file
     * @apiNote IShowtimeSystem, IMovie, setMovieStatusState, MovieStatus
     */
    private static void setMovieStatus(){
        IShowtimeSystem stHandler = ShowtimeManager.getInstance();
        IMovie MovieHandler = MovieManager.getInstance();
        enum setMovieStatusState {ID,STATUS,SETTING};

        int ID = 0;
        MovieStatus movieStatus = null;
        boolean complete = false;
        setMovieStatusState state =  setMovieStatusState.ID;

        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Set new Movie Status");
        System.out.println("--------------------------------------");
        MovieHandler.printMovieAdmin();
        while (!complete) {
            switch(state){
                case ID:
                    try{
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter movie ID :");
                        ID = sc.nextInt();
                        if (ID==0) return;
                    }catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = setMovieStatusState.ID;
                    }
                case STATUS:
                    try{
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter movie status :");

                        MovieStatus[] arr = MovieStatus.values();

                        for(MovieStatus ar:arr){ 
                            System.out.printf("choice %d : %s\n",(ar.ordinal()+1) ,ar.toString()); 
                        }
                        int input = sc.nextInt();

                        if (input==0) {
                            state=setMovieStatusState.ID;
                            break;
                        }if (input > arr.length+1 || input < 0) {
                            state=setMovieStatusState.STATUS;
                            System.out.println("Enter a valid choice");
                            break;
                        } 

                        movieStatus = arr[input-1]; 
                        
                    } catch(InputMismatchException e){
                        inputMismatchHandler();
                        state = setMovieStatusState.STATUS;
                    }
                case SETTING:
                    try{
                        if (movieStatus == MovieStatus.END_OF_SHOWING) stHandler.movieShowtimeEnd(ID);
                        MovieHandler.setMovieStatus(ID, movieStatus);
                        stHandler.setMovieStatus(ID, movieStatus);
                        complete = true;
                } catch(IllegalArgumentException e){
                    System.out.println("Invalid movie status input");
                    state = setMovieStatusState.STATUS;
                    break;
                }
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew movie status has been set");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
    /**
     * Controller used in start() to provide UI to update movie attributes
     */
    private static void updateMovie() {
        int choice = 0;
        while(true) {
            updateMovieMenu();
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
                    setMovieType();
                    break;
                case 2:
                    setMovieStatus();
                    break;
                case 3:
                    setMovieDirector();
                    break;
                case 4:
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Staff Movie Update Menu");
                    System.out.println("-------------------------------------");
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }
        }
    }
    /**
     * Controller used in updateMovie() to display movie sales and review
     * @apiNote ISales
     */
    private static void showTopMovie() {
        ISales SalesHandler = MovieManager.getInstance();
        int choice2;

        System.out.println("Display Top 5 Movie by : ");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : Sales");
        System.out.println("choice 2 : ratings");
        System.out.println("choice 3 : exit");
        System.out.println("--------------------------------------");

        while(true){
            try {
                System.out.println("Enter choice");
                choice2 = sc.nextInt();
                switch (choice2) {
                    case 1:
                        SalesHandler.getTop5_sales();
                        break;
                    case 2:
                        SalesHandler.getTop5_rating();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Illegal Input \n t\tBack to Movie Menu");
                        System.out.println("--------------------------------------");
                        return;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, exiting from ");
                sc.nextLine();
                return;
            }
        }
    }
}
