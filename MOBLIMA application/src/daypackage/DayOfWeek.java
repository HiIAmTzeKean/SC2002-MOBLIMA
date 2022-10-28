package daypackage;

public enum DayOfWeek {
	MON("Monday"),
	TUE("Tuesday"),
	WED("Wednesday"),
	THU("Thursday"),
	FRI("Friday"),
	SAT("Saturday"),
	SUN("Sunday");
	    
	private String day;
	
	DayOfWeek(String day) {
		this.day = day;
	}
	public String getDayOfWeek() {
		return this.day;
	}
	public boolean equals(String day) {
		if (this.day==day)
			return true;
		return false;
	}
}
