package viewPackage;

public class MOBLIMA extends View {

    public static void start(){ 
        viewPackage.View.start(); 
    }
    public static void main(String args []){ 
        System.out.print("\033[H\033[2J");
        System.out.println("----------------------------------");
        System.out.println("\t\tWELCOME TO MOBLIMA\t\t"); 
        System.out.println("----------------------------------");

        start(); 
        
        System.out.println("----------------------------------");
        System.out.println("\t\tEXITING MOBLIMA\t\t"); 
        System.out.println("----------------------------------");
    }
}
