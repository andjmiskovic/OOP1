package models;

import java.util.Map;

import functions.GeneralFunctions;

public class SalaryCoefficient {
	private double base, homeVisit, homeVisitWithTime, specializationBonus, homeVisitBonus, homeVisitWithTimeBonus,
			holidayBonus, processedSampleBonus;
	private Map<Qualification, Double> qualificationCoefficients;

	public SalaryCoefficient() {
	}

	public SalaryCoefficient(double base, double homeVisit, double homeVisitWithTime, double specializationBonus,
			double homeVisitBonus, double homeVisitWithTimeBonus, double holidayBonus, double processedSampleBonus,
			Map<Qualification, Double> qualificationCoefficients) {
		this.base = base;
		this.homeVisit = homeVisit;
		this.homeVisitWithTime = homeVisitWithTime;
		this.qualificationCoefficients = qualificationCoefficients;
		this.processedSampleBonus = processedSampleBonus;
		this.specializationBonus = specializationBonus;
		this.homeVisitBonus = homeVisitBonus;
		this.homeVisitWithTimeBonus = homeVisitWithTimeBonus;
		this.holidayBonus = holidayBonus;
	}

	public double getBase() {
		return base;
	}

	public double getSpecializationBonus() {
		return specializationBonus;
	}

	public void setSpecializationBonus(double specializationBonus) {
		this.specializationBonus = specializationBonus;
	}

	public double getHomeVisitBonus() {
		return homeVisitBonus;
	}

	public void setHomeVisitBonus(double homeVisitBonus) {
		this.homeVisitBonus = homeVisitBonus;
	}

	public double getHomeVisitWithTimeBonus() {
		return homeVisitWithTimeBonus;
	}

	public void setHomeVisitWithTimeBonus(double homeVisitWithTimeBonus) {
		this.homeVisitWithTimeBonus = homeVisitWithTimeBonus;
	}

	public double getHolidayBonus() {
		return holidayBonus;
	}

	public void setHolidayBonus(double holidayBonus) {
		this.holidayBonus = holidayBonus;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getHomeVisit() {
		return homeVisit;
	}

	public void setHomeVisit(double homeVisit) {
		this.homeVisit = homeVisit;
	}

	public double getHomeVisitWithTime() {
		return homeVisitWithTime;
	}

	public void setHomeVisitWithTime(double homeVisitWithTime) {
		this.homeVisitWithTime = homeVisitWithTime;
	}

	public Map<Qualification, Double> getQualificationCoefficients() {
		return qualificationCoefficients;
	}

	public void setQualificationCoefficients(Map<Qualification, Double> qualificationCoefficients) {
		this.qualificationCoefficients = qualificationCoefficients;
	}

	@Override
	public String toString() {
		return String.valueOf(this.base) + ',' + String.valueOf(this.homeVisit) + ','
				+ String.valueOf(this.homeVisitWithTime) + ',' + String.valueOf(this.specializationBonus) + ','
				+ String.valueOf(this.homeVisitBonus) + ',' + String.valueOf(this.homeVisitWithTimeBonus) + ','
				+ String.valueOf(this.holidayBonus) + ',' + String.valueOf(processedSampleBonus) + ','
				+ GeneralFunctions.qualificationCoefficientsToString(qualificationCoefficients);
	}

	public double getProcessedSampleBonus() {
		return processedSampleBonus;
	}

	public void setProcessedSampleBonus(double processedSampleBonus) {
		this.processedSampleBonus = processedSampleBonus;
	}

}
