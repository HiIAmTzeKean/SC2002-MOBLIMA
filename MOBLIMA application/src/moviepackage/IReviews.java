package moviepackage;
import java.util.ArrayList;
public interface IReviews {
	void addReview(String movieName, String review, float rating);
	ArrayList<Review> getReviews(int movieID);
}