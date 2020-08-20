package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Chemist extends Employee {
	private ArrayList<Specialization> listOfSpecializations;
	private int numberOfFinishedReports;

	public Chemist() {
		super();
		this.listOfSpecializations = new ArrayList<Specialization>();
		this.numberOfFinishedReports = 0;
	}

	public Chemist(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active, double celery, double bonus,
			double yearsOfService, Qualification qualification, ArrayList<Specialization> listOfSpecializations, int numberOfFinishedReports) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active, celery, bonus,
				yearsOfService, qualification);
		this.listOfSpecializations = listOfSpecializations;
		this.setNumberOfFinishedReports(numberOfFinishedReports);
	}

	public Chemist(Employee employee, ArrayList<Specialization> listOfSpecializations, int numberOfFinishedReports) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
				employee.getLastName(), employee.getPhoneNumber(), employee.getAddress(), employee.getDateOfBirth(),
				employee.getGender(), employee.isActive(), employee.getCelery(), employee.getBonus(),
				employee.getYearsOfService(), employee.getQualification());
		this.listOfSpecializations = listOfSpecializations;
		this.setNumberOfFinishedReports(numberOfFinishedReports);
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

	public int getNumberOfFinishedReports() {
		return numberOfFinishedReports;
	}

	public void setNumberOfFinishedReports(int numberOfFinishedReports) {
		this.numberOfFinishedReports = numberOfFinishedReports;
	}

	public void incrementNumberOfFinishedReports() {
		this.numberOfFinishedReports++;
	}
	
	public String specializationsToString(ArrayList<Specialization> listOfSpecializations) {
		ArrayList<String> list = new ArrayList<String>();
		for (Specialization specialization : listOfSpecializations) {
			list.add(specialization.getName());
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public String toString() {
		return "chemist," + super.toString() + "," + specializationsToString(listOfSpecializations) + ","
				+ String.valueOf(numberOfFinishedReports) + "\n";
	}

	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, boolean active, double celery,
			double bonus, double yearsOfService, Qualification qualification, ArrayList<Specialization> listOfSpecializations, int numberOfFinishedReports) {
		super.updateInfo(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active,
				celery, bonus, yearsOfService, qualification);
		this.listOfSpecializations = listOfSpecializations;
		this.setNumberOfFinishedReports(numberOfFinishedReports);
	}
}
