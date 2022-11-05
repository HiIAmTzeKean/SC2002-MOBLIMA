package viewPackage.staffpackage;

import viewPackage.View;
import java.util.InputMismatchException;
import java.util.Scanner;

import cinemapackage.Cinema;
import cinemapackage.CinemaManager;
import cinemapackage.ICinema;
import daypackage.Day;
import daypackage.DayOfWeek;
import moviepackage.IMovie;
import moviepackage.Movie;
import moviepackage.MovieManager;
import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;

/**
 * Staff showtime view
 * Allows modification of showtime data file
 * Staff must be logged in to use this class
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class StaffShowtime extends View {
	public static void displayMenu() {
		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("Update Showtimes");
		System.out.println("--------------------------------------");
		System.out.println("choice 1 : Add showtimes");
		System.out.println("choice 2 : Change showtime timing");
		System.out.println("choice 3 : List showtimes");
		System.out.println("choice 4 : Return");
		System.out.println("-------------------------------------");
	}
	
	/**
	 * Allows the staff to add a showtime to the showtime data file
	 * @apiNote IMovie,ICinema,IShowtimeSystem
	 */
	private static void addShowtime() {
		IMovie MovieHandler = MovieManager.getInstance();
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
		System.out.println("--------------------------------------");

		while (!complete) {
            switch (state) {
                case MOVIE:
					try {
						MovieHandler.printMovieAdmin();
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
						System.out.println("Enter Day of the month ");
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
						System.out.println("Enter time (24HR FORMAT EG: 2000)");
						time = sc.next();
						if (time.equals("0")){
							state = addShowtimeState.DAY;
							break;
						}
						else if(time.length()!=4) {
							System.out.println("Invalid time input!");
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
					System.out.println("Unable to add showtime as Movie has ended its showing");
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
	/**
	 * Allows the staff to update the showtime date and time
	 * that is stored in the data file
	 * @apiNote IShowtimeSystem
	 */
	private static void updateShowtime() {
		IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
		enum setDayShowtime { ID, FULLDATE, TIME, CREATE  };
		
		boolean complete = false;
		sc = new Scanner(System.in);
		String FullDate=null, Time=null;
		Day day = new Day();
		setDayShowtime state = setDayShowtime.ID; 
		int ID = 0;

		System.out.print("\033[H\033[2J");
		System.out.println("--------------------------------------");
		System.out.println("\t\tUpdating Showtime Day");
		System.out.println("--------------------------------------");

		ssHandler.printShowtimeAdmin();
		System.out.println("-------------------------------------");

		while (!complete) {
            switch (state) {
                case ID:
                try {

                    System.out.println("[Enter 0 to return]");
                    System.out.println("Enter Showtime ID:");
                    ID = sc.nextInt();
                    if (ID==0) return;

                } catch (InputMismatchException e) {
                    inputMismatchHandler();
                    state = setDayShowtime.ID;
                    break;
                }
                case FULLDATE:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter new Showtime Date (Format: YYYYMMDD)");
                        FullDate = sc.next();
                        if (FullDate.equals("0")) {
                            state = setDayShowtime.ID;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setDayShowtime.FULLDATE;
                        break;
                    }
				case TIME:
                    try {
                        System.out.println("[Enter 0 to return]");
                        System.out.println("Enter New Showtime Time");
                        Time = sc.next();
                        if (Time.equals("0")) {
                            state = setDayShowtime.ID;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setDayShowtime.TIME;
                        break;
                    }
                case CREATE:
					try {
						day = ssHandler.getDay(FullDate);
					} catch (IllegalArgumentException e) {
						System.out.println("Unable to get day from given Date");
						state = setDayShowtime.CREATE;
						break; 
					}
                    try {
                        ssHandler.changeShowtimeDay(ID, day);
                        complete = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Unable to set new day for showtime ");
                        System.out.println("Exiting function!");
                        waitForEnter(null);
                        return;
                    }
            }
        }
		System.out.println("-------------------------------------");
        System.out.println("New Day set for Showtime");
        System.out.println("-------------------------------------");
        waitForEnter(null);
	}
	
	/**
	 * Controls showtime view logic
	 * @apiNote IShowtimeSystem
	 */
	public static void start() {
		sc = new Scanner(System.in);
		int choice;

		while (true) {
			displayMenu();
			try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
                if (choice>4 || choice<1) {
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
					try{
						IShowtimeSystem showtimeSys = ShowtimeManager.getInstance();
						showtimeSys.printShowtimeAdmin();
					} catch (IllegalArgumentException e) {
						System.out.println("No showtimes are available yet");
					}
					waitForEnter(null);
					break;
				case 4:
					System.out.println("--------------------------------------");
					System.out.println("\t\tExiting Staff Showtime Menu");
					System.out.println("-------------------------------------");
					waitForEnter(null);
					return;
				default:
					System.out.println("Enter valid choice");
			}
		}
	}
}
