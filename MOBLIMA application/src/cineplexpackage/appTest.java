package cineplexpackage;

import java.util.ArrayList;

import cinemapackage.*;

public class appTest {
    public static void main(String args []){
        boolean createDat = false;
        if (createDat){
            CineplexManager cm = new CineplexManager();
            cm.createCineplex(0,"Westgate", "Jurong East");
            cm.createCineplex(1,"JEM", "Jurong East");

            ArrayList<Cinema> cinemas = CinemaManager.deseraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat");
            CinemaManager cinemaManager = new CinemaManager(cinemas);
            
            cm.addCinema(0,cinemaManager.getCinema(0));
            cm.addCinema(0,cinemaManager.getCinema(1));
            cm.addCinema(0,cinemaManager.getCinema(2));
            ArrayList<Cineplex>c=cm.getCineplexes();
            CineplexManager.seraliseCineplexes("./MOBLIMA application/data/cineplex/cineplex.dat", c);
        }
        else {
            ArrayList<Cinema> cc = CinemaManager.deseraliseCinemas("./MOBLIMA application/data/cinema/cinema.dat");
            CinemaManager cinemaManager = new CinemaManager(cc);
            ArrayList<Cineplex> c = CineplexManager.deseraliseCineplexes("./MOBLIMA application/data/cineplex/cineplex.dat");
            CineplexManager cineplexManager = new CineplexManager(c);
            
            cineplexManager.getCineplex(0).printCineplexCinemas();
            System.out.println();
            // cinemaManager.deleteCinema(0,cineplexManager);
            // System.out.println();
            // cinemaManager.deleteCinema(1,cineplexManager);
            // System.out.println();
            cineplexManager.removeCinema(0, cinemaManager.getCinema(2));
            cineplexManager.getCineplex(0).printCineplexCinemas();
            System.out.println();
            cinemaManager.printCinemas();
        }
    }
}
