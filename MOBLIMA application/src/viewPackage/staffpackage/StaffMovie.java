package viewPackage.staffpackage;
import moviepackage.MovieManager;
import moviepackage.MovieStatus;
import moviepackage.MovieType;
// import moviepackage.Review;
import viewPackage.View;
import moviepackage.AgeRestriction;
import moviepackage.IMovie;
import moviepackage.ISales;

import java.util.Scanner;
// import java.util.ArrayList;
import java.util.InputMismatchException;


public class StaffMovie extends View {

    private static IMovie MovieHandler = MovieManager.getInstance(); 
    private static ISales SalesHandler = MovieManager.getInstance();


    public static void displayMenu(){ 

        System.out.println("Update Movie Listings");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Create Movie");
		System.out.println("choice 2 : Delete Movie");
		System.out.println("choice 3 : Update Movie");
		System.out.println("choice 4 : View Top 5 Movies");
		System.out.println("choice 5 : Go Back to Main Menu");
        System.out.println("--------------------------------------");


    }


    public static void start(){ 

        Scanner sc = new Scanner(System.in); 
        int choice = 0; 
		
		do {	
			displayMenu();
			System.out.println("Enter choice"); 
			choice = sc.nextInt(); 
			System.out.println("\f");
			
			switch (choice) { 
				case  1 : 
                        System.out.println("Creating New Movie"); 
                        System.out.println("--------------------------------------");

                        
                        System.out.println("Enter the details of the movie you want to create: ");
                        System.out.println("Movie ID:");
                        int movieID = sc.nextInt();

                        // ?System.out.println("Movie Name:");
                        System.out.println("Movie Title: ");
                        String movieTitle = sc.nextLine();
                        System.out.println("Synopsis: ");
                        String movieSynopsis = sc.next();
                        System.out.println("Director: ");
                        String movieDirector = sc.next();
                        System.out.println("Cast: ");
                        String movieCast = sc.next();
                        System.out.println("Age Restriction: ");
                        AgeRestriction movieAgeRestriction = AgeRestriction.valueOf(sc.next().toUpperCase());
                        System.out.println("Status ");
                        MovieStatus movieStatus = MovieStatus.valueOf(sc.next().toUpperCase());
                        System.out.println("Type ");
                        MovieType movieType = MovieType.valueOf(sc.next().toUpperCase());
                        // ArrayList<Review> reviews = new ArrayList<Review>();
                        System.out.println("Duration ");
                        int duration = sc.nextInt();

                        MovieHandler.createMovie(movieID, movieTitle, movieStatus, movieSynopsis,movieDirector, movieCast, movieAgeRestriction, movieType , duration);
                        System.out.println("\t\t new movie created");
				break;
				case  2 : 
                        System.out.println("Deleting Movie"); 
                        System.out.println("--------------------------------------");
                       
                        MovieHandler.printMovies();
                        System.out.println("Which movie would you like to delete?");
                        int ID = sc.nextInt();

                        MovieHandler.deleteMovie(ID);
                        System.out.println("\t\t Movie Deleted");
				break;
				case  3 : updateMovie(); 
				break;
				case  4 : showTopMovie();
				break;
				case  5 : 
                        System.out.println("\t\tExiting Staff Cinema Menu");
                        System.out.println("-------------------------------------");
                        MovieManager.close();
                        StaffAuth.start();
				break;
				default : System.out.println("Enter valid choice");
						  choice = 0;		
			}
		}while(choice<6 && choice>=0);

        sc.close();
    }

    public static void updateMovie() {
        int choice =0; 
        Scanner sc = new Scanner(System.in);

            do {	
            System.out.println("\f");
            System.out.println("Update Movie");
            System.out.println("--------------------------------------");
            System.out.println("choice 1 : set movie type");
            System.out.println("choice 2 : set movie status");
            System.out.println("choice 3 : Set movie director");
            System.out.println("choice 4 : Go Back to Staff Movie Main Menu");
            
            System.out.println("Enter choice"); 
            choice = sc.nextInt(); 
            System.out.println("\f");
            int ID;
            
            switch (choice) { 
                case  1 : 
                        System.out.println("Set new Movie type"); 
                        System.out.println("--------------------------------------");
                    
                        MovieHandler.printMovies();
                        System.out.println("Enter movie ID :");
                         ID = sc.nextInt();
                        System.out.println("Enter new Type :");
                        MovieType movieType = MovieType.valueOf(sc.next().toUpperCase());

                        MovieManager.setMovieType(ID, movieType); 
                        System.out.println("\t\tNew movie type has been set"); 
                break;
                case  2 : 
                        System.out.println("Set new Movie Status"); 
                        System.out.println("--------------------------------------");
                    
                        MovieHandler.printMovies();
                        System.out.println("Enter movie ID :");
                         ID = sc.nextInt();
                        System.out.println("Enter new movie Status ");
                        MovieStatus movieStatus = MovieStatus.valueOf(sc.next().toUpperCase());

                        MovieManager.setMovieStatus(ID, movieStatus); 
                        System.out.println("\t\tNew movie status has been set");

                break;
                case  3 : System.out.println("Set new Movie Director"); 
                          System.out.println("--------------------------------------");
                    
                        MovieHandler.printMovies();
                        System.out.println("Enter movie ID :");
                         ID = sc.nextInt();
                        System.out.println("Enter new movie Director ");
                        String director = sc.next(); 

                        MovieManager.setMovieDirector(ID, director); 
                        System.out.println("\t\tNew movie director has been set");
                break;
                case  4 : start();
                break;
                default : System.out.println("Enter valid choice");
                          choice = 0;		
            }
            
            
        }while(choice<5 && choice>=0);

        sc.close(); 

    }

    public static void showTopMovie() { 
        
        

        int choice2;
        Scanner sc = new Scanner(System.in);

		System.out.println("Display Top 5 Movie by : ");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Sales");
		System.out.println("choice 2 : ratings");
        
        
        try {
		System.out.println("Enter choice"); 
		 choice2 = sc.nextInt(); 
		System.out.println("\f");
        } catch(InputMismatchException e){
            System.out.println(e.toString());
        }
        //!! exception  gone wrong ? what is this error
        // if (choice2 != 1 && choice2 != 2) 
            
            switch(choice2){ 
                case 1 : SalesHandler.getTop5_sales();
                break;
                case 2 : SalesHandler.getTop5_rating();
            }

        //  else throw new IllegalArgumentException("invalid choice")
        sc.close(); 
        
	    }

    }
