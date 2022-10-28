package showtimepackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import cinemapackage.ICinemaBooking;
import customerpackage.Customer;
import daypackage.IDay;

public class ShowtimeManager implements IShowtimeSystem {
	
	private static ArrayList<Showtime> showtimes;
	
	public ShowtimeManager(){
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
	public void bookSeat(int showtimeID, String seatRow, int seatCol, int customerID) {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).bookSeat(seatRow, seatCol, customerID);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in booking seat");
		}
	}

	@Override
	public boolean isBooked(int showtimeID, String seatRow, int seatCol) {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).isBooked(seatRow, seatCol);
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public float getPrice(int showtimeID) {
		try {
			return showtimes.get(getShowtimeIndex(showtimeID)).getPrice();
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
	public void printSeats(int showtimeID) {
		try {
			showtimes.get(getShowtimeIndex(showtimeID)).printSeats();
		}
		catch (IllegalArgumentException ex){
			throw new IllegalArgumentException("Error in retriving seat");
		}
	}

	@Override
	public void printMovie(int showtimeID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printCinema(int showtimeID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printCineplex(int showtimeID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCinemaCode(int showtimeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCineplexName(int showtimeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCineplexLocation(int showtimeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate(int showtimeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Strring getTime(int showtimeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBasePrice(float basePrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getBasePrice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addShowtime(int movieID, int CinemaID, IDay day) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movieShowtimeEnd(int movieID) {
		// TODO Auto-generated method stub
		
	}
		
}

