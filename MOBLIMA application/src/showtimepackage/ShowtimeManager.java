package showtimepackage;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import cinemapackage.ICinemaBooking;
import customerpackage.Customer;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;

public class ShowtimeManager implements IShowtimeSystem {
	
	private static ArrayList<Showtime> showtimes;
	private static ShowtimeManager showtimeManager;
	private static int lastID;
	
	private ShowtimeManager(){
		lastID = 0;
	}
	private ShowtimeManager(ArrayList<Showtime> showtimeArray){
		ShowtimeManager.showtimes = showtimeArray;
		lastID = showtimes.size();
	}

	private static ArrayList<Showtime> deseraliseShowtimes(String filename){
		ArrayList<Showtime>  c = null;
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c = (ArrayList<Showtime>) in.readObject();
			in.close();
		} 
		catch (IOException i) {
			// i.printStackTrace();
			return new ArrayList<Showtime>();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}

	private static void seraliseShowtimes(String filename, ArrayList<Showtime> c) {
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(c);
			oos.close();
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static ShowtimeManager getInstance(){
		if (showtimeManager == null){
			ArrayList<Showtime> c = ShowtimeManager.deseraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat");
			ShowtimeManager.showtimeManager = new ShowtimeManager(c);
			return ShowtimeManager.showtimeManager;
		}
		return ShowtimeManager.showtimeManager;
	}

	public static void close() {
		ShowtimeManager.seraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat",showtimes);
		ShowtimeManager.showtimeManager = null;
	}


	public int getShowtimeIndex(int showtimeID) throws IllegalArgumentException {
		if (showtimes== null || showtimes.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}
		int count = 0;
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			if (it.next().getID() == showtimeID) {
				return count;
			}
			count++;
		}
		// Not found
		throw new IllegalArgumentException("Showtime is not found");
	}
	@Override
	public void bookSeat(int showtimeID, String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customerID);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in booking seat");
		}
	}

	@Override
	public boolean isBooked(int showtimeID, String seatRow, int seatCol) throws IllegalArgumentException{
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).isBooked(seatRow, seatCol);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public float getPrice(int showtimeID, Customer customer) throws IllegalArgumentException{
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printShowtimes() {
		if (showtimes== null || showtimes.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			it.next().printShowtime();
		}
	}

	@Override
	public void printSeats(int showtimeID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printSeats();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printMovie(int showtimeID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printMovie();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printCinemaLayout(int showtimeID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printLayout();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printCineplex(int showtimeID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printCineplex();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public String getCinemaCode(int showtimeID) throws IllegalArgumentException{
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getCinemaCode();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void setBasePrice(float basePrice) {
		Showtime.setBasePrice(basePrice);
	}

	@Override
	public float getBasePrice() {
		return Showtime.getBasePrice();
	}

	@Override
	public void addShowtime(Movie movie, ICinemaBooking cinema, IDay day) throws IllegalArgumentException{
		if (movie.getMovieStatus()==MovieStatus.COMING_SOON || 
			movie.getMovieStatus()==MovieStatus.NOW_SHOWING ||
			movie.getMovieStatus()==MovieStatus.PREVIEW){
			ShowtimeManager.showtimes.add(new Showtime(movie,cinema,day,++lastID));
		}
		throw new IllegalArgumentException("Movie status is not Coming, Showing or Preview");
	}

	@Override
	public void movieShowtimeEnd(int movieID) {
		if (showtimes== null || showtimes.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID) {
				s.setMovieStatus(MovieStatus.END_OF_SHOWING);
			}
		}
	}
	@Override
	public String getTime(int showtimeID) throws IllegalArgumentException {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getTime();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}
	@Override
	public void printShowtimeAdmin() {
		if (showtimes== null || showtimes.size() == 0){
			// exit before any looping is done
			throw new IllegalArgumentException("No Cinema exist");
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			it.next().printShowtimeAdmin();
		}
	}
	@Override
	public String getDate(int showtimeID) throws IllegalArgumentException{
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getDate();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}
}

