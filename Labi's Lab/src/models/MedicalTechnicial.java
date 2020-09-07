package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import fileHandler.AllAnalysisRequests;

public class MedicalTechnicial extends Employee {
	private Qualification qualification;

	public MedicalTechnicial() {
		super();
		this.qualification = Qualification.One;
	}

	public MedicalTechnicial(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, boolean active,
			double yearsOfService, Qualification qualification) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active,
				yearsOfService);
		this.qualification = qualification;
	}

	public MedicalTechnicial(Employee employee, Qualification qualification) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAddress(), employee.getDateOfBirth(),
				employee.getGender(), employee.isActive(), employee.getYearsOfService());
		this.qualification = qualification;
	}

	public int getNumberOfHouseVisits(ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		return AllAnalysisRequests.getNumberOfHouseVisitsByTechnicialInTime(this, days, start, end);
	}

	public int getNumberOfHouseVisitsWithTime(ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		return AllAnalysisRequests.getNumberOfHouseVisitsWithTimeByTechnicialInTime(this, days, start, end);
	}

	public String toString() {
		return "technicial," + super.toString() + "," + qualification.toString() + "\n";
	}

	public void updateInfo(Employee employee, Qualification qualification) {
		super.updateInfo(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAddress(), employee.getDateOfBirth(),
				employee.getGender(), employee.isActive(), employee.getYearsOfService());
		this.qualification = qualification;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
}
