package viewPackage.customerpackage;
import viewPackage.View;
import java.util.Scanner;
import moviepackage.IMovie;
import moviepackage.ISales;
import moviepackage.MovieManager;
//update ranking functions for customer
public class CustomerMovieListing implements View {
	private String selectedMovieName = null; 
	private static Scanner scan = new Scanner(System.in);	
	public String getSelectedMovieName(){
		return selectedMovieName;
	}

	public void printTable(){
		System.out.println("Enter value from the following options:");
		System.out.println("1 -> Display list of all movies");
		System.out.println("2 -> Display Top 5 movies by ticket sales");
		System.out.println("3 -> Display Top 5 movies by reviewer's ratings");
		System.out.println("4 -> See details of one movie");
		System.out.println("5 -> Exit this menu");
		System.out.println();
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
				salesRankHandler.getTop5_ratingCustomer(); //update for customer
				System.out.println();
				break;
				}
			case 4:{
				System.out.println("Enter name of movie to see details of:");
				scan.nextLine();
				String movieName = scan.nextLine();
				try {
					movieDisplayHandler.findMoviebyName(movieName).printMovieComplete(); //check this part
				}
				catch(IllegalArgumentException e){
					System.out.println("Something went wrong — movie not found.");
					scan.nextLine();
				}
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
