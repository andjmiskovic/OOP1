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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import functions.GeneralFunctions;
import functions.Validations;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentStateOfRequest;
import models.MedicalTechnicial;
import models.Patient;
import models.Specialization;

public class AllAnalysisRequests {
	public static ArrayList<AnalysisRequest> listOfRequests;
	public static String fileName = "src/files/analysis_req.csv";

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
		LocalDate homeVisitDate;
		LocalTime homeVisitTime;
		boolean homeVisitWithTime;
		boolean homeVisit = Boolean.parseBoolean(attributes[2]);
		if (homeVisit) {
			homeVisitDate = GeneralFunctions.stringToLocalDate(attributes[3].trim());
			homeVisitWithTime = Boolean.parseBoolean(attributes[4]);
			if (homeVisitWithTime) {
				homeVisitTime = LocalTime.parse(attributes[5].trim());
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
		LocalDate requestedDate = GeneralFunctions.stringToLocalDate(attributes[9].trim());
		LocalDate processedDate = GeneralFunctions.stringToLocalDate(attributes[10].trim());
		MedicalTechnicial medicalTechnicial = Users.getMedicalTechnicialByUsername(attributes[11].trim());
		Map<Specialization, Chemist> processedGroups = readSpecializationMap(attributes[12].trim().split("\\|"));
		if (currentState != CurrentStateOfRequest.FINISHED_REPORT)
			return new AnalysisRequest(id, totalPrice, homeVisit, homeVisitDate, homeVisitWithTime, homeVisitTime,
					currentState, analyses, patient, requestedDate, processedDate, medicalTechnicial, processedGroups,
					GeneralFunctions.listOfSpecializationsFromString(attributes[13].trim()));
		return new AnalysisRequest(id, totalPrice, homeVisit, homeVisitDate, homeVisitWithTime, homeVisitTime,
				currentState, analyses, patient, requestedDate, processedDate, medicalTechnicial, processedGroups,
				null);
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
				Specialization key = Specialization.fromString(arr[0].trim());
				Chemist value = Users.getChemistByUsername(arr[1].trim());
				map.put(key, value);
			}
		}
		return map;
	}

	public static ArrayList<AnalysisRequest> getRequestsWithTakenSamples() {
		ArrayList<AnalysisRequest> analysisRequests = new ArrayList<AnalysisRequest>();
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getCurrentState() == CurrentStateOfRequest.PROCESSING)
				analysisRequests.add(analysisRequest);
		}
		return analysisRequests;
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
			try {
				if (analysisRequest.getPatient().getUserName().equals(patient.getUserName()))
					list.add(analysisRequest);
			} catch (Exception e) {
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

	public static Map<String, Integer> numberOfHomeVisitsInPeriod(MedicalTechnicial medicalTechnicial, LocalDate start,
			LocalDate end) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int homeVisits = 0;
		int homeVisitsWithTime = 0;
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getMedicalTechnicial() == medicalTechnicial) {
				if (analysisRequest.isHomeVisit()) {
					if (analysisRequest.getHomeVisitDate().isAfter(start)) {
						if (analysisRequest.getHomeVisitDate().isBefore(end)) {
							if (analysisRequest.isHomeVisitWithTime())
								homeVisitsWithTime += 1;
							else
								homeVisits += 1;
						}
					}
				}
			}
		}
		map.put("date", homeVisits);
		map.put("time", homeVisitsWithTime);
		return map;
	}

	public static double calculateRequestPriceWithDiscount(ArrayList<AnalysisType> analysisTypes, boolean date,
			boolean time) {
		double price = 0;
		Map<Specialization, Double> spendPerSpecialization = new HashMap<Specialization, Double>();
		Map<Specialization, Integer> numberOfAnalysisPerSpecialization = new HashMap<Specialization, Integer>();
		for (Specialization specialization : Specialization.values()) {
			spendPerSpecialization.put(specialization, 0.0);
			numberOfAnalysisPerSpecialization.put(specialization, 0);
		}
		if (time)
			price += SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
		else if (date)
			price += SalaryCoefficients.salaryCoefficient.getHomeVisit();
		for (AnalysisType analysisType : analysisTypes) {
			spendPerSpecialization.put(analysisType.getGroup(),
					spendPerSpecialization.get(analysisType.getGroup()) + analysisType.getPrice());
			numberOfAnalysisPerSpecialization.put(analysisType.getGroup(),
					numberOfAnalysisPerSpecialization.get(analysisType.getGroup()) + 1);
		}
		for (AnalysisType analysisType : analysisTypes) {
			double discount = 1;
			boolean groupDiscount = false;
			Specialization specialization = analysisType.getGroup();
			if (analysisType.getGroupDiscount() != null) {
				if (numberOfAnalysisPerSpecialization.get(specialization) > 2)
					groupDiscount = true;
			}
			if (spendPerSpecialization.get(specialization) >= SalaryCoefficients.groupDiscountLimits
					.get(specialization))
				discount *= 0.95;
			price += AllAnalysisTypes.getAnalysisTypePriceWithDiscount(analysisType, LocalDate.now(), groupDiscount)
					* discount;
		}
		return price;
	}

	public static Map<Specialization, Double> incomesPerSpecialization(LocalDate start, LocalDate end,
			ArrayList<Specialization> selectedSpecialization, ArrayList<DayOfWeek> daysOfWeeks, boolean homeVisit,
			int numberOfAnalysis, CurrentStateOfRequest currentStateOfRequest) {
		Map<Specialization, Double> map = new HashMap<Specialization, Double>();
		for (Specialization specialization : selectedSpecialization)
			map.put(specialization, 0.0);
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getRequestedDate().isAfter(start) & analysisRequest.getRequestedDate().isBefore(end)) {
				if (daysOfWeeks.contains(analysisRequest.getRequestedDate().getDayOfWeek())) {
					if (analysisRequest.isHomeVisit() == homeVisit) {
						if (analysisRequest.getCurrentState() == currentStateOfRequest) {
							if (analysisRequest.getAnalyses().size() >= numberOfAnalysis) {
								for (AnalysisType analysisType : analysisRequest.getAnalyses().keySet()) {
									Specialization specialization = analysisType.getGroup();
									if (map.containsKey(specialization)) {
										double value = map.get(specialization)
												+ analysisPriceWithinRequest(analysisRequest, analysisType);
										map.put(specialization, value);
									}
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	public static ArrayList<AnalysisRequest> filterAnalysisRequests(LocalDate start, LocalDate end,
			ArrayList<Specialization> selectedSpecialization, ArrayList<DayOfWeek> daysOfWeeks, boolean homeVisit,
			int numberOfAnalysis, CurrentStateOfRequest currentStateOfRequest) {
		ArrayList<AnalysisRequest> map = new ArrayList<AnalysisRequest>();
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getRequestedDate().isAfter(start) & analysisRequest.getRequestedDate().isBefore(end)) {
				if (analysisRequest.isHomeVisit() == homeVisit) {
					if (analysisRequest.getCurrentState() == currentStateOfRequest) {
						if (analysisRequest.getAnalyses().size() >= numberOfAnalysis) {
							for (AnalysisType analysisType : analysisRequest.getAnalyses().keySet()) {
								if (selectedSpecialization.contains(analysisType.getGroup())) {
									map.add(analysisRequest);
									break;
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	public static double analysisPriceWithinRequest(AnalysisRequest analysisRequest, AnalysisType analysisType) {
		LocalDate requestedDate = analysisRequest.getRequestedDate();
		double price = AllAnalysisTypes.getAnalysisTypePriceWithDiscount(analysisType, requestedDate, false);
		Specialization specialization = analysisType.getGroup();
		double specializationLimit = 0;
		int numberOfAnalysisWithinSpecialization = 0;
		for (AnalysisType analysisType2 : analysisRequest.getAnalyses().keySet()) {
			if (analysisType2.getGroup() == specialization) {
				specializationLimit += analysisType2.getPrice();
				numberOfAnalysisWithinSpecialization += 1;
			}
		}
		if (specializationLimit >= SalaryCoefficients.groupDiscountLimits.get(specialization))
			price *= 0.95;
		if (analysisType.getGroupDiscount() != null) {
			if (numberOfAnalysisWithinSpecialization > 2) {
				if (requestedDate.isAfter(analysisType.getGroupDiscount().getStartDate())) {
					if (requestedDate.isBefore(analysisType.getGroupDiscount().getEndDate())) {
						price *= analysisType.getGroupDiscount().getValue();
					}
				}
			}
		}
		return price;
	}

	public static void addAnalysisRequest(AnalysisRequest analysisRequest) {
		listOfRequests.add(analysisRequest);
		saveData();
	}

	public static void setMedicalTechnicial(String id, MedicalTechnicial medicalTechnicial) {
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getId().equals(id)) {
				analysisRequest.setMedicalTechnicial(medicalTechnicial);
				analysisRequest.setCurrentState(CurrentStateOfRequest.SAMPLE_TAKING);
				saveData();
				return;
			}
		}
	}

	public static void setSampleTaken(String id) {
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (analysisRequest.getId().equals(id)) {
				analysisRequest.setCurrentState(CurrentStateOfRequest.PROCESSING);
				saveData();
				return;
			}
		}
	}

	public static Map<String, Integer> numberOfAnalysesByTypeAndSex(AnalysisType analysis, LocalDate startDate,
			LocalDate endDate, int startAge, int endAge) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int male = 0;
		int female = 0;
		for (AnalysisRequest analysisRequest : listOfRequests) {
			for (AnalysisType analysisType : analysisRequest.getAnalyses().keySet()) {
				if (analysisType == analysis) {
					LocalDate requestedDate = analysisRequest.getRequestedDate();
					if (requestedDate.isAfter(startDate) & requestedDate.isBefore(endDate)) {
						int patientsAge = analysisRequest.getPatient().getAge();
						if (endAge >= patientsAge & patientsAge >= startAge) {
							try {
								if (analysisRequest.getPatient().getGender().toLowerCase().equals("male"))
									male += 1;
								else
									female += 1;
							} catch (Exception e) {
							}
						}
					}
				}
			}
		}
		map.put("male", male);
		map.put("female", female);
		return map;
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

	public static int getNumberOfRequestsByPatientInTime(Patient patient, ArrayList<DayOfWeek> days, LocalDate start,
			LocalDate end) {
		int number = 0;
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (end != null & start != null) {
				if (analysisRequest.getRequestedDate().isBefore(end)
						& analysisRequest.getRequestedDate().isAfter(start)) {
					if (days.contains(analysisRequest.getRequestedDate().getDayOfWeek())) {
						try {
							if (analysisRequest.getPatient().getUserName().equals(patient.getUserName()))
								number += 1;
						} catch (Exception e) {
						}
					}
				}
			}
		}
		return number;
	}

	public static int getNumberOfProcessedSamplesByChemistInTime(Chemist chemist, ArrayList<DayOfWeek> days,
			LocalDate start, LocalDate end) {
		int number = 0;
		try {
			for (AnalysisRequest analysisRequest : listOfRequests) {
				if (end != null & start != null) {
					if (analysisRequest.getRequestedDate().isBefore(end)
							& analysisRequest.getRequestedDate().isAfter(start)) {
						if (days.contains(analysisRequest.getRequestedDate().getDayOfWeek())) {
							for (Entry<Specialization, Chemist> entry : analysisRequest.getProcessedGroups()
									.entrySet()) {
								if (entry.getValue().getUserName().equals(chemist.getUserName()))
									number += 1;
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return number;
	}

	public static int getNumberOfHouseVisitsByTechnicialInTime(MedicalTechnicial medicalTechnicial,
			ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		int number = 0;
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (end != null & start != null) {
				if (analysisRequest.isHomeVisit()) {
					if (analysisRequest.getRequestedDate().isBefore(end)
							& analysisRequest.getRequestedDate().isAfter(start)) {
						if (days.contains(analysisRequest.getRequestedDate().getDayOfWeek())) {
							if (analysisRequest.getMedicalTechnicial() != null) {
								if (analysisRequest.getMedicalTechnicial().getUserName()
										.equals(medicalTechnicial.getUserName())) {
									number += 1;
								}
							}
						}
					}
				}
			}
		}
		return number;
	}

	public static int getNumberOfHouseVisitsWithTimeByTechnicialInTime(MedicalTechnicial medicalTechnicial,
			ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		int number = 0;
		for (AnalysisRequest analysisRequest : listOfRequests) {
			if (end != null & start != null) {
				if (analysisRequest.isHomeVisitWithTime()) {
					if (analysisRequest.getRequestedDate().isBefore(end)
							& analysisRequest.getRequestedDate().isAfter(start)) {
						if (days.contains(analysisRequest.getRequestedDate().getDayOfWeek())) {
							if (analysisRequest.getMedicalTechnicial() != null) {
								if (analysisRequest.getMedicalTechnicial().getUserName()
										.equals(medicalTechnicial.getUserName())) {
									number += 1;
								}
							}
						}
					}
				}
			}
		}
		return number;

	}

	public static ArrayList<AnalysisRequest> getRequestsByPatientInTime(Patient patient, ArrayList<DayOfWeek> days,
			LocalDate start, LocalDate end) {
		ArrayList<AnalysisRequest> analysisRequests = new ArrayList<AnalysisRequest>();
		for (AnalysisRequest analysisRequest : listOfRequests) {
			try {
				if (analysisRequest.getPatient().getUserName().equals(patient.getUserName())) {
					if (end != null & start != null) {
						if (analysisRequest.getRequestedDate().isBefore(end)
								& analysisRequest.getRequestedDate().isAfter(start)) {
							if (days.contains(analysisRequest.getRequestedDate().getDayOfWeek()))
								analysisRequests.add(analysisRequest);
						}
					}
				}

			} catch (Exception e) {
			}
		}
		return analysisRequests;
	}

	public static double getTotalPriceOfRequestsByGroupByPatient(ArrayList<Specialization> group, Patient patient,
			ArrayList<DayOfWeek> days, LocalDate start, LocalDate end) {
		double price = 0;
		try {
			for (AnalysisRequest analysisRequest : getRequestsByPatientInTime(patient, days, start, end)) {
				for (AnalysisType analysisType : analysisRequest.getAnalyses().keySet()) {
					if (group.contains(analysisType.getGroup()))
						price += analysisPriceWithinRequest(analysisRequest, analysisType);
				}
			}
		} catch (Exception e) {
		}
		return price;
	}

	public static Map<LocalDate, Double> calculateStatisticsPerAnalysisTypeByPatient(Patient currentPatient,
			AnalysisType analysisTypes) {
		Map<LocalDate, Double> map = new HashMap<LocalDate, Double>();
		for (AnalysisRequest analysisRequest : listOfRequests) {
			try {
				if (analysisRequest.getPatient().getUserName().equals(currentPatient.getUserName())) {
					if (analysisRequest.getAnalyses().containsKey(analysisTypes)) {
						if (analysisRequest.getAnalyses().get(analysisTypes) != null) {
							map.put(analysisRequest.getRequestedDate(),
									analysisRequest.getAnalyses().get(analysisTypes));
						}
					}
				}
			} catch (Exception e) {
			}
		}
		return map;
	}

	public static String uniqueRequestId() {
		String id = String.valueOf((int) (Math.random() * 1000));
		while (!Validations.isUniqueAnalysisRequestId(id)) {
			id = String.valueOf((int) (Math.random() * 1000));
		}
		return id;
	}

}
