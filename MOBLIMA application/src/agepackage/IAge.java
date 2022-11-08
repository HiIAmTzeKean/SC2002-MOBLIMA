package agepackage;

/**
 * Superclass interface for age handling
 * @author Gambhir Dhruv
 * @since 08-11-2022
 */
public interface IAge {
	/**
	* Sets age category according to ageNumber
	* SENIOR if ageNumber>=60, CHILD if ageNumber<=13, ADULT for rest
	* @return
	*/
	void setAgeCategory();
	/**
	 * Setter for age number. Calls the private method setAgeCategory to update
	 * the category
	 * @param ageNumber
	 */
	void setAgeNumber(int ageNumber);
	/**
	 * Getter to obtain age number
	 * @return age number
	 */
	int getAgeNumber();
	/**
	 * Getter to obtain the enum set in Age class.
	 * @return AgeCategory enum
	 */
	AgeCategory getAgeCategory();
	/**
	 * Function to be used for calculation of the final price of the movie ticket booking
	 * @return Multiplier depending on AgeCategory
	 */
	float getAgeMultiplier();
}
