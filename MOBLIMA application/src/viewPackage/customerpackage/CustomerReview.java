package customerviewpackage;

import java.util.Scanner;
import moviepackage.IReviews;

public class CustomerReview {
	private String review;
	private float rating;
	//could this use a movie type object, which can be set using getMovieFromID()?
	public static Scanner scan = new Scanner(System.in);
	
	//change to addReview() by movie name
	public void setReviewAndRating(int movieID) {
		System.out.print("Please enter your review: ");
		review = scan.next();
		System.out.print("Please enter your rating: ");
		rating = scan.nextFloat();
		IReviews ir; //check this part
		ir.addReview(movieID, review, rating);
	}
	
	public String getReview() {
		return review;
	}
}
