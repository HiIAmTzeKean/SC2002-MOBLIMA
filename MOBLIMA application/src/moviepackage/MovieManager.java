package moviepackage;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
//TODO - Add JavaDoc Comments
/**
 * Manager class that implements interface methods to manage movies, sales, and reviews for a cinema.
 */
public class MovieManager implements ISales, IReviews, IMovie {
	private ArrayList <Movie> movies;
	public MovieManager(){
		this.movies = new ArrayList<>();
	}
	public ArrayList<Movie> getMovies(){
		return this.movies;
	}
	@Override
	/**
	 * Prints the movies stored in the movies array. Used to support class functions to get and set movie attributes.
	 */
	public void printMovies(){
		for(int i = 0; i<this.movies.size();i++){
			System.out.printf("%d. %s",i+1,movies.get(i).getMovieTitle());
		}
	}
	@Override
	public void addReview(){
		for(int i = 0; i<movies.size(); i++){
			System.out.printf("%d : %s\n", i+1, movies.get(i).getMovieTitle());
		}
		System.out.println("Which movie would you like to review?");
		Scanner sc = new Scanner(System.in);
		int movieIndex = sc.nextInt();
		if(movieIndex<=0 || movieIndex > movies.size()+1){
			sc.close();
			throw new IllegalArgumentException("No Movie was found.");
		}
		movieIndex--;
		System.out.println("Please enter a rating between 0-10");
		float rating = sc.nextFloat();
		if(rating <= 0 || rating > 5){
			System.out.println("The rating you have given is not within the boundaries. Please try again.");
			sc.close();
			return;
		}
		System.out.println("Please enter a short review about the movie between 0-10");
		String review = sc.nextLine();
		if(review == "" || review == " "){
			sc.close();
			throw new IllegalArgumentException("Reviews cannot be blank.");
		}
		Review newReview = new Review(rating, review);
		movies.get(movieIndex).getReviews().add(newReview);
		System.out.printf("Review succesfully added for Movie %s\n",movies.get(movieIndex).getMovieTitle());
		sc.close();
	}
	@Override
	public void createMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the details of the movie you want to create: ");
		System.out.println("Movie Name:");
		System.out.println("Movie Title: ");
		String movieTitle = sc.nextLine();
		System.out.println("Synopsis: ");
		String movieSynopsis = sc.next();
		System.out.println("Director: ");
		String movieDirector = sc.next();
		System.out.println("Synopsis: ");
		String movieCast = sc.next();
		System.out.println("Age Restriction: ");
		AgeRestriction movieAgeRestriction = AgeRestriction.valueOf(sc.next().toUpperCase());
		System.out.println("Status ");
		MovieStatus movieStatus = MovieStatus.valueOf(sc.next().toUpperCase());
		System.out.println("Type ");
		MovieType movieType = MovieType.valueOf(sc.next().toUpperCase());
		ArrayList<Review> reviews = new ArrayList<Review>();
		Movie newMovie = new Movie(movieTitle,movieStatus,movieSynopsis,movieDirector,movieCast,movieAgeRestriction,movieType,reviews,0);
		this.movies.add(newMovie);
		sc.close();
	}
	@Override
	public void deleteMovie() {
		Scanner sc = new Scanner(System.in);
		this.printMovies();
		System.out.println("Which movie would you like to delete?");
		int toDelete = sc.nextInt();
		if(toDelete <= 0 || toDelete > (this.getMovies().size()+1)){
			sc.close();
			throw new IllegalArgumentException("Movie is not found");
		}
		else{
			movies.remove(toDelete-1);
		}
		sc.close();
	}
	@Override
	public void getMovieStatus() {
		Scanner sc = new Scanner(System.in);
		this.printMovies();
		System.out.println("Which movie would you like to get the status for?");
		int toCheckStatus = sc.nextInt();
		if(toCheckStatus <= 0 || toCheckStatus > (this.getMovies().size()+1)){
			sc.close();
			throw new IllegalArgumentException("Movie is not found");
		}
		else{
			System.out.printf("Status of Movie %s is %d\n",movies.get(toCheckStatus).getMovieTitle(),movies.get(toCheckStatus-1).getMovieStatus());
		}
		sc.close();
	}
	@Override
	public void setMovieStatus() {
		Scanner sc = new Scanner(System.in);
		this.printMovies();
		System.out.println("Which movie would you like to set the status for?");
		int movieIndex = sc.nextInt();
		movieIndex--;
		if(movieIndex <= 0 || movieIndex > movies.size()){
			sc.close();
			throw new IllegalArgumentException("Movie not found.");
		}
		System.out.println("What would you like to change the status to?");
		for(ShowStatus status: ShowStatus.values()){
			System.out.println(status);
		}
		//TODO: How to do exception handling for this bit?
		try{
			//TODO: Check if needed
			ShowStatus newStatus = ShowStatus.valueOf(sc.next().toUpperCase());
			movies.get(movieIndex).setMovieStatus(newStatus);
		}
		catch(Exception e){	
		}
		sc.close();
	}
	@Override
	public void getMovieDirector() {
		if(movies.size() == 0){
			throw new NullPointerException("There are no movies added for this cinema.");
		}
		else{
			Scanner sc = new Scanner(System.in);
			this.printMovies();
			System.out.println("Which movie would you like to get the director for?");
			int movieIndex = sc.nextInt();
			movieIndex--;
			if(movieIndex <= 0 || movieIndex > movies.size()){
				sc.close();
				throw new IllegalArgumentException("Movie not found. Please try again.");
			}
			else{
				Movie movie = movies.get(movieIndex);
				System.out.printf("Director of Movie %s is %s",movie.getMovieTitle(),movie.getDirector());
				sc.close();
			}
		}	
	}
	@Override
	public void setMovieDirector() {
		if(movies.size() == 0){
			throw new NullPointerException("There are no movies added for this cinema.");
		}
		else{
			Scanner sc = new Scanner(System.in);
			this.printMovies();
			System.out.println("Which movie would you like to set the director for?");
			int movieIndex = sc.nextInt();
			movieIndex--;
			if(movieIndex <= 0 || movieIndex > movies.size()){
				sc.close();
				throw new IllegalArgumentException("Movie not found. Please try again.");
			}
			else{
				Movie movie = movies.get(movieIndex);
				System.out.println("Enter the name of the new director:");
				String newDirector = sc.nextLine();
				if(newDirector == "" || newDirector == " "){
					sc.close();
					throw new IllegalArgumentException("Please enter a valid director name");
				}
				movie.setMovieDirector(newDirector);
				sc.close();
			}
		}	
	}
	@Override
	public void getMovieType() {
		if(movies.size() == 0){
			throw new NullPointerException("There are no movies added for this cinema.");
		}
		else{
			Scanner sc = new Scanner(System.in);
			this.printMovies();
			System.out.println("Which movie would you like to get the type for?");
			int movieIndex = sc.nextInt();
			movieIndex--;
			if(movieIndex <= 0 || movieIndex > movies.size()){
				sc.close();
				throw new IllegalArgumentException("Movie not found. Please try again.");
			}
			else{
				Movie movie = movies.get(movieIndex);
				System.out.printf("Type of Movie %s is %s",movie.getMovieTitle(),movie.getMovieType());
				sc.close();
			}
		}	
	}
	@Override
	public void deleteReview() {
	}
	@Override
	public void getReviews(){	
		if(movies.size() == 0){
			throw new NullPointerException("There are no movies added for this cinema.");
		}
		Scanner sc = new Scanner(System.in);
		this.printMovies();
		System.out.println("Select the movie that you want to get reviews for");
		int movieIndex = sc.nextInt();
		movieIndex--;
		if(movieIndex <= 0 || movieIndex > movies.size()){
			sc.close();
			throw new IllegalArgumentException("Movie not found. Please try again.");
		}
		ArrayList<Review> reviews = movies.get(movieIndex).getReviews();
		if(reviews.size() == 0){
			System.out.println("There are currently no reviews for this movie.");
			sc.close();
			return;
		}
		else{
			for(Review review: reviews){
				System.out.println(review);
			}
		}
		sc.close();
	}
	@Override
	public void getSales(){
		if(movies.size() == 0){
			throw new NullPointerException("There are no movies added for this cinema.");
		}
		this.printMovies();
		Scanner sc = new Scanner(System.in);
		System.out.println("Select the movie you want to get sales for");
		int movieIndex = sc.nextInt();
		movieIndex--;
		if(movieIndex<=0 || movieIndex>this.movies.size()){
			sc.close();
			throw new IllegalArgumentException("Movie not found");
		}
		else{
			System.out.printf("Current Sales for Movie %s : %d\n",getMovies().get(movieIndex).getMovieTitle(),getMovies().get(movieIndex).getSales());
		}
		sc.close();
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