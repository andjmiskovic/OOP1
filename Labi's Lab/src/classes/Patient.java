package classes;

import java.time.LocalDate;
import java.util.Map;

import classes.Specialization;

public class Patient extends Person {
	private Map<Specialization, Boolean> discountedGroups;

	public Patient() {
		super();
	}

	public Patient(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active);
	}

	public Patient(Person person) {
		super(person.getLBO(), person.getUserName(), person.getPassword(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getAddress(), person.getDateOfBirth(), person.getGender(),
				person.isActive());
	}
	
	
	public Map<Specialization, Boolean> getDiscountedGroups() {
		return discountedGroups;
	}

	public void setDiscountedGroups(Map<Specialization, Boolean> discountedGroups) {
		this.discountedGroups = discountedGroups;
	}

	public String toString() {
		return "patient," + super.toString() + '\n';
	}
	
	@Override
	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, boolean active) {
		super.updateInfo(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active);
	}
}
