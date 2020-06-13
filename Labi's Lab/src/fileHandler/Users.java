package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import classes.Admin;
import classes.Chemist;
import classes.Employee;
import classes.MedicalTechnicial;
import classes.Patient;
import classes.Person;

public class Users {
	public static ArrayList<Patient> listOfPatients;
	public static ArrayList<MedicalTechnicial> listOfMedicalTechnicials;
	public static ArrayList<Chemist> listofChemists;
	public static ArrayList<Admin> listOfAdmins;

	public static void readUsersFromCSV(String fileName) {
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
				Person user = createPerson(attributes);
				if (role.equals("patient")) {
					Patient patient = createPatient(user, attributes);
					listOfPatients.add(patient);
				}
				Employee employee = createEmployee(user, attributes);
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
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static Person createPerson(String[] metadata) {
		for (int i = 1; i < 11; i++) {
			metadata[i].trim();
		}
		String LBO = metadata[1];
		String userName = metadata[2];
		String password = metadata[3];
		String name = metadata[4];
		String lastName = metadata[5];
		String phoneNumber = metadata[6];
		String adress = metadata[7];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		String date = metadata[8];
		LocalDate dateOfBirth = LocalDate.parse(date, formatter);
		String gender = metadata[9];
		boolean active = Boolean.parseBoolean(metadata[10]);
		return new Person(LBO, userName, password, name, lastName, phoneNumber, adress, dateOfBirth, gender, active);
	}

	private static Employee createEmployee(Person user, String[] metadata) {
		for (int i = 11; i < 14; i++) {
			metadata[i].trim();
		}
		double celery = Double.parseDouble(metadata[10]);
		double bonus = Double.parseDouble(metadata[11]);
		String startDate = metadata[12];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		LocalDate startedWorking = LocalDate.parse(startDate, formatter);
		return new Employee(user, celery, bonus, startedWorking);
	}

	private static Admin createAdmin(Employee employee, String[] metadata) {
		return new Admin(employee);
	}

	private static Patient createPatient(Person user, String[] metadata) {
		return new Patient(user);
	}

	private static Chemist createChemist(Employee employee, String[] metadata) {
		for (int i = 14; i < 16; i++) {
			metadata[i].trim();
		}
		String[] listOfSpecializations = metadata[13].split("|");
		ArrayList<String> arrayListOfSpecializations = new ArrayList<String>();
		Collections.addAll(arrayListOfSpecializations, listOfSpecializations);
		int numberOfFinishedReports = Integer.parseInt(metadata[14]);
		return new Chemist(employee, arrayListOfSpecializations, numberOfFinishedReports);
	}

	private static MedicalTechnicial createMedicalTechnicial(Employee employee, String[] metadata) {
		for (int i = 14; i < 16; i++) {
			metadata[i].trim();
		}
		int numberOfFinishedRequests = Integer.parseInt(metadata[13]);
		int numberOfHouseVisits = Integer.parseInt(metadata[14]);
		return new MedicalTechnicial(employee, numberOfFinishedRequests, numberOfHouseVisits);
	}

	public void convertToCSV(String fileName) throws IOException {
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
		csvWriter.flush();
		csvWriter.close();
	}

}
