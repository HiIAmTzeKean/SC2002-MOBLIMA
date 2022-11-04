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

// import java.util.ArrayList;
import java.util.InputMismatchException;

public class StaffMovie extends View {

    private static IMovie MovieHandler = MovieManager.getInstance();
    private static ISales SalesHandler = MovieManager.getInstance();
    private static IShowtimeSystem stHandler = ShowtimeManager.getInstance();

    public static void displayMenu() {
        System.out.print("\033\143");
        System.out.println("--------------------------------------");
        System.out.println("Update Movie Listings");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : Create Movie");
        System.out.println("choice 2 : Delete Movie");
        System.out.println("choice 3 : Update Movie");
        System.out.println("choice 4 : View Top 5 Movies");
        System.out.println("choice 5 : Go Back to Main Menu");
        System.out.println("--------------------------------------");
    }

    public static void createMovie() {
        enum createMovieEnum {
            TITLE, SYNOPSIS, DIRECTOR, CAST, AGERESTRICTION, STATUS, TYPE, DURATION
        }
        boolean complete = false;
        String movieTitle = null;
        String movieSynopsis  = null;
        String movieDirector  = null;
        String movieCast  = null;
        MovieStatus movieStatus  = null;
        AgeRestriction movieAgeRestriction  = null;
        MovieType movieType  = null;
        int duration  = 0;

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
                    sc.nextLine(); 
                    state = createMovieEnum.CAST;
                    break;
                }
            case AGERESTRICTION:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Age Restriction: ");
                String input = sc.next();
                if (input.equals("0")) {
                    state=createMovieEnum.CAST;
                    break;
                }
                movieAgeRestriction = AgeRestriction.valueOf(input.toUpperCase());
            }
            catch (IllegalArgumentException ex){
                state = createMovieEnum.AGERESTRICTION;
                System.out.println("Invalid age restriction given");
                break;
            }
            catch(InputMismatchException e){
                sc.nextLine(); 
                state = createMovieEnum.AGERESTRICTION;
                break;
            }
            case STATUS:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Status ");
                String input = sc.next();
                if (input.equals("0")) {
                    state=createMovieEnum.AGERESTRICTION;
                    break;
                }
                movieStatus = MovieStatus.valueOf(input.toUpperCase());
            }
            catch (IllegalArgumentException ex){
                state = createMovieEnum.STATUS;
                System.out.println("Invalid movie status given");
                break;
            }
            catch(InputMismatchException e){
                sc.nextLine(); 
                state = createMovieEnum.STATUS;
                break;
            }
            case TYPE:
            try {
                System.out.println("[Enter 0 to go back]");
                System.out.println("Enter Type ");
                String input = sc.next();
                if (input.equals("0")) {
                    state=createMovieEnum.STATUS;
                    break;
                }
                movieType = MovieType.valueOf(input.toUpperCase());
            }
            catch(InputMismatchException e){
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
                complete = true;
            }
            catch(InputMismatchException e){
                sc.nextLine(); 
                state = createMovieEnum.DURATION;
                break;
            }
            default:
                state = createMovieEnum.TITLE;
                break;
        }
        }
        MovieHandler.createMovie(movieTitle, movieStatus, movieSynopsis,movieDirector, movieCast, movieAgeRestriction, movieType , duration);
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew Movie Created");
        System.out.println("--------------------------------------");

    }

    public static void setMovieDirector(){
        enum setMovieDirectorState {ID,DIRECTOR,SETTING}
        setMovieDirectorState state = setMovieDirectorState.ID;
        int ID = 0;
        String director = null;
        boolean complete = false;

        System.out.println("--------------------------------------");
        System.out.println("Set new Movie Director");
        System.out.println("--------------------------------------");
        MovieHandler.printMovies();

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
                }
                case DIRECTOR:
                try {
                    System.out.println("Enter new movie Director ");
                    director = sc.next();
                    if (director.equals("0")){
                        state = setMovieDirectorState.DIRECTOR;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                    sc.nextLine();
                    state = setMovieDirectorState.DIRECTOR;
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
                }
            }
        }
         
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew movie director has been set");
        System.out.println("--------------------------------------");
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            displayMenu();
            System.out.println("Enter choice");
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.toString());
                sc.nextLine();
                start();
            }

            switch (choice) {
                case 1:
                    createMovie();
                    break;
                case 2:
                    System.out.println("--------------------------------------");
                    System.out.println("Deleting Movie");
                    System.out.println("--------------------------------------");
                    try {
                        MovieHandler.printMovies();
                        System.out.println("--------------------------------------");
                        System.out.println("Which movie would you like to delete?\n Enter ID");
                        int ID = sc.nextInt();

                        MovieHandler.deleteMovie(ID);
                    } catch (InputMismatchException e) {
                        System.out.println(e.toString());
                        sc.nextLine();
                        StaffMovie.start();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.toString());
                        sc.nextLine();
                        StaffMovie.start();
                    }
                    System.out.println("--------------------------------------");
                    System.out.println("\t\t Movie Deleted");
                    System.out.println("--------------------------------------");
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
                    System.out.print("\033\143");
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }
        } while (choice < 6 && choice >= 0);

        sc.close();
    }

    public static void updateMovie() {
        int choice = 0;
        try {
            do {
                System.out.print("\033\143");
                System.out.println("--------------------------------------");
                System.out.println("Update Movie");
                System.out.println("--------------------------------------");
                System.out.println("choice 1 : set movie type");
                System.out.println("choice 2 : set movie status");
                System.out.println("choice 3 : Set movie director");
                System.out.println("choice 4 : Go Back to Staff Movie Main Menu");
                System.out.println("--------------------------------------");

                System.out.println("Enter choice");
                choice = sc.nextInt();
                int ID;

                switch (choice) {
                    case 1:
                        System.out.println("--------------------------------------");
                        System.out.println("Set new Movie type");
                        System.out.println("--------------------------------------");

                        MovieHandler.printMovies();
                        System.out.println("Enter movie ID :");
                        ID = sc.nextInt();
                        System.out.println("Enter new Type :");
                        String movieType = sc.next();

                        MovieType mt = MovieType.valueOf(sc.next().toUpperCase());
                        System.out.println(" 1 ");

                        MovieHandler.setMovieType(ID, movieType);
                        System.out.println(" 2 ");
                        stHandler.setMovieType(ID, mt);
                        System.out.println(" 3 ");
                        System.out.println("--------------------------------------");
                        System.out.println("\t\tNew movie type has been set");
                        System.out.println("--------------------------------------");
                        break;
                    case 2:
                        System.out.println("--------------------------------------");
                        System.out.println("Set new Movie Status");
                        System.out.println("--------------------------------------");

                        MovieHandler.printMovies();

                        System.out.println("Enter movie ID :");
                        ID = sc.nextInt();
                        System.out.println("Enter new movie Status ");
                        String ms = sc.next();
                        MovieStatus movieStatus = MovieStatus.valueOf(ms.toUpperCase());
                        if (movieStatus == MovieStatus.END_OF_SHOWING)
                            stHandler.movieShowtimeEnd(ID);

                        MovieHandler.setMovieStatus(ID, ms);
                        stHandler.setMovieStatus(ID, movieStatus);
                        System.out.println("--------------------------------------");
                        System.out.println("\t\tNew movie status has been set");
                        System.out.println("--------------------------------------");

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

            } while (choice < 5 && choice >= 0);

        } catch (InputMismatchException e) {
            System.out.println(e.toString());
            sc.nextLine();
            updateMovie();
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            sc.nextLine();
            updateMovie();
        }
    }

    public static void showTopMovie() {
        int choice2;

        System.out.println("Display Top 5 Movie by : ");
        System.out.println("--------------------------------------");
        System.out.println("choice 1 : Sales");
        System.out.println("choice 2 : ratings");
        System.out.println("--------------------------------------");

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
                default:
                    System.out.println("Illegal Input \n t\tBack to Movie Menu");
                    System.out.println("--------------------------------------");
                    return;
            }

        } catch (InputMismatchException e) {
            System.out.println(e.toString());
            sc.nextLine();
            return;
        }

    }
}
