package moviepackage;

public interface IMovie {
	/**
	 * Searches the MovieManager's movies array and returns the index of the movie with the requested movieID.
	 * @param movieID of movie to return
	 * @return the index of the requested movie in the movies array.
	 * @throws IllegalArgumentException if the movie is not found.
	 */
	int findMovie(int movieID) throws IllegalArgumentException;
	
	/**
	 * Returns the movie object if the movieTitle is the same as the requested string.
	 * @param movieTitle to search for.
	 * @return the movie object of the requested movie.
	 * @throws IllegalArgumentException if the movie is not found.
	 */
	Movie findMoviebyName(String movieTitle) throws IllegalArgumentException;
	
	/**
	 * Returns the movie object if the movieID is the same as the requested integer.
	 * @param movieID to search for.
	 * @return the movie object of the requested movie.
	 * @throws IllegalArgumentException if the movie is not found.
	 */
	Movie getMoviefromID(int movieID) throws IllegalArgumentException;
	
	/**
	 * Instantiates a movie object based after checking the function arguments and checking for input errors.
	 * If the movie object is succesfully instantiated, it is added to the movies array of the MovieManager.
	 * @param movieTitle String to be added.
	 * @param movieStatus MovieStatus object to be set.
	 * @param synopsis String to be added.
	 * @param director String to be added.
	 * @param cast String to be added.
	 * @param ageRestriction AgeRestriction object to be added.
	 * @param movieType MovieType object to be added.
	 * @param duration integer value to be added.
	 * @throws IllegalArgumentException if arguments are invalid.
	 */
	void createMovie(String movieTitle, MovieStatus movieStatus, 
	String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration) throws IllegalArgumentException;
	
	/**
	 * Searches the movies array of the MovieManager object and deletes the Movie from the array if it is found.
	 * @param movieID to return the status for.
	 * @throws IllegalArgumentException if the movieID to delete is not found.
	 */
	void deleteMovie(int movieID) throws IllegalArgumentException;
	
	/**
	 * Searches the movies array of the MovieManager object and returns the MovieStatus if available.
	 * @param movieID to return the status for.
	 * @return MovieStatus object for the requested movie.
	 * @throws IllegalArgumentException if the movie is not found.
	 */
	MovieStatus getMovieStatus(int movieID)throws IllegalArgumentException;
	
	/**
	 * Sets the MovieStatus value for the requested movieID in the function.
	 * @param movieID to set the movieStatus for. 
	 * @param status to set the movieStatus to.
	 * @throws IllegalArgumentException if the movie is not found.
	*/
	void setMovieStatus(int movieID, MovieStatus status)throws IllegalArgumentException;
	
	/**
	 * Returns the String value of the Director attribute for the movie with the given movieID.
	 * @param movieID to get the director for
	 * @return String value of director attribute.
	 * @throws IllegalArgumentException if movie is not found.
	*/
	String getMovieDirector(int movieID)throws IllegalArgumentException;
	
	/**
	 * Sets the MovieDirector attribute for the requested movieID. 
	 * @param movieID to set the director for
	 * @param directorName String value to set the director to.
	 * @throws IllegalArgumentException if directorName is an empty string or if movie is not found.
	 */
	void setMovieDirector(int movieID, String directorName)throws IllegalArgumentException;
	
	/**
	 * Returns the MovieType attribute for the requested movieID.
	 * @param movieID to get the MovieType for
	 * @return MovieType attribute for requested movie
	 * @throws IllegalArgumentException if requested movie is not found.
	 */
	MovieType getMovieType(int movieID)throws IllegalArgumentException;

	/**
	 * Sets the MovieType attribute for the requested movieID.
	 * @param movieID
	 * @param movieType
	 * @throws IllegalArgumentException if the requested movie is not found.
	 */
	void setMovieType(int movieID, MovieType movieType)throws IllegalArgumentException;

	public Movie getClone(int movieID) throws IllegalArgumentException;
	public Movie getClone(String movieName) throws IllegalArgumentException;
	
	/**
	 * Prints a list of details for all movies.
	 * @throws IllegalArgumentException if there are no movies to print information for.
	 */
	void printMovies() throws IllegalArgumentException;
	
	void printMovieTitles() throws IllegalArgumentException;

	void printMoviesAdmin() throws IllegalArgumentException;
}