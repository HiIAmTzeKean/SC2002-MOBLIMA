package test;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import agepackage.*;
import cinemapackage.*;
import cineplexpackage.*;
import customerpackage.*;
import daypackage.*;
import moviepackage.*;
import showtimepackage.*;
import viewPackage.customerpackage.CustomerReview;
public class TestData {
    public static void main(String args []) {
        createAllData();

        Scanner s = new Scanner(System.in);
        MovieManager movieManager = MovieManager.getInstance();

        int ans = 1;
        while(ans == 1){
            // showtimeManager.printShowtimesByMovieNameAndCineplexID("Black Adam", 1);
            // Day d = new Day("20220101","1200");
            // int showtimeID = showtimeManager.getShowtime("Black Adam", d,cineplexID,cinemaType).getID();
            // Customer cust = new Customer("name", 12345678, "name@gmail.com", new Age(10));
            // try{
            //     showtimeManager.getPrice(showtimeID, cut);
            //     // If i answer yes to price
            //     showtimeManager.bookSeat(showtimeID,"A", 1, cust);
            // }
            // catch (IllegalArgumentException | CustomerNullException e){

            // }
            //showtimeManager.printShowtimesByMovieNameAndCineplexID("Black Adam", 1);
            movieManager.printMovies();
            ans = s.nextInt();
        }
        MovieManager.close();
        CinemaManager.close();
        CineplexManager.close();
        ShowtimeManager.close();
        DiscountCode.close();
    }
    public static void createAllData(){
        createMovie();
        createCinema();
        createCineplex();
        createShowtimes();
        createCoupons();
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
        //7 Now Showing
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
            "Prey for the Devil",
            MovieStatus.NOW_SHOWING,
            "In response to a global rise in demonic possessions, Ann seeks out a place at an exorcism school reopened by the Catholic Church.",
            "Daniel Stamm",
            "Jacqueline Byers, Colin Salmon, Virginia Madsen, Ben Cross, Christian Navarro",
            AgeRestriction.R21,
            MovieType._3D,
            93
        );
        mm.createMovie(
            "Silent Parade",
            MovieStatus.NOW_SHOWING,
            "Detective Galileo returns to the big screen! The popular mystery series by Keigo Higashino features Masaharu Fukuyama as Professor Manabu Yukawa, a brilliant physicist who helps the police crack unsolved cold cases using his scientific theories and unique methods of deduction",
            "Hiroshi Nishitani",
            "Masaharu Fukuyama, Ko Shibasaki, Kazuki Kitamura",
            AgeRestriction.PG,
            MovieType.BLOCKBUSTER,
            130
        );
        mm.createMovie(
            "Minions 2: The Rise of Gru",
            MovieStatus.NOW_SHOWING,
            "In the 1970s, Gru (Steve Carell) is growing up in the suburbs. A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
            "Kyle Balda",
            "Steve Carell, Taraji P. Henson, Michelle Yeoh, Jean-Claude Van Damme, Lucy Lawless",
            AgeRestriction.PG,
            MovieType._2D,
            87
        ); 
         mm.createMovie(
            "The Legend of Maula Jatt",
            MovieStatus.NOW_SHOWING,
            "From times untold where legends are written in soil with blood, a hero is born. Maula Jatt, a fierce prizefighter with a tortured past seeks vengeance against his arch nemesis Noori Natt, the most feared warrior in the land of Punjab.",
            "Bilal Lashari",
            "Fawad Khan, Hamza Abbasi, Mahira Khan, Humaima Malick",
            AgeRestriction.R21,
            MovieType.BLOCKBUSTER,
            152
        ); 
        mm.createMovie(
            "Mrs Harris Goes To Paris",
            MovieStatus.NOW_SHOWING,
            "MRS. HARRIS GOES TO PARIS is the enchanting tale of a seemingly ordinary British housekeeper whose dream to own a couture Christian Dior gown takes her on an extraordinary adventure to Paris.",
            "Anthony Fabian",
            "Lesley Manville, Isabelle Huppert, Jason Isaacs, Anna Chancellor",
            AgeRestriction.PG13,
            MovieType._3D,
            116
        );
        
