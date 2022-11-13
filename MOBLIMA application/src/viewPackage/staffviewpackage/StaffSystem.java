package viewpackage.staffviewpackage;

import java.util.InputMismatchException;
import java.util.Scanner;
import customerpackage.DiscountCode;
import daypackage.Day;
import showtimepackage.IShowtimeSystem;
import showtimepackage.ShowtimeManager;
import viewpackage.View;

/**
 * Provides UI to staff to modify system settings
 * 
 * @author Ng Tze Kean
 * @since 05-11-2022
 */
public class StaffSystem extends View {
    public static void displayMenu() {
        System.out.print("\033[H\033[2J");
        System.out.println("------------------------------------------------------------------");
        System.out.println("                      Update System Settings");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Choice 1 : Set New BasePrice");
        System.out.println("Choice 2 : Set New Base Multiplier for Weekday/Weekend Price");
        System.out.println("Choice 3 : Set New Holiday");
        System.out.println("Choice 4 : Remove Holiday");
        System.out.println("Choice 5 : Add Discount Code");
        System.out.println("Choice 6 : Remove Discount Code");
        System.out.println("Choice 7 : Return");
        System.out.println("------------------------------------------------------------------");
    }

    /**
	 * Allows the staff to add a new discount code which is stored in DiscountCode class
	 * 
	 * @apiNote DiscountCode
	 */
    private static void addDiscountCode() {
        DiscountCode DC = DiscountCode.getInstance();
        enum setDiscoundEnum {
            CODE, DISCOUNT, CREATE
        }
        ;
        setDiscoundEnum state = setDiscoundEnum.CODE;
        String code = null;
        float discount = 0f;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("       Adding Discount Code");
        System.out.println("--------------------------------------");
        DC.printDiscountCode();
        while (!completed) {
            switch (state) {
                case CODE:
                    try {
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter Discount code: ");
                        code = sc.next();
                        if (code.equals("0"))
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setDiscoundEnum.CODE;
                        break;
                    }
                case DISCOUNT:
                    try {
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter discount amount (multiplier): ");
                        discount = sc.nextFloat();
                        if (discount == 0f) {
                            state = setDiscoundEnum.CODE;
                            break;
                        }
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = setDiscoundEnum.DISCOUNT;
                        break;
                    }
                case CREATE:
                    DC.addDiscountCodeTicket(code, discount);
                    completed = true;
            }
        }
        DC.printDiscountCode();
        System.out.println("--------------------------------------");
        System.out.println("      Discount Code Added!");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    /**
	 * Allows the staff to update the showtime date and time
	 * that is stored in the data file
	 * 
	 * @apiNote IShowtimeSystem
	 */
    private static void removeDiscountCode() {
        DiscountCode DC = DiscountCode.getInstance();
        enum removeDiscoundEnum {
            CODE, CREATE
        }
        ;
        removeDiscoundEnum state = removeDiscoundEnum.CODE;
        String code = null;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("      Removing Discount Code");
        System.out.println("--------------------------------------");
        DC.printDiscountCode();
        while (!completed) {
            switch (state) {
                case CODE:
                    try {
                        System.out.println("[Enter 0 to go back]");
                        System.out.println("Enter Discount code: ");
                        code = sc.next();
                        if (code.equals("0"))
                            return;
                    } catch (InputMismatchException e) {
                        inputMismatchHandler();
                        state = removeDiscoundEnum.CODE;
                        break;
                    }
                case CREATE:
                    try {
                        DC.removeDiscountCode(code);
                        completed = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid code supplied!\nTry again!");
                        state = removeDiscoundEnum.CODE;
                        break;
                    }
            }
        }
        DC.printDiscountCode();
        System.out.println("--------------------------------------");
        System.out.println("        Discount Code Removed!");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    public static void start() {
        sc = new Scanner(System.in);
        int choice = 0;

        while (true) {
            displayMenu();
            try {
                System.out.println("Enter choice");
                choice = sc.nextInt();
                if (choice > 7 || choice < 1) {
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
                    addDiscountCode();
                    break;
                case 6:
                    removeDiscountCode();
                    break;
                case 7:
                    System.out.println("--------------------------------------");
                    System.out.println("      Exiting Staff System Menu");
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
     * 
     * @apiNote IShowtimeSystem
     */
    private static void setBasePrice() {
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("       Setting New Base Price");
        System.out.println("-------------------------------------");
        float BasePrice = 0;
        boolean complete = false;

        while (!complete) {
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Current Base Price: " + ssHandler.getBasePrice());
                System.out.println("Enter New Base Price");
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
        System.out.println("New Base Price: " + ssHandler.getBasePrice());
        System.out.println("--------------------------------------");
        System.out.println("         New Base Price Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    /**
     * Provides UI to staff for setting multiplier day
     * Prints the current multiplier and prompts for I/O
     * 
     * @apiNote Day
     */
    private static void setMultiplier() {
        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("        Setting New Multiplier");
        System.out.println("--------------------------------------");
        float multiplier = 0;
        boolean complete = false;

        while (!complete) {
            try {
                System.out.println("[Enter 0 to return]");
                System.out.println("Current Day Multiplier: " + Day.getMultiplier());
                System.out.println("Enter New Day Multiplier");
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
        System.out.println("New Day Multiplier is: " + Day.getMultiplier());
        System.out.println("--------------------------------------");
        System.out.println("         New Multiplier Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    /**
     * Provides UI to staff for setting a holiday
     * Prints the current showtime prompts for I/O
     * Staff will select from the list of days to make the day a holiday
     * 
     * @apiNote IShowtimeSystem
     */
    private static void unsetHoliday() {
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        enum dayEnum {
            DATE, SET
        }
        ;
        dayEnum state = dayEnum.DATE;
        String date = null;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("          Remove holiday");
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
                        System.out.println("Exiting the function now!");
                        waitForEnter(null);
                        state = dayEnum.DATE;
                        return;
                    }
            }
        }
        ssHandler.printShowtimeAdmin();
        System.out.println("--------------------------------------");
        System.out.println("           Holiday removed!");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }

    /**
     * Provides UI to staff for removing a holiday
     * Prints the current showtime prompts for I/O
     * Staff will select from the list of days to remove holiday
     * 
     * @apiNote IShowtimeSystem
     */
    private static void setHoliday() {
        IShowtimeSystem ssHandler = ShowtimeManager.getInstance();
        enum dayEnum {
            DATE, SET
        }
        ;
        dayEnum state = dayEnum.DATE;
        String date = null;
        boolean completed = false;

        System.out.print("\033[H\033[2J");
        System.out.println("--------------------------------------");
        System.out.println("     Setting New Day as Holiday");
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
                        System.out.println("Exiting the function now!");
                        waitForEnter(null);
                        state = dayEnum.DATE;
                        return;
                    }
            }
        }
        ssHandler.printShowtimeAdmin();
        System.out.println("--------------------------------------");
        System.out.println("           New Holiday Set! ");
        System.out.println("--------------------------------------");
        waitForEnter(null);
    }
}
