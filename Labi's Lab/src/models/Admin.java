package models;

import java.time.LocalDate;

public class Admin extends Employee {

	public Admin() {
		super();
	}

	public Admin(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active,
			double yearsOfService) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active,
				yearsOfService);
	}

	public Admin(Employee employee) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAddress(), employee.getDateOfBirth(),
				employee.getGender(), employee.isActive(), 
				employee.getYearsOfService());
	}

	public String toString() {
		return "admin," + super.toString() + '\n';
	}
}
