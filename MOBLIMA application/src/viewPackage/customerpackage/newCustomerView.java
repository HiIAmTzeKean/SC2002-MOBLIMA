package viewPackage.customerpackage;
import java.util.*;
import daypackage.*;
import cineplexpackage.*;
import customerpackage.*;
import moviepackage.*;
import reviewpackage.ReviewHandler;
import showtimepackage.IShowtime;
import showtimepackage.ShowtimeManager;
import viewPackage.*;

public class newCustomerView extends View {
    public static void start(){
        Scanner sc = new Scanner(System.in);
        System.out.println("MOBLIMA Customer View\n");
        int choice = 0;
        while(true){
            //System.out.print("\033[H\033[2J");
            displayMenu();
            try{
                System.out.println("Enter Choice");
                choice = sc.nextInt();
                if(choice<1 || choice>10){
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
                //View Movie Details
                case 1:
                    System.out.print("\033[H\033[2J");
                    viewAvailableMovies();
                    waitForEnter(null);
                    break;
                //View Movie Showtimes
                case 2:
                    System.out.print("\033[H\033[2J");
                    viewMovieDetails();
                    waitForEnter(null);
                    break;
                //See Top 5 Movies by Sales
                case 3:
                    System.out.print("\033[H\033[2J");
                    printTop5Sales();
                    waitForEnter(null);
                    break;
                //See Top 5 Movies by Rating
                case 4:
                    System.out.print("\033[H\033[2J");
                    printTop5Rating();
                    waitForEnter(null);
                    break;
                case 5:
                    System.out.print("\033[H\033[2J");
                    seeAvailableShowtimes();
                    waitForEnter(null);
                    break;
                case 6:
                    System.out.print("\033[H\033[2J");
                    makeNewBooking();
                    waitForEnter(null);
                    break;
                case 7:
                    System.out.print("\033[H\033[2J");    
                    seeBookingHistory();
                    waitForEnter(null);
                    break;
                case 8:
                    System.out.print("\033[H\033[2J");    
                    viewReviews();
                    waitForEnter(null);
                    break;
                case 9:
                    System.out.print("\033[H\033[2J");    
                    createReview();
                    waitForEnter(null);
                    break; 
                case 10:
                    System.out.println("-------------------------------------");
                    System.out.println("\t\tExiting Customer Cinema Menu");
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
        System.out.println("Choice 1 : View Available Movies");
        System.out.println("Choice 2 : View Movie Details");
        System.out.println("Choice 3 : See Top 5 Movies by Sales");
        System.out.println("Choice 4 : See Top 5 Movies by Rating");
        System.out.println("Choice 5 : See Available Showtimes"); 
        //ShowtimeManager -> PrintShowtimes()
        System.out.println("Choice 6 : Make a New Booking");
        //CustomerBook -> Select Cineplex -> Select Movie -> printShowtimebyMovieNameandCineplexID(str moviename, int cineplexid) throws IAE
        //Couple Booking or Normal Booking?
        //Couple -> check if it is a platinum type first
        //Normal -> Rest
        //Discount Code Ticket -> customerpackage->discountcode.java -> get an instance of this class
        //Object check valid (str code) -> if valid, continue to getprice
        //Ask for customer details -> instantiate customer object
        //Print price -> getPrice() -> IAG when showtimeID is not valid -> check why there was a lapse
        //CustomerNullException -> if customer object is null
        //Confirm booking? Yes / No
        //Confirmed -> bookSeat()
        //bookCoupleSeat() if platinum && coupleseat chosen
        System.out.println("Choice 7 : See Booking History");
        //Instantiate booking manager object -> getInstance()
        //Print all transactions for customer(String customerEmail)
        System.out.println("Choice 8 : View Reviews");
        System.out.println("Choice 9 : Create Review");
        //call customerreview class to do options
        //from moviemanager
        //print all movies that are not end of showing
        //get the movieid and string
        System.out.println("Choice 10 : Return");
        System.out.println("--------------------------------------");
    }
   
    private static void viewAvailableMovies(){
        CustomerMovieListing.viewAvailableMovies();
    }
    private static void viewMovieDetails(){
        CustomerMovieListing.viewMovieDetails();
    }
    private static void printTop5Sales(){
        ISales salesHandler = MovieManager.getInstance();
        salesHandler.getTop5_salesCustomer();
    }
    private static void printTop5Rating(){
        ISales ratingHandler = MovieManager.getInstance();
        ratingHandler.getTop5_ratingCustomer();
    }
    private static void seeAvailableShowtimes(){
        IShowtime showtimeHandler = ShowtimeManager.getInstance();
        showtimeHandler.printShowtimes();
    }
    private static void viewReviews(){
        //Specify the movie name?
        CustomerReview.viewReviews();
    }
    private static void createReview(){
        CustomerReview.createReview();
    }
    private static void makeNewBooking() {
    	CustomerBook.start();
    }
    private static void seeBookingHistory(){
    	CustomerBook.history();
    }
}
