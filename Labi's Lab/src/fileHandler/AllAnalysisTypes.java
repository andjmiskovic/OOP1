package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import models.AnalysisType;
import models.Discount;
import models.Specialization;

public class AllAnalysisTypes {
	public static ArrayList<AnalysisType> listOfAnalysisTypes;
	public static String fileName = "src/files/analysis_typ.csv";

	public static void readFromCSV() {
		listOfAnalysisTypes = new ArrayList<AnalysisType>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				AnalysisType analysis = createAnalysisType(attributes);
				listOfAnalysisTypes.add(analysis);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static AnalysisType createAnalysisType(String[] attributes) {
		String name = attributes[0].trim();
		String description = attributes[1].trim();
		String unit = attributes[2].trim();
		String specString = attributes[3].trim().substring(0, 1).toUpperCase()
				+ attributes[3].trim().substring(1).toLowerCase();
		Specialization specialization = Specialization.valueOf(specString.trim());
		double price = Double.parseDouble(attributes[4]);
		double lowerBound = Double.parseDouble(attributes[5]);
		double upperBound = Double.parseDouble(attributes[6]);
		try {
			String discountID = attributes[7].trim();
			Discount discount = AllDiscounts.getDiscountById(discountID);
			String discountID2 = attributes[8].trim();
			Discount discount2 = AllDiscounts.getDiscountById(discountID2);
			return new AnalysisType(name, description, unit, specialization, price, lowerBound, upperBound, discount,
					discount2);
		} catch (Exception e) {
			return new AnalysisType(name, description, unit, specialization, price, lowerBound, upperBound, null, null);
		}

	}

	public static void removeAnalysisType(String name) {
		listOfAnalysisTypes.remove(getAnalysisTypeByName(name));
		saveData();
	}

	public static void addAnalysisType(String name, String description, String unit, Specialization group, double price,
			double lowerBound, double upperBound, Discount discount, Discount d2) {
		listOfAnalysisTypes
				.add(new AnalysisType(name, description, unit, group, price, lowerBound, upperBound, discount, d2));
		saveData();
	}

	public static AnalysisType getAnalysisTypeByName(String name) {
		for (AnalysisType analysisType : listOfAnalysisTypes) {
			if (analysisType.getName().equals(name)) {
				return analysisType;
			}
		}
		return null;
	}

	public static void saveData() {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (AnalysisType type : listOfAnalysisTypes) {
				csvWriter.append(type.toString());
			}
		} catch (Exception e3) {
			e3.printStackTrace();
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

	public static double getAnalysisTypePriceWithDiscount(AnalysisType analysisType, LocalDate date,
			boolean groupDiscount) {
		double price = 0;
		if (groupDiscount) {
			if (analysisType.getGroupDiscount() != null) {
				LocalDate today = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (analysisType.getGroupDiscount().getEndDate().isAfter(date)
						&& analysisType.getGroupDiscount().getDaysOfDiscount().contains(today.getDayOfWeek()))
					price = analysisType.getPrice() * analysisType.getGroupDiscount().getValue();
				else
					price = analysisType.getPrice();
			}
		} else
			price = analysisType.getPrice();

		if (analysisType.getTypeDiscount() != null) {
			LocalDate today = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (analysisType.getTypeDiscount().getEndDate().isAfter(today)
					&& analysisType.getTypeDiscount().getDaysOfDiscount().contains(today.getDayOfWeek())) {
				return price * analysisType.getTypeDiscount().getValue();
			} else
				return price;
		} else
			return price;
	}

}
