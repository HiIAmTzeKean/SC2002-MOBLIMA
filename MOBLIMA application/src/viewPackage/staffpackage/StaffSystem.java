package viewPackage.staffpackage;

import java.util.InputMismatchException;
import java.util.Scanner;
import daypackage.Day;
import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;
import viewPackage.View;

/**
 * Provides UI to staff to modify system settings
 * @author Ng Tze Kean
* @since 05-11-2022
 */
public class StaffSystem extends View {
    public static void displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Update System Settings");
        System.out.println("--------------------------------------");
        System.out.println("Choice 1 : Set new BasePrice");
        System.out.println("Choice 2 : Set new base multiplier for weekday/weekend pricing");
        System.out.println("Choice 3 : Set new Holiday");
        System.out.println("Choice 4 : Remove Holiday");
        System.out.println("Choice 5 : Return");
        System.out.println("--------------------------------------");
    }
    public static void start() {
        sc = new Scanner(System.in);
        int choice = 0;

        while (true) {
            displayMenu();
            try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
                if (choice > 5 || choice < 1) {
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
                    setBasePrice();
                    break;
                case 2:
                    setMultiplier();
                    break;
                case 3:
                    setHoliday();
                    break;
                case 4:
                    unsetHoliday();
                    break;
                case 5:
                    System.out.println("--------------------------------------");
                    System.out.println("Exiting staff system menu");
                    System.out.println("--------------------------------------");
                    waitForEnter(null);
                    return;
                default:
                    System.out.println("Enter valid choice");
                    choice = 0;
            }
        }
    }
    /**
     * Provides UI to staff for setting base price of the showtime
     * Prints the current base price and prompts for I/O
     * @apiNote IShowtimeSystem
     */
    private static void setBasePrice() {
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Setting new base price");
        System.out.println("-------------------------------------");
        float BasePrice = 0;
        boolean complete = false;

        while (!complete) {
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Current base price: " + ssHandler.getBasePrice());
                System.out.println("Enter the new base price");
                BasePrice = sc.nextFloat();
                if (BasePrice == 0f)
                    return;
                complete = true;
            } catch (InputMismatchException e) {
                inputMismatchHandler();
                continue;
            }
        }
        ssHandler.setBasePrice(BasePrice);
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew base Price Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    /**
     * Provides UI to staff for setting multiplier day
     * Prints the current multiplier and prompts for I/O
     * @apiNote Day
     */
    private static void setMultiplier() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Setting new multiplier");
        System.out.println("--------------------------------------");
        float multiplier = 0;
        boolean complete = false;

        while (!complete) {
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Current day multiplier: " + Day.getMultiplier());
                System.out.println("Enter the new day multiplier");
                multiplier = sc.nextFloat();
                if (multiplier == 0f)
                    return;
                complete = true;
            } catch (InputMismatchException e) {
                inputMismatchHandler();
                continue;
            }
        }

        Day.setMultiplier(multiplier);

        System.out.println("--------------------------------------");
        System.out.println("\t\tNew multiplier Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
    /**
     * Provides UI to staff for setting a holiday
     * Prints the current showtime prompts for I/O
     * Staff will select from the list of days to make the day a holiday
     * @apiNote IShowtimeSystem
     */
    private static void unsetHoliday(){
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        enum dayEnum {
            DATE, SET
        };
        dayEnum state = dayEnum.DATE;
        String date = null, time = null;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Remove holiday");
        System.out.println("--------------------------------------");

        while (!completed) {
            switch (state) {
                case DATE:
                    try {
                        ssHandler.printShowtimeAdmin();
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter Full Date (format:YYYYMMDD): ");
                        date = sc.next();
                        if (date.equals("0"))
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = dayEnum.DATE;
                        break;
                    }
                case SET:
                    try {
                        Day day = new Day(date);
                        ssHandler.unsetHoliday(day);
                        completed = true;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = dayEnum.SET;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("No showtime with date exist");
                        state = dayEnum.DATE;
                        return;
                    }
            }
        }
        ssHandler.printShowtimeAdmin();
        System.out.println("--------------------------------------");
        System.out.println("\t\tHoliday removed!");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
    /**
     * Provides UI to staff for removing a holiday
     * Prints the current showtime prompts for I/O
     * Staff will select from the list of days to remove holiday
     * @apiNote IShowtimeSystem
     */
    private static void setHoliday() {
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        enum dayEnum {
            DATE, SET
        };
        dayEnum state = dayEnum.DATE;
        String date = null, time = null;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("Setting new day as holiday");
        System.out.println("--------------------------------------");

        while (!completed) {
            switch (state) {
                case DATE:
                    try {
                        ssHandler.printShowtimeAdmin();
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter Full Date (format:YYYYMMDD): ");
                        date = sc.next();
                        if (date.equals("0"))
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = dayEnum.DATE;
                        break;
                    }
                case SET:
                    try {
                        Day day = new Day(date);
                        ssHandler.setHoliday(day);
                        completed = true;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = dayEnum.SET;
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("No showtime with date exist");
                        state = dayEnum.DATE;
                        return;
                    }
            }
        }
        ssHandler.printShowtimeAdmin();
        System.out.println("--------------------------------------");
        System.out.println("\t\tNew Holiday Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
}
