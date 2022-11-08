package agepackage;

/**
 * Superclass interface for age handling
 * @author Gambhir Dhruv
 * @since 08-11-2022
 */
public interface IAge {
	/**
	*Sets age category according to ageNumber
	*SENIOR if ageNumber>=60, CHILD if ageNumber<=13, ADULT for rest
	*/
	setAgeCategory()
	/**
	 * Sets the age number
	 * Calls setAgeCatergory()
	 * @param ageNumber
	 */
	void setAgeNumber(int ageNumber);
	/**
	 * @return age number
	 */
	int getAgeNumber();
	/**
	 * @return age category {SENIOR, ADULT, CHILD}
	 */
	AgeCategory getAgeCategory();
	/**
	 * @return Multiplier according to discout. 0.7 for seniors, 0.5 for children and 1 for adult
	 */
	float getAgeMultiplier();
}
