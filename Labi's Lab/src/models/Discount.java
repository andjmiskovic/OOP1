package models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import functions.GeneralFunctions;

public class Discount extends IDObject {
	private double value;
	private LocalDate startDate, endDate;
	private ArrayList<DayOfWeek> daysOfDiscount;

	public Discount() {
	}

	public Discount(String id, double value, ArrayList<DayOfWeek> daysOfDiscount, LocalDate startDate,
			LocalDate endDate) {
		super(id);
		this.daysOfDiscount = daysOfDiscount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.setValue(value);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<DayOfWeek> getDaysOfDiscount() {
		return daysOfDiscount;
	}

	public void setDaysOfDiscount(ArrayList<DayOfWeek> daysOfDiscount) {
		this.daysOfDiscount = daysOfDiscount;
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

	@Override
	public String toString() {
		return super.toString() + ',' + String.valueOf(value) + ','
				+ GeneralFunctions.daysOfWeekToString(daysOfDiscount) + ','
				+ GeneralFunctions.localDateToString(startDate) + ',' + GeneralFunctions.localDateToString(endDate)
				+ '\n';
	}
}
