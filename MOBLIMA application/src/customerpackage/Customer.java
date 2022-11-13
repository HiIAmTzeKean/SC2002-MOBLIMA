package customerpackage;

import java.io.Serializable;

import agepackage.Age;

/**
 * Customer object that stores an instance of customer
 * 
 * @author Ng Tze Kean
 * @since 06-11-2022
 */
public class Customer implements Serializable {
	private int id;
	private String name;
	private boolean isStudent;
	private int mobile;
	private String email;
	private Age age;

	public Customer(String name, int mobile, String email, Age age) {
		if (name==null || name.equals("") ||
			mobile < 10000000 || mobile > 99999999 ||
			email ==null || email.equals("") ||
			age == null) throw new IllegalArgumentException("Inavlid parameters");

		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
		this.id = email.hashCode();
	}

	public Customer(String name, int mobile, String email, Age age, boolean isStudent) {
		this(name, mobile, email, age);
		this.isStudent = isStudent;
	}

	/**
	 * Getter for the name of the customer
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the hashcode of the email.
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * Setter for name attribute
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for mobile number attribute
	 * 
	 * @return
	 */
	public int getMobile() {
		return mobile;
	}

	/**
	 * Setter for mobile number attribute
	 * 
	 * @param mobile
	 */
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	/**
	 * Getter for email attribute
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email attribute
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the age object composited in customer class either for modification
	 * or
	 * checking
	 * 
	 * @return Age object
	 */
	public Age getAge() {
		return age;
	}

	/**
	 * Sets the age object for customer class. Either this method or setting the
	 * numeric value of age is
	 * allowed to change the age of customer.
	 * 
	 * @param age
	 */
	public void setAge(Age age) {
		this.age = age;
	}

	/**
	 * Sets the numeric age in the age object compositied in customer class
	 * 
	 * @param ageNumeric int value
	 */
	public void setNumericAge(int ageNumeric) {
		age.setAgeNumber(ageNumeric);
	}

	/**
	 * Sets the student flag
	 * 
	 */
	public void setStudent() {
		isStudent = true;
	}

	/**
	 * Unsets the studnt flag
	 * 
	 */
	public void unsetStudent() {
		isStudent = false;
	}

	/**
	 * Returns boolean flag is the customer is a student
	 * 
	 * @return
	 */
	public boolean isStudent() {
		return isStudent;
	}

	/**
	 * Returns the multiplier according to the type of customer.
	 * Depends on the age of the customer and if the customer is a student.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public float getMultiplier() throws IllegalArgumentException {
		if (age == null)
			throw new IllegalArgumentException("Age not set");
		return isStudent ? age.getAgeMultiplier() * 0.7f : age.getAgeMultiplier();
	}
}
