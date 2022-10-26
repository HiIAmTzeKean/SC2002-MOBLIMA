package agepackage;

public class Age implements IAge {

	
	private int age = getAge();
	private AgeCategory category;
	      
	public float getMultiplier(int age) {
		if(age>=60){
			this.category = AgeCategory.SENIOR;
			return 0.7;
		}
		else if(age<=12){
			this.category = AgeCategory.CHILD;
			return 0.5;
		}
		else{
			this.category = AgeCategory.ADULT;
			return 1;
		}
	}
	
	
	

}
