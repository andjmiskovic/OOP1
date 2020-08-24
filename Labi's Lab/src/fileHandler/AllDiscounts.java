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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import classes.Discount;

public class AllDiscounts {
	public static ArrayList<Discount> listOfDiscounts;
	public static String fileName = "C:\\Users\\Lenovo\\git\\OOP1\\Labi's Lab\\src\\Files\\discounts.csv";

	public static void readFromCSV() throws IOException {
		listOfDiscounts = new ArrayList<Discount>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				Discount discount = createDiscount(attributes);
				listOfDiscounts.add(discount);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static Discount createDiscount(String[] attributes) {
		String id = attributes[0].trim();
		double singleDiscount = Double.parseDouble(attributes[1]);
		ArrayList<DayOfWeek> daysOfDiscount = new ArrayList<DayOfWeek>();
		String dayString = attributes[2].trim();
		for (int i = 0; i < dayString.length(); i++) {
			daysOfDiscount.add(DayOfWeek.of(Character.getNumericValue(dayString.charAt(i))));
		}
		LocalDate startDate = LocalDate.parse(attributes[3].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		LocalDate endDate = LocalDate.parse(attributes[4].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		return new Discount(id, singleDiscount, daysOfDiscount, startDate, endDate);
	}

	public static void saveData() throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (Discount discount : AllDiscounts.listOfDiscounts) {
				csvWriter.append(discount.toString());
			}

		} catch (Exception e3) {
			e3.printStackTrace();
		}
		csvWriter.flush();
		csvWriter.close();
	}

	public static Discount getDiscountById(String id) {
		if (id.equals(""))
			return null;
		for (Discount discount : listOfDiscounts) {
			if (discount.getId().equals(id)) {
				return discount;
			}
		}
		return null;
	}
}
