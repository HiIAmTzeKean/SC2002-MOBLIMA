package moviepackage;
import java.util.ArrayList;
public interface IReviews {
	void addReview(int movieID, Review review);
	ArrayList<Review> getReviews(int movieID);
}