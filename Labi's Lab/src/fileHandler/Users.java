package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import functions.GeneralFunctions;
import models.Admin;
import models.Chemist;
import models.Employee;
import models.MedicalTechnicial;
import models.Patient;
import models.Person;
import models.Qualification;
import models.Specialization;

public class Users {
	public static ArrayList<Patient> listOfPatients;
	public static ArrayList<MedicalTechnicial> listOfMedicalTechnicials;
	public static ArrayList<Chemist> listofChemists;
	public static ArrayList<Admin> listOfAdmins;
	public static ArrayList<Employee> listOfEmployees;
	public static String fileName = "src/files/users.csv";

	public static void readUsersFromCSV() {
		listOfPatients = new ArrayList<Patient>();
		listOfMedicalTechnicials = new ArrayList<MedicalTechnicial>();
		listofChemists = new ArrayList<Chemist>();
		listOfAdmins = new ArrayList<Admin>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				String role = attributes[0];
				Patient patient = createPatient(attributes);
				if (role.equals("patient")) {
					listOfPatients.add(patient);
				} else {
					Employee employee = createEmployee(patient, attributes);
					if (role.equals("admin")) {
						Admin admin = createAdmin(employee, attributes);
						listOfAdmins.add(admin);
					} else if (role.equals("chemist")) {
						Chemist chemist = createChemist(employee, attributes);
						listofChemists.add(chemist);
					} else if (role.equals("technicial")) {
						MedicalTechnicial technicial = createMedicalTechnicial(employee, attributes);
						listOfMedicalTechnicials.add(technicial);
					}
				}
				line = br.readLine();
			}
			listOfEmployees = getEmployees();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static Employee createEmployee(Patient user, String[] metadata) {
		double yeardOfService = Double.parseDouble(metadata[11].trim());
		return new Employee((Person) user, yeardOfService);
	}

	private static Admin createAdmin(Employee employee, String[] metadata) {
		return new Admin(employee);
	}

