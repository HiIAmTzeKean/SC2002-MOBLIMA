package daypackage;
import daypackage.*;
import java.util.*;

public class Day implements IDay {

	private int isHoliday;
	private DayOfWeek dayOfWeek;
	private static float multiplier;

	// range 1 - 30
	private int dayNumber;
	// range 1 - 12
	private int monthNumber;
	private int yearNumber;

	// "1300" means 1pm
	private String time;
	
	public Day{
		this.isHoliday = 0;
		this.multiplier = 1;
	}
	
	@Override
	public float getMultiplier() {
		// if holiday then the multiplier should be doubled
		// for eg. if the mulitplier is 0.5 then holiday = 0.5 * 2
		if(this.dayOfWeek.equals(SUN)||this.dayOfWeek.equals(SAT)
		   return this.multiplier
		else if(this.isHoliday)
		   return 2;
		else
		   return 1;
		   
	}
	@Override
	public void setMultiplier(float newMulitplier) {
		// TODO Auto-generated method stub
		if(isHoliday)
			this.multiplier = 2*newMulitplier;
		else
			this.multiplie = newMulitplier;
		
	}
	@Override
	public void setHoliday() {
		// TODO Auto-generated method stub
		this.isHoldiay = 1;
		
	}
	@Override
	public void removeHoliday() {
		// TODO Auto-generated method stub
		this.isHoliday = 0;
		
	}
	@Override
	public boolean isHoliday() {
		// TODO Auto-generated method stub
		if(isHoliday)
			return true;
		else
			return false;
	}
	@Override
	public void setDate(int dayNumber, int monthNumber, int yearNumber) {
		// TODO Auto-generated method stub
		this.dayNumber = dayNumber;
		this.monthNumber = monthNumber;
		this.yearNumber = yearNumber;
		
	}
	@Override
	public DayOfWeek getDayOfWeek() {
		// TODO Auto-generated method stub
		return this.dayOfWeek;
	}
	@Override
	public String getDate() {
		// returns date string
		// DDMMYYYY
		String day = Integer.toString(this.dayNumber);
		String month = Integer.toString(this.monthNumber);
		String year = Integer.toString(this.yearNumber);
		String fulldate = year + month +day;
		return fulldate;
	}
	@Override
	public void setDayOfWeek(DayOfWeek day) {
		// TODO Auto-generated method stub
		this.dayOfWeek = day;
		
	}
	@Override
	public void setTime(String time){
		this.time = time;
	}
	@Override
	public String getTime(){
		retuen this.time;
	}
	

	
}
