package showtimepackage;
import cinemapackage.Seat;
public class Showtime {
	

	private Movie movie;
	private Cinema cinema;
	private Date time;
	private float basePrice;
	private Day day;
	private Seat[][] layout = new Seat[10][10];
	
	Showtime()
	{
		for(int i=0;i<10;i++)
			for(int j=0;j<1;j++)
				layout[i][j] = new Seat(i,j);
		
		basePrice = 10;
	}
	
	public void printLayout()
	{
		for(int i=0;i<10;i++){
			for(int j=0;j<1;j++)
				System.out.print(layout[i][j].isBooked + "  ");
			System.out.println("");
		}
	}	
}
