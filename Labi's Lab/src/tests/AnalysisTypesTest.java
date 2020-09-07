package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import models.AnalysisType;

class AnalysisTypesTest {

	@Test
	public void testGetAnalysisTypePriceWithDiscount() {
		AllDiscounts.readFromCSV();
		AllAnalysisTypes.readFromCSV();
		AnalysisType analysisType = AllAnalysisTypes.getAnalysisTypeByName("vitamin e");
		double price = AllAnalysisTypes.getAnalysisTypePriceWithDiscount(analysisType, LocalDate.of(2020, 8, 30),
				false);
		assertEquals(1800 * 0.9, price);
	}
	
	@Test
	public void testGetAnalysisTypeByName() {
		AnalysisType expected = AllAnalysisTypes.listOfAnalysisTypes.get(0);
		String name = expected.getName();
		AnalysisType actual = AllAnalysisTypes.getAnalysisTypeByName(name);
		assertEquals(expected, actual);
	}

}
