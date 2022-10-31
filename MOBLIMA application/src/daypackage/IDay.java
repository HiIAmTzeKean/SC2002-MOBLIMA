package daypackage;

public interface IDay {

	/**
	 * @return the multiplier set by staff for weekends
	 */
	float getDayMultiplier();
	/**
	 * @return true is showtime is on a holiday else false
	 */
	boolean isHoliday();
	/**
	 * Sets holiday variable to true
	 */
	void setHoliday();
	/**
	 * Sets holiday variable 
	 */
	void removeHoliday();
	/**
	 * Sets the day, month and year number and convert and save it to String variable fulldate in YYYYMMDD format
	 * @param dayNumber
	 * @param monthNumber
	 * @param yearNumber
	 */
	void setDate(int dayNumber, int monthNumber, int yearNumber);
	/**
	 * @return the date of the showtime
	 */
	String getDate();
	/**
	 * Sets day of the week for a showtime
	 * @param day
	 */
	void setDayOfWeek(DayOfWeek day);
	/**
	 * @return day of the week for a showtime
	 */
	DayOfWeek getDayOfWeek();
	/**
	 * Sets time of the showtime
	 * @param time
	 */
	void setTime(String time);
	/**
	 * @return time of the showtime
	 */
	String getTime();

}

