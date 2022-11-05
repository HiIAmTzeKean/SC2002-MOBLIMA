package viewPackage.staffpackage;

import viewPackage.View;
import java.util.InputMismatchException;
import java.util.Scanner;

import cinemapackage.Cinema;
import cinemapackage.CinemaManager;
import cinemapackage.ICinema;
import daypackage.Day;
import daypackage.DayOfWeek;
import daypackage.IDay;
import moviepackage.Movie;
import moviepackage.MovieManager;
import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;

public class StaffShowtime extends View {
	public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("Update Showtimes");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Add showtimes");
		System.out.println("choice 2 : Change showtime timing");
		System.out.println("choice 3 : Return");
		System.out.println("-------------------------------------");
	}
	
	private static void addShowtime() {
		MovieManager MovieHandler = MovieManager.getInstance();
		ICinema CinemaHandler = CinemaManager.getInstance();
		IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
		enum addShowtimeState {MOVIE, CINEMA, DAY, DATE, TIME, HOLIDAY, CREATE};
		// Variables
		addShowtimeState state = addShowtimeState.MOVIE;
		boolean complete = false;
		int ID, yearNumber, monthNumber, dayNumber;
		String time;
		Movie movie = null;
		Cinema cinema = null;
		Day day = new Day();

		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tSetting new showtime");
		System.out.println("-------------------------------");

		while (!complete) {
            switch (state) {
                case MOVIE:
					try {
						MovieHandler.printMovies();
			
						System.out.println("[Enter 0 to return]");
						System.out.println("Enter Movie ID");
						ID = sc.nextInt();

						if (ID == 0) return;
						movie = MovieHandler.getMoviefromID(ID);
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.MOVIE;
						break;
					} catch (IllegalArgumentException e){
						System.out.println("Invalid movie id chosen!");
						state = addShowtimeState.MOVIE;
						break;
					}
				case CINEMA:
					try {
						CinemaHandler.printCinemasAdmin();
			
						System.out.println("[Enter 0 to return]");
						System.out.println("Enter Cinema ID");
						ID = sc.nextInt();
						if (ID == 0) {
							state = addShowtimeState.MOVIE;
							break;
						}
						cinema = CinemaHandler.cloneCinemaByID(ID);
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.CINEMA;
						break;
					}
				case DATE:
					try {
						System.out.println("[Enter 0 to return]");
						System.out.println("Enter day of the month ");
						dayNumber = sc.nextInt();
						System.out.println("Enter Month  ");
						monthNumber = sc.nextInt();
						System.out.println("Enter year  ");
						yearNumber = sc.nextInt();

						day.setDate(dayNumber, monthNumber, yearNumber);
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.DATE;
						break;
					} catch (IllegalArgumentException e){
						state = addShowtimeState.DATE;
						break;
					}
				case DAY:
					try {
						System.out.println("choice 1 : SUN");
						System.out.println("choice 2 : MON");
						System.out.println("choice 3 : TUE");
						System.out.println("choice 4 : WED");
						System.out.println("choice 5 : THURS");
						System.out.println("choice 6 : FRI");
						System.out.println("choice 7 : SAT");
						
						System.out.println("[Enter 0 to return]");
						System.out.println("Enter choice for day of the week [integer]: ");

						
						DayOfWeek[] arr = {DayOfWeek.SUN,DayOfWeek.MON,DayOfWeek.TUE,DayOfWeek.WED,
											DayOfWeek.THU,DayOfWeek.FRI,DayOfWeek.SAT};

						ID = sc.nextInt();
						if (ID == 0) {
							state = addShowtimeState.DATE;
							break;
						}
						else if(ID>7 || ID<1){
							System.out.println("Invalid choice");
							state = addShowtimeState.DAY;
							break;
						}
						day.setDayOfWeek(arr[ID-1]);
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.DAY;
						break;
					}
				case TIME:
					try {
						System.out.println("[Enter 0 to return]");
						System.out.println("Enter time (24HR FORMAT EG: 2000");
						time = sc.next();
						if (time.equals("0")){
							state = addShowtimeState.DAY;
							break;
						}
						day.setTime(time);
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.TIME;
						break;
					} catch (IllegalArgumentException e){
						System.out.println("Invalid time input!");
						state = addShowtimeState.TIME;
						break;
					}
				case HOLIDAY:
					try {
						System.out.println("[Enter 0 to return]");
						System.out.println("Is the date a holiday?");
						System.out.println("choice 1 : Yes");
						System.out.println("choice 2 : No");
						ID = sc.nextInt();
						if (ID == 0){
							state = addShowtimeState.TIME;
							break;
						}
						if (ID == 1)
							day.setHoliday();
					} catch (InputMismatchException e) {
						inputMismatchHandler();
						state = addShowtimeState.HOLIDAY;
						break;
					}
				case CREATE:
				try {
					ssHandler.addShowtime(movie, cinema, day);
					complete=true;
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to add showtime");
					System.out.println("Exiting function!");
					waitForEnter(null);
					return;
				}
			}
		}
		System.out.println("--------------------------------------");
		System.out.println("\t\tNew showtime added");
		System.out.println("-------------------------------");
		waitForEnter(null);
	}
	private static void updateShowtime() {

	}
	public static void start() {
		sc = new Scanner(System.in);
		int choice;

		while (true) {
			displayMenu();
			try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
                if (choice>3 || choice<1) {
                    System.out.println("Invalid input!");
					waitForEnter(null);
                    continue;
                }
            } catch (InputMismatchException e) {
                inputMismatchHandler();
				waitForEnter(null);
                continue;
            }

			switch (choice) {
				case 1:
					addShowtime();
					break;
				case 2:
					updateShowtime();
					break;
				case 3:
					System.out.println("--------------------------------------");
					System.out.println("\t\tExiting Staff Showtime Menu");
					System.out.println("-------------------------------------");
					MovieManager.close();
					ShowtimeManager.close();
					CinemaManager.close();
					return;
				default:
					System.out.println("Enter valid choice");
			}
		}
	}
}
