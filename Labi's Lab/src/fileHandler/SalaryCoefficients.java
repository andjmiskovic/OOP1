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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import functions.GeneralFunctions;
import models.Admin;
import models.Chemist;
import models.MedicalTechnicial;
import models.Qualification;
import models.SalaryCoefficient;
import models.Specialization;

public class SalaryCoefficients {
	public static SalaryCoefficient salaryCoefficient;
	public static Map<Specialization, Double> groupDiscountLimits;
	public static String fileName = "src/files/salary_coef.csv";

	public static void readFromCSV() {
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			salaryCoefficient = createSalaryCoefficient(br.readLine().split(","));
			groupDiscountLimits = GeneralFunctions.readSpecializationValues(br.readLine().trim().split("\\|"));
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
		double processedSamples = Double.parseDouble(attributes[7]);
		String[] strings = attributes[8].trim().split("\\|");
		Map<Qualification, Double> qualificationCoefficients = GeneralFunctions.readQualificationCoefficients(strings);
		return new SalaryCoefficient(base, homeVisit, homeVisitWithTime, specializationBonus, homeVisitBonus,
				homeVisitWithTimeBonus, holidayBonus, processedSamples, qualificationCoefficients);
	}

	public static double getSalaryCoefficient(double yearsOfService) {
		if (yearsOfService < 10)
			return 0.5;
		if (yearsOfService < 15)
			return 0.65;
		if (yearsOfService < 20)
			return 0.8;
		return 1;
	}

	public static double getSalary(Admin admin, LocalDate start, LocalDate end) {
		int numberOfDays = GeneralFunctions.getDaysCountBetweenDates(start, end);
		return GeneralFunctions.round(
				numberOfDays * salaryCoefficient.getBase() * getSalaryCoefficient(admin.getYearsOfService()) / 30, 2);
	}

	public static double getSalary(MedicalTechnicial medicalTechnicial, LocalDate start, LocalDate end) {
		int numberOfDays = GeneralFunctions.getDaysCountBetweenDates(start, end);
		int numberOfHomeVisits = AllAnalysisRequests.numberOfHomeVisitsInPeriod(medicalTechnicial, start, end)
				.get("date");
		int numberOfHomeVisitsWithTime = AllAnalysisRequests.numberOfHomeVisitsInPeriod(medicalTechnicial, start, end)
				.get("time");
		return GeneralFunctions.round(numberOfDays
				* (salaryCoefficient.getBase() * getSalaryCoefficient(medicalTechnicial.getYearsOfService())
						* salaryCoefficient.getQualificationCoefficients().get(medicalTechnicial.getQualification())
						+ numberOfHomeVisits * salaryCoefficient.getHomeVisitBonus()
						+ numberOfHomeVisitsWithTime * salaryCoefficient.getHomeVisitWithTimeBonus())
				/ 30, 2);
	}

	public static double getSalary(Chemist chemist, LocalDate start, LocalDate end) {
		ArrayList<DayOfWeek> days = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
		int numberOfDays = GeneralFunctions.getDaysCountBetweenDates(start, end);
		int numberOfSpecializations = chemist.getListOfSpecializations().size();
		return GeneralFunctions
				.round(numberOfDays * (salaryCoefficient.getBase() * getSalaryCoefficient(chemist.getYearsOfService())
						* salaryCoefficient.getQualificationCoefficients().get(chemist.getQualification())
						+ numberOfSpecializations * salaryCoefficient.getSpecializationBonus()
						+ chemist.getNumberOfProcessedSamples(days, start, end)
								* salaryCoefficient.getProcessedSampleBonus())
						/ 30, 2);
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
			csvWriter.append(GeneralFunctions.groupDiscountLimitsToString(groupDiscountLimits));
		} catch (Exception e4) {
			e4.printStackTrace();
		}
		csvWriter.flush();
		csvWriter.close();
	}

}
