package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import fileHandler.SalaryCoefficients;
import fileHandler.Users;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.Patient;
import models.Specialization;

class AnalysisRequestsTest {

	@Before
	public void before() {
		Users.readUsersFromCSV();
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		SalaryCoefficients.readFromCSV();
		AllAnalysisRequests.readRequestsFromCSV();
	}

	@Test
	public void testReadAnalysesTypesMap() {
		before();
		Map<AnalysisType, Double> expectedMap = new HashMap<AnalysisType, Double>();
		expectedMap.put(AllAnalysisTypes.getAnalysisTypeByName("vitamin e"), 0.9);
		expectedMap.put(AllAnalysisTypes.getAnalysisTypeByName("vitamin b12"), 1.9);

		String[] analysis = "vitamin e:0.9|vitamin b12:1.9".split("\\|");
		Map<AnalysisType, Double> map = AllAnalysisRequests.readAnalysesTypesMap(analysis);
		assertEquals(expectedMap, map);
	}

	@Test
	public void testReadSpecializationMap() {
		before();
		Map<Specialization, Chemist> expectedMap = new HashMap<Specialization, Chemist>();
		expectedMap.put(Specialization.Alergology, Users.getChemistByUsername("c"));
		expectedMap.put(Specialization.Biochemistry, Users.getChemistByUsername("c"));

		String[] analysis = "ALERGOLOGY:c|BIOCHEMISTRY:c".split("\\|");
		Map<Specialization, Chemist> map = AllAnalysisRequests.readSpecializationMap(analysis);
		assertEquals(expectedMap, map);
	}

	@Test
	public void testGetAnalysisRequestsByPatient() {
		before();
		ArrayList<AnalysisRequest> expectedAnalysisRequests = new ArrayList<AnalysisRequest>();
		expectedAnalysisRequests.add(AllAnalysisRequests.getAnalysisRequestById("17"));
		Patient patient = Users.getPatientByUsername("nPatient");
		ArrayList<AnalysisRequest> actualAnalysisRequests = AllAnalysisRequests.getAnalysisRequestsByPatient(patient);
		assertEquals(expectedAnalysisRequests, actualAnalysisRequests);
	}

	@Test
	public void testCalculateRequestPriceWithDiscount() {
		before();
		ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
		analysisTypes.add(AllAnalysisTypes.getAnalysisTypeByName("vitamin e"));
		double price = AllAnalysisRequests.calculateRequestPriceWithDiscount(analysisTypes, false, false);
		assertEquals(1800.0, price, 0);
	}

	@Test
	public void testGetNumberOfRequestsByPatientInTime() {
		before();
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		LocalDate start = LocalDate.of(2020, 8, 25);
		LocalDate end = LocalDate.of(2020, 8, 27);
		Patient patient = Users.getPatientByUsername("p");
		int actual = AllAnalysisRequests.getNumberOfRequestsByPatientInTime(patient, days, start, end);
		assertEquals(0, actual);
	}

	@Test
	public void testGetAnalysisRequestsByPatientInTime() {
		before();
		ArrayList<AnalysisRequest> expectedAnalysisRequests = new ArrayList<AnalysisRequest>();
		expectedAnalysisRequests.add(AllAnalysisRequests.getAnalysisRequestById("17"));
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		days.add(DayOfWeek.TUESDAY);
		LocalDate start = LocalDate.of(2020, 8, 23);
		LocalDate end = LocalDate.of(2020, 8, 27);
		Patient patient = Users.getPatientByUsername("nPatient");
		ArrayList<AnalysisRequest> actualAnalysisRequests = AllAnalysisRequests.getRequestsByPatientInTime(patient,
				days, start, end);
		assertEquals(expectedAnalysisRequests, actualAnalysisRequests);
	}

	@Test
	public void testGetTotalPriceOfRequestsByGroupByPatient() {
		before();
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		LocalDate start = LocalDate.of(2020, 8, 25);
		LocalDate end = LocalDate.of(2020, 8, 27);
		Patient patient = Users.getPatientByUsername("nPatient");
		ArrayList<Specialization> group = new ArrayList<Specialization>();
		group.add(Specialization.Alergology);
		double actualAnalysisRequests = AllAnalysisRequests.getTotalPriceOfRequestsByGroupByPatient(group, patient,
				days, start, end);
		assertEquals(0.0, actualAnalysisRequests);
	}

	@Test
	public void testCalculateStatisticsPerAnalysisTypeByPatient() {
		before();
		Map<LocalDate, Double> expectedMap = new HashMap<LocalDate, Double>();
		expectedMap.put(LocalDate.of(2020, 8, 25), 3.5);

		AnalysisType analysis = AllAnalysisTypes.getAnalysisTypeByName("vitamin e");
		Patient patient = Users.getPatientByUsername("nPatient");
		Map<LocalDate, Double> map = AllAnalysisRequests.calculateStatisticsPerAnalysisTypeByPatient(patient, analysis);
		assertEquals(expectedMap, map);
	}
	
	@Test
	public void testGetAnalysisRequestById() {
		AnalysisRequest expected = AllAnalysisRequests.listOfRequests.get(0);
		String id = expected.getId();
		AnalysisRequest actual = AllAnalysisRequests.getAnalysisRequestById(id);
		assertEquals(expected, actual);
	}
}
