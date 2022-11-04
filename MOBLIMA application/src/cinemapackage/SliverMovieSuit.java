package cinemapackage;

import java.util.ArrayList;
import java.util.Iterator;

public class SliverMovieSuit extends Cinema {
    public SliverMovieSuit(String code, int id) {
        super(code,id);
        super.setCinemaType(CinemaType.SLIVER);
        // Create the layout of sliver class
        // 5 - 4 seating
        // Total of 5 roles
        for (int i=0; i<5; i++) {
            seats.add(new ArrayList<Seat>());
            for (int j=0; j<9; j++) {
                seats.get(i).add(new Seat(j,i));
            }
        }
    }
    public SliverMovieSuit(String code,int id, CinemaType type, int Cineplexid, ArrayList<ArrayList<Seat>> seats){
		this.code = code;
		this.id = id;
		this.cinemaType = type;
		this.Cineplexid = Cineplexid;
		this.seats = seats;
	}
    public Cinema cloneCinema(){
		ArrayList<ArrayList<Seat>> seatcopy = new ArrayList<ArrayList<Seat>>();
		int count = 0;
		for (Iterator<ArrayList<Seat>> it = seats.iterator(); it.hasNext();){
			seatcopy.add(new ArrayList<Seat>());
			for (Iterator<Seat> it2 = it.next().iterator(); it2.hasNext();) {
				Seat s = it2.next();
				seatcopy.get(count).add(new Seat(s.getX(), s.getY()));
			}
			count++;
		}

		int row = 0;
		int col = 0;
		for (Iterator<ArrayList<Seat>> it = seatcopy.iterator(); it.hasNext();) {
			col = 0;
			for (Iterator<Seat> it2 = it.next().iterator(); it2.hasNext();) {
				if (seats.get(row).get(col).isBooked()){
					it2.next().setBooked(seats.get(row).get(col).getCustomerID());
				}
				else it2.next();
				col++;
			}
			row++;
		}
		Cinema c = new SliverMovieSuit(this.code,this.id,this.cinemaType,this.Cineplexid,seatcopy);
		return c;
	}
    @Override
    public void printLayout(){
        System.out.printf("======== Layout of %s Sliver Class Cinema ========\n",super.getCinemaCode());
        System.out.printf("      1  2  3  4  5     6  7  8  9\n");

        for (int i=0; i<5; i++) {
            System.out.printf("%c  - ", rowList
			[i]);
            for (int j=0; j<9; j++) {
                System.out.printf("|%d|",seats.get(i).get(j).isBooked()? 1:0);
                if (j==4) System.out.printf(" - ");
            }
            System.out.printf(" -  %c\n", rowList
			[i]);
        }
        System.out.printf("   -  -  -  -  -  -  -  -  -  -  -  -\n");

        System.out.printf("             ________________\n");
        System.out.printf("             |    SCREEN    |\n");
        System.out.printf("\n");
    }
    public float getMultiplier(){
        return 1.0f;
    }
    public void bookCoupleSeat(String seatRow, int seatCol, int customerID) throws IllegalArgumentException{
		if (seatCol != 1 || seatCol != 3 || seatCol != 5 || seatRow!="C")
			throw new IllegalArgumentException("Invalid column/Row selection");
		
		if (!isBooked(seatRow,seatCol) && !isBooked(seatRow,seatCol+1)) {
			seats.get(2).get(seatCol).setBooked(customerID);
			seats.get(2).get(seatCol+1).setBooked(customerID);
		}
		else throw new IllegalArgumentException("Seat was not booked since there is already a booking by someone else");
	}
}