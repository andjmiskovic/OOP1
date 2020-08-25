package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Discount extends IDObject {
	private double value;
	private LocalDate startDate, endDate;
	private ArrayList<DayOfWeek> daysOfDiscount;

	public Discount() {
	}

	public Discount(String id,double value, ArrayList<DayOfWeek> daysOfDiscount,
			LocalDate startDate, LocalDate endDate) {
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
	
	public String daysOfWeekToString(ArrayList<DayOfWeek> days) {
		String s = "";
		for (DayOfWeek dayOfWeek : days) {
			s += dayOfWeek.getValue();
		}
		return s;
	}
	
	public String localDateToString(LocalDate date) {
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");
		return dateFormat.format(date);
	}

	@Override
	public String toString() {
		return super.toString() + ',' + String.valueOf(value) + ','
				+ daysOfWeekToString(daysOfDiscount) + ',' + localDateToString(startDate) + ',' + localDateToString(endDate);
	}
}
