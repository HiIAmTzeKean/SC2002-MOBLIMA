package cinemapackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class appTest {
    public static void main(String args []){
        boolean createDat = true;
        if (createDat){
            File file = new File("./MOBLIMA application/data/cinema/cinema.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch (IOException ex){}

            CinemaManager cm = CinemaManager.getInstance();
            cm.createCinema(0,"Westgate 1","Platinum");
            cm.createCinema(1,"Westgate 2","Gold");
            cm.createCinema(2,"Westgate 3","Sliver");
            cm.createCinema(3,"JEM 1","Platinum");
            cm.createCinema(4,"JEM 2","Gold");
            cm.createCinema(5,"JEM 3","Gold");
            cm.createCinema(6,"JEM 4","Sliver");
            CinemaManager.close();
        }
        else {
            CinemaManager cm = CinemaManager.getInstance();
            cm.printCinemas();
            CinemaManager.close();
        }
    }
}
