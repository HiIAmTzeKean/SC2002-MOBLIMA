package agepackage;
import customerpackage.Customer;
public class Age implements IAge {

	
	private int age;
	private AgeCategory category;
	      
	public float getMultiplier() {
		age = getAge();
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
