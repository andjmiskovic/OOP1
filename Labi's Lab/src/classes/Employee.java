package classes;

import java.time.LocalDate;

public class Employee extends Person {
	private double salary, bonus, yearsOfService;
	private Qualification qualification;

	public Employee() {
		super();
		this.salary = 0;
		this.bonus = 0;
		this.yearsOfService = 0;
	}

	public Employee(String LBO, String userName, String password, String name, String lastName, String phoneNumber,
			String address, LocalDate dateOfBirth, String gender, boolean active, double salary, double bonus,
			double yearsOfService, Qualification qualification) {
		super(LBO, userName, password, name, lastName, phoneNumber, address, dateOfBirth, gender, active);
		this.salary = salary;
		this.bonus = bonus;
		this.yearsOfService = yearsOfService;
		this.qualification = qualification;
	}

	public Employee(Person person, double salary, double bonus, double yearsOfService, Qualification qualification) {
		super(person.getLBO(), person.getUserName(), person.getPassword(), person.getName(), person.getLastName(),
				person.getPhoneNumber(), person.getAddress(), person.getDateOfBirth(), person.getGender(),
				person.isActive());
		this.salary = salary;
		this.bonus = bonus;
		this.yearsOfService = yearsOfService;
		this.qualification = qualification;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getYearsOfService() {
		return this.yearsOfService;
	}

	public void setYearsOfService(double yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public String toString() {
		return super.toString() + "," + String.valueOf(salary) + "," + String.valueOf(bonus) + ","
				+ String.valueOf(yearsOfService) + "," + qualification.toString();
	}

	public void updateInfo(String LBO, String userName, String password, String name, String lastName,
			String phoneNumber, String adress, LocalDate dateOfBirth, String gender, boolean active, double celery,
			double bonus, double yearsOfService, Qualification qualification) {
		super.updateInfo(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
		this.salary = celery;
		this.bonus = bonus;
		this.yearsOfService = yearsOfService;
		this.qualification = qualification;
	}

}
