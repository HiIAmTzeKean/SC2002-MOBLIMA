package agepackage;
import java.io.Serializable;

/**
 * Age class that stores the age of customer
 * @apiNote IAge
 * @author Gambhir Dhruv
 * @since 08-11-2022
 */

public class Age implements Serializable, IAge {
	private int ageNumber;
	private AgeCategory category;
	public Age(int ageNumber) {
		this.ageNumber = ageNumber;
		setAgeCategory();
	}
	      
	public void setAgeCategory(){
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
	public void setAgeNumber(int ageNumber){
		this.ageNumber=ageNumber;
		setAgeCategory();
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
