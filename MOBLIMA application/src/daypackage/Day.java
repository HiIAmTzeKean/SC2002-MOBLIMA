package daypackage;

public class Day implements IDay {

	private boolean holiday;
	private DayOfWeek dayOfWeek;
	private static float weekendMultiplier;
	private static float holidayMultiplier;

	// range 1 - 30
	private int dayNumber;
	// range 1 - 12
	private int monthNumber;
	private int yearNumber;
	private String fullDate;

	// "1300" means 1pm
	private String time;
	
	public Day(){
		this.holiday = false;
		weekendMultiplier = 1;
		holidayMultiplier = 1;
	}
	
	public float getDayMultiplier() {
		if(holiday){
			if(this.dayOfWeek==DayOfWeek.SUN || this.dayOfWeek==DayOfWeek.SAT){
				return weekendMultiplier*holidayMultiplier;
			}
			else{
				return holidayMultiplier;
			}
		}
		else{
			if(this.dayOfWeek==DayOfWeek.SUN || this.dayOfWeek==DayOfWeek.SAT){
				return weekendMultiplier;
			}
			else{
				return 1;
			}
		}
	}

	public void setWeekendMultiplier(float newWeekendMulitplier) {
		weekendMultiplier = newWeekendMulitplier;
	}
	
	public void setHolidayMultiplier(float newHolidayMulitplier) {
		holidayMultiplier = newHolidayMulitplier;
	}

	
	public void setHoliday() {
		this.holiday = true;
	}

	public void removeHoliday() {
		this.holiday = false;
	}
	
	public boolean isHoliday() {
		if(holiday)
			return true;
		else
			return false;
	}
	
	public void setDate(int dayNumber, int monthNumber, int yearNumber) {
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		this.fullDate = Integer.toString(this.yearNumber) + Integer.toString(this.monthNumber) + Integer.toString(this.dayNumber);
	}

	public String getDate() {
		return this.fullDate;
	}
	
	public void setDayOfWeek(DayOfWeek day) {
		this.dayOfWeek = day;
	}
	
	public DayOfWeek getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return this.time;
	}
	
}
