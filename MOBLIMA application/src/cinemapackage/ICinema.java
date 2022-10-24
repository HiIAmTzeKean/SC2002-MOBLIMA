package cinemapackage;

public interface ICinema {

	void createCinema(String name, int id, String type);

	/**
	 * Search through array of cinemas and locate the id to be delete. If the ID is found, success
	 * message will be printed else an 
	 * @param id
	 */
	void deleteCinema(int id);

}