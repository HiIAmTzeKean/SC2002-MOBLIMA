package viewpackage.customerviewpackage;
import java.util.InputMismatchException;
import java.util.Scanner;
import moviepackage.IMovie;
import moviepackage.ISales;
import moviepackage.MovieManager;
import viewpackage.View;
//update ranking functions for customer
public class CustomerMovieListing extends View {
	private String selectedMovieName = null; 
	private static Scanner scan = new Scanner(System.in);	
	
	/** 
	 * @return String
	 */
	public String getSelectedMovieName(){
		return selectedMovieName;
	}
	public static void start(){
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do{
			//printTable();
			try{
				System.out.println("Enter choice");
				choice = sc.nextInt();
			} catch(InputMismatchException e){
				System.out.println("Invalid input!");
				continue;
			}
		}
		while(choice < 6 && choice > 0);
	}
	
	/** 
	 * @param movieName
	 * @return Boolean
	 */
	public static Boolean isValidMovieName(String movieName){
		IMovie MovieHandler = MovieManager.getInstance();
		return(MovieHandler.isValidMovieName(movieName));
	}
	public static void viewAvailableMovies(){
		IMovie MovieHandler = MovieManager.getInstance();
		MovieHandler.printMovies();
	}
	public static void viewMovieDetails(){
		Scanner sc = new Scanner(System.in);
		try{
			IMovie MovieHandler = MovieManager.getInstance();
			MovieHandler.printMovieTitles();
			System.out.println("Enter the Name of the Movie You Wish to See Details For:");
			String movieName = sc.nextLine();
			MovieHandler.findMoviebyName(movieName).printMovieComplete();
		}
		catch(IllegalArgumentException e){
			System.out.printf("Requested Movie Not Found. Please Try Again.");
			//System.out.println(e);
		}	
	}
	public static void printMovieTitles(){
		IMovie movieDisplayHander = MovieManager.getInstance();
		movieDisplayHander.printMovieTitles();
	}
	public void movieSelection() {
		IMovie movieDisplayHandler = MovieManager.getInstance(); 
		ISales salesRankHandler = MovieManager.getInstance();
		Scanner scan = new Scanner(System.in);
		boolean selected = false, exit = false;
		int menuOption;
		while(!selected && !exit) {
			menuOption = scan.nextInt();
			switch(menuOption) {
			case 1:{
				System.out.println("List of all movies:");
				movieDisplayHandler.printMovies();
				System.out.println();
				break;
				}
			case 2:{
				System.out.println("Top 5 movies by ticket sales:");
				salesRankHandler.getTop5_salesCustomer(); //update for customer
				System.out.println();
				break;
				}
			case 3:{
				System.out.println("Top 5 movies by reviewer's ratings:");
				salesRankHandler.getTop5_ratingCustomer(); 
				System.out.println();
				break;
				}
			case 5:{
				exit = true;
				break;
				}
			default:{
				System.out.println("Incorrect value entered, please try again.");
				break;
				}//end default
			}//end switch case
		}//end while loop
		scan.close();
		MovieManager.close();
	}//end movieSelection function definition
}//end class
