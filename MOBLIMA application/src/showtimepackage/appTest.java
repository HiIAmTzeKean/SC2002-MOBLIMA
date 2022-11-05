package showtimepackage;

import java.io.File;
import java.io.IOException;
import agepackage.Age;
import agepackage.AgeCategory;
import cinemapackage.Cinema;
import cinemapackage.CinemaManager;
import customerpackage.BookingManager;
import customerpackage.Customer;
import daypackage.Day;
import daypackage.DayOfWeek;
import daypackage.IDay;
import moviepackage.MovieManager;

public class appTest {
    public static void main(String args []) {
        
        MovieManager movieMan = MovieManager.getInstance();
        CinemaManager cinemaManager = CinemaManager.getInstance();
        BookingManager bookingManager = BookingManager.getInstance();

        Customer c;
        String colList[] = {"A", "B", "C", "D", "E"};
        String nameList[] = {"john","ali","jun",
                            "april","bob","shawn",
                            "doe","foo","fug"};
        String emailList[] = {"john@gmail.com","ali@gmail.com","jun@gmail.com",
                                "april@gmail.com","bob@gmail.com","shawn@gmail.com",
                                "doe@gmail.com","foo@gmail.com","fug@gmail.com"};
        int mobileList[] = {11111111,22222222, 33333333,
                                44444444,55555555,66666666,
                                77777777,88888888,99999999};
        Age ageList[] = {new Age(10,AgeCategory.CHILD), new Age(20,AgeCategory.ADULT), new Age(30,AgeCategory.ADULT),
                        new Age(11,AgeCategory.CHILD), new Age(22,AgeCategory.ADULT), new Age(30,AgeCategory.ADULT),
                        new Age(15,AgeCategory.CHILD), new Age(60,AgeCategory.SENIOR), new Age(50,AgeCategory.SENIOR)
                        };

        //false
        //true
        boolean createDat = true;
        boolean addseat = true;
        if (createDat){
            File file = new File("./MOBLIMA application/data/showtime/showtime.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch (IOException ex){}
            
            ShowtimeManager man = ShowtimeManager.getInstance();

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
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(1), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(2), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(3), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(1), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(1), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(2), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(3), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(1), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(2), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(3), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(1), cinemaManager.cloneCinemaByID(3), d1_t2);
            // Now showing movies
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(3), d0_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(1), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(3), d0_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(1), d0_t2);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(2), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(3), d1_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(2), d1_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(3), cinemaManager.cloneCinemaByID(3), d1_t2);
            // comming soon
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.cloneCinemaByID(1), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.cloneCinemaByID(1), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.cloneCinemaByID(2), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(5), cinemaManager.cloneCinemaByID(3), d2_t2);
            // End showtime
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.cloneCinemaByID(1), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.cloneCinemaByID(2), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.cloneCinemaByID(3), d2_t0);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.cloneCinemaByID(1), d2_t1);
            man.addShowtimeSystem(movieMan.getMoviefromID(7), cinemaManager.cloneCinemaByID(3), d2_t2);
            //
            System.out.println("Done");
            man.printShowtimeAdmin();
        }
        if (addseat) {
            ShowtimeManager man = ShowtimeManager.getInstance();
            
            for (int i = 1; i < 28; i ++) {
                for (int j=0; j< 5; j++){
                    try{
                        Showtime showtimeObj=man.getShowtimeByID(i);
                        if (showtimeObj.getCinemaClass().toString().equals("PLATINUM")){
                            int col = (int) ((int) 6*Math.random());
                            int row = (int) ((int) 3*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[col].hashCode());
                            c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                            bookingManager.addBooking(man.getShowtimeByID(i).getCinemaCode()+man.getDate(i)+man.getTime(i), man.getShowtimeByID(i),
                                            man.getPrice(i,c), c, colList[row],col);
                        }
                        else if (showtimeObj.getCinemaClass().toString().equals("GOLD")){
                            int col = (int) ((int) 9*Math.random());
                            int row = (int) ((int) 3*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[col].hashCode());
                            c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                            bookingManager.addBooking(man.getShowtimeByID(i).getCinemaCode()+man.getDate(i)+man.getTime(i), man.getShowtimeByID(i),
                                            man.getPrice(i,c), c, colList[row],col);
                        }
                        else{
                            int col = (int) ((int) 9*Math.random());
                            int row = (int) ((int) 5*Math.random());
                            man.bookSeat(i, colList[row], col, emailList[col].hashCode());
                            c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                            bookingManager.addBooking(man.getShowtimeByID(i).getCinemaCode()+man.getDate(i)+man.getTime(i), man.getShowtimeByID(i),
                                            man.getPrice(i,c), c, colList[row],col);
                        }  
                    }
                    catch(IllegalAccessError ex){
                        return;
                    }
                }
            }
            ShowtimeManager.close();
        }
        else{
            ShowtimeManager man = ShowtimeManager.getInstance();
            man.printSeats(22);
            man.printSeats(19);
            bookingManager.printAllTransactionsForCustomer(emailList[0]);
        }
        ShowtimeManager.close();
        MovieManager.close();
        BookingManager.close();
        CinemaManager.close();
    }
}
