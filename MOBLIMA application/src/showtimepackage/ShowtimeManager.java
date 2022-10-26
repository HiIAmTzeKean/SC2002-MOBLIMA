package showtimepackage;

public class ShowtimeManager implements IBooking, IShowtimeSystem {
	
	private ArrayList<Showtime>  showtimes;
	
	public ShowtimwManager(){
		this.showtimes = new ArrayList<>();
	}
	
	public void printShowtimes(){
		for(int i = 0; i<this.showtimes.size();i++){
			System.out.println(showtimes.get(i).getTime());
		}
	}
	
	public void addShowtime(Showtime show){
		showtimes.add(show);
	}
	
	public void deleteShowtime(int n){
		showtimes.remove(n);
	}
	
	
	public void bookShow(int n){
		bookSeat();
	}
		
		
	}
	
}
