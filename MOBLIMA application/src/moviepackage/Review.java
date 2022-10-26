package moviepackage;
public class Review {

	private String review;
	private float rating;

	public Review(float rating, String review){
		this.rating = rating;
		this.review = review;
	}
	public String getReview(){
		return this.review;
	}
	public float getRating(){
		return this.rating;
	}
	public String toString(){
		return "Rating(" + String.valueOf(rating) + "): " + this.getReview(); 
	}
}