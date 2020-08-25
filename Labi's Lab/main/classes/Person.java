package classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Person {

	private String LBO, userName, password, name, lastName, phoneNumber, address, gender;
	private LocalDate dateOfBirth;
	private boolean active;

	public Person(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active) {
		this.LBO = LBO;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
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
		this.address = "";
		this.dateOfBirth = null;
		this.gender = null;
		this.active = true;
	}

	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, boolean active) {
		this.setLBO(LBO);
		this.setUserName(userName);
		this.setPassword(password);
		this.setName(name);
		this.setLastName(lastName);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
		this.setDateOfBirth(dateOfBirth);
		this.setGender(gender);
		this.setActive(active);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public int getAge() {
		if (dateOfBirth != null)
			return Period.between(dateOfBirth, LocalDate.now()).getYears();
		return 0;
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
	
	public String localDateToString(LocalDate date) {
		if (date!=null) 
			return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		return " ";
	}

	@Override
	public String toString() {
		return LBO + "," + userName + "," + password + "," + name + "," + lastName + "," + phoneNumber + "," + address
				+ "," + localDateToString(dateOfBirth) + "," + gender + ","
				+ String.valueOf(active);
	}

}
