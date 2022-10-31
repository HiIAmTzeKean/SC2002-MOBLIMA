package customerpackage;
import agepackage.Age;

public class Customer {

	private String name;
	private int mobile;
	private String email;
	private Age age;

	public Customer(String name, int mobile, String email, Age age){
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
	}
	public String getName() {
		return name;
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
	public float getMultiplier(){
		return age.getAgeMultiplier();
	}

