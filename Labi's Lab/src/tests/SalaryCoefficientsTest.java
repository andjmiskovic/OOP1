package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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

class SalaryCoefficientsTest {
	
	@Before
	public void before() {
		Users.readUsersFromCSV();
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		SalaryCoefficients.readFromCSV();
		AllAnalysisRequests.readRequestsFromCSV();
	}

	@Test
	void testGetSalaryCoefficient() {
		assertEquals(0.8, SalaryCoefficients.getSalaryCoefficient(19.5));
	}
	
	@Test
	void testGetSalaryAdmin() {
		Users.readUsersFromCSV();
		SalaryCoefficients.readFromCSV();
		LocalDate start = LocalDate.of(2020, 8, 20);
		LocalDate end = LocalDate.of(2020, 8, 30);
		Admin admin = Users.getAdminByUsername("a");
		double actual = SalaryCoefficients.getSalary(admin, start, end);
		assertEquals(16.67, actual);
	}
	
	@Test
	void testGetSalaryMedicalTechnicial() {
		before();
		LocalDate start = LocalDate.of(2020, 8, 20);
		LocalDate end = LocalDate.of(2020, 8, 30);
		MedicalTechnicial medicalTechnicial = Users.getMedicalTechnicialByUsername("m");
		double actual = SalaryCoefficients.getSalary(medicalTechnicial, start, end);
		assertEquals(7000.0, actual);
	}
	
	@Test
	void testGetSalaryChemist() {
		before();
		LocalDate start = LocalDate.of(2020, 8, 20);
		LocalDate end = LocalDate.of(2020, 8, 30);
		Chemist chemist = Users.getChemistByUsername("c");
		double actual = SalaryCoefficients.getSalary(chemist, start, end);
		assertEquals(6700.0, actual);
	}

}
