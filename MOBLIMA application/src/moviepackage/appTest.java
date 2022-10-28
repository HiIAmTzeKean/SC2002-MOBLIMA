package moviepackage;
import java.io.File;
import java.io.IOException;
public class appTest {
    public static void main(String[]args){
        boolean createData = true;
        if(createData){
            File file = new File("./MOBLIMA application/data/movie/movie.dat");
            file.delete();
            try{
                file.createNewFile();
            }
            catch(IOException e){}
            
            MovieManager mm = MovieManager.getInstance();
            //Creating Movies
            mm.createMovie(
                1,
                "Black Adam",
                MovieStatus.NOW_SHOWING,
                "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods -- and imprisoned just as quickly -- Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                "Jaume Collet-Serra",
                "Dwayne Johnson, Aldis Hodge, Noah Centineo, Sarah Shahi, Marwan Kenzari, Quintessa Swindell",
                AgeRestriction.PG13,MovieType.BLOCKBUSTER,125
            );
   
            mm.createMovie(
                2,
                "Ajoomma",
                MovieStatus.NOW_SHOWING,
                "Produced by award-winning filmmaker Anthony Chen. Auntie, is a middle-aged Singaporean woman who has dedicated the best years of her life to caring for her family. Now widowed with her grown up son, Sam about to fly the roost, Auntie is left to contend with a whole new identity beyond her roles of daughter, wife, and mother.",
                "He Shuming",
                "Hong Huifang, Jung Dong-Hwan, Kang Hyung Suk, Yeo Jingoo",
                AgeRestriction.M18,
                MovieType._2D,
                90
            );
            
            mm.createMovie(
                3,
                "Black Panther: Wakanda Forever",
                MovieStatus.COMING_SOON,
                "Queen Ramonda, Shuri, MBaku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King TChalla's death.",
                "Ryan Coogler",
                "Letitia Wright, Tenoch Huerta, Martin Freeman, Lupita Nyong'o, Danai Gurira, Winston Duke",
                AgeRestriction.PG13,
                MovieType._3D,
                150
            );
            
            mm.createMovie(
                4,
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
                5,
                "Amsterdam",
                MovieStatus.NOW_SHOWING,
                "Three close friends find themselves at the center of one of the most secret plots in American history.",
                "David O. Russell",
                "Christian Bale, Margot Robbie, John David Washington, Chris Rock, Anya Taylor-Joy, Zoe Saldana, Rami Malek",
                AgeRestriction.M18,
                MovieType._2D,
                134
            );
            
            mm.createMovie(
                6,
                "Strange World",
                MovieStatus.PREVIEW,
                "The legendary Clades are a family of explorers whose differences threaten to topple their latest and most crucial mission.",
                "Don Hall",
                "Jake Gyllenhaal, Alan Tudyk, Dennis Quaid, Jaboukie Young-White, Gabrielle Union, Lucy Liu",
                AgeRestriction.PG,
                MovieType._2D,
                126
            );
            
            mm.createMovie(
                7,
                "Jurassic World Dominion",
                MovieStatus.NOW_SHOWING,
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
        else{
            System.out.println("Reading movies from file");
            MovieManager mm = MovieManager.getInstance();
            mm.printMovies();
            MovieManager.close();
        }
    }
}
