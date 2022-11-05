package cinemapackage;

public enum CinemaType{
	PLATINUM("Platinum"),
	GOLD("Gold"),
	Silver("Silver");
	private String type;

	CinemaType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public boolean equals(String type) {
		if (this.type==type)
			return true;
		return false;
	}
}