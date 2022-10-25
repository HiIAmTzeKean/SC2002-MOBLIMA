package agepackage;
public enum AgeCategory {
	SENIOR("Senior Citizen"),
	ADULT("Adult"),
	CHILD("Child');
	      
	private String category;
	      
	AgeCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public boolean equals(String category) {
		if (this.category==category)
			return true;
		return false;
	}
}
