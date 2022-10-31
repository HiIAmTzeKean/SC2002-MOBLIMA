package agepackage;

public interface IAge {

	/**
	 * Sets the age number
	 * Sets age category according to age. If age>=60, category is Senior; if age<=13, category is Child; for rest category is Adult
	 * @param ageNumber
	 */
	void setAge(int ageNumber);
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
