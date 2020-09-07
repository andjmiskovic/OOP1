package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import fileHandler.AllAnalysisRequests;

public class PatientStatistics {
	public Patient patient;
	public int numberOfRequests;
	public double totalPriceByTime;
	private LocalDate startDate, endDate;
	private ArrayList<DayOfWeek> days;
	private ArrayList<Specialization> specializations;

	public PatientStatistics() {
	}

	public PatientStatistics(Patient patient) {
		this.patient = patient;
		this.numberOfRequests = 0;
		this.totalPriceByTime = 0;
		this.startDate = null;
		this.endDate = null;
		this.days = new ArrayList<DayOfWeek>();
		this.specializations = new ArrayList<Specialization>();
	}

	public int getNumberOfRequests() {
		return AllAnalysisRequests.getNumberOfRequestsByPatientInTime(patient, days, startDate, endDate);
	}

	public double getTotalPriceByTime() {
		return AllAnalysisRequests.getTotalPriceOfRequestsByGroupByPatient(specializations, patient, days, startDate,
				endDate);
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

	public ArrayList<Specialization> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(ArrayList<Specialization> specializations) {
		this.specializations = specializations;
	}

	public void update(LocalDate start, LocalDate end, ArrayList<DayOfWeek> daysOfWeeks,
			ArrayList<Specialization> selectedSpecialization) {
		this.startDate = start;
		this.endDate = end;
		this.days = daysOfWeeks;
		this.specializations = selectedSpecialization;
	}
}