	private static Patient createPatient(String[] metadata) {
		String LBO = metadata[1].trim();
		String userName = metadata[2].trim();
		String password = metadata[3].trim();
		String name = metadata[4].trim();
		String lastName = metadata[5].trim();
		String phoneNumber = metadata[6].trim();
		String adress = metadata[7].trim();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String date = metadata[8].trim();
		LocalDate dateOfBirth = null;
		if (!date.equals(""))
			dateOfBirth = LocalDate.parse(date, formatter);
		String gender = metadata[9].trim();
		boolean active = Boolean.parseBoolean(metadata[10].trim());
		return new Patient(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
	}

	private static Chemist createChemist(Employee employee, String[] metadata) {
		Qualification qualification = Qualification.valueOf(metadata[12].trim());
		ArrayList<Specialization> arrayListOfSpecializations = new ArrayList<Specialization>();
		String[] listOfSpecializations = metadata[13].trim().split("\\|");
		try {
			for (String specialization : listOfSpecializations)
				arrayListOfSpecializations.add(Specialization.fromString(specialization.trim()));
			return new Chemist(employee, qualification, arrayListOfSpecializations);
		} catch (Exception e) {
			return new Chemist(employee, qualification, new ArrayList<Specialization>());
		}
	}

	private static MedicalTechnicial createMedicalTechnicial(Employee employee, String[] metadata) {
		Qualification qualification = Qualification.valueOf(metadata[12].trim());
		return new MedicalTechnicial(employee, qualification);
	}

	public static void saveData() {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e4) {
			e4.printStackTrace();
		}
		try {
			for (Patient p : listOfPatients) {
				csvWriter.append(p.toString());
			}
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			for (Admin a : listOfAdmins) {
				csvWriter.append(a.toString());
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			for (MedicalTechnicial m : listOfMedicalTechnicials) {
				csvWriter.append(m.toString());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			for (Chemist c : listofChemists) {
				csvWriter.append(c.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			csvWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		listOfEmployees = getEmployees();
	}

	public static Patient getPatientByUsername(String username) {
		for (Patient patient : listOfPatients) {
			if (patient.getUserName().equals(username)) {
				return patient;
			}
		}
		return null;
	}

	public static Patient getPatientByLBO(String LBO) {
		for (Patient patient : listOfPatients) {
			if (patient.getLBO().equals(LBO)) {
				return patient;
			}
		}
		return null;
	}

	public static Admin getAdminByUsername(String username) {
		for (Admin admin : listOfAdmins) {
			if (admin.getUserName().equals(username)) {
				return admin;
			}
		}
		return null;
	}

	public static MedicalTechnicial getMedicalTechnicialByUsername(String username) {
		for (MedicalTechnicial medicalTechnicial : listOfMedicalTechnicials) {
			if (medicalTechnicial.getUserName().equals(username)) {
				return medicalTechnicial;
			}
		}
		return null;
	}

	public static Chemist getChemistByUsername(String username) {
		for (Chemist chemist : listofChemists) {
			if (chemist.getUserName().equals(username)) {
				return chemist;
			}
		}
		return null;
	}

	public static void addAdmin(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService) {
		listOfAdmins.add(new Admin(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender,
				true, yearsOfService));
		saveData();
	}

	public static void addPatient(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender) {
		listOfPatients.add(
				new Patient(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender, true));
		saveData();
	}

	public static void addMedicalTechnicial(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService,
			Qualification qualification) {
		listOfMedicalTechnicials.add(new MedicalTechnicial(LBO, username, password, name, lastName, phoneNumber,
				address, dateOfBirth, gender, true, yearsOfService, qualification));
		saveData();
	}

	public static void addChemist(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService,
			Qualification qualification, ArrayList<Specialization> listOfSpecializations) {
		listofChemists.add(new Chemist(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth,
				gender, true, yearsOfService, qualification, listOfSpecializations));
		saveData();
	}

	public static void removeAdmin(String username) {
		Admin admin = getAdminByUsername(username);
		listOfAdmins.remove(admin);
		saveData();
	}

	public static void removePatient(String username) {
		Patient patient = getPatientByUsername(username);
		listOfPatients.remove(patient);
		saveData();
	}

	public static void removeChemist(String username) {
		Chemist chemist = getChemistByUsername(username);
		listofChemists.remove(chemist);
		saveData();
	}

	public static void removeMedicalTechnicial(String username) {
		MedicalTechnicial medicalTechnicial = getMedicalTechnicialByUsername(username);
		listOfMedicalTechnicials.remove(medicalTechnicial);
		saveData();
	}

	public static void editAdmin(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService) {
		removeAdmin(username);
		addAdmin(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender, yearsOfService);
		saveData();
	}

	public static void editPatient(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender) {
		removePatient(username);
		addPatient(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender);
		saveData();
	}

	public static void editChemist(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService,
			Qualification qualification, ArrayList<Specialization> listOfSpecializations) {
		removeChemist(username);
		addChemist(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender, yearsOfService,
				qualification, listOfSpecializations);
		saveData();
	}

	public static void editMedicalTechnicial(String LBO, String username, String password, String name, String lastName,
			String phoneNumber, String address, LocalDate dateOfBirth, String gender, double yearsOfService,
			Qualification qualification) {
		removeMedicalTechnicial(username);
		addMedicalTechnicial(LBO, username, password, name, lastName, phoneNumber, address, dateOfBirth, gender,
				yearsOfService, qualification);
		saveData();
	}

	public static ArrayList<Employee> getEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.addAll(listOfMedicalTechnicials);
		employees.addAll(listofChemists);
		employees.addAll(listOfAdmins);
		return employees;
	}

	public static Employee getEmployeeByUserName(String username) {
		for (Employee employee : getEmployees()) {
			if (employee.getUserName().equals(username)) {
				return employee;
			}
		}
		return null;
	}

	public static Employee getEmployeeByUserName(String role, String username) {
		switch (role.toLowerCase()) {
		case "admin":
			return getAdminByUsername(username);
		case "chemist":
			return getChemistByUsername(username);
		case "medicaltechnicial":
			return getMedicalTechnicialByUsername(username);
		default:
			break;
		}
		return null;
	}

	public static void removeEmployee(String role, String username) {
		switch (role.toLowerCase()) {
		case "admin":
			removeAdmin(username);
			break;
		case "chemist":
			removeChemist(username);
			break;
		case "medicaltechnicial":
			removeMedicalTechnicial(username);
			break;
		default:
			break;
		}
	}

	public static String newPatientUserName() {
		return "newPatient" + String.valueOf(listOfPatients.size());
	}

	public static Map<String, Double> incomesPerEmployeeOccupation(LocalDate start, LocalDate end,
			ArrayList<DayOfWeek> daysOfWeeks) {
		double adminCount = 0;
		double medicalCount = 0;
		double chemistCount = 0;
		Map<String, Double> map = new HashMap<String, Double>();
		int dayCount = GeneralFunctions.countDaysFromTo(start, end, daysOfWeeks);
		if (dayCount > 0) {
			for (Admin admin : listOfAdmins)
				adminCount += SalaryCoefficients.getSalary(admin, start, end);
			for (MedicalTechnicial medicalTechnicial : listOfMedicalTechnicials)
				medicalCount += SalaryCoefficients.getSalary(medicalTechnicial, start, end);
			for (Chemist chemist : listofChemists)
				chemistCount += SalaryCoefficients.getSalary(chemist, start, end);
			map.put("Admin", adminCount / dayCount);
			map.put("Medical Technicial", medicalCount / dayCount);
			map.put("Chemist", chemistCount / dayCount);
		} else {
			map.put("Admin", 0.0);
			map.put("Medical Technicial", 0.0);
			map.put("Chemist", 0.0);
		}
		return map;
	}

}
