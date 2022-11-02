package test;

import java.io.*;
import java.util.Scanner;

import agepackage.*;
import cinemapackage.*;
import cineplexpackage.*;
import customerpackage.*;
import daypackage.*;
import moviepackage.*;
import showtimepackage.*;
public class TestData {
    public static void main(String args []) {
        createMovie();
        createCinema();
        createCineplex();
        createShowtimes();

        Scanner s = new Scanner(System.in);
        ShowtimeManager showtimeManager = ShowtimeManager.getInstance();

        int ans = 1;
        while(ans == 1){
            //int id = s.nextInt();
            //showtimeManager.getShowtimeByMovieAndDate("Black Adam", new Day());
            showtimeManager.printShowtimes();
            ans = s.nextInt();
        }
        MovieManager.close();
        CinemaManager.close();
        CineplexManager.close();
        ShowtimeManager.close();
        DiscountCode.close();
    }
    public static void createMovie(){
        File file = new File("./MOBLIMA application/data/movie/movie.dat");
        file.delete();
        try{
            file.createNewFile();
        }
        catch(IOException e){}
        
        MovieManager mm = MovieManager.getInstance();
        //Creating Movies
        mm.createMovie(
            "Black Adam",
            MovieStatus.PREVIEW,
            "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            "Jaume Collet-Serra",
            "Dwayne Johnson, Aldis Hodge, Noah Centineo, Sarah Shahi, Marwan Kenzari, Quintessa Swindell",
            AgeRestriction.PG13,MovieType.BLOCKBUSTER,125
        );

        mm.createMovie(
            "Ajoomma",
            MovieStatus.PREVIEW,
            "Produced by award-winning filmmaker Anthony Chen. Auntie, is a middle-aged Singaporean woman who has dedicated the best years of her life to caring for her family. Now widowed with her grown up son, Sam about to fly the roost, Auntie is left to contend with a whole new identity beyond her roles of daughter, wife, and mother.",
            "He Shuming",
            "Hong Huifang, Jung Dong-Hwan, Kang Hyung Suk, Yeo Jingoo",
            AgeRestriction.M18,
            MovieType._2D,
            90
        );
        
        mm.createMovie(
            "Black Panther: Wakanda Forever",
            MovieStatus.NOW_SHOWING,
            "Queen Ramonda, Shuri, MBaku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King TChalla's death.",
            "Ryan Coogler",
            "Letitia Wright, Tenoch Huerta, Martin Freeman, Lupita Nyong'o, Danai Gurira, Winston Duke",
            AgeRestriction.PG13,
            MovieType._3D,
            150
        );
        
        mm.createMovie(
            "Ticket to Paradise",
            MovieStatus.NOW_SHOWING,
            "From Working Title, Smokehouse Pictures and Red Om Films, Ticket to Paradise is a romantic comedy about the sweet surprise of second chances.",
            "Ol Parker",
            "George Clooney, Julia Roberts, Kaitlyn Dever, Billie Lourd, Maxime Bouttier",
            AgeRestriction.R21,
            MovieType.BLOCKBUSTER,
            104
        );
        
        mm.createMovie(
            "Amsterdam",
            MovieStatus.COMING_SOON,
            "Three close friends find themselves at the center of one of the most secret plots in American history.",
            "David O. Russell",
            "Christian Bale, Margot Robbie, John David Washington, Chris Rock, Anya Taylor-Joy, Zoe Saldana, Rami Malek",
            AgeRestriction.M18,
            MovieType._2D,
            134
        );
        
        mm.createMovie(
            "Strange World",
            MovieStatus.COMING_SOON,
            "The legendary Clades are a family of explorers whose differences threaten to topple their latest and most crucial mission.",
            "Don Hall",
            "Jake Gyllenhaal, Alan Tudyk, Dennis Quaid, Jaboukie Young-White, Gabrielle Union, Lucy Liu",
            AgeRestriction.PG,
            MovieType._2D,
            126
        );
        
        mm.createMovie(
            "Jurassic World Dominion",
            MovieStatus.END_OF_SHOWING,
            "Four years after Isla Nublar has been destroyed, dinosaurs now live—and hunt—alongside humans all over the world",
            "Colin Trevorrow",
            "Chris Pratt, Bryce Dallas Howard, Laura Dern, Jeff Goldblum, Sam Neill",
            AgeRestriction.PG13,
            MovieType._3D,
            147
        );
        System.out.println("All movies have been added, closing MovieManager");
        MovieManager.close();
    }
    public static void createCinema(){
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
    public static void createCineplex(){
        File file = new File("./MOBLIMA application/data/cineplex/cineplex.dat");
        file.delete();
        try{
            file.createNewFile();
        }
        catch (IOException ex){}
        CinemaManager cinemaManager = CinemaManager.getInstance();
        CineplexManager cm =  CineplexManager.getInstance();
        cm.createCineplex("Westgate", "Jurong East");
        cm.createCineplex("JEM", "Jurong East");
        cm.addCinema(1,cinemaManager.getCinema(1));
        cm.addCinema(1,cinemaManager.getCinema(2));
        cm.addCinema(1,cinemaManager.getCinema(3));
        CinemaManager.close();
    }
    public static void createShowtimes(){
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

        File file = new File("./MOBLIMA application/data/showtime/showtime.dat");
        file.delete();
        try{
            file.createNewFile();
        }
        catch (IOException ex){}
        
        ShowtimeManager man = ShowtimeManager.getInstance();
        MovieManager movieMan = MovieManager.getInstance();
        CinemaManager cinemaManager = CinemaManager.getInstance();
        BookingManager bookingManager = BookingManager.getInstance();
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
        
        IDay d2_t0 = new Day(2,1,2022,"1200");
        IDay d2_t1 = new Day(2, 1, 2022,"1600");
        IDay d2_t2 = new Day(2, 1, 2022,"2000");

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
        
        for (int i = 1; i < 28; i ++) {
            for (int j=0; j< 5; j++){
                try{
                    Showtime showtimeObj=man.getShowtimeByID(i);
                    if (showtimeObj.getCinemaClass().toString().equals("PLATINUM")){
                        int col = (int) ((int) 5*Math.random())+1;
                        int row = (int) ((int) 2*Math.random());
                        man.bookSeat(i, colList[row], col, emailList[col].hashCode());
                        c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                        bookingManager.addBooking(man.getShowtimeByID(i).getCinemaCode()+man.getDate(i)+man.getTime(i), man.getShowtimeByID(i),
                                        man.getPrice(i,c), c, colList[row],col);
                    }
                    else if (showtimeObj.getCinemaClass().equals("GOLD")){
                        int col = (int) ((int) 8*Math.random())+1;
                        int row = (int) ((int) 3*Math.random());
                        man.bookSeat(i, colList[row], col, emailList[col].hashCode());
                        c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                        bookingManager.addBooking(man.getShowtimeByID(i).getCinemaCode()+man.getDate(i)+man.getTime(i), man.getShowtimeByID(i),
                                        man.getPrice(i,c), c, colList[row],col);
                    }
                    else{
                        int col = (int) ((int) 8*Math.random())+1;
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
    }
    public static void createCoupons(){
        File file = new File("./MOBLIMA application/data/discountcode/discountcode.dat");
        file.delete();
        try{
            file.createNewFile();
        }
        catch (IOException ex){}
        DiscountCode d = DiscountCode.getInstance();
        d.addDiscountCodeTicket("WelcomeCoupon", 0.5f);
        d.addDiscountCodeTicket("VIP", 0.3f);
        d.addDiscountCodeTicket("Promo22", 0.2f);
        DiscountCode.close();
    }
}
