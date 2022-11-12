package viewPackage.customerpackage;

import java.util.Scanner;
import moviepackage.IReviews;
import moviepackage.IMovie;
import moviepackage.Movie;
import moviepackage.MovieManager;
import moviepackage.Review;

public class CustomerReview {
	private String review;
	private float rating;
	private static Scanner scan = new Scanner(System.in);
	private static IReviews reviewHandler = MovieManager.getInstance(); 
	private static IMovie movieHandler = MovieManager.getInstance();
	
	public static void createReview(){
		try{
			movieHandler.printMovieTitles();
			System.out.println("Enter the Name of the Movie you Wish to Review.");
			String toFind = scan.nextLine();
			Movie toReview = movieHandler.findMoviebyName(toFind);
			System.out.printf("Enter a Rating For %s [0-5.0]\n",toReview.getMovieTitle());
			float rating = scan.nextFloat();
			scan.nextLine();
			System.out.printf("Enter a Short Review for %s\n",toReview.getMovieTitle());
			String review = scan.nextLine();
			reviewHandler.addReview(toFind, review, rating);
		}
		catch(IllegalArgumentException e){
			System.out.println("Invalid Review Arguments. Please Try Again.");
		}
	}

	public static void viewReviews(){
        try{
			movieHandler.printMovieTitles();
            System.out.println("Enter the Name of the Movie you Wish to Get Reviews for.");
            String toFind = scan.nextLine();
            reviewHandler.printReviews(toFind);
        }
        catch(IllegalArgumentException e){
            System.out.println("Requested Movie Was Not Found.");
        }
	}
}
