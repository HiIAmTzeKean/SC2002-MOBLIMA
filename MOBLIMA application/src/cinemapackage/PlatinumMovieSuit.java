package cinemapackage;

import java.util.ArrayList;

public class PlatinumMovieSuit extends Cinema {
    public PlatinumMovieSuit(String name, int id) {
        super(name,id);
        super.setCinemaType(CinemaType.PLATINUM);
        // Create the layout of sliver class
        // 2 - 2 - 2 seating
        // Total of 3 roles
        for (int i=0; i<3; i++) {
            seats.add(new ArrayList<Seat>());
            for (int j=0; j<6; j++) {
                seats.get(i).add(new Seat(j,i));
            }
        }
    }
    public void printLayout(){
        System.out.printf("======== Layout of %s Platinum Class Cinema ========\n",super.getName());
        System.out.printf("      1  2     3  4     5  6\n");

        for (int i=0; i<3; i++) {
            System.out.printf("%c  - ", colList[i]);
            for (int j=0; j<6; j++) {
                System.out.printf("|%d|",seats.get(i).get(j).IsBooked()? 1:0);
                if (j==1 || j==3) System.out.printf(" - ");
            }
            System.out.printf(" -  %c\n", colList[i]);
            System.out.printf("   -  -  -  -  -  -  -  -  -  -\n");
        }

        System.out.printf("          ________________\n");
        System.out.printf("          |    SCREEN    |\n");
        System.out.printf("\n");
    }
}