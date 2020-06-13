package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {

	private String LBO;
	private String userName;
	private String password;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String adress;
	private String gender;
	private LocalDate dateOfBirth;
	private boolean active;

	public Person(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String adress, LocalDate dateOfBirth, String gender, boolean active) {
		this.LBO = LBO;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.active = active;
	}

	public Person() {
		this.LBO = "";
		this.userName = "";
		this.password = "";
		this.name = "";
		this.lastName = "";
		this.phoneNumber = "";
		this.adress = "";
		this.dateOfBirth = null;
		this.gender = null;
		this.active = true;
	}

	public String getLBO() {
		return LBO;
	}

	public void setLBO(String lBO) {
		LBO = lBO;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return LBO + "," + userName + "," + password + "," + name + "," + lastName + "," + phoneNumber + "," + adress
				+ "," + dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yy.")) + "," + gender + ","
				+ String.valueOf(active);
	}

	public static String toString(Person person) {
		return person.getLBO() + "," + person.getUserName() + "," + person.getPassword() + "," + person.getName() + ","
				+ person.getLastName() + "," + person.getPhoneNumber() + "," + person.getAdress() + ","
				+ person.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yy.")) + "," + person.getGender()
				+ "," + String.valueOf(person.isActive());
	}

}
