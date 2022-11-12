package daypackage;

import java.io.Serializable;
/**
 * Day object class. Day object is serialisable and is composited in
 * Showtime object
 * @apiNote IDay
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Day implements Serializable, IDay {
	private boolean holiday;
	private DayOfWeek dayOfWeek;
	private static float multiplier=1f;;
	// range 1 - 30. DD format
	private int dayNumber;
	// range 1 - 12. MM format
	private int monthNumber;
	// range 2022 - present. YYYY format
	private int yearNumber;
	// range 20220101 - present. YYYYMMDD format
	private String fullDate;
	// "1300" means 1pm
	private String time;

	public Day(){
		this.holiday = false;
		this.dayNumber = 01;
		this.monthNumber = 01;
		this.yearNumber = 2022;
		this.dayOfWeek = DayOfWeek.SAT;
		time = "1200";
		setDate(dayNumber, monthNumber, yearNumber);
		if (multiplier == 0f) multiplier = 1f;
	}
	public Day(String date){
		this(date,"1200");
	}
	public Day(int dayNumber,int monthNumber, int yearNumber, String time){
		if (dayNumber>31 || dayNumber <1 || monthNumber>12 || monthNumber<1) {
			throw new IllegalArgumentException("Invalid date string supplied");
		}
		this.holiday = false;
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		this.dayOfWeek = null;
		this.time = time;
		setDate(dayNumber, monthNumber, yearNumber);
		if (multiplier == 0f) multiplier = 1f;
	}
	public Day(String fullDate, String time){
		setDate(fullDate);
		this.holiday = false;
		this.dayOfWeek = null;
		this.time = time;
		this.fullDate = fullDate;
		if (multiplier == 0f) multiplier = 1f;
	}
	
	/** 
	 * @param anotherDay
	 * @return boolean
	 */
	public boolean equals(Day anotherDay){
		if (anotherDay.dayNumber == dayNumber &&
			anotherDay.monthNumber == monthNumber &&
			anotherDay.yearNumber == yearNumber &&
			anotherDay.time.equals(time))
			return true;
		return false;
	}
	
	/** 
	 * @param anotherDay
	 * @return boolean
	 */
	public boolean equalsDate(Day anotherDay){
		if (anotherDay.dayNumber == dayNumber &&
			anotherDay.monthNumber == monthNumber &&
			anotherDay.yearNumber == yearNumber)
			return true;
		return false;
	}
	
	/** 
	 * @return float
	 */
	public float getDayMultiplier() {
		if(holiday){
			if(this.dayOfWeek==DayOfWeek.SUN || this.dayOfWeek==DayOfWeek.SAT){
				return multiplier*2;
			}
			else{
				return multiplier*1.5f;
			}
		}
		else{
			if(this.dayOfWeek==DayOfWeek.SUN || this.dayOfWeek==DayOfWeek.SAT){
				return multiplier*1.25f;
			}
			else{
				return multiplier;
			}
		}
	}
	
	/**
	 * Sets multiplier
	 * @param newMulitplier
	 */
	public static void setMultiplier(float newMulitplier) {
		multiplier = newMulitplier;
	}
	
	/** 
	 * @return float
	 */
	public static float getMultiplier() {
		return multiplier;
	}

	public void setHoliday() {
		this.holiday = true;
	}

	public void removeHoliday() {
		this.holiday = false;
	}
	
	
	/** 
	 * @return boolean
	 */
	public boolean isHoliday() {
		if(holiday)
			return true;
		else
			return false;
	}
	
	
	/** 
	 * @param dayNumber
	 * @param monthNumber
	 * @param yearNumber
	 */
	public void setDate(int dayNumber, int monthNumber, int yearNumber) {
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		String monthString;
		String dayString;
		if (monthNumber<10){
			monthString = "0"+Integer.toString(this.monthNumber);
		}
		else{
			monthString = Integer.toString(this.monthNumber);
		}
		if (dayNumber<10){
			dayString = "0"+Integer.toString(this.dayNumber);
		}
		else{
			dayString = Integer.toString(this.dayNumber);
		}
		this.fullDate = Integer.toString(this.yearNumber) + monthString + dayString;
	}
	
	/** 
	 * @param fullDate
	 * @throws IllegalArgumentException
	 */
	public void setDate(String fullDate) throws IllegalArgumentException {
		if (fullDate.length() != 8) {
			throw new IllegalArgumentException("Invalid date string supplied");
		}
		this.dayNumber = Integer.valueOf(fullDate.substring(6, 8));
		this.monthNumber = Integer.valueOf(fullDate.substring(4, 6));
		this.yearNumber = Integer.valueOf(fullDate.substring(0, 4));
		if (dayNumber>31 || dayNumber <1 || monthNumber>12 || monthNumber<1) {
			throw new IllegalArgumentException("Invalid date string supplied");
		}
	}
	
	/** 
	 * @return String
	 */
	public String getDate() {
		return this.fullDate;
	}
	
	
	/** 
	 * @param day
	 */
	public void setDayOfWeek(DayOfWeek day) {
		this.dayOfWeek = day;
	}
	
	
	/** 
	 * @return DayOfWeek
	 */
	public DayOfWeek getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	
	/** 
	 * @param time
	 * @throws IllegalArgumentException
	 */
	public void setTime(String time) throws IllegalArgumentException{
		if (time.length() != 4) {
			throw new IllegalArgumentException("Invalid time string length supplied");
		}
		try {
			if (Integer.parseInt(time)>2359 || Integer.parseInt(time)<0) {
				throw new IllegalArgumentException("Invalid time string supplied");
			}
			this.time = time;
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid time string supplied");
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
	
	/** 
	 * @return String
	 */
	public String getTime(){
		return this.time;
	}
	
}
