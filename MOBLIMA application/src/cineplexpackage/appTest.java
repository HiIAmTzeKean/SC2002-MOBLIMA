package cineplexpackage;

import java.io.File;
import java.io.IOException;

import cinemapackage.*;

public class appTest {
    public static void main(String args []){
        boolean createDat = true;
        if (createDat){
            File file = new File("./MOBLIMA application/data/cineplex/cineplex.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch (IOException ex){}
            
            CineplexManager cm =  CineplexManager.getInstance();
            cm.createCineplex(0,"Westgate", "Jurong East");
            cm.createCineplex(1,"JEM", "Jurong East");
            CinemaManager cinemaManager = CinemaManager.getInstance();
            cm.addCinema(0,cinemaManager.getCinema(0));
            cm.addCinema(0,cinemaManager.getCinema(1));
            cm.addCinema(0,cinemaManager.getCinema(2));

            CineplexManager.close();
            CinemaManager.close();
        }
        else {
            CinemaManager cinemaManager = CinemaManager.getInstance();
            CineplexManager cineplexManager =  CineplexManager.getInstance();
            
            cineplexManager.getCineplex(0).printCineplexCinemas();
            // System.out.println();
            // cinemaManager.deleteCinema(0,cineplexManager);
            // System.out.println();
            // cinemaManager.deleteCinema(1,cineplexManager);
            // System.out.println();
            // cineplexManager.removeCinema(0, cinemaManager.getCinema(2));
            // cineplexManager.getCineplex(0).printCineplexCinemas();
            // System.out.println();
            cinemaManager.printCinemas();
        }
    }
}
