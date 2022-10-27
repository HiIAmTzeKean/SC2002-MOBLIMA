package daypackage;
import daypackage.*;

public class Day implements IDay {

	private int isHoliday;
	private DayOfWeek dayOfWeek;
	private static int multiplier;

	// range 1 - 30
	private int dayNumber;
	// range 1 - 12
	private int monthNumber;
	private int yearNumber;

	// "1300" means 1pm
	private String time;
	@Override
	public void getMultiplier() {
		// if holiday then the multiplier should be doubled
		// for eg. if the mulitplier is 0.5 then holiday = 0.5 * 2
		
	}
	@Override
	public void setMultiplier() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHoliday() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeHoliday() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setMultiplier(float newMulitplier) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isHoliday() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setDate(int dayNumber, int monthNumber, int yearNumber) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public DayOfWeek getDayOfWeek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDate() {
		// returns date string
		// DDMMYYYY
	}
	@Override
	public void setDayOfWeek(DayOfWeek day) {
		// TODO Auto-generated method stub
		
	}

	
}