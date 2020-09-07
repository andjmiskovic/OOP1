package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import models.Admin;
import models.Chemist;
import models.MedicalTechnicial;
import models.Patient;

class UsersTest {

	@Before
	public void before() {
		Users.readUsersFromCSV();
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		SalaryCoefficients.readFromCSV();
		AllAnalysisRequests.readRequestsFromCSV();
	}

	@Test
	public void testGetPatientByLBO() {
		Users.readUsersFromCSV();
		Patient expectedPatient = Users.getPatientByUsername("p");
		Patient actualPatient = Users.getPatientByLBO(expectedPatient.getLBO());
		assertEquals(expectedPatient, actualPatient);
	}

	@Test
	public void testGetPatientByUsername() {
		Users.readUsersFromCSV();
		Patient expectedPatient = Users.getPatientByLBO("00000000009");
		Patient actualPatient = Users.getPatientByUsername(expectedPatient.getUserName());
		assertEquals(expectedPatient, actualPatient);
	}

	@Test
	public void testIncomesPerEmployeeOccupation() {
		before();
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		days.add(DayOfWeek.MONDAY);
		LocalDate start = LocalDate.of(2020, 8, 25);
		LocalDate end = LocalDate.of(2020, 8, 27);
		Map<String, Double> map = Users.incomesPerEmployeeOccupation(start, end, days);
		Map<String, Double> expectedMap = new HashMap<String, Double>();
		expectedMap.put("Admin", 0.0);
		expectedMap.put("Medical Technicial", 0.0);
		expectedMap.put("Chemist", 0.0);
		assertEquals(expectedMap, map);
	}
	
	@Test
	public void testGetChemistByUserName() {
		before();
		Chemist expected = Users.listofChemists.get(0);
		String id = expected.getUserName();
		Chemist actual = Users.getChemistByUsername(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetMedicaTechnicialByUserName() {
		before();
		MedicalTechnicial expected = Users.listOfMedicalTechnicials.get(0);
		String id = expected.getUserName();
		MedicalTechnicial actual = Users.getMedicalTechnicialByUsername(id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetAdminByUserName() {
		before();
		Admin expected = Users.listOfAdmins.get(0);
		String id = expected.getUserName();
		Admin actual = Users.getAdminByUsername(id);
		assertEquals(expected, actual);
	}

}
