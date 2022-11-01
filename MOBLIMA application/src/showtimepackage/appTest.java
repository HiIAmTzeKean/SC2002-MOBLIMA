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
        //false
        //true
        boolean createDat = false;
        boolean addseat = false;
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
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(1), d1_t2);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(2), d1_t2);
            man.addShowtimeSystem(movieMan.getClone(1), cinemaManager.cloneCinemaByID(3), d1_t2);
            // Now showing movies
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d0_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d0_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d0_t2);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d1_t0);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d1_t1);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(1), d1_t2);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(2), d1_t2);
            man.addShowtimeSystem(movieMan.getClone(3), cinemaManager.cloneCinemaByID(3), d1_t2);
            // comming soon
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(1), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(2), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(3), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(1), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(2), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(3), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(1), d2_t2);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(2), d2_t2);
            man.addShowtimeSystem(movieMan.getClone(5), cinemaManager.cloneCinemaByID(3), d2_t2);
            // End showtime
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(1), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(2), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(3), d2_t0);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(1), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(2), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(3), d2_t1);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(1), d2_t2);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(2), d2_t2);
            man.addShowtimeSystem(movieMan.getClone(7), cinemaManager.cloneCinemaByID(3), d2_t2);
            //
            System.out.println("Done");
            man.printShowtimeAdmin();
            ShowtimeManager.close();
        }
        else if (addseat) {
            String colList[] = {"A", "B", "C", "D", "E"};
            String emailList[] = {"john@gmail.com","ali@gmail.com","jun@gmail.com",
                                  "april@gmail.com","bob@gmail.com","shawn@gmail.com",
                                  "doe@gmail.com","foo@gmail.com","fug@gmail.com"};

            for (int i = 1; i < 36; i ++) {
                for (int j=0; j< 10; j++){
                    try{
                        if (i%3 == 1){
                            int col = (int) ((int) 6*Math.random());
                            int row = (int) ((int) 3*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[row].hashCode());
                        }
                        else if (i%3 == 2){
                            int col = (int) ((int) 9*Math.random());
                            int row = (int) ((int) 3*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[row].hashCode());
                        }
                        else{
                            int col = (int) ((int) 9*Math.random());
                            int row = (int) ((int) 5*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[row].hashCode());
                        }  
                    }
                    catch(IllegalAccessError ex){
                        return;
                    }
                }
            }
            man.printShowtimeAdmin();
            ShowtimeManager.close();
        }
        else{
            man.printSeats(22);
            man.printSeats(35);
        }
        ShowtimeManager.close();
    }
}
