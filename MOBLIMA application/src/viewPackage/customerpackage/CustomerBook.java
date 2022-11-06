package viewPackage.customerpackage;

public class CustomerBook {
    // public static void bookMenu(){
    //     Scanner sc = new Scanner(System.in);
    //     enum bookMenuState{
    //         SELECTCINEPLEX, SELECTMOVIE
    //     };
    //     bookMenuState state = bookMenuState.SELECTCINEPLEX;
    //     ICineplex cineplexHander = CineplexManager.getInstance();
    //     String cineplexChoice = null;
    //     Cineplex selectedCineplexObj = null;
    //     String movieChoice = null;
    //     boolean complete = false;
    //     System.out.print("\033[H\033[2J");
    //     System.out.println("-------------------------------------");
    //     while(!complete){
    //         switch(state){
    //             case SELECTCINEPLEX:
    //                 try{
    //                     System.out.println("[Enter 0 to return]");
    //                     cineplexHander.printCineplexes();
    //                     System.out.println("Choose a Cineplex from the Above Options:");
    //                     cineplexChoice = sc.nextLine();
    //                     if(cineplexChoice.equals("0")){
    //                         return;
    //                     }
    //                     selectedCineplexObj = cineplexHander.getCineplex(cineplexChoice);
    //                     state = bookMenuState.SELECTMOVIE;
    //                 }
    //                 catch(InputMismatchException e){
    //                     inputMismatchHandler();
    //                     state = bookMenuState.SELECTCINEPLEX;
    //                     break;
    //                 }
    //                 catch(IllegalArgumentException e){
    //                     System.out.println(e);
    //                 }
    //             break;
    //             case SELECTMOVIE:
    //                 try{
    //                     System.out.println("[Enter 0 to return]"); 
    //                     CustomerMovieListing movieListing = new CustomerMovieListing();
    //                     movieListing.movieSelection();
    //                 }
    //                 catch(InputMismatchException e){
    //                     inputMismatchHandler();
    //                     state = bookMenuState.SELECTMOVIE;
    //                 }
                
    //         }
    //     }

}