package moviepackage;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;
public class Movie implements Serializable{
	//Used to allocate each movie with a unique ID
	private static int instanceCounter = 1;
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
	private ArrayList<Review> reviews; 
	private static HashMap<MovieType, Double> multiplier;
	protected static SortByRating ratingComparator2;
	protected static SortBySales salesComparator2;
	static{
		multiplier = new HashMap<MovieType, Double>();
		multiplier.put(MovieType._2D,1.10);
		multiplier.put(MovieType._3D,1.25);
		multiplier.put(MovieType.BLOCKBUSTER,1.5);
		ratingComparator2 = new SortByRating();
		salesComparator2 = new SortBySales();
	}
	public Movie(String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration, ArrayList<Review> reviews){
		this.id = instanceCounter++;
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ageRestriction = ageRestriction;
		this.movieType = movieType;
		this.duration = duration;
		this.sales = 0;
		this.reviews = reviews;
	}
	public Movie(String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration){
		this.id = instanceCounter++;
		this.movieTitle = movieTitle;
		this.movieStatus = movieStatus;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.ageRestriction = ageRestriction;
		this.movieType = movieType;
		this.duration = duration;
		this.sales = 0;
		this.reviews = new ArrayList<Review>();
	}
	protected Movie(Movie that){
		this(
			that.getMovieTitle(),
			that.getMovieStatus(),
			that.getSynopsis(),
			that.getDirector(),
			that.getCast(),
			that.getAgeRestriction(),
			that.getMovieType(),
			that.getDuration());
	}
	public int getID(){
		return this.id;
	}
	protected static void incrementInstance(int toIncrement){
		instanceCounter+=toIncrement;
	}
	protected static int getInstance(){
		return instanceCounter;
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
		float sum = 0;
		int len = this.getReviews().size();
		if(reviews.size() == 0){
			return answer;
		}
		for(Review review: this.getReviews()){
			sum+= review.getRating();
		}
		answer = sum/len;
		return answer;
	}
	public void addReview(Review review){
		this.reviews.add(review);
	}
	public int getSales(){
		System.out.printf("ID: %d\n",this.id);
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
	public void deleteMovie(){
		this.setMovieStatus(MovieStatus.END_OF_SHOWING);
	}
	public void printMovieComplete(){
		//Printing out the requirements needed in the PDF
		System.out.println("---------------------");
		System.out.printf("Title: %s\n",this.movieTitle);
		System.out.printf("Showing Status: %s\n",this.movieStatus.toString());
		System.out.printf("Synopsis: %s\n",this.synopsis);
		System.out.printf("Director: %s\n",this.director);
		System.out.printf("Cast: %s\n",this.cast);
		System.out.printf("Average Review Score: %f\n",this.getReviewScores());
		System.out.printf("Past Reviews:\n");
		int counter = 1;
		for(Iterator<Review> it = reviews.iterator(); it.hasNext();){
			if(counter<=3){
				break;
			}
			Review re = it.next();
			System.out.printf("%.1f : %s", re.getRating(), re.getReview());
			counter++;
		}
	}
	public void printMovieIncomplete(){
		System.out.println("---------------------");
		System.out.printf("Name: %s (%s)\n",this.movieTitle, this.movieStatus.toString());
		System.out.printf("Director: %s\n",this.director);
		System.out.printf("Age Rating: %s\n",this.ageRestriction.toString());
		System.out.printf("Duration: %d minutes\n",this.duration);
		//Right now this prints all the reviews, are we thinking about setting a cap on this?
		//Maybe iterate in reverse to look at the most recent reviews? Last 3 reviews? etc.
		System.out.println("Reviews:");
		if(this.reviews.size() == 0){
			System.out.println("There are no reviews yet for this movie.");
		}
		else{
			for(Iterator<Review> it = reviews.iterator(); it.hasNext();){
				Review re = it.next();
				System.out.printf("%.1f : %s\n", re.getRating(), re.getReview());
			}
		}
		
	}
	public Movie getClone(){
		Movie toReturn = new Movie(this);
		return toReturn;
	}
}