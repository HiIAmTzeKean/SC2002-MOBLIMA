package cinemapackage;

import java.util.ArrayList;

public class SliverMovieSuit extends Cinema {
    public SliverMovieSuit(String name, int id) {
        super(name,id);
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
    public void printLayout(){
        System.out.printf("======== Layout of %s Sliver Class Cinema ========\n",super.getName());
        System.out.printf("      1  2  3  4  5     6  7  8  9\n");

        for (int i=0; i<5; i++) {
            System.out.printf("%c  - ", colList[i]);
            for (int j=0; j<9; j++) {
                System.out.printf("|%d|",seats.get(i).get(j).IsBooked()? 1:0);
                if (j==4) System.out.printf(" - ");
            }
            System.out.printf(" -  %c\n", colList[i]);
        }
        System.out.printf("   -  -  -  -  -  -  -  -  -  -  -  -\n");

        System.out.printf("             ________________\n");
        System.out.printf("             |    SCREEN    |\n");
        System.out.printf("\n");
    }
    public float getMultiplier(){
        return 1.0f;
    }
}