package viewPackage.staffpackage;
import viewPackage.View;


import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;


public class StaffAuth extends View {
	

	private static String[] passwordDB = {"password1", "password2","password3","password4","password5","password6","password7","password8","password9","password10"};
	private static String[] usernameDB = {"username1", "username2","username3","username4","username5","username6","username7","username8","username9","username10"};
	
	private static String password = new String(); 
	private static String username = new String(); 
	
	
	

	public static void login() {
		// TODO - implement StaffAuth.login

		System.out.println("Please login to the MOBLIMA system");
		Scanner sc = new Scanner(System.in);
		boolean contains; 

		int no_of_tries = 0 ;
		int index = -1;

		//username scanning and checking 
		while (no_of_tries < 3 ) {

			try {
				System.out.println("Enter Username \n trial:" + (no_of_tries+1));
				username = sc.next(); 
			} catch ( InputMismatchException  e){ 
				System.out.println(e.toString());
			}

			contains = Arrays.stream(usernameDB).anyMatch(username::equals);
			System.out.print("\f"); 
			
			if(!contains) { 
				System.out.println("Username not found"); 
				no_of_tries++; 
				System.out.print("\f"); continue;
			}

			index = Arrays.asList(usernameDB).indexOf(username);
		}
		
		//password scanning and checking 
		while (no_of_tries < 3) {
			try { 
			System.out.println("Enter Password \n trial:" + (no_of_tries+1));
			password = sc.next(); 
			}  catch (  InputMismatchException  e){ 
				System.out.println(e.toString());
			}

			System.out.print("\f"); 
			
			if(password != passwordDB[index]) { 
				System.out.println("Wrong password"); 
			    no_of_tries++; 
				System.out.print("\f"); continue;
			}	
			
			System.out.println("Access Granted");
			StaffView.start();;
		} 
		
		System.out.println("System Locked. Number of tries exceeded.");
		sc.close(); 
		// MOBLIMA.start();
		
		// throw new UnsupportedOperationException();
	}

	public static void logout() {
		// TODO - implement StaffAuth.logout
		
			View.start();

	
		//throw new UnsupportedOperationException();
	}

}
