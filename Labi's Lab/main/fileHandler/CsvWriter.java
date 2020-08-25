package fileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CsvWriter {
	public static void main(String[] args) {

		try {
			File nekiFile = new File("");
			nekiFile = new File(nekiFile.getAbsolutePath() + "\\src\\Files\\test.csv");
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(nekiFile);
			StringBuilder sb = new StringBuilder();
			sb.append("id,");
			sb.append(',');
			sb.append("Name");
			sb.append('\n');

			sb.append("1");
			sb.append(',');
			sb.append("Prashant Ghimire");
			sb.append('\n');

			writer.write(sb.toString());

			System.out.println("done!");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}
}