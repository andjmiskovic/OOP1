package classes;

import java.time.LocalDate;

import javax.lang.model.element.VariableElement;

public class Admin extends Employee {

	public Admin() {
		super();
	}

	public Admin(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String adress, LocalDate dateOfBirth, String gender, boolean active, double celery, double bonus,
			LocalDate start) {
		super(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active, celery, bonus,
				start);
	}

	public Admin(Employee employee) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAdress(), employee.getDateOfBirth(), 
				employee.getGender(), employee.isActive(), employee.getCelery(), employee.getBonus(), employee.getStart());
	}
	
	public String toString() {
		return super.toString();
	}
}
