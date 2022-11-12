package moviepackage;
import java.util.Collections;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
/**
 * Manager class that implements interface methods to manage movies, sales, and reviews for a cinema.
 * @author Chaitanya Jadhav
 * @version 1.0
 * @since 2022-10-28
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
			//Is this a cheap way of solving this?
			//There's a mismatch between the id counter in the movie class when external movies are loaded in
			//I defined a protected method that just adds the number of movies in the array 
			//once it's been read from the file and add that to the counter.
			//So the next movie object created would have an ID of 8, and no conflicts.
			//System.out.printf("getInstance(): size of m is %d\n",m.size());
			//System.out.printf("GetInstance: incrementing the counter by %d\n",m.size());
			Movie.incrementInstance(m.size());
			MovieManager.movieManager = new MovieManager(m);
			//System.out.printf("GetInstance: instance value is now %d\n",Movie.instanceCounter);	
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
	public int findMovie(int movieID) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		int search = 0;
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getID() == movieID){
				return search;
			}
			search++;
		}
		throw new IllegalArgumentException("Movie not found");
	}

	/**
	 * Returns the movie object of the movie with the corresponding movieName
	 * @param movieName to search for
	 * @return corresponding movie object if found.
	 * @throws IllegalArgumentException if the movies array is empty or if the movie is not found.
	 */	
	public Movie findMoviebyName(String movieName) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getMovieTitle().equals(movieName)){
				return m;
			}
		}
		throw new IllegalArgumentException("Movie not found");
	}
	/**
	 * Returns the movie object of the movie with the corresponding movieName
	 * @param movieName to search for
	 * @return corresponding movie object if found.
	 * @throws IllegalArgumentException if the movies array is empty or if the movie is not found.
	 */	
	public Movie getMoviefromID(int movieID) throws IllegalArgumentException{
		try{
			Movie toReturn = movies.get(findMovie(movieID));
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
	public void printMovies() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		System.out.println("|------------------------------------------------------------------------------------------------------|");
		System.out.printf("|       %-30s        |    %-15s     |  %-8s  |  %-15s |\n",
								"Movie Name",
								"Status",
								"Duration",
								"Type");
		System.out.println("|------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getMovieStatus()!=MovieStatus.END_OF_SHOWING){
				System.out.printf("|       %-30s        |    %-15s     |  %-8s  |  %-15s |\n",
							m.getMovieTitle(),
							m.getMovieStatus().toString(),
							Integer.toString(m.getDuration()),
							m.getMovieType().toString());
			}
		}						
		System.out.println("|------------------------------------------------------------------------------------------------------|");
	}						   
	
	/**
	 * Prints a tabular output of movie titles. 
	 * Used to support customer class functions to help user to choose a movie.
	 * Ignores movies that have been marked as "End of Showing".
	 * @throws IllegalArgumentException if movie array is empty.
	 */	
	@Override
	public void printMovieTitles()throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		System.out.println("|---------------------------------------------|");
		System.out.printf("|       %-30s        |\n","Movie Name");
		System.out.println("|---------------------------------------------|");	
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			if(m.getMovieStatus()!=MovieStatus.END_OF_SHOWING){
				System.out.printf("|       %-30s        |\n",m.getMovieTitle());
			}	
		}
		System.out.println("|---------------------------------------------|");
	}
	/**
	 * Prints a tabular output of movie information. 
	 * Used to support staff class functions.
	 * @throws IllegalArgumentException if movie array is empty.
	 */		
	@Override
	public void printMoviesAdmin() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-8s   |       %-30s        |    %-30s     |     %-15s | %-15s | %5s |\n",
						"Movie ID",
								"Movie Name",
								"Director",
								"Status",
								"Type",
								"Sales");
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = movies.iterator(); it.hasNext();){
			Movie m = it.next();
			System.out.printf("|   %-8s   |       %-30s        |    %-30s     |     %-15s | %-15s | %5s |\n",
			    			Integer.toString(m.getID()),
							m.getMovieTitle(),
							m.getDirector(),
							m.getMovieStatus().toString(),
							m.getMovieType().toString(),
							Integer.toString(m.getSales()));
		}
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------------------------------|");			
	}
	/** 
	 * Function that checks if the review object is valid and appends it to the a movie's review array if the movieID is valid.
	 * @param movieID ID of the movie to add the review to.
	 * @param review Review object to append to the movie's existing reviews.
	 * @throws IllegalArgumentException in the case of an invalid review or movieID.
	 **/
	@Override
	public void addReview(String movieName, String review, float rating) throws IllegalArgumentException{
		Movie target;
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
		target.addReview(newReview);
	}
	/**
	 * Instantiates a movie object if the arguments are valid and appends it to the movies array.
	 * @throws IllegalArgumentException if movie constructor arguments are invalid.
	 */
	@Override
	public void createMovie(String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration)throws IllegalArgumentException {
		//Do a check for other invalid entry cases
		if(movieTitle=="" || movieTitle== " " || synopsis == "" || synopsis == " " | director == "" || director == " " || cast == "" || cast == " " || duration == 0 || movieType == null || ageRestriction == null){
			throw new IllegalArgumentException("Insufficient details added for creating movie.");
		}
		//Finally, create the movie object and add it to the movie array
		Movie toAdd = new Movie(movieTitle,movieStatus,synopsis,director,cast,ageRestriction,movieType,duration);
		toAdd.printMovieIncomplete();
		movies.add(toAdd);
	}
	/**
	 * Deletes the Movie object with the movieID from the movies array if it exists.
	 * @param movieID movieID to delete from the array
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
	@Override
	public void deleteMovie(int movieID) throws IllegalArgumentException {
		try{
			Movie toDelete = getMoviefromID(movieID);
			toDelete.deleteMovie();
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
	/**
	 * Sets the MovieStatus for the movie with the corresponding movieID.
	 * @param movieID to set MovieStatus for
	 * @param status to set for the requested movie
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search, or if MovieStatus argument is invalid.
	 */
	@Override
	public void setMovieStatus(int movieID, MovieStatus status)throws IllegalArgumentException{
		try{
			Movie target = getMoviefromID(movieID);
			target.setMovieStatus(status);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}

	}
	/**
	 * Gets the director parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @return String value of the movieDirector
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
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
	
	/**
	 * Sets the director parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param directorName string to change director to
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search, or if the directorName string is invalid.
	 */
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
	/**
	 * Gets the movieType parameter for the movie with the corresponding movieID.
	 * @param movieID to get movieType for
	 * @return MovieType object for the movie.
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
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
	/**
	 * Sets the movieType parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param movieType to set value to
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search or if movieType is invalid.
	 */
	@Override
	 public void setMovieType(int movieID, MovieType movieType)throws IllegalArgumentException{
		int target = 0;
		try{
			target = findMovie(movieID);
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
		movies.get(target).setMovieType(movieType);
	}
	
	/**
	 * Gets the ArrayList of reviews for the movie with the corresponding movieID.
	 * @param movieID to get reviews for
	 * @return ArrayList of reviews for the corresponding movie.
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
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
	/**
	 * Gets the sales parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @return value of sales for the current movie
	 * @throws IllegalArgumentException if the movieID is not found or if there are no movies to search.
	 */
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
	/**
	 * Adds to the sales parameter for the movie with the corresponding movieID.
	 * @param movieID to get director for
	 * @param sales amount of sales to add to the counter
	 * @throws IllegalArgumentException if the movieID is not found, if there are no movies to search or if sales=0.
	 */
	@Override
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
	/**
	 * Creates a copy of the movies array, sorts it by the average review rating parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	@Override
	public void getTop5_rating() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		int counter = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.ratingComparator2);
		if(moviecopy.size() >= 5){
			limit = 5;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								"Movie Name",
								"Age Rating",
								"Movie Type",
								"Duration",
								"Average Rating");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = moviecopy.iterator(); it.hasNext();){
			if(counter == limit) break;
			//System.out.println(counter);
			Movie m = it.next();
			String movieDuration = Integer.toString(m.getDuration());
			String reviewScore = Float.toString(m.getReviewScores());
			if(reviewScore.compareTo("0.0") == 0){
				reviewScore = "NA";
			}
			System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
							m.getMovieTitle(),
							m.getAgeRestriction().toString(),
							m.getMovieType().toString(),
							movieDuration,
							reviewScore);
			counter++;	
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");	
	}
	/**
	 * Creates a copy of the movies array, sorts it by the sales parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	@Override
	public void getTop5_sales() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		int counter = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.salesComparator2);
		if(moviecopy.size() >= 5){
			limit = 5;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								"Movie Name",
								"Age Rating",
								"Movie Type",
								"Duration",
								"Sales");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = moviecopy.iterator(); it.hasNext();){
			if(counter == limit) break;
			//System.out.println(counter);
			Movie m = it.next();
			System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
							m.getMovieTitle(),
							m.getAgeRestriction().toString(),
							m.getMovieType().toString(),
							Integer.toString(m.getDuration()),
							Integer.toString(m.getSales()));
			counter++;
	
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");	
	}
	/**
	 * Creates a copy of the movies array, sorts it by the rating parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * Used in customer views, hence ignores movies that are marked as END_OF_SHOWING
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	@Override
	public void getTop5_ratingCustomer() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		int counter = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.ratingComparator2);
		if(moviecopy.size() > 5){
			limit = 5;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								"Movie Name",
								"Age Rating",
								"Movie Type",
								"Duration",
								"Average Rating");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = moviecopy.iterator(); it.hasNext();){
			if(counter == limit) break;	
				//System.out.println(counter);
			Movie m = it.next();
			if(m.getMovieStatus()!=MovieStatus.END_OF_SHOWING){
				String movieDuration = Integer.toString(m.getDuration());
				System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								m.getMovieTitle(),
								m.getAgeRestriction().toString(),
								m.getMovieType().toString(),
								movieDuration,
								Float.toString(m.getReviewScores()));
				counter++;
			}
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
	}
	@Override
	/**
	 * Creates a copy of the movies array, sorts it by the sales parameter, and prints the information for the first n<=5 movies in the array based on array size.
	 * Used in customer views, hence ignores movies that are marked as END_OF_SHOWING
	 * @throws IllegalArgumentException if the movies array is empty.
	 */
	public void getTop5_salesCustomer() throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to sort");
		}
		int limit = 0;
		int counter = 0;
		ArrayList<Movie> moviecopy = this.getMovies();
		Collections.sort(moviecopy, Movie.salesComparator2);
		if(moviecopy.size() >= 5){
			limit = 5;
		}
		else{
			limit = moviecopy.size();
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								"Movie Name",
								"Age Rating",
								"Movie Type",
								"Duration",
								"Sales");
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");
		for(Iterator<Movie> it = moviecopy.iterator(); it.hasNext();){
			if(counter == limit) break;
				//System.out.println(counter);
			Movie m = it.next();
			if(m.getMovieStatus()!=MovieStatus.END_OF_SHOWING){
				System.out.printf("|       %-30s        |    %-10s     |  %-15s  |  %-10s | %-14s |\n",
								m.getMovieTitle(),
								m.getAgeRestriction().toString(),
								m.getMovieType().toString(),
								Integer.toString(m.getDuration()),
								Integer.toString(m.getSales()));
				counter++;
			}
		}
		System.out.println("|--------------------------------------------------------------------------------------------------------------------|");	
	}
	/**
	 * Searches the movies array and returns a deep copy of the object.
	 * @param movieID of the movie to be duplicated.
	 * @throws IllegalArgumentException if the movie is not found.
	 */	
	@Override
	public Movie getClone(int movieID) throws IllegalArgumentException{
		try{
			Movie toClone = movies.get(findMovie(movieID));
			return toClone.getClone();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * Searches the movies array and returns a deep copy of the object.
	 * @param movieName of the movie to be duplicated.
	 * @throws IllegalArgumentException if the movie is not found.
	 */
	@Override
	public Movie getClone(String movieName) throws IllegalArgumentException{
		try{
			Movie toClone = findMoviebyName(movieName);
			return toClone.getClone();
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
	}
	/**
	 * Searches the movie array for the movie with the corresponding movieName.
	 * Returns a tabulated view of all reviews for the selected movie.
	 * Used in customer classes during movie review lookup,
	 * @param movieName to return the reviews for.
	 * @throws IllegalAgumentException if the requested movie is not found.
	 */
	@Override
	public void printReviews(String movieName) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		try{
			Movie toPrint = findMoviebyName(movieName);
            System.out.print("\033[H\033[2J");    
			System.out.printf("Reviews for %s\n", movieName);
			System.out.println("-----------------------------------------------------------------");
			if(toPrint.getReviews().size()<=1){
				System.out.println("**Number of Ratings not More than One**");
				System.out.println("N/A");
				return;
			}
			for(Review r: toPrint.getReviews()){
				System.out.printf("Rating : %.1f\n", r.getRating());
				String reviewString = wrapString(r.getReview(),"\n",40);
				System.out.printf("Review : %s\n", reviewString);
				System.out.println("-----------------------------------------------------------------");
			}
		}
		catch(IllegalArgumentException e){
			throw new IllegalArgumentException("Movie not found");
		}
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
	
	/** 
	 * @param MovieName
	 * @return Boolean
	 * @throws IllegalArgumentException
	 */
	@Override
	public Boolean isValidMovieName(String MovieName) throws IllegalArgumentException{
		if(movies.size() == 0 || movies == null){
			throw new IllegalArgumentException("There are no movies to find");
		}
		Boolean answer = false;
		for(Movie m: movies){
			if(m.getMovieTitle().compareTo(MovieName) == 0){
				if(m.getMovieStatus()==MovieStatus.END_OF_SHOWING){
					throw new IllegalArgumentException("Requested Movie is No Longer Showing.");
				}
				else{
					answer = true;
					return answer;
				}
			}
		}	
		//throw new IllegalArgumentException("Movie not found");}
		return false;
	}
}