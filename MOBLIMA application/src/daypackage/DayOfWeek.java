package daypackage;

/**
 * Enum DayOfWeek used by Day class
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
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

	public boolean equals(String day) {
		if (this.day==day)
			return true;
		return false;
	}
}
