package classes;

import java.time.LocalDate;

public class MedicalTechnicial extends Employee{
	private int numberOfHouseVisits, numberOfFinishedRequests;

	public MedicalTechnicial() {
		super();
		this.numberOfFinishedRequests = 0;
		this.numberOfHouseVisits = 0;
	}
		
	public MedicalTechnicial(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
    		String adress, LocalDate dateOfBirth, String gender, boolean active,
    		double celery, double bonus, LocalDate start, int numberOfFinishedRequests, int numberOfHouseVisits) {
		super(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active, celery, bonus, start);
		this.numberOfFinishedRequests = numberOfFinishedRequests;
		this.numberOfHouseVisits = numberOfHouseVisits;
	}
	
	public MedicalTechnicial(Employee employee, int numberOfFinishedRequests, int numberOfHouseVisits) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAdress(), employee.getDateOfBirth(), 
				employee.getGender(), employee.isActive(), employee.getCelery(), employee.getBonus(), employee.getStart());
		this.numberOfFinishedRequests = numberOfFinishedRequests;
		this.numberOfHouseVisits = numberOfHouseVisits;
	}

	public int getNumberOfHouseVisits() {
		return numberOfHouseVisits;
	}

	public void incrementNumberOfHouseVisits() {
		this.numberOfHouseVisits ++;
	}
	
	public void setNumberOfHouseVisits(int numberOfHouseVisits) {
		this.numberOfHouseVisits = numberOfHouseVisits;
	}

	public int getNumberOfFinishedRequests() {
		return numberOfFinishedRequests;
	}

	public void incrementNumberOfFinishedRequests() {
		this.numberOfFinishedRequests ++;
	}
	
	public void setNumberOfFinishedRequests(int numberOfFinishedRequests) {
		this.numberOfFinishedRequests = numberOfFinishedRequests;
	}
	
	public String toString() {
		return "technicial," + super.toString() + "," + String.valueOf(numberOfHouseVisits) + "," + String.valueOf(numberOfFinishedRequests) + "\n";
	}
}

