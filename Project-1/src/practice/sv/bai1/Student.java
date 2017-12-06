package practice.sv.bai1;

public class Student {
	String lastName, firstName, birthDay, email;

	public Student() {}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student(String lastName, String firstName, String birthDay, String email) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDay = birthDay;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [lastName=" + lastName + ", firstName=" + firstName + ", birthDay=" + birthDay + ", email="
				+ email + "]";
	}
	
	
}
