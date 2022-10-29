package agepackage;
public enum AgeCategory {
	SENIOR("Senior Citizen"),
	ADULT("Adult"),
	CHILD("Children");

	private String category;
	
	AgeCategory(String category) {
		this.category = category;
	}

	public boolean equals(String category) {
		if (this.category==category)
			return true;
		return false;
	}
}
