package viewPackage.customerpackage;

import java.util.Scanner;
import moviepackage.IReviews;
import moviepackage.MovieManager;

public class CustomerReview {
	private String review;
	private float rating;
	private static Scanner scan = new Scanner(System.in);
	private static IReviews reviewHandler = MovieManager.getInstance(); 
	
	public void setReviewAndRating(String movieName) {
		System.out.print("Please enter your review: ");
		review = scan.next();
		System.out.print("Please enter your rating: ");
		rating = scan.nextFloat();
		reviewHandler.addReview(movieName, review, rating);
		scan.close();
		MovieManager.close();
	}
}
