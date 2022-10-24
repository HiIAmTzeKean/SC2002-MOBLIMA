package cinemapackage;

import java.io.File;
import java.util.ArrayList;

public class appTest {
    public static void main(String args []){
        boolean createDat = true;
        if (createDat){
            CinemaManager cm = new CinemaManager();
            cm.createCinema(0,"Westgate 1","Platinum");
            cm.createCinema(1,"Westgate 2","Gold");
            cm.createCinema(2,"Westgate 3","Sliver");
            cm.createCinema(3,"JEM 1","Platinum");
            cm.createCinema(4,"JEM 2","Gold");
            cm.createCinema(5,"JEM 3","Gold");
            cm.createCinema(6,"JEM 4","Sliver");
            ArrayList<Cinema> c = cm.getCinemas();
            CinemaManager.seraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat", c);
        }
        else {
            ArrayList<Cinema> c = CinemaManager.deseraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat");
            CinemaManager cm = new CinemaManager(c);
        }
    }
}
