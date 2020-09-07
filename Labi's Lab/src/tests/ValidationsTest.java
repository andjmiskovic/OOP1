package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import fileHandler.Users;
import functions.Validations;

class ValidationsTest {

	@Before
	public void initialize() {
		Users.readUsersFromCSV();
	}

	@Test
	void testIsUniqueLBO() {
		initialize();
		assertEquals(false, Validations.isUniqueLBO("00000000009"));
	}

	@Test
	void testIsUniqueUserName() {
		assertEquals(false, Validations.isUniqueUserName("m"));
	}

	@Test
	void testIsUniqueLBO_allowedLBO() {
		assertEquals(true, Validations.isUniqueLBO("121212121", "121212121"));
	}

	@Test
	void testIsUniqueUserName_allowedUserName() {
		assertEquals(true, Validations.isUniqueUserName("m", "m"));
	}

	@Test
	void testIsUniqueAnalysisTypeName() {
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		assertEquals(false, Validations.isUniqueAnalysisTypeName("vitamin e"));
	}

	@Test
	void testIsValideName() {
		assertEquals(false, Validations.validName(".ajdakn879"));
	}

	@Test
	void testIsValideLBO() {
		assertEquals(false, Validations.validLBO(".ajdakn879"));
	}

	@Test
	void testIsValidePassword() {
		assertEquals(true, Validations.validPassword("password123"));
	}

	@Test
	void testIsNumeric() {
		assertEquals(false, Validations.isNumeric("8389ut3879"));
	}

	@Test
	void testValidationMessage() {
		initialize();
		assertEquals("", Validations.validationTextForSignUp(false, null, null, "username", "password", "password",
				"12312309809", "Ana", "Anicic", "Adresa 123", "0937580237", LocalDate.of(2000, 3, 1)));
	}
}
