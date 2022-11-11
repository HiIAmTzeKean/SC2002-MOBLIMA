package agepackage;
import java.io.Serializable;

/**
 * Age object that stores an instance of Age details
 * Object is composited under customer class
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Age implements Serializable, IAge {
	private int ageNumber;
	private AgeCategory category;
	/**
	 * Constructor for Age class. Passes an integer in and age category will be set
	 * within constructor.
	 * @param ageNumber
	 */
	public Age(int ageNumber) {
		this.ageNumber = ageNumber;
		setAgeCategory();
	}
	/**
	 * Private method to be called within Age class. Sets the age category according to
	 * age input
	 */
	private void setAgeCategory(){
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
	@Override
	public void setAgeNumber(int ageNumber){
		this.ageNumber=ageNumber;
		setAgeCategory();
	}
	@Override
	public int getAgeNumber(){
		return this.ageNumber;
	}
	@Override
	public AgeCategory getAgeCategory(){
		return this.category;
	}
	@Override
	public float getAgeMultiplier() {
		if(this.category == AgeCategory.SENIOR){
			return (float) 0.5;
		}
		else if(this.category == AgeCategory.CHILD){
			return (float) 0.7;
		}
		else{
			return 1;
		}
	}
}
