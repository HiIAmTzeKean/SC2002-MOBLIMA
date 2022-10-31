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
            cm.createCineplex("Westgate", "Jurong East");
            cm.createCineplex("JEM", "Jurong East");
            CinemaManager cinemaManager = CinemaManager.getInstance();
            cm.addCinema(1,cinemaManager.getCinema(1));
            cm.addCinema(1,cinemaManager.getCinema(2));
            cm.addCinema(1,cinemaManager.getCinema(3));

            CineplexManager.close();
            CinemaManager.close();
        }
        else {
            // using an object that implements ICinema
            // -> i do not need to know what methods are in CinemaManager
            // By using the interface, i do not accidentally use a method that is not supposed to be
            // seen by another package
            ICinema cinemaHandler = CinemaManager.getInstance();
            CineplexManager cineplexManager =  CineplexManager.getInstance();
            
            // Here we can see the power of DIP
            // using an interface type object to perform a function that it states it has
            // some class will implement this function, but we dont need to know who
            cinemaHandler.deleteCinema(1,cineplexManager);
        }
    }
}
