package moviepackage;
import java.util.Comparator;
import java.util.ArrayList;

public class Movie {
	private String movieTitle;
	private MovieStatus movieStatus;
	private String synopsis;
	private String director;
	private String cast;
	private AgeRestriction ageRestriction;
	private MovieType movieType;
	private ArrayList<Review> reviews;
	private int sales;
	public Movie(String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, ArrayList<Review> reviews, int sales){
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ageRestriction = ageRestriction;
		this.movieType = movieType;
		this.reviews = reviews;
		this.sales = sales;
	}
	public String getMovieTitle(){
		return this.movieTitle;
	}
	public MovieStatus getMovieStatus(){
		return this.movieStatus;
	}
	public String getSynopsis(){
		return this.synopsis;
	}
	public String getDirector(){
		return this.director;
	}
	public void setMovieDirector(String director){
		this.director = director;
	}
	public String getCast(){
		return this.cast;
	}
	public AgeRestriction getAgeRestriction(){
		return this.ageRestriction;
	}
	public MovieType getMovieType(){
		return this.movieType;
	}
	public ArrayList<Review> getReviews(){
		return this.reviews;
	}
	public float getReviewScores(){
		float answer = 0;
		for(Review review: this.getReviews()){
			answer+= review.getRating();
		}
		return answer/(float)this.getReviews().size();
	}
	public int getSales(){
		return this.sales;
	}
	public void setMovieStatus(ShowStatus status){
		//TODO: Check if needed??
	}
	
	public static Comparator<Movie> salesComparator = new Comparator<Movie>(){
		public int compare(Movie m1, Movie m2){
			return m1.getSales() - m2.getSales();
		}
	};
	public static Comparator<Movie> reviewComparator = new Comparator<Movie>() {
		public int compare(Movie m1, Movie m2){
			return Float.compare(m1.getReviewScores(),m2.getReviewScores());
		}
	};
}