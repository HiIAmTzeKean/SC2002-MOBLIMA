package cinemapackage;

public class EliteSeat extends Seat{
    public EliteSeat(int x, int y){
		super(x,y);
	}
    @Override
    public void print(){
		System.out.printf("|-%d-|", super.isBooked()?1:0);
	}
}
