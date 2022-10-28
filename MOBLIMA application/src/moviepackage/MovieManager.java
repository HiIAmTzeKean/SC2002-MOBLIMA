package moviepackage;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
//TODO - Add JavaDoc Comments
//TODO - Add Serializable Data
/**
 * Manager class that implements interface methods to manage movies, sales, and reviews for a cinema.
 */
public class MovieManager implements ISales, IReviews, IMovie {
	private static ArrayList <Movie> movies;
	private static MovieManager movieManager;
	private MovieManager(){
		movies = new ArrayList<Movie>();
	}
	private MovieManager(ArrayList<Movie> movies){
		MovieManager.movies = movies;
	}
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
	public static MovieManager getInstance(){
		if(movieManager == null){
			ArrayList<Movie> m = MovieManager.deseraliseMovies("./MOBLIMA application/data/movie/movie.dat");
			MovieManager.movieManager = new MovieManager(m);
			return MovieManager.movieManager;
		}
		return MovieManager.movieManager;
	}
	public static void close(){
		MovieManager.serialiseMovies("./MOBLIMA application/data/movie/movie.dat", movies);
		MovieManager.movieManager = null;
	}
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
	public ArrayList<Movie> getMovies(){
		return movies;
	}

	@Override
	/**
	 * Prints the movies stored in the movies array. Used to support class functions to get and set movie attributes.
	 */
	public void printMovies(){
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			m.printMovieIncomplete();
		}
	}
	@Override
	public void addReview(int movieID, Review review){
		int target = 0;
		try{
			target = findMovie(movieID);

		}catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		//Check if the review text is not empty
		if(review.getReview() == "" || review.getReview() == " "){
			throw new IllegalArgumentException("Review must not be empty");
		}
		//Check if review rating is within the preset bounds
		if(review.getRating() <= 0 || review.getRating() >= 5.0){
			throw new IllegalArgumentException("Review rating is invalid");
		}
		//If no errors, add the review to the array of the movie object
		movies.get(target).addReview(review);
	}
	@Override
	public void createMovie(int movieID, String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration)throws IllegalArgumentException {
		//Check if ID already exists - O(n) complexity can be improved?
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getID() == movieID){
				throw new IllegalArgumentException("MovieID already exists");
			}
		}
		//Do a check for other invalid entry cases
		if(movieTitle=="" || synopsis == "" || director == "" || cast == "" || duration == 0){
			throw new IllegalArgumentException("Insufficient details added for creating movie.");
		}
		//Finally, create the movie object and add it to the movie array
		Movie toAdd = new Movie(movieID, movieTitle,movieStatus,synopsis,director,cast,ageRestriction,movieType,duration);
		movies.add(toAdd);
	}
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
	public void getTop5_rating(){
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
	@Override
	public void getTop5_sales(){
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