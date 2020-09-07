package models;

import java.time.LocalDate;

public class Employee extends Person {
	private double yearsOfService;

	public Employee() {
		super();
		this.yearsOfService = 0;
	}

	public Employee(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active, double yearsOfService) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active);
		this.yearsOfService = yearsOfService;
	}

	public Employee(Person person, double yearsOfService) {
		super(person.getLBO(), person.getUserName(), person.getPassword(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getAddress(), person.getDateOfBirth(), person.getGender(),
				person.isActive());
		this.yearsOfService = yearsOfService;
	}

	public double getYearsOfService() {
		return this.yearsOfService;
	}

	public void setYearsOfService(double yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public String toString() {
		return super.toString() + "," + String.valueOf(yearsOfService);
	}

	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String adress, LocalDate dateOfBirth, String gender, boolean active,
			double yearsOfService) {
		super.updateInfo(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
		this.yearsOfService = yearsOfService;
	}

}
