package classes;

import java.time.LocalDate;

public class Patient extends Person{
		
	public Patient() {
		super();
	}
	
	public Patient(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
					String adress, LocalDate dateOfBirth, String gender, boolean active) {
		super(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
	}
	
	public Patient(Person person) {
		super(person.getLBO(), person.getUserName(), person.getPassword(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getAdress(), person.getDateOfBirth(), person.getGender(),
				person.isActive());
	}
	
	public String toString() {
		return super.toString();
	}
}
