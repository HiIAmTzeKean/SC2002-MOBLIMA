package cinemapackage;

/**
 * Subclass which inherits from Seat class.
 * Specialise in print method.
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class EliteSeat extends Seat{
    public EliteSeat(int x, int y){
		super(x,y);
	}
    @Override
    public void print(){
		System.out.printf("|-%d-|", super.isBooked()?1:0);
	}
}
