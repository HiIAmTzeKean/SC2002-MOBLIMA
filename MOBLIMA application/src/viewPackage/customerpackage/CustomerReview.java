package viewPackage.customerviewpackage;

import java.util.Scanner;

//just get and set for review and rating. 
//Check if reviewpackage needs something else 
public class CustomerReview {
	private String review;
	private int rating;
	
	public static Scanner scan = new Scanner(System.in);
	
	public void setReview() {
		System.out.print("Please enter your review: ");
		review = scan.next();
	}
	
	public void setRating() {
		System.out.print("Please enter your rating: ");
		rating = scan.nextInt();
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getReview() {
		return review;
	}
}
