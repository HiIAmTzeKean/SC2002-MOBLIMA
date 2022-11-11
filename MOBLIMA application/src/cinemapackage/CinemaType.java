package cinemapackage;

/**
 * Eunm types for cinema
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public enum CinemaType{
	PLATINUM("Platinum"),
	GOLD("Gold"),
	SILVER("Silver");
	private String type;

	CinemaType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public boolean equals(String type) {
		if (this.type.equals(type))
			return true;
		return false;
	}
}