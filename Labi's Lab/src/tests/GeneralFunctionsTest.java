package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import fileHandler.Users;
import functions.GeneralFunctions;
import models.AnalysisType;
import models.Chemist;
import models.Qualification;
import models.Specialization;

class GeneralFunctionsTest {

	@Test
	void testReadSpecializationValues() {
		Map<Specialization, Double> specializations = new HashMap<Specialization, Double>();
		specializations.put(Specialization.Alergology, 5000.0);
		specializations.put(Specialization.Genetics, 7000.0);
		Map<Specialization, Double> actual = GeneralFunctions
				.readSpecializationValues("ALERGOLOGY:5000.0|GENETICS:7000.0".split("\\|"));
		assertEquals(specializations, actual);
	}

	@Test
	void testReadSpecializationValues_whenParsedEmptyString() {
		Map<Specialization, Double> specializations = new HashMap<Specialization, Double>();
		Map<Specialization, Double> actual = GeneralFunctions.readSpecializationValues(" ".split("\\|"));
		assertEquals(specializations, actual);
	}

	@Test
	void testReadQualificationCoefficients() {
		Map<Qualification, Double> qualifications = new HashMap<Qualification, Double>();
		qualifications.put(Qualification.One, 5000.0);
		qualifications.put(Qualification.Three, 7000.0);
		Map<Qualification, Double> actual = GeneralFunctions
				.readQualificationCoefficients("One:5000.0|Three:7000.0".split("\\|"));
		assertEquals(qualifications, actual);
	}

	@Test
	void testReadQualificationCoefficients_whenParsedEmptyString() {
		Map<Qualification, Double> qualifications = new HashMap<Qualification, Double>();
		Map<Qualification, Double> actual = GeneralFunctions.readQualificationCoefficients(" ".split("\\|"));
		assertEquals(qualifications, actual);
	}

	@Test
	void testlistOfSpecializationsFromString() {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		specializations.add(Specialization.Alergology);
		specializations.add(Specialization.Drugs);
		ArrayList<Specialization> actual = GeneralFunctions.listOfSpecializationsFromString("ALERGOLOGY|DRUGS");
		assertEquals(specializations, actual);
	}
	
	@Test
	void testlistOfSpecializationsFromString_whenParsedEmptyString() {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		ArrayList<Specialization> actual = GeneralFunctions.listOfSpecializationsFromString(" ");
		assertEquals(specializations, actual);
	}

	@Test
	void testAnalysisTypesToString() {
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
		analysisTypes.add(AllAnalysisTypes.getAnalysisTypeByName("vitamin e"));
		analysisTypes.add(AllAnalysisTypes.getAnalysisTypeByName("vitamin b12"));
		String expectedString = "vitamin e|vitamin b12";
		String actual = GeneralFunctions.analysisTypesToString(analysisTypes);
		assertEquals(expectedString, actual);
	}

	@Test
	void testAnalysisTypesToString_whenParsedEmptyArray() {
		ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
		String expectedString = "";
		String actual = GeneralFunctions.analysisTypesToString(analysisTypes);
		assertEquals(expectedString, actual);
	}

	@Test
	void testSpecializationsToString() {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		specializations.add(Specialization.Alergology);
		specializations.add(Specialization.Genetics);
		String expectedString = "ALERGOLOGY|GENETICS";
		String actual = GeneralFunctions.specializationsToString(specializations);
		assertEquals(expectedString, actual);
	}

	@Test
	void testSpecializationsToString_whenParsedEmptyArray() {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		String expectedString = " ";
		String actual = GeneralFunctions.specializationsToString(specializations);
		assertEquals(expectedString, actual);
	}

	@Test
	void testAnalysisTypeMapToString() {
		AllAnalysisTypes.readFromCSV();
		Map<AnalysisType, Double> map = new HashMap<AnalysisType, Double>();
		map.put(AllAnalysisTypes.getAnalysisTypeByName("vitamin e"), null);
		map.put(AllAnalysisTypes.getAnalysisTypeByName("vitamin b12"), 0.9);
		String expectedString = "vitamin e:null|vitamin b12:0.9";
		String actual = GeneralFunctions.analysisTypeMapToString(map);
		assertEquals(expectedString, actual);
	}

	@Test
	void testAnalysisTypeMapToString_whenParsedEmptyMap() {
		AllAnalysisTypes.readFromCSV();
		Map<AnalysisType, Double> map = new HashMap<AnalysisType, Double>();
		String expectedString = " ";
		String actual = GeneralFunctions.analysisTypeMapToString(map);
		assertEquals(expectedString, actual);
	}

	@Test
	void testSpecializationMapToString() {
		Users.readUsersFromCSV();
		Map<Specialization, Chemist> specializations = new HashMap<Specialization, Chemist>();
		specializations.put(Specialization.Alergology, Users.getChemistByUsername("c"));
		specializations.put(Specialization.Genetics, Users.getChemistByUsername("c"));
		String expectedString = "GENETICS:c|ALERGOLOGY:c";
		String actual = GeneralFunctions.specializationMapToString(specializations);
		assertEquals(expectedString, actual);
	}

	@Test
	void testSpecializationMapToString_whenParsedEmptyArray() {
		Users.readUsersFromCSV();
		Map<Specialization, Chemist> specializations = new HashMap<Specialization, Chemist>();
		String expectedString = " ";
		String actual = GeneralFunctions.specializationMapToString(specializations);
		assertEquals(expectedString, actual);
	}

	@Test
	void testDaysOfWeekToString() {
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		days.add(DayOfWeek.TUESDAY);
		days.add(DayOfWeek.SATURDAY);
		String actual = GeneralFunctions.daysOfWeekToString(days);
		assertEquals("126", actual);
	}

	@Test
	void testDaysOfWeekToString_whenParsedEmptyArray() {
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		String actual = GeneralFunctions.daysOfWeekToString(days);
		assertEquals("", actual);
	}

	@Test
	void testLocalTimeToString() {
		String actual = GeneralFunctions.localTimeToString(LocalTime.of(12, 9));
		assertEquals("12:09", actual);
	}

	@Test
	void testLocalDateToString() {
		String actual = GeneralFunctions.localDateToString(LocalDate.of(2020, 9, 12));
		assertEquals("12.09.2020.", actual);
	}
	
	@Test
	void testGetDaysCountBetweenDates() {
		LocalDate start = LocalDate.of(2020, 8, 3);
		LocalDate end = LocalDate.of(2020, 8, 8);
		int actual = GeneralFunctions.getDaysCountBetweenDates(start, end);
		assertEquals(5, actual);
	}
	
	@Test
	void testCountDaysFromTo() {
		LocalDate start = LocalDate.of(2020, 8, 2);
		LocalDate end = LocalDate.of(2020, 8, 8);
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		int actual = GeneralFunctions.countDaysFromTo(start, end, days);
		assertEquals(1, actual);
	}
	
	@Test
	void testRound() {
		assertEquals(0.9, GeneralFunctions.round(0.888888, 1));
	}

}
