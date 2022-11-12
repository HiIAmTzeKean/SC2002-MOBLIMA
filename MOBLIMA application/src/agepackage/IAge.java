package agepackage;

/**
 * Interface for age class. Used in customer object
 * 
 * @author Gambhir Dhruv
 * @since 08-11-2022
 */
public interface IAge {
	/**
	 * Setter for age number. Calls the private method setAgeCategory to update
	 * the category
	 * 
	 * @param ageNumber
	 */
	void setAgeNumber(int ageNumber);

	/**
	 * Getter to obtain age number
	 * 
	 * @return age number
	 */
	int getAgeNumber();

	/**
	 * Getter to obtain the enum set in Age class.
	 * 
	 * @return AgeCategory enum
	 */
	AgeCategory getAgeCategory();

	/**
	 * Function to be used for calculation of the final price of the movie ticket
	 * booking
	 * 
	 * @return Multiplier depending on AgeCategory
	 */
	float getAgeMultiplier();
}
