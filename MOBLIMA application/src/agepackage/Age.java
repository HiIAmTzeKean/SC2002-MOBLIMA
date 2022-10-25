package agepackage;

public class Age implements IAge {

	
	private int age = getAge();
	private int multiplier;
	private AgeCategory category;
	      
	public void setCategory(int age) {
		if(age>=60)
			this.category = AgeCategory.SENIOR;
		else if(age<=12)
			this.category = AgeCategory.CHILD;
		else
			this.category = AgeCategory.ADULT;
	}
	
	
	

}
