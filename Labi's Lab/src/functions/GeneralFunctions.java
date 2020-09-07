package functions;

import java.time.temporal.ChronoUnit;
import static java.util.stream.Collectors.joining;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fileHandler.AllAnalysisTypes;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.Qualification;
import models.Specialization;

public class GeneralFunctions {
	public static LocalDate stringToLocalDate(String s) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		if (s.equals(""))
			return null;
		return LocalDate.parse(s, formatter);
	}

	public static String localDateToString(LocalDate date) {
		if (date == null)
			return " ";
		return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
	}

	public static String localTimeToString(LocalTime time) {
		if (time != null)
			return time.format(DateTimeFormatter.ofPattern("HH:mm"));
		return " ";
	}

	public static ArrayList<Specialization> listOfSpecializationsFromString(String s) {
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

	public static Map<Qualification, Double> readQualificationCoefficients(String[] listOfPairs) {
		Map<Qualification, Double> map = new HashMap<Qualification, Double>();
		for (String pair : listOfPairs) {
			if (!pair.trim().equals("")) {
				String[] arr = pair.split(":", 0);
				Qualification key = Qualification.valueOf(arr[0].trim());
				try {
					map.put(key, Double.valueOf(arr[1]));
				} catch (Exception e) {
					map.put(key, 0.0);
				}
			}
		}
		return map;
	}

	public static Map<Specialization, Double> readSpecializationValues(String[] listOfPairs) {
		Map<Specialization, Double> map = new HashMap<Specialization, Double>();
		for (String pair : listOfPairs) {
			if (!pair.trim().equals("")) {
				String[] arr = pair.split(":", 0);
				Specialization key = Specialization
						.fromString(arr[0].substring(0, 1) + arr[0].substring(1).toLowerCase());
				try {
					map.put(key, Double.valueOf(arr[1]));
				} catch (Exception e) {
					map.put(key, 0.0);
				}
			}
		}
		return map;
	}

	public static String analysisTypesToString(ArrayList<AnalysisType> listOsAnalysisTypes) {
		ArrayList<String> list = new ArrayList<String>();
		for (AnalysisType analysisType : listOsAnalysisTypes) {
			list.add(analysisType.getName());
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public static String specializationsToString(ArrayList<Specialization> listOfSpecializations) {
		if (listOfSpecializations == null)
			return " ";
		if (listOfSpecializations.size() == 0)
			return " ";
		ArrayList<String> list = new ArrayList<String>();
		for (Specialization specialization : listOfSpecializations) {
			try {
				list.add(specialization.getName());
			} catch (Exception e) {
			}
		}
		return list.stream().collect(Collectors.joining("|"));
	}

	public static String specializationMapToString(Map<Specialization, Chemist> map) {
		if (map == null)
			return " ";
		if (map.size() == 0)
			return " ";
		return map.entrySet().stream().map(e -> e.getKey().getName() + ":" + nullOrChemist(e.getValue()))
				.collect(joining("|"));
	}

	private static String nullOrChemist(Chemist value) {
		if (value != null)
			return value.getUserName();
		return " ";
	}

	public static String analysisTypeMapToString(Map<AnalysisType, Double> map) {
		if (map == null)
			return " ";
		if (map.size() == 0)
			return " ";
		return map.entrySet().stream().map(e -> e.getKey().getName() + ":" + nullOrDouble(e.getValue()))
				.collect(joining("|"));
	}

	private static String nullOrDouble(Double value) {
		if (value != null)
			return String.valueOf(value);
		return "null";
	}

	public static String daysOfWeekToString(ArrayList<DayOfWeek> days) {
		String s = "";
		for (DayOfWeek dayOfWeek : days) {
			s += dayOfWeek.getValue();
		}
		return s;
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
		ArrayList<T> newList = new ArrayList<T>();
		for (T element : list) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	public static ArrayList<Specialization> getAllSpecializations(Map<AnalysisType, Double> analysisTypes) {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		for (Entry<AnalysisType, Double> analysisType : analysisTypes.entrySet())
			specializations.add(analysisType.getKey().getGroup());
		return removeDuplicates(specializations);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static double getRandomValue(AnalysisType analysisType) {
		double max = analysisType.getUpperBound() * 1.3;
		double min = analysisType.getLowerBound() * 0.7;
		return (double) (round(Math.random() * (max - min) + min, 2));
	}

	public static boolean isGroupProccessed(AnalysisRequest analysisRequest, AnalysisType analysisType) {
		if (!analysisRequest.getProcessedGroups().containsKey(analysisType.getGroup())) {
			for (Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet()) {
				if (entry.getKey().getGroup() == analysisType.getGroup()) {
					if (entry.getValue() == null)
						return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isRequestFinished(AnalysisRequest analysisRequest) {
		for (Entry<AnalysisType, Double> analysisType2 : analysisRequest.getAnalyses().entrySet()) {
			if (analysisType2.getValue() == null)
				return false;
		}
		return true;
	}

	public static String qualificationCoefficientsToString(Map<Qualification, Double> map) {
		return map.entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(joining("|"));
	}

	public static String groupDiscountLimitsToString(Map<Specialization, Double> map) {
		return "\n" + map.entrySet().stream().map(e -> e.getKey().getName() + ":" + e.getValue()).collect(joining("|"));
	}

	public static int getDaysCountBetweenDates(LocalDate start, LocalDate end) {
		ArrayList<DayOfWeek> days = new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
		return countDaysFromTo(start, end, days);
	}

	public static int countDaysFromTo(LocalDate start, LocalDate end, ArrayList<DayOfWeek> daysOfWeeks) {
		return (int) Stream.iterate(start, d -> d.plusDays(1)).limit(start.until(end, ChronoUnit.DAYS))
				.filter(d -> daysOfWeeks.contains(d.getDayOfWeek())).count();
	}
}
