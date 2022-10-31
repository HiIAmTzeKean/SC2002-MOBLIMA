package viewPackage.customerviewpackage;

import java.util.Scanner;
import moviepackage.IReviews;

public class CustomerReview {
	private String review;
	private float rating;
	public static Scanner scan = new Scanner(System.in);
	
	public void setReviewAndRating(String movieName) {
		System.out.print("Please enter your review: ");
		review = scan.next();
		System.out.print("Please enter your rating: ");
		rating = scan.nextFloat();
		IReviews ir; //check this part
		ir.addReview(movieName, review, rating);
	}
	
	//check if needs interface for access by IReviews
	public String getReview() {
		return review;
	}
}
