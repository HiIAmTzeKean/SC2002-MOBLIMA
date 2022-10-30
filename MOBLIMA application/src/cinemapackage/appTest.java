package cinemapackage;

import java.io.File;
import java.io.IOException;

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
            cm.createCinema("WG1","Platinum");
            cm.createCinema("WG2","Gold");
            cm.createCinema("WG3","Sliver");
            cm.createCinema("JE1","Platinum");
            cm.createCinema("JE2","Gold");
            cm.createCinema("JE3","Gold");
            cm.createCinema("JE4","Sliver");
            CinemaManager.close();
        }
        else {
            ICinema cm = CinemaManager.getInstance();
            cm.printCinemas();
            CinemaManager.close();
        }
    }
}
