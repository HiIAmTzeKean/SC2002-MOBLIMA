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
		boolean contd = true;
		do {
		System.out.print("Please enter your review: ");
		review = scan.next();
		System.out.print("Please enter your rating: ");
		rating = scan.nextFloat();
		try {
			reviewHandler.addReview(movieName, review, rating);
			contd = false;
		}
		catch(IllegalArgumentException e){
			System.out.println("Erroneous value entered. Please take note of following requirements: ");
			System.out.println("a) Review cannot be empty");
			System.out.println("b) Rating must be between 0 and 5 (both exclusive)");
			System.out.println();
		}
		} while(contd);
		scan.close();
		MovieManager.close();
	}
}
