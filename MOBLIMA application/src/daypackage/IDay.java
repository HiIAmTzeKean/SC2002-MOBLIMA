package daypackage;

public interface IDay {

	float getMultiplier();
	void setMultiplier(float newMulitplier);
	boolean isHoliday();
	void setHoliday();
	void removeHoliday();
	void setDate(int dayNumber, int monthNumber, int yearNumber);
	String getDate();
	void setDayOfWeek(DayOfWeek day);
	DayOfWeek getDayOfWeek();
	void setTime(String time);
	String getTime();

}
