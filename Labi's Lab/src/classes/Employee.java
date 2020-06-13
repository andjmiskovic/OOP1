package classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee extends Person {
	private double celery, bonus;
	private int yearsOfService;
	private LocalDate start;

	public Employee() {
		super();
		this.celery = 0;
		this.bonus = 0;
		this.start = LocalDate.now();
		this.yearsOfService = 0;
	}

	public Employee(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String adress, LocalDate dateOfBirth, String gender, boolean active, double celery, double bonus,
			LocalDate start) {
		super(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
		this.celery = celery;
		this.bonus = bonus;
		this.start = start;
	}

	public Employee(Person person, double celery, double bonus, LocalDate start) {
		super(person.getLBO(), person.getUserName(), person.getPassword(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getAdress(), person.getDateOfBirth(), person.getGender(),
				person.isActive());
		this.celery = celery;
		this.bonus = bonus;
		this.start = start;
	}

	public double getCelery() {
		return celery;
	}

	public void setCelery(double celery) {
		this.celery = celery;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public int getYearsOfService() {
		Period period = Period.between(this.start, LocalDate.now());
		this.yearsOfService = period.getYears();
		return this.yearsOfService;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public String toString() {
		return "employee," + super.toString() + "," + String.valueOf(celery) + "," + String.valueOf(bonus) +
				"," + String.valueOf(yearsOfService) + ","+ start.format(DateTimeFormatter.ofPattern("dd.MM.yy.")) + "\n";
	}	

}
