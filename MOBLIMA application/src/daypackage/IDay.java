package daypackage;

/**
 * Interface that is implemented by Day object
 * 
 * @apiNote IDay
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public interface IDay {
	/**
	 * @return float multiplier of Day class
	 */
	float getDayMultiplier();

	/**
	 * Checks if holiday variable is true
	 * If set, then the day is a holiday
	 * 
	 * @return boolean
	 */
	boolean isHoliday();

	/**
	 * Sets holiday variable to true
	 */
	void setHoliday();

	/**
	 * Unset holiday variable
	 */
	void removeHoliday();

	/**
	 * Sets the day, month and year number and convert and save it to String
	 * variable fulldate in YYYYMMDD format
	 * 
	 * @param dayNumber
	 * @param monthNumber
	 * @param yearNumber
	 */
	void setDate(int dayNumber, int monthNumber, int yearNumber);

	/**
	 * Overloaded function. Allows set day by a date string.
	 * 
	 * @param fullDate
	 */
	void setDate(String fullDate);

	/**
	 * Returns the date of Day object in YYYYMMDD format
	 * 
	 * @return String
	 */
	String getDate();

	/**
	 * Sets the day of the week. [Mon, Tues ... Sun]
	 * 
	 * @param day enum
	 */
	void setDayOfWeek(DayOfWeek day);

	/**
	 * Returns the day of the week. [Mon, Tues ... Sun]
	 * 
	 * @return enum DayOfWeek
	 */
	DayOfWeek getDayOfWeek();

	/**
	 * Sets time of the Day object
	 * Paramter time passed in to be in 24H format 1359H
	 * 
	 * @param time
	 */
	void setTime(String time) throws IllegalArgumentException;

	/**
	 * Returns the time stored in Day object
	 * 
	 * @return String
	 */
	String getTime();

}
