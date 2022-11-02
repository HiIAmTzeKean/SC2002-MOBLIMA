package showtimepackage;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import cinemapackage.ICinemaBooking;
import customerpackage.Customer;
import daypackage.Day;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieStatus;
import moviepackage.MovieType;

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
		if (ShowtimeManager.showtimeManager == null){
			ArrayList<Showtime> c = ShowtimeManager.deseraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat");
			ShowtimeManager.showtimeManager = new ShowtimeManager(c);
			return ShowtimeManager.showtimeManager;
		}
		return ShowtimeManager.showtimeManager;
	}

	public static void close() {
		if (ShowtimeManager.showtimeManager != null){
			ShowtimeManager.seraliseShowtimes("./MOBLIMA application/data/showtime/showtime.dat",showtimes);
		}
		ShowtimeManager.showtimeManager = null;
	}

	public void addShowtimeSystem(Movie movie, ICinemaBooking cinema, IDay day){
		ShowtimeManager.showtimes.add(new Showtime(movie,cinema,day,++lastID));
	}
	public Showtime getShowtimeByID(int showtimeID){
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getID() == showtimeID) {
				return s;
			}
		}
		throw new IllegalArgumentException("Showtime is not found");
	}
	public Showtime getShowtimeByMovieAndDate(String movieName, Day day){
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			//TODO Update this day.equals
			if (s.getMovieName() == movieName && s.getDate()==day.getDate() && s.getTime()==day.getTime()) {
				return s;
			}
		}
		throw new IllegalArgumentException("Showtime is not found");
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

	public void bookSeatAdmin(int showtimeID, String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customerID);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in booking seat");
		}
	}
	@Override
	public void bookSeat(int showtimeID, String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		try {
			Showtime s = showtimes.get(getShowtimeIndex(showtimeID));
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING ||
				s.getMovieStatus() != MovieStatus.COMING_SOON) {
				showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customerID);
			}
			else {
				throw new IllegalArgumentException("Error in booking seat");
			}
		}
		catch (IllegalArgumentException ex){
			ex.printStackTrace();
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

	public float getPrice(int showtimeID, Customer customer, String discountCodeTicket) throws IllegalArgumentException{
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice(customer,discountCodeTicket);
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
		System.out.println("|----------------------------------------------------------- Showtimes ------------------------------------------------------|");
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------|");
		System.out.printf("|   %-15s   |       %-30s        |    %-15s     |    %-8s     |    %-5s    |\n",
						"Movie Status",
								"Movie Name",
								"Cinema Class",
								"Date",
								"Time");
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s= it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus()==MovieStatus.PREVIEW)
				s.printShowtime();
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s= it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus()==MovieStatus.NOW_SHOWING)
				s.printShowtime();
		}
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s= it.next();
			if (s.getMovieStatus() != MovieStatus.END_OF_SHOWING && s.getMovieStatus()==MovieStatus.COMING_SOON)
				s.printShowtime();
		}
		System.out.println("|----------------------------------------------------------------------------------------------------------------------------|");
	}

	@Override
	public void printSeats(int showtimeID) throws IllegalArgumentException{
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printSeat();
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
			return;
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
	@Override
	public Day getDay(String dateString) throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getDate() == dateString){
				return s.getDayObject();
			}
		}
		throw new IllegalArgumentException("No such date in showtimes");
	}


	@Override
	public void setMovieType(int movieID, MovieType type) throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID){
				s.getMovieObject().setMovieType(type);;
			}
		}
		throw new IllegalArgumentException("No such MovieID in showtimes");
	}
	@Override
	public void setMovieStatus(int movieID, MovieStatus status) throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID){
				s.getMovieObject().setMovieStatus(status);
			}
		}
		throw new IllegalArgumentException("No such MovieID in showtimes");
	}
	@Override
	public void setMovieDirector(int movieID, String director) throws IllegalArgumentException {
		for (Iterator<Showtime> it = showtimes.iterator(); it.hasNext();) {
			Showtime s = it.next();
			if (s.getMovieID() == movieID){
				s.getMovieObject().setMovieDirector(director);
			}
		}
		throw new IllegalArgumentException("No such MovieID in showtimes");
	}
}

