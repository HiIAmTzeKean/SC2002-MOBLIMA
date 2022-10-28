package moviepackage;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
public class Movie {
	private int id;
	private String movieTitle;
	private MovieStatus movieStatus;
	private String synopsis;
	private String director;
	private String cast;
	private AgeRestriction ageRestriction;
	private MovieType movieType;
	private int sales;
	private int duration;
	private ArrayList<Review> reviews = new ArrayList<Review>();
	private static HashMap<MovieType, Double> multiplier;
	static{
		multiplier = new HashMap<MovieType, Double>();
		multiplier.put(MovieType.valueOf("2D"),1.10);
		multiplier.put(MovieType.valueOf("3D"),1.25);
		multiplier.put(MovieType.valueOf("BLOCKBUSTER"),1.5);
	}
	public Movie(int movieID, String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration){
		this.id = movieID;
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ageRestriction = ageRestriction;
		this.movieType = movieType;
		this.duration = duration;
		this.sales = 0;
	}
	public int getID(){
		return this.id;
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
	public void setMovieType(MovieType movieType){
		this.movieType = movieType;
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
	public void addReview(Review review){
		this.reviews.add(review);
	}
	public int getSales(){
		return this.sales;
	}
	public double getMultiplier(){
		double m = multiplier.get(this.movieType);
		return m;
	}
	public int getDuration(){
		return this.duration;
	}
	public void setDuration(int duration){
		this.duration = duration;
	}
	public void setMovieStatus(MovieStatus status){
		this.movieStatus = status;
	}
	public void addSales(int toAdd){
		this.sales+=toAdd;
	}
	public void printMovieComplete(){
		System.out.printf("Name: %s\n",this.movieTitle);
		System.out.printf("Director: %s\n",this.director);
		System.out.printf("Cast: %s\n",this.cast);
		System.out.printf("Age Rating: %s\n",this.ageRestriction.toString());
		System.out.printf("Duration: %d\n",this.duration);
		System.out.printf("Average Review Score: %\n",this.getReviewScores());
	}
	public void printMovieIncomplete(){
		System.out.printf("Name: %s\n",this.movieTitle);
		System.out.printf("Director: %s\n",this.director);
		System.out.printf("Age Rating: %s\n",this.ageRestriction.toString());
		System.out.printf("Duration: %d\n",this.duration);
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