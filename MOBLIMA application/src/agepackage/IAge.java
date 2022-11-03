package agepackage;

public interface IAge {

	/**
	 * Sets the age number
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
