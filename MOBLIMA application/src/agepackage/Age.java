package agepackage;
public class Age implements IAge {

	
	private int ageNumber;
	private AgeCategory category;
	      
	public void setAgeNumber(int ageNumber){
		this.ageNumber = ageNumber;
	}

	public int getAgeNumber(){
		return this.ageNumber;
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
