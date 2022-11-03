package viewPackage.customerpackage;

import java.util.Scanner;
import moviepackage.IMovie;
import moviepackage.ISales;
import moviepackage.MovieManager;

public class CustomerMovieListing {
	private String selectedMovieName = null; 
	private static Scanner scan = new Scanner(System.in);
	
	private static IMovie movieDisplayHandler = MovieManager.getInstance(); 
	private static ISales salesRankHandler = MovieManager.getInstance();
	
	public String getSelectedMovieName(){
		return selectedMovieName;
	}
	
	public void movieSelection() {
		boolean selected = false, exit = false;
		int menuOption;
		while(!selected && !exit) {
			System.out.println("Enter value from the following options:");
			System.out.println("1 -> Display list of all movies");
			System.out.println("2 -> Display Top 5 movies by ticket sales");
			System.out.println("3 -> Display Top 5 movies by reviewer's ratings");
			System.out.println("4 -> See details of one movie");
			System.out.println("5 -> Select a movie");
			System.out.println("6 -> Exit this menu");
			System.out.println();
			
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
				salesRankHandler.getTop5_sales();
				System.out.println();
				break;
				}
			case 3:{
				System.out.println("Top 5 movies by reviewer's ratings:");
				salesRankHandler.getTop5_rating();
				System.out.println();
				break;
				}
			case 4:{
				System.out.println("Enter name of movie to see details of: ");
				String movieName = scan.next();
				movieDisplayHandler.findMoviebyName(movieName).printMovieComplete(); //check this part
				System.out.println();
				break;
				}
			case 5:{
				System.out.println("Enter your selected movie name:");
				selectedMovieName = scan.next();
				selected = true;
				break;
				}
			case 6:{
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
