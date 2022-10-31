package showtimepackage;

import java.io.File;
import java.io.IOException;

import cinemapackage.CinemaManager;
import daypackage.Day;
import daypackage.DayOfWeek;
import daypackage.IDay;
import moviepackage.MovieManager;

public class appTest {
    public static void main(String args []) {
        ShowtimeManager man = ShowtimeManager.getInstance();
        MovieManager movieMan = MovieManager.getInstance();
        CinemaManager cinemaManager = CinemaManager.getInstance();

        boolean createDat = false;
        if (createDat){
            File file = new File("./MOBLIMA application/data/showtime/showtime.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch (IOException ex){}
                
            IDay d0_t0 = new Day();
            IDay d0_t1 = new Day();
            d0_t1.setTime("1600");
            IDay d0_t2 = new Day();
            d0_t2.setTime("2000");

            IDay d1_t0 = new Day();
            d1_t0.setDate(2, 1, 2022);
            IDay d1_t1 = new Day();
            d1_t1.setDate(2, 1, 2022);
            d1_t1.setTime("1600");
            IDay d1_t2 = new Day();
            d1_t2.setDate(2, 1, 2022);
            d1_t2.setTime("2000");
            
            IDay d2_t0 = new Day();
            d2_t0.setDate(2, 1, 2022);
            IDay d2_t1 = new Day();
            d2_t1.setDate(2, 1, 2022);
            d2_t1.setTime("1600");
            IDay d2_t2 = new Day();
            d2_t2.setDate(2, 1, 2022);
            d2_t2.setTime("2000");

            IDay d3_t0 = new Day();
            d3_t0.setDate(31, 12, 2021);
            d3_t0.setDayOfWeek(DayOfWeek.FRI);
            IDay d3_t1 = new Day();
            d3_t1.setDate(31, 12, 2021);
            d3_t1.setTime("1600");
            d3_t1.setDayOfWeek(DayOfWeek.FRI);
            IDay d3_t2 = new Day();
            d3_t2.setDate(31, 12, 2021);
            d3_t2.setTime("2000");
            d3_t2.setDayOfWeek(DayOfWeek.FRI);

            // Preview movies
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(1), d1_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(2), d1_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.getCinema(3), d1_t2);
            // Now showing movies
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(1), d1_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(2), d1_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.getCinema(3), d1_t2);
            // comming soon
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(1), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(2), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(3), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(1), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(2), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(3), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(1), d2_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(2), d2_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.getCinema(3), d2_t2);
            // End showtime
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(1), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(2), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(3), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(1), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(2), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(3), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(1), d2_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(2), d2_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.getCinema(3), d2_t2);
            //
            System.out.println("Done");
            ShowtimeManager.close();
        }
        else{
            man.printShowtimeAdmin();
        }
    }
}
