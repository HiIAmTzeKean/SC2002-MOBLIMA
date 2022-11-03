package customerpackage;
import java.io.Serializable;

import agepackage.Age;

public class Customer implements Serializable{
	private int id;
	private String name;
	private int mobile;
	private String email;
	private Age age;

	public Customer(String name, int mobile, String email, Age age){
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
		this.id = email.hashCode();
	}
	public String getName() {
		return name;
	}
	public int getID(){
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	public float getMultiplier() throws IllegalArgumentException{
		if (age == null) throw new IllegalArgumentException("Age not set");
		return age.getAgeMultiplier();
	}
}
