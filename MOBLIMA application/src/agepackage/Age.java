package agepackage;
import java.io.Serializable;

public class Age implements Serializable, IAge {

	
	private int ageNumber;
	private AgeCategory category;
	      
	public void setAge(int ageNumber){
		this.ageNumber = ageNumber;
		
		if(this.ageNumber>=60){
			this.category = AgeCategory.SENIOR;
		}
		else if(ageNumber<=12){
			this.category = AgeCategory.CHILD;
		}
		else{
			this.category = AgeCategory.ADULT;
		}
		
	}

	public int getAgeNumber(){
		return this.ageNumber;
	}

	public AgeCategory getAgeCategory(){
		return this.category;
	}
	public float getAgeMultiplier() {
		if(this.category == AgeCategory.SENIOR){
			return (float) 0.7;
		}
		else if(this.category == AgeCategory.CHILD){
			return (float) 0.5;
		}
		else{
			return 1;
		}
	}
}