        //Next 2 Preview
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
        //3 Coming Soon
        mm.createMovie(
            "Puss in Boots: The Last Wish",
            MovieStatus.COMING_SOON,
            "Daring outlaw Puss in Boots discovers that his passion for peril and disregard for safety have taken their toll. Puss has burned through eight of his nine lives, though he lost count along the way. Getting those lives back will send Puss in Boots on his grandest quest yet.",
            "Joel Crawford",
            "Antonio Banderas, Salma Hayek, Olivia Colman, Harvey Guillen",
            AgeRestriction.PG,
            MovieType.BLOCKBUSTER,
            140
        );
        //3 Ended
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
        mm.createMovie("Top Gun Maverick",
            MovieStatus.END_OF_SHOWING, 
            "Maverick is drawn into a confrontation with his own deepest fears, culminating in a mission that demands the ultimate sacrifice from those who will be chosen to fly it. ", 
            "Joseph Kosinski", 
            "Tom Cruise, Miles Teller, Jennifer Connelly, Jon Hamm,", 
            AgeRestriction.PG13, 
            MovieType._2D, 
            131
        );
        mm.createMovie(
            "Smile",
            MovieStatus.END_OF_SHOWING,
            "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) starts experiencing frightening occurrences that she can't explain.",
            "Parker Finn",
            "Sosie Bacon, Jessie T. Usher, Kyle Gallner",
            AgeRestriction.M18,
            MovieType._2D,
            115
        );
        //Adding Reviews for Black Panther
        mm.getMoviefromID(1).addReview(new Review(4.0f, "Great movie with great action sequences."));
        mm.getMoviefromID(1).addReview(new Review(4.5f, "Great sequel to another the first movie. Must watch for all Marvel fans."));
        //Adding Reviews for Ticket to Paradise
        mm.getMoviefromID(2).addReview(new Review(2.5f,"Not the best romcom but it was funny at times."));
        mm.getMoviefromID(2).addReview(new Review(2.0f, "Very boring movie, not worth the watch."));
        //Adding Reviews for Prey for the Devil 
        mm.getMoviefromID(3).addReview(new Review(1.0f, "Filled with lazy jump scares and embarrassingly transparent plot twists and metaphors."));
        mm.getMoviefromID(3).addReview(new Review(1.5f, "It never manages to pull together a narrative cohesive or scary enough to rise above a patchwork of familiar possession horror tropes."));
        //Adding Reviews for Silent Parade
        mm.getMoviefromID(4).addReview(new Review(4.0f, "Nice to see the character return to the big screen, lots of action and good directing and acting."));
        mm.getMoviefromID(4).addReview(new Review(4.5f, "Good mystery movie with lots of twists and action."));
        //Adding Reviews for Minions 2 : The Rise of Gru
        mm.getMoviefromID(5).addReview(new Review(3.5f, "A fun treat for the whole family."));
        mm.getMoviefromID(5).addReview(new Review(4.0f, "Silly, fun, fast, with a great cast and soundtrack."));
        //Adding Reviews for the Legend of Maula Jatt
        mm.getMoviefromID(6).addReview(new Review(5.0f, "Bilal Lashari, the director, has given new life to Pakistani cinema with this masterpiece."));
        mm.getMoviefromID(6).addReview(new Review(5.0f, "Excellent music, fight scenes, and acting by all characters!"));
        //Adding Reviews for Mrs Harris Goes to Paris
        mm.getMoviefromID(7).addReview(new Review(4.5f, "This is a warm and witty crowd-pleasing delight from start to finish."));
        mm.getMoviefromID(7).addReview(new Review(4.0f, "It's a familiar formula but that's part of the charm. "));
        //Adding Reviews for Jurassic World Dominion
        mm.getMoviefromID(11).addReview(new Review(2.5f, "It was similar to the others in the series: predictable, but entertaining"));
        mm.getMoviefromID(12).addReview(new Review(3, "Longer then it really needed to be. But the return of the old cast redeemed the movie over all."));
        //Adding Reviews for Top Gun Maverick
        mm.getMoviefromID(14).addReview(new Review(2, "First, Top Gun Maverick has amazing flight scenes and dog fights. Other than that it is mediocre at best."));
        mm.getMoviefromID(14).addReview(new Review(3, "Nothing new here - just another predictable guy movie where the good guys always win despite overwhelming odds and the bad guys always explode."));
        //Adding Reviews for Smile
        mm.getMoviefromID(15).addReview(new Review(4, "Very enjoyable horror movie experience. All in all happy I saw this in theatres and would watch again."));
        mm.getMoviefromID(15).addReview(new Review(5, "Great scary movie that is believable. It has the perfect blend of paranormal and psychological fear to make it a scary movie. "));
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
        cm.createCinema("WG3","Silver");
        cm.createCinema("JE1","Platinum");
        cm.createCinema("JE2","Gold");
        cm.createCinema("JE3","Gold");
        cm.createCinema("JE4","Silver");
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
        CineplexManager.close();
        System.out.println("WRITTEN TO CIMEPLEX FILE");
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
        Age ageList[] = {new Age(10), new Age(20), new Age(30),
                        new Age(11), new Age(22), new Age(30),
                        new Age(15), new Age(60), new Age(50)
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
                        c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                        man.bookSeat(i, colList[row], col,c);
                    }
                    else if (showtimeObj.getCinemaClass().equals("GOLD")){
                        int col = (int) ((int) 8*Math.random())+1;
                        int row = (int) ((int) 3*Math.random());
                        c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                        man.bookSeat(i, colList[row], col,c);
                    }
                    else{
                        int col = (int) ((int) 8*Math.random())+1;
                        int row = (int) ((int) 5*Math.random());
                        c = new Customer(nameList[col], mobileList[col], emailList[col], ageList[col]); 
                        man.bookSeat(i, colList[row], col,c);
                    }  
                }
                catch(IllegalArgumentException ex){
                    return;
                }
                catch(CustomerNullException ex){
                    ex.printStackTrace();
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
