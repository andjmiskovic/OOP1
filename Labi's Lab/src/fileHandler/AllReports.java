package fileHandler;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import classes.Analyze;
import classes.Report;

public class AllReports {
	public static ArrayList<Report> listOfReports;
	public static String[] statesOfReport = {"POČETAK OBRADE", "UZET UZORAK", "OBRADA NALAZA", "GOTOV NALAZ"};

	private static void readReportsFromCSV(String fileName) {
		listOfReports = new ArrayList<Report>();

		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				Report report = createReport(attributes);
				listOfReports.add(report);
			}
			line = br.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static Report createReport(String[] attributes) {
		for (int i = 0; i < attributes.length; i++) {
			attributes[i].trim();
		}
		int id = Integer.parseInt(attributes[0]);
		ArrayList<Analyze> analyzes = new ArrayList<Analyze>();
		for(String analyzeId: attributes[1].split("|")) {
			for(Analyze analyze: AllAnalyzes.listOfAnalyzes) {
				if(analyze.getId().equals(analyzeId.trim())) {
					analyzes.add(analyze);
				}
			}
		}
		String currentState = attributes[2];
		boolean homeVisit = Boolean.parseBoolean(attributes[3]);
		return new Report(id, analyzes, currentState, homeVisit);
	}

	public void convertToCSV(String fileName) throws IOException {
		FileWriter csvWriter = null;
		try {
			csvWriter = new FileWriter(fileName);
		} catch (IOException e4) {
			e4.printStackTrace();
		}
		try {
			for (Report report: listOfReports) {
				csvWriter.append(report.toString());
			}
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		csvWriter.flush();
		csvWriter.close();
	}

}
