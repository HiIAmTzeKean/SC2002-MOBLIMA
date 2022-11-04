package viewPackage.staffpackage;
import viewPackage.MOBLIMA;
import viewPackage.View;


import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;


public class StaffAuth extends View {
	private static String[] passwordDB = {"password1", "password2","password3","password4","password5","password6","password7","password8","password9","password10"};
	private static String[] usernameDB = {"username1", "username2","username3","username4","username5","username6","username7","username8","username9","username10"};
	
	public static void login() {
		String password;
		String username;

		System.out.print("\033\143");
		System.out.println("-------------------------------------");
		System.out.println("Please login to MOBLIMA");
		System.out.println("-------------------------------------");

		boolean login = false;
		int no_of_tries = 0 ;
		int index = -1 ;

		// Tze kean code
		while (no_of_tries < 3 ) {
			System.out.println("You only have 3 trials before getting locked out");
			System.out.println("Current trial: " + (no_of_tries+1));

			try {
				System.out.println("Enter Username:");
				username = sc.next();
				System.out.print("\f"); 
			} catch (InputMismatchException  e){ 
				System.out.println("Invalid input given!");
				no_of_tries++; 
				continue;
			}
			if(! Arrays.stream(usernameDB).anyMatch(username::equals)) { 
				System.out.println("Username not found in system"); 
				no_of_tries++; 
				continue;
			}
			try {
				System.out.println("Enter Password:");
				password = sc.next();
			}  catch (InputMismatchException  e){ 
				System.out.println("Invalid input given!");
				no_of_tries++; 
				continue;
			}

			index = Arrays.asList(usernameDB).indexOf(username);
			if (password.equals(passwordDB[index])){ 
				System.out.println("-------------------------------------");
				System.out.println("Access Granted");
				System.out.println("-------------------------------------");
				login = true;
				break;
			}
			System.out.println("Wrong Username/Password combination");
			no_of_tries++;
		}

		if (login){
			System.out.print("\033\143");
			StaffView.start();
		} 
		else System.out.println("System Locked. Number of tries exceeded.");
	}
}
