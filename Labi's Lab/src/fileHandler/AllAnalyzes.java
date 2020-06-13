package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import classes.Analyze;

public class AllAnalyzes {
	public static ArrayList<Analyze> listOfAnalyzes;
	public static ArrayList<String> listOfTypes = new ArrayList<String>(Arrays.asList("USLUGE", "ALERGOLOGIJA",
			"BIOHEMIJA", "HEMATOLOGIJ_I_HEMOSTAZA", "IMUNOLOGIJA", "LEKOVI", "IMUNO_HEMIJA", "IMUNOHEMIJA_ELIZA",
			"TRANSFUZIOLOGIJA", "SEROLOGIJA", "SEROLOGIJA_AUTOMATIZOVANA", "MIKROBIOLOGIJA", "GENETIKA"));

	private static void readAnalyzesFromCSV(String fileName) {
		listOfAnalyzes = new ArrayList<Analyze>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				Analyze analyze = createAnalyze(attributes);
				listOfAnalyzes.add(analyze);
			}
			line = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static Analyze createAnalyze(String[] attributes) {
		for (int i = 0; i < attributes.length; i++) {
			attributes[i].trim();
		}
		String id = attributes[0];
		String type = attributes[1];
		String description = attributes[2];
		String unit = attributes[3];
		double minValue = Double.parseDouble(attributes[4]);
		double maxValue = Double.parseDouble(attributes[5]);
		double price = Double.parseDouble(attributes[6]);
		double discount = Double.parseDouble(attributes[7]);
		return new Analyze(id, type, description, unit, minValue, maxValue, price, discount);
	}

	public void convertToCSV(String fileName) throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e4) {
			e4.printStackTrace();
		}
		try {
			for (Analyze analyze : listOfAnalyzes) {
				csvWriter.append(analyze.toString());
			}
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		csvWriter.flush();
		csvWriter.close();
	}

}
