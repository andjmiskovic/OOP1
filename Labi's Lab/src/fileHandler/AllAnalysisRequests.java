package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import classes.AnalysisRequest;
import classes.AnalysisType;
import classes.Chemist;
import classes.CurrentStateOfRequest;
import classes.MedicalTechnicial;
import classes.Patient;
import classes.Specialization;

public class AllAnalysisRequests {
	public static ArrayList<AnalysisRequest> listOfRequests;
	public static String fileName = "C:\\Users\\Lenovo\\git\\OOP1\\Labi's Lab\\src\\Files\\analysis_req.csv";

	public static void readRequestsFromCSV() {
		listOfRequests = new ArrayList<AnalysisRequest>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				AnalysisRequest analysisRequest = createRequest(attributes);
				listOfRequests.add(analysisRequest);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static AnalysisRequest createRequest(String[] attributes) {
		String id = attributes[0].trim();
		double totalPrice = Double.parseDouble(attributes[1]);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		LocalDate homeVisitDate;
		LocalTime homeVisitTime;
		boolean homeVisitWithTime;
		boolean homeVisit = Boolean.parseBoolean(attributes[2]);
		if (homeVisit) {
			homeVisitDate = stringToLocalDate(attributes[3].trim(), formatter);
			homeVisitWithTime = Boolean.parseBoolean(attributes[4]);
			if (homeVisitWithTime) {
				homeVisitTime = LocalTime.parse(attributes[5]);
			} else {
				homeVisitTime = null;
			}
		} else {
			homeVisitDate = null;
			homeVisitWithTime = false;
			homeVisitTime = null;
		}
		CurrentStateOfRequest currentState = CurrentStateOfRequest.valueOf(attributes[6].trim());
		Map<AnalysisType, Double> analyses = readAnalysesTypesMap(attributes[7].split("\\|"));
		Patient patient = Users.getPatientByUsername(attributes[8].trim());
		LocalDate requestedDate = stringToLocalDate(attributes[9].trim(), formatter);
		LocalDate processedDate = stringToLocalDate(attributes[10].trim(), formatter);
		MedicalTechnicial medicalTechnicial = Users.getMedicalTechnicialByUsername(attributes[11].trim());
		Map<Specialization, Chemist> processedGroups = readSpecializationMap(attributes[12].trim().split("\\|"));
		ArrayList<Specialization> unprocessedGroups = listOfSpecializations(attributes[13].trim());
		return new AnalysisRequest(id, totalPrice, homeVisit, homeVisitDate, homeVisitWithTime, homeVisitTime,
				currentState, analyses, patient, requestedDate, processedDate, medicalTechnicial, processedGroups,
				unprocessedGroups);
	}

	public static LocalDate stringToLocalDate(String s, DateTimeFormatter formatter) {
		if (s.equals(""))
			return null;
		return LocalDate.parse(s, formatter);
	}

	public static ArrayList<Specialization> listOfSpecializations(String s) {
		String[] listOfSpecializations = s.split("\\|");
		ArrayList<Specialization> arrayListOfSpecializations = new ArrayList<Specialization>();
		for (String specialization : listOfSpecializations) {
			if (specialization.trim().length() > 0) {
				arrayListOfSpecializations.add(Specialization.fromString(specialization.trim()));
			}
		}
		return arrayListOfSpecializations;
	}

	public static ArrayList<AnalysisType> stringToAnalysesTypes(String[] listOfAnalysisTypes) {
		ArrayList<AnalysisType> analyses = new ArrayList<AnalysisType>();
		for (String string : listOfAnalysisTypes) {
			if (!string.isEmpty()) {
				analyses.add(AllAnalysisTypes.getAnalysisTypeByName(string.trim()));
			}
		}
		return analyses;
	}

	public static Map<AnalysisType, Double> readAnalysesTypesMap(String[] listOfPairs) {
		Map<AnalysisType, Double> map = new HashMap<AnalysisType, Double>();
		for (String pair : listOfPairs) {
			if (!pair.isEmpty()) {
				String[] arr = pair.split(":", 0);
				AnalysisType key = AllAnalysisTypes.getAnalysisTypeByName(arr[0].trim());
				if (arr[1].trim().equals("null")) {
					map.put(key, null);
				} else {
					map.put(key, Double.valueOf(arr[1].trim()));
				}
			}
		}
		return map;
	}

	public static Map<Specialization, Chemist> readSpecializationMap(String[] listOfPairs) {
		Map<Specialization, Chemist> map = new HashMap<Specialization, Chemist>();
		for (String pair : listOfPairs) {
			if (!pair.isEmpty()) {
				String[] arr = pair.split(":", 0);
				Specialization key = Specialization.valueOf(arr[0].trim());
				Chemist value = Users.getChemistByUsername(arr[1].trim());
				map.put(key, value);
			}
		}
		return map;
	}

	public static ArrayList<Chemist> stringToChemists(String[] listOfChemists) {
		ArrayList<Chemist> chemists = new ArrayList<Chemist>();
		for (String username : listOfChemists) {
			if (username.trim() != "") {
				chemists.add(Users.getChemistByUsername(username.trim()));
			}
		}
		return chemists;
	}

	public static AnalysisRequest getAnalysisRequestById(String id) {
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getId().equals(id))
				return analysisRequest;
		}
		return null;
	}

	public static ArrayList<AnalysisRequest> getAnalysisRequestsByPatient(Patient patient) {
		ArrayList<AnalysisRequest> list = new ArrayList<AnalysisRequest>();
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getPatient() == patient) {
				list.add(analysisRequest);
			}
		}
		return list;
	}

	public static ArrayList<AnalysisRequest> getLastTwoAnalysisRequestsByPatient(Patient patient) {
		ArrayList<AnalysisRequest> list = getAnalysisRequestsByPatient(patient);
		Collections.sort(list, new Comparator<AnalysisRequest>() {
			public int compare(AnalysisRequest o1, AnalysisRequest o2) {
				if (o1.getProcessedDate() == null || o2.getProcessedDate() == null)
					return 0;
				return o1.getProcessedDate().compareTo(o2.getProcessedDate());
			}
		});
		ArrayList<AnalysisRequest> list2 = new ArrayList<AnalysisRequest>();
		if (list.size() > 0) {
			list2.add(list.get(0));
		}
		if (list.size() > 1) {
			list2.add(list.get(1));
		}
		return list2;
	}

	public static double calculateRequestPriceWithtDiscount(ArrayList<AnalysisType> analysisTypes, LocalDate date,
			boolean time) {
		double price = 0;
		if (date != null)
			price += SalaryCoefficients.salaryCoefficient.getHomeVisit();
		if (time)
			price += SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
		for (AnalysisType analysisType : analysisTypes)
			price += AllAnalysisTypes.getAnalysisTypePriceWithDiscount(analysisType, date);
		return price;
	}

	public static void addAnalysisRequest(AnalysisRequest analysisRequest) {
		listOfRequests.add(analysisRequest);
		saveData();
	}

	public static void saveData() {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (AnalysisRequest request : listOfRequests) {
				csvWriter.append(request.toString());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
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
	}

}
