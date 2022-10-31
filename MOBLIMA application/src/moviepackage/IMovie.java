package moviepackage;

public interface IMovie {
	int findMovie(int id) throws IllegalArgumentException;
	Movie findMoviebyName(String movieTitle) throws IllegalArgumentException;
	Movie getMoviefromID(int id) throws IllegalArgumentException;
	void createMovie(String movieTitle, MovieStatus movieStatus, 
	String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration);
	void deleteMovie(int movieID) throws IllegalArgumentException;
	MovieStatus getMovieStatus(int movieID)throws IllegalArgumentException;
	void setMovieStatus(int movieID, MovieStatus status)throws IllegalArgumentException;
	String getMovieDirector(int movieID)throws IllegalArgumentException;
	void setMovieDirector(int movieID, String directorName)throws IllegalArgumentException;
	MovieType getMovieType(int movieID)throws IllegalArgumentException;
	void setMovieType(int movieID, String movieType)throws IllegalArgumentException;
	void printMovies()throws IllegalArgumentException;
}