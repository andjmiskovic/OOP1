package fileHandler;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import classes.Qualification;
import classes.SalaryCoefficient;
import classes.Specialization;

public class SalaryCoefficients {
	public static SalaryCoefficient salaryCoefficient;
	public static Map<Specialization, Double> groupDiscountLimits;
	public static String fileName = "C:\\Users\\Lenovo\\git\\OOP1\\Labi's Lab\\src\\Files\\salary_coef.csv";

	public static void readFromCSV() throws IOException {
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			salaryCoefficient = createSalaryCoefficient(br.readLine().split(","));
			groupDiscountLimits = readSpecializationValues(br.readLine().trim().split("\\|"));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static SalaryCoefficient createSalaryCoefficient(String[] attributes) {
		double base = Double.parseDouble(attributes[0]);
		double homeVisit = Double.parseDouble(attributes[1]);
		double homeVisitWithTime = Double.parseDouble(attributes[2]);
		double specializationBonus = Double.parseDouble(attributes[3]);
		double homeVisitBonus = Double.parseDouble(attributes[4]);
		double homeVisitWithTimeBonus = Double.parseDouble(attributes[5]);
		double holidayBonus = Double.parseDouble(attributes[6]);
		String[] strings = attributes[7].trim().split("\\|");
		Map<Qualification, Double> qualificationCoefficients = readQualificationCoefficients(strings);
		return new SalaryCoefficient(base, homeVisit, homeVisitWithTime, specializationBonus, homeVisitBonus,
				homeVisitWithTimeBonus, holidayBonus, qualificationCoefficients);
	}

	public static Map<Qualification, Double> readQualificationCoefficients(String[] listOfPairs) {
		Map<Qualification, Double> map = new HashMap<Qualification, Double>();
		for (String pair : listOfPairs) {
			if (pair.trim() != "") {
				String[] arr = pair.split(":", 0);
				Qualification key = Qualification.valueOf(arr[0].trim());
				double value = Double.valueOf(arr[1]);
				map.put(key, value);
			}
		}
		return map;
	}
	
	public static Map<Specialization, Double> readSpecializationValues(String[] listOfPairs) {
		Map<Specialization, Double> map = new HashMap<Specialization, Double>();
		for (String pair : listOfPairs) {
			if (pair.trim() != "") {
				String[] arr = pair.split(":", 0);
				Specialization key = Specialization.valueOf(arr[0].substring(0, 1) + arr[0].substring(1).toLowerCase());
				double value = Double.valueOf(arr[1]);
				map.put(key, value);
			}
		}
		return map;
	}
	
	public static String groupDiscountLimitsToString(Map<Specialization, Double> map) {
		return "\n" + map.entrySet().stream().map(e -> e.getKey().getName() + ":" + e.getValue()).collect(joining("|"));
	}

	public static void saveData() throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			csvWriter.append(salaryCoefficient.toString());
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		try {
			csvWriter.append(groupDiscountLimitsToString(groupDiscountLimits));
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		csvWriter.flush();
		csvWriter.close();
	}

}
