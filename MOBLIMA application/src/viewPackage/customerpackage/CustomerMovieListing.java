package viewPackage.customerpackage;
import java.util.InputMismatchException;
import java.util.Scanner;
import moviepackage.IMovie;
import moviepackage.ISales;
import moviepackage.MovieManager;
import viewPackage.View;
//update ranking functions for customer
public class CustomerMovieListing extends View {
	private String selectedMovieName = null; 
	private static Scanner scan = new Scanner(System.in);	
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
			switch(choice){
				case 1:
					printMovies();
				case 2:

					
			}
		}
		while(choice < 6 && choice > 0);
	}
	public static void menu(){

	}

	public static void printMovies(){
		IMovie MovieHandler = MovieManager.getInstance();
		MovieHandler.printMovies();
	}
	public static void printMovie(){
		Scanner sc = new Scanner(System.in);
		try{
			IMovie MovieHandler = MovieManager.getInstance();
			MovieHandler.printMovies();
			System.out.println("Enter name of movie to see details of:");
			//scan.nextLine();
			String movieName = sc.nextLine();
			MovieHandler.findMoviebyName(movieName).printMovieComplete();
		}
		catch(IllegalArgumentException e){
			System.out.println(e);
		}	
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
