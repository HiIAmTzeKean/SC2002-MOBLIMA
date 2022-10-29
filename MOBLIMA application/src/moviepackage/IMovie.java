package moviepackage;

public interface IMovie {
	void createMovie(int movieID, String movieTitle, MovieStatus movieStatus, String synopsis, String director, String cast, AgeRestriction ageRestriction, MovieType movieType, int duration);
	void deleteMovie(int movieID) throws IllegalArgumentException;
	MovieStatus getMovieStatus(int movieID);
	void setMovieStatus(int movieID, MovieStatus status);
	String getMovieDirector(int movieID);
	void setMovieDirector(int movieID, String directorName);
	MovieType getMovieType(int movieID);
	void setMovieType(int movieID, String movieType);
	void printMovies();
}