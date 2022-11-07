package moviepackage;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;
public class Movie implements Serializable{
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
	//Code Block that Instantiates Multiplier Values that are used in pricing functions.
	//Rating Comparators and Sales Comparators are also instantiated to allow easy comparision between  movie objects.
	static{
		multiplier = new HashMap<MovieType, Double>();
		multiplier.put(MovieType._2D,1.10);
		multiplier.put(MovieType._3D,1.25);
		multiplier.put(MovieType.BLOCKBUSTER,1.5);
		ratingComparator2 = new SortByRating();
		salesComparator2 = new SortBySales();
	}
	/**
	 * Constructs a Movie object based on the parameters.
	 * Used during serialization when reviews are also specified in object data.
	 * @param movieTitle
	 * @param movieStatus
	 * @param synopsis
	 * @param director
	 * @param cast
	 * @param ageRestriction
	 * @param movieType
	 * @param duration
	 * @param reviews
	 */
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
	/**
	 * Constructor for movie object.
	 * Used in staff view when the reviews array is initialized as an empty ArrayList.
	 * @param movieTitle
	 * @param movieStatus
	 * @param synopsis
	 * @param director
	 * @param cast
	 * @param ageRestriction
	 * @param movieType
	 * @param duration
	 */
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
	/**
	 * Protected constructor for a Movie that is only used to create a deep copy of the specified object.
	 * //TODO: Where is this used? 
	 * @param that movie object to make a deep copy of.
	 */
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
	/**
	 * @return movieID parameter of the object.
	 */
	public int getID(){
		return this.id;
	}
	/**
	 * Protected function that is used to add x values to the instanceCounter of the movie class.
	 * Used by MovieManager when movies are serialized to ensure that the next movie generated has an ID that does not clash with pre-serialized movies.
	 * @param toIncrement amount to increment by.
	 */
	protected static void incrementInstance(int toIncrement){
		instanceCounter+=toIncrement;
	}
	/**
	 * Protected function that is used to return the value of the Static instance counter.
	 * @return value of instanceCounter.
	 */
	protected static int getInstance(){
		return instanceCounter;
	}
	/**
	 * Get method for movieTitle attribute.
	 * @return value of movieTitle.
	 */
	public String getMovieTitle(){
		return this.movieTitle;
	}
	/**
	 * Get method for movieStatus attribute.
	 * @return value of movieStatus.
	 */
	public MovieStatus getMovieStatus(){
		return this.movieStatus;
	}
	/**
	 * Get method for synopsis attribute.
	 * @return value of synopsis.
	 */
	public String getSynopsis(){
		return this.synopsis;
	}
	/**
	 * Get method for synopsis attribute.
	 * @return value of director.
	 */
	public String getDirector(){
		return this.director;
	}
	/**
	 * Set method for director attribute.
	 * @param director new string value to set attribute to.
	 */
	public void setMovieDirector(String director){
		this.director = director;
	}
	/**
	 * Get method for cast attribute.
	 * @return value of cast.
	 */
	public String getCast(){
		return this.cast;
	}
	/**
	 * Get method for ageRestriction attribute.
	 * @return value of ageRestriction.
	 */
	public AgeRestriction getAgeRestriction(){
		return this.ageRestriction;
	}
	/**
	 * Get method for movieType attribute.
	 * @return value of movieType.
	 */
	public MovieType getMovieType(){
		return this.movieType;
	}
	/**
	 * Set method for movieType attribute.
	 * @param movieType to set attribute to.
	 */
	public void setMovieType(MovieType movieType){
		this.movieType = movieType;
	}
	/**
	 * Get method for reviews attribute attribute.
	 * @return value of reviews.
	 */
	public ArrayList<Review> getReviews(){
		return this.reviews;
	}
	/**
	 * Iterates through all reviews and returns the average score.
	 * Used in customer view functions to give overall movie rating
	 * @return average movie rating.
	 */
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
	/**
	 * Appends a review object to the ArrayList of reviews.
	 * @param review object to append to ArrayList.
	 */
	public void addReview(Review review){
		this.reviews.add(review);
	}
	/**
	 * Get method for sales attribute.
	 * @return value of sales.
	 */
	public int getSales(){
		//System.out.printf("ID: %d\n",this.id);
		return this.sales;
	}
	/**
	 * Get method for multiplier. Used in sales classes to determine price.
	 * Return value depends on the movieType attribute of the object.
	 * @return value of multiplier.
	 */
	public double getMultiplier(){
		double m = multiplier.get(this.movieType);
		return m;
	}
	/**
	 * Get method for duration attribute.
	 * @return value of duration.
	 */
	public int getDuration(){
		return this.duration;
	}
	/**
	 * Set method for duration attribute.
	 * @param duration to set movie to
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	/**
	 * Set method for movieStatus attribute.
	 * @param status to set movie to
	 */
	public void setMovieStatus(MovieStatus status){
		this.movieStatus = status;
	}
	/**
	 * Increments the sale counter by the specified attribute
	 * @param toAdd amount to add by. 
	 */
	public void addSales(int toAdd){
		this.sales+=toAdd;
	}
	/**
	 * Marks the current movie status as "END_OF_SHOWING" enum status. 
	 * Used to achieve a "soft delete" to remove the movie from the Customer view but keep it in staff view.
	 */
	public void deleteMovie(){
		this.setMovieStatus(MovieStatus.END_OF_SHOWING);
	}
	public void printMovieComplete(){
		//Printing out the requirements needed in the PDF
		System.out.print("\033[H\033[2J");             
		System.out.println("---------------------------------------");
		System.out.printf("Title: %s\n",this.movieTitle);
		System.out.println("---------------------------------------");		
		System.out.printf("Showing Status: %s\n",this.movieStatus.toString());
		System.out.println("---------------------------------------");
		String synopsisString = wrapString(this.synopsis, "\n", 30);
		System.out.printf("Synopsis: %s\n",synopsisString);
		System.out.println("---------------------------------------");
		System.out.printf("Director: %s\n",this.director);
		System.out.println("---------------------------------------");
		String castString = wrapString(this.cast, "\n", 30);
		System.out.printf("Cast: %s\n",castString);
		System.out.println("---------------------------------------");
		System.out.printf("Average Review Score: %.1f\n",this.getReviewScores());
		System.out.println("---------------------------------------");
		// System.out.printf("Past Reviews:\n");
		// int counter = 1;
		// for(Iterator<Review> it = reviews.iterator(); it.hasNext();){
		// 	if(counter<=3){
		// 		break;
		// 	}
		// 	Review re = it.next();
		// 	System.out.printf("%.1f : %s", re.getRating(), re.getReview());
		// 	counter++;
		// }
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
	/**
	 * Function that is used to generate a deep copy of movie object by calling the protected constructor.
	 * @return instantiated deep copy of the movie object.
	 */
	public Movie getClone(){
		Movie toReturn = new Movie(this);
		return toReturn;
	}
	/**
	 * Helper function that is used in printReviews() to wrap long strings to a specified length.
	 * @param s input string to wrap
	 * @param deliminator to divide string by
	 * @param length to wrap string with
	 * @return wrapped string value
	 */
	private String wrapString(String s, String deliminator, int length){
		String result = "";
		int lastdelimPos = 0;
		for(String token:s.split(" ", -1)){
			if(result.length()-lastdelimPos + token.length() > length){
				result = result + deliminator + token;
				lastdelimPos = result.length() + 1;
			}
			else{
				result += (result.isEmpty()? "" : " ") + token;
			}
		}
		return result;
	}
}