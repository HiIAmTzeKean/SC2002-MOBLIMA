package moviepackage;
public interface ISales {
	/**
	 * Searches for the movie and returns the sales attributes.
	 * @param movieID to add sales for
	 * @return integer value of sales for the requested movie.
	 * @throws IllegalArgumentException if movie is not found.
	 */
	int getSales(int movieID) throws IllegalArgumentException;
	/**
	 * Adds an integer value to the sales attribute of the movie.
	 * @param movieID to add sales to
	 * @param sales integer value of sales to increment.
	 * @throws IllegalArgumentException if movie is not found or if sales = 0
	 */
	void addSales(int movieID, int sales) throws IllegalArgumentException;
	/**
	 * Creates a copy of the movies array and sorts it by the sales attribute.
	 * Prints out the top five movies sorted in descending order.
	 * @throws IllegalArgumentException if movie array is empty.
	 */
	void getTop5_sales() throws IllegalArgumentException;
	/**
	 * Creates a copy of the movies array and sorts it by the sales attribute.
	 * Prints out the top five movies sorted in descending order.
	 * @throws IllegalArgumentException if movie is not found.
	 */
	void getTop5_rating() throws IllegalArgumentException;
	void getTop5_ratingCustomer() throws IllegalArgumentException;
	void getTop5_salesCustomer() throws IllegalArgumentException;
}