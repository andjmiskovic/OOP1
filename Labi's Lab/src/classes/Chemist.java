package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Chemist extends Employee{
	private ArrayList<String> listOfSpecializations; 
	private int numberOfFinishedReports;
	
	public Chemist() {
		super();
		this.listOfSpecializations = new ArrayList<String>();
		this.numberOfFinishedReports = 0;
	}
		
	public Chemist(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
					String adress, LocalDate dateOfBirth, String gender, boolean active,
					double celery, double bonus, LocalDate start, ArrayList<String> listOfSpecializations, int numberOfFinishedReports){
		super(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active, celery, bonus, start);
		this.listOfSpecializations = listOfSpecializations;
		this.setNumberOfFinishedReports(numberOfFinishedReports);
	}
	
	public Chemist(Employee employee, ArrayList<String> listOfSpecializations, int numberOfFinishedReports) {
		super(employee.getLBO(), employee.getUserName(), employee.getPassword(), employee.getName(),
			employee.getLastName(), employee.getPhoneNumber(), employee.getAdress(), employee.getDateOfBirth(), 
			employee.getGender(), employee.isActive(), employee.getCelery(), employee.getBonus(), employee.getStart());
		this.listOfSpecializations = listOfSpecializations;
		this.setNumberOfFinishedReports(numberOfFinishedReports);
	}

	public ArrayList<String> getListOfSpecializations() {
		return listOfSpecializations;
	}

	public void setListOfSpecializations(ArrayList<String> listOfSpecializations) {
		this.listOfSpecializations = listOfSpecializations;
	}
	
	public void addSpecialization(String specialization) {
		this.listOfSpecializations.add(specialization);
	}

	public int getNumberOfFinishedReports() {
		return numberOfFinishedReports;
	}

	public void setNumberOfFinishedReports(int numberOfFinishedReports) {
		this.numberOfFinishedReports = numberOfFinishedReports;
	}
	
	public void incrementNumberOfFinishedReports() {
		this.numberOfFinishedReports ++;
	}
	
	public String toString() {
		String StringFromListOfSpecializations = "";
		if (listOfSpecializations.size() > 0) {
			StringFromListOfSpecializations = listOfSpecializations.get(0);
			for(int i=1; i< listOfSpecializations.size(); i++) {
				StringFromListOfSpecializations += "|" + listOfSpecializations.get(i);
			}
		}
		return "chemist," + super.toString() + "," + StringFromListOfSpecializations + "," + String.valueOf(numberOfFinishedReports) + "\n";
	}
}


