package moviepackage;
import static org.junit.Assert.assertNotEquals;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
public class appTest {
    @Test
    public void whenModifyingOriginalObject_thenCopyShouldNotChange(){
        Movie newmovie = new Movie(
            "Test Movie WOW",
            MovieStatus.NOW_SHOWING,
            "SCSE is the greatest school of all time",
            "Dr.Loke",
            "Clown Students",
            AgeRestriction.R21,
            MovieType.BLOCKBUSTER,
            169);
        Movie deepCopy = new Movie(newmovie);
        deepCopy.addReview(new Review(1,"Nice movie"));
        assertNotEquals(newmovie.getReviews(),deepCopy.getReviews());
    }
    public static void main(String[]args){
        boolean createData = false;
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
        else{
            System.out.println("Reading movies from file");
            MovieManager mm = MovieManager.getInstance();
            System.out.printf("Number of movies read: %d\n",mm.getMovies().size());
            //mm.printMovies();
            System.out.println();
            //mm.printMoviesAdmin();
            // System.out.println("\nTesting findMovieByName():");
            // Movie test = mm.findMoviebyName("Strange World");
            // test.printMovieComplete();

            // System.out.println("\n\nTesting findMoviebyID:");
            // for(int i = 1; i<=mm.getMovies().size(); i++){
            //     System.out.printf("%s\n",mm.getMoviefromID(i).getMovieTitle());
            // }

            //mm.printMovies();
            // System.out.println("\n\nTesting deleteMovie");
            //mm.getTop5_ratingCustomer();
            //mm.printMovieAdmin();
        }
    }
}
