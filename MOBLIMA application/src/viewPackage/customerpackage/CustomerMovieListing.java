package viewPackage.customerviewpackage;

import java.util.Scanner;
import moviepackage.IMovie;

/*
- change to select by movie name
- List the Top 5 ranking by ticket sales OR by overall reviewers’ ratings
 */
public class CustomerMovieList {
	public static Scanner scan = new Scanner(System.in);
	private int selectedMovieID = 0; 
	
	public void MovieSelectionNavigation() {
		boolean selected = false;
		int menuOption;
		while(!selected) {
			System.out.println("Enter value from the following options:");
			System.out.println("1 -> Display List of Movies");
			System.out.println("2 -> See details of one movie");
			System.out.println("3 -> Select a movie");
			System.out.println("4 -> exit this menu");
			
			menuOption = scan.nextInt();
			switch(menuOption) {
			case 1:{
				IMovie displayMoviesObj;
				displaymovies.printMovies();
				break;}
			case 2:{
				//change to search by name instead
				IMovie getMovieObj;
				System.out.println("enter movie ID");
				int id = scan.nextInt();
				getMovieObj.getMoviefromID(id);
				break;}
			case 3:{
				System.out.println("enter selected movie index");
				int id = scan.nextInt();
				selectedMovieID = id;
				selected = true;
				break;}
			}
		}
	}	
}
