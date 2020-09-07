package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import fileHandler.AllAnalysisRequests;

public class EmployeeStatistics {
	public Employee employee;
	public int numberOfRequests;
	private LocalDate startDate, endDate;
	private ArrayList<DayOfWeek> days;

	public EmployeeStatistics() {
	}

	public EmployeeStatistics(Employee employee) {
		this.employee = employee;
		this.numberOfRequests = 0;
		this.startDate = null;
		this.endDate = null;
		this.days = new ArrayList<DayOfWeek>();
	}

	public int getNumberOfRequests() {
		if (employee instanceof MedicalTechnicial)
			return AllAnalysisRequests.getNumberOfHouseVisitsByTechnicialInTime((MedicalTechnicial) employee, days,
					startDate, endDate);
		else if (employee instanceof Chemist)
			return AllAnalysisRequests.getNumberOfProcessedSamplesByChemistInTime((Chemist) employee, days, startDate,
					endDate);
		return 0;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ArrayList<DayOfWeek> getDays() {
		return days;
	}

	public void setDays(ArrayList<DayOfWeek> days) {
		this.days = days;
	}

	public void update(LocalDate start, LocalDate end, ArrayList<DayOfWeek> daysOfWeeks) {
		this.startDate = start;
		this.endDate = end;
		this.days = daysOfWeeks;
	}

	public String getClassName() {
		if (employee instanceof MedicalTechnicial) {
			return "MedicalTechnicial";
		} else if (employee instanceof Chemist) {
			return "Chemist";
		} else {
			return "Admin";
		}
	}
}
