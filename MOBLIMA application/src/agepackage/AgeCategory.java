package agepackage;
public enum AgeCategory {
	SENIOR,
	ADULT,
	CHILD;
	      
	private AgeCategory category;
	      
	public void setCategory(int age) {
		if(age>=60)
			this.category = AgeCategory.SENIOR;
		else if(age<=12)
			this.category = AgeCategory.CHILD;
		else
			this.category = AgeCategory.ADULT;
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
