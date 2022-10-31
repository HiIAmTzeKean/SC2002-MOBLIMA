package moviepackage;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
//TODO: Move all function documentation to the interface file
/**
 * Manager class that implements interface methods to manage movies, sales, and reviews for a cinema.
 */
public class MovieManager implements ISales, IReviews, IMovie {
	private static ArrayList <Movie> movies;
	private static MovieManager movieManager;
	/**
	 * Default Constructor for MovieManager - initializes an empty ArrayList for Movies.
	 */
	private MovieManager(){
		movies = new ArrayList<Movie>();
	}
	/**
	 * Alternate Constructor for MovieManager that is used when serialising data - assigns the 
	 * @param movies the ArrayList of movies that is assigned to the instantiated object.
	 */
	private MovieManager(ArrayList<Movie> movies){
		MovieManager.movies = movies;
	}
	/**
	 * Reads serialised data from a .dat file, returns an empty ArrayList if there is an error reading the file.
	 * @param fileName the filename to read the serialized data from
	 * @return an ArrayList containing the serialised movie data from the file.
	 */
	private static ArrayList<Movie> deseraliseMovies(String fileName){
		ArrayList<Movie> m = null;
		try{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			m = (ArrayList<Movie>) in.readObject();
			in.close();
		}
		catch(IOException i){
			return new ArrayList<Movie>();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		return m;
	}
	/**
	 * Function that writes a serialisable ArrayList of movies to a .dat file.
	 * @param filename filepath of the .dat file to serialise data to
	 * @param m ArrayList of movies to serialise
	 */
	private static void serialiseMovies(String filename, ArrayList<Movie> m){
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(m);
			oos.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	/**
	 * Checks if there is an existing instance of MovieManager. 
	 * If none exists, load movie information stored in the data file with deserialiseMovies() and return the instantiated object.
	 * @return an instantiated MovieManager instance.
	 */
	public static MovieManager getInstance(){
		if(movieManager == null){
			ArrayList<Movie> m = MovieManager.deseraliseMovies("./MOBLIMA application/data/movie/movie.dat");
			MovieManager.movieManager = new MovieManager(m);
			return MovieManager.movieManager;
		}
		return MovieManager.movieManager;
	}
	/**
	 * A destructor that saves the current state of the movies array into a .dat file and deallocates memory to the MovieManager object.
	 */
	public static void close(){
		MovieManager.serialiseMovies("./MOBLIMA application/data/movie/movie.dat", movies);
		MovieManager.movieManager = null;
	}
	/**
	 * Returns the index of the movie with the corresponding movieID
	 * @param id movieID to search for
	 * @return index of movie in the movies array
	 * @throws IllegalArgumentException if the movies array is empty or if the movie is not found.
	 */
	public int findMovie(int id) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		int search = 0;
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getID() == id){
				return search;
			}
			search++;
		}
		throw new IllegalArgumentException("Movie not found");
	}
	public int findMoviebyName(String movieName) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		int search = 0;
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getMovieTitle() == movieName){
				return search;
			}
			search++;
		}
		throw new IllegalArgumentException("Movie not found");
	}
	public Movie getMoviefromID(int id) throws IllegalArgumentException{
		try{
			Movie toReturn = movies.get(findMovie(id));
			return toReturn;
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * @return current array of movies
	 */
	public ArrayList<Movie> getMovies(){
		return movies;
	}

	@Override
	/**
	 * Prints the movies stored in the movies array. Used to support class functions to get and set movie attributes.
	 * Ignores movies that have been marked as "End of Showing"
	 */
	public void printMovies(){
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getMovieStatus()!=MovieStatus.END_OF_SHOWING){
				m.printMovieIncomplete();
			}
		}
	}
	/** 
	 * Function that checks if the review object is valid and appends it to the a movie's review array if the movieID is valid.
	@param movieID ID of the movie to add the review to.
	@param review Review object to append to the movie's existing reviews.
	@throws IllegalArgumentException in the case of an invalid review or movieID.
	 **/
	@Override
	//TODO: Change to String movieName
	public void addReview(String movieName, String review, float rating) throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMoviebyName(movieName);

		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		//Check if the review text is not empty
		if(review == "" || review == " "){
			throw new IllegalArgumentException("Review must not be empty");
		}
		//Check if review rating is within the preset bounds
		if(rating <= 0 || rating >= 5.0){
			throw new IllegalArgumentException("Review rating is invalid");
		}
		Review newReview = new Review(rating, review);
		//If no errors, add the review to the array of the movie object
		movies.get(target).addReview(newReview);
	}
	/**
	 * Instantiates a movie object if the arguments are valid and appends it to the movies array.
	 * @throws IllegalArgumentException if movie constructor arguments are invalid.
	 */
	@Override
	public void createMovie(String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration)throws IllegalArgumentException {
		//Do a check for other invalid entry cases
		if(movieTitle=="" || synopsis == "" || director == "" || cast == "" || duration == 0){
			throw new IllegalArgumentException("Insufficient details added for creating movie.");
		}
		//Finally, create the movie object and add it to the movie array
		Movie toAdd = new Movie(movieTitle,movieStatus,synopsis,director,cast,ageRestriction,movieType,duration);
		movies.add(toAdd);
	}
	/**
	 * Deletes the Movie object with the movieID from the movies array if it exists.
	 * @param movieID movieID to delete from the array
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	@Override
	public void deleteMovie(int movieID) throws IllegalArgumentException {
		int target = 0;
		try{
			target = findMovie(movieID);
			movies.remove(target);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * Returns a MovieStatus object for the movie with the corresponding ID.
	 * @param movieID movieID to return MovieStatus for
	 * @return MovieStatus object for the requested movie
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	@Override
	public MovieStatus getMovieStatus(int movieID)throws IllegalArgumentException{
		int target = 0;
		MovieStatus toReturn;
		try{
			target = findMovie(movieID);
			toReturn = movies.get(target).getMovieStatus();
			return toReturn;
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	//TODO: Discuss - use string or use MovieStatus?
	/**
	 * Sets the MovieStatus for the movie with the corresponding movieID.
	 * @param movieID to set MovieStatus for
	 * @param status to set for the requested movie
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search, or if MovieStatus argument is invalid.
	 */
	@Override
	public void setMovieStatus(int movieID, MovieStatus status)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		movies.get(target).setMovieStatus(status);
	}
	@Override
	/**
	 * Gets the director parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @return String value of the movieDirector
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	public String getMovieDirector(int movieID)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
			return movies.get(target).getDirector();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	
	@Override
	/**
	 * Sets the director parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param directorName string to change director to
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search, or if the directorName string is invalid.
	 */
	public void setMovieDirector(int movieID, String directorName)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		if(directorName == "" || directorName == " "){
			throw new IllegalArgumentException("Director name cannot be blank");
		}
		movies.get(target).setMovieDirector(directorName);
	}
	@Override
	/**
	 * Gets the movieType parameter for the movie with the corresponding movieID.
	 * @param movieID to get movieType for
	 * @return MovieType object for the movie.
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	public MovieType getMovieType(int movieID)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
			return movies.get(target).getMovieType();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * Sets the movieType parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param movieType to set value to
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search or if movieType is invalid.
	 */
	public void setMovieType(int movieID, String movieType)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		try{
			MovieType mType = MovieType.valueOf(movieType);
			movies.get(target).setMovieType(mType);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Invalid movie type");
		}
	}
	
	@Override
	/**
	 * Gets the ArrayList of reviews for the movie with the corresponding movieID.
	 * @param movieID to get reviews for
	 * @return ArrayList of reviews for the corresponding movie.
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	public ArrayList<Review> getReviews(int movieID)throws IllegalArgumentException{	
		int target = 0;
		try{
			target = findMovie(movieID);
			return movies.get(target).getReviews();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	@Override
	/**
	 * Gets the sales parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @return value of sales for the current movie
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	public int getSales(int movieID)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
			return movies.get(target).getSales();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * Adds to the sales parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param sales amount of sales to add to the counter
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search or if sales=0.
	 */
	public void addSales(int movieID, int sales) throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		if(sales == 0){
			throw new IllegalArgumentException("Sales cannot be zero");
		}
		movies.get(target).addSales(sales);
	}
	@Override
	/**
	 * Creates a copy of the movies array, sorts it by the average review rating parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	public void getTop5_rating() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.reviewComparator);
		if(moviecopy.size() > 5){
			limit = 4;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("The Top Five Selling Movies Are:");
		for(int i = 0; i<limit; i++){
			System.out.printf("%d : %s (%d)\n", i+1, moviecopy.get(i).getMovieTitle(), moviecopy.get(i).getSales());
		}
	}
	@Override
	/**
	 * Creates a copy of the movies array, sorts it by the sales parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	public void getTop5_sales() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.salesComparator);
		if(moviecopy.size() > 5){
			limit = 4;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("The Top Five Selling Movies Are:");
		for(int i = 0; i<limit; i++){
			System.out.printf("%d : %s (%d)\n", i+1, moviecopy.get(i).getMovieTitle(), moviecopy.get(i).getSales());
		}
	}
}