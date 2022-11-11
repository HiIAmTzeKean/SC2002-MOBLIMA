package moviepackage;
import java.io.Serializable;
public class Review implements Serializable{

	private String review;
	private float rating;

	/**
	 * Constructor for the Review object.
	 * @param rating
	 * @param review
	 */
	public Review(float rating, String review){
		this.rating = rating;
		this.review = review;
	}
	/**
	 * Get method for the review attribute
	 * @return review attribute
	 */
	public String getReview(){
		return this.review;
	}
	/**
	 * Get method for the rating attribute
	 * @return rating attribute
	 */
	public float getRating(){
		return this.rating;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Overloaded method of toString() to faciliate printing values of reviews.
	 */
	public String toString(){
		return "Rating(" + String.valueOf(rating) + "): \n Review:" + this.getReview(); 
	}
}