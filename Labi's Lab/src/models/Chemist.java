package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import fileHandler.AllAnalysisRequests;
import functions.GeneralFunctions;

public class Chemist extends Employee {
	private ArrayList<Specialization> listOfSpecializations;
	private Qualification qualification;

	public Chemist() {
		super();
		this.listOfSpecializations = new ArrayList<Specialization>();
		this.qualification = Qualification.One;
	}

	public Chemist(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active, double yearsOfService,
			Qualification qualification, ArrayList<Specialization> listOfSpecializations) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active,
				yearsOfService);
		this.qualification = qualification;
		this.listOfSpecializations = listOfSpecializations;
	}

	public Chemist(Employee employee, Qualification qualification, ArrayList<Specialization> listOfSpecializations) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAddress(), employee.getDateOfBirth(),
				employee.getGender(), employee.isActive(), employee.getYearsOfService());
		this.qualification = qualification;
		this.listOfSpecializations = listOfSpecializations;
	}

	public ArrayList<Specialization> getListOfSpecializations() {
		return listOfSpecializations;
	}

	public void setListOfSpecializations(ArrayList<Specialization> listOfSpecializations) {
		this.listOfSpecializations = listOfSpecializations;
	}

	public void addSpecialization(String specialization) {
		this.listOfSpecializations.add(Specialization.valueOf(specialization));
	}

	public int getNumberOfProcessedSamples(ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		return AllAnalysisRequests.getNumberOfProcessedSamplesByChemistInTime(this, days, start, end);
	}

	public String toString() {
		return "chemist," + super.toString() + "," + qualification.toString() + ','
				+ GeneralFunctions.specializationsToString(listOfSpecializations) + "\n";
	}

	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, boolean active,
			double yearsOfService, Qualification qualification, ArrayList<Specialization> listOfSpecializations) {
		super.updateInfo(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active,
				yearsOfService);
		this.qualification = qualification;
		this.listOfSpecializations = listOfSpecializations;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
}
