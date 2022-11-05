package viewPackage.staffpackage;

import viewPackage.View;
import java.util.InputMismatchException;
import java.util.Scanner;
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
		System.out.println("choice 1 : add showtimes");
		System.out.println("choice 2 : update showtime ");
		System.out.println("choice 3 : Return");
		System.out.println("-------------------------------------");
	}

	private static void addShowtime() {
		MovieManager MovieHandler = MovieManager.getInstance();
		ICinema CinemaHandler = CinemaManager.getInstance();
		IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
		System.out.println("--------------------------------------");
		System.out.println("\t\tSetting new showtime");
		System.out.println("-------------------------------");

		MovieHandler.printMovies();

		System.out.println("Enter Movie ID");
		int id = sc.nextInt();

		Movie m = MovieHandler.getMoviefromID(id);

		CinemaHandler.printCinemas();

		System.out.println("Enter Cinema ID");
		id = sc.nextInt();

		IDay day = new Day();

		System.out.println("Enter choice for day of the week");
		System.out.println("choice 1 : SUN");
		System.out.println("choice 2 : MON");
		System.out.println("choice 3 : TUE");
		System.out.println("choice 4 : WED");
		System.out.println("choice 5 : THURS");
		System.out.println("choice 6 : FRI");
		System.out.println("choice 7 : SAT");

		int ch;

		try {
			ch = sc.nextInt();
			if (ch >= 1 && ch <= 7) {

				switch (ch) {
					case 1:
						day.setDayOfWeek(DayOfWeek.SUN);
						break;
					case 2:
						day.setDayOfWeek(DayOfWeek.MON);
						break;
					case 3:
						day.setDayOfWeek(DayOfWeek.TUE);
						break;
					case 4:
						day.setDayOfWeek(DayOfWeek.WED);
						break;
					case 5:
						day.setDayOfWeek(DayOfWeek.THU);
						break;
					case 6:
						day.setDayOfWeek(DayOfWeek.FRI);
						break;
					case 7:
						day.setDayOfWeek(DayOfWeek.SAT);
						break;
				}
			} else
				throw new IllegalArgumentException("invalid input : must be from 1-7");

		} catch (InputMismatchException e) {
			System.out.println(e.toString());
		}

		// getting date

		try {

			int yearNumber, monthNumber, dayNumber;

			System.out.println("Enter Date ");
			System.out.println("Enter day of the month ");
			dayNumber = sc.nextInt();
			System.out.println("Enter Month  ");
			monthNumber = sc.nextInt();
			System.out.println("Enter year  ");
			yearNumber = sc.nextInt();

			day.setDate(dayNumber, monthNumber, yearNumber);

		} catch (InputMismatchException e) {
			System.out.println(e.toString() + " enter valid Intergers only");
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString() + " enter valid Intergers only");
		}

		// getting time

		String time;

		System.out.println("Enter time (24HR FORMAT EG: 2000");
		try {
			time = sc.next();
			day.setTime(time);
		} catch (InputMismatchException e) {
			System.out.println(e.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}

		// getting holiday

		int holiday;
		try {
			holiday = sc.nextInt();
			if (holiday == 0 || holiday == 1) {
				if (holiday == 1)
					day.setHoliday();
			} else
				throw new IllegalArgumentException("invalid input : must be 1 or 0");

		} catch (InputMismatchException e) {
			System.out.println(e.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}

		ssHandler.addShowtime(m, CinemaHandler.cloneCinemaByID(id), day); // change to static

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
	private static void updateShowtime() {

	}

}
