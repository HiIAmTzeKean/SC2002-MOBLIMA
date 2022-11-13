package moviepackage;
import java.util.ArrayList;
public interface IReviews {
	/**
	 * Searches the movie array for the movie with the same title as MovieName and verifies that review and rating are valid arguments.
	 * If the movie is found and arguments are validated, a Review object is instantiated and appended to the movie's review array. 
	 * @param movieName to add review to
	 * @param review String content of the review
	 * @param rating Float value of the rating
	 * @throws IllegalArgumentException if movie is not found or if the review is invalid.
	 */
	void addReview(String movieName, String review, float rating) throws IllegalArgumentException;
	/**
	 * Returns the review array for the requested movieID
	 * @param movieID to get the review array for
	 * @return review attribute of requested movie object
	 * @throws IllegalArgumentException if movie is not found.
	 */
	ArrayList<Review> getReviews(int movieID) throws IllegalArgumentException;

	/**
	 * Prints the text representation of the reviews for a requested movie
	 * @param movieName to print the reviews for
	 * @throws IllegalArgumentException if the movie is not found or if the reviews array is empty
	 */
	void printReviews(String movieName) throws IllegalArgumentException;
}