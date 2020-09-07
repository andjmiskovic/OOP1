package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import models.AnalysisRequest;
import models.AnalysisType;
import models.Patient;
import tableModels.AnalysisRecommendationModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RequestDetailsDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static AnalysisRequest analysisRequest;
	public static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public RequestDetailsDialog(AnalysisRequest analysisRequest) {
		setTitle(analysisRequest.getId() + "- Details");
		RequestDetailsDialog.analysisRequest = analysisRequest;
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		initGUI();
		pack();
	}

	private void initGUI() {
		ArrayList<Map.Entry<AnalysisType, Double>> results = new ArrayList<Map.Entry<AnalysisType, Double>>();
		for (Map.Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet())
			results.add(entry);
		getContentPane().add(setUpTable(results));
	}

	public static JPanel setUpTable(ArrayList<Map.Entry<AnalysisType, Double>> results) {
		table = new JTable(new AnalysisRecommendationModel(results));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		JButton print = new JButton("click here to print the results");
		print.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				printResults(analysisRequest);
			}
		});
		print.setForeground(Color.WHITE);
		print.setBackground(myBlue);
		print.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelTable.add(print, BorderLayout.SOUTH);
		return panelTable;
	}

	public static char[] printResultsText(AnalysisRequest analysisRequest) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		Patient p = analysisRequest.getPatient();
		String text = "Analysis Results for patient " + p.getName() + " " + p.getLastName();
		text += "\nAddress: " + p.getAddress() + "\nLBO: " + p.getLBO();
		text += "\nRequested date: " + analysisRequest.getRequestedDate().format(dateTimeFormatter);
		text += "\nProcessed date: ";
		if (analysisRequest.getProcessedDate() != null)
			text += analysisRequest.getProcessedDate().format(dateTimeFormatter);
		else
			text += "still processing";
		text += "\n\nAnalysis data:\n";
		for (Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet()) {
			text += "\n" + entry.getKey().getName() + ": " + entry.getKey().getDescription() + "\nLower Bound: "
					+ String.valueOf(entry.getKey().getLowerBound()) + "\tUpper Bound: "
					+ String.valueOf(entry.getKey().getUpperBound()) + "\tMeasured Value: "
					+ String.valueOf(entry.getValue()) + " " + entry.getKey().getUnit() + "\n";
		}
		text += "\n\nThank you for trusting us.\nLabi's Lab, " + LocalDate.now().format(dateTimeFormatter);
		return text.toCharArray();
	}

	public static void printResults(AnalysisRequest analysisRequest) {
		try {
			String fileName = "src/Files/myResults"
					+ analysisRequest.getRequestedDate().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_"))
					+ analysisRequest.getId() + ".txt";
			File myObj = new File(fileName);
			if (myObj.createNewFile()) {
				FileWriter myWriter = new FileWriter(fileName);
				myWriter.write(printResultsText(analysisRequest));
				myWriter.close();
				JOptionPane.showMessageDialog(null, "Successfully wrote to the file.", "Done",
						JOptionPane.PLAIN_MESSAGE);
				if (!Desktop.isDesktopSupported())
					JOptionPane.showMessageDialog(null, "An error occurred, opening file throw Desktop did not work!",
							"Error", JOptionPane.ERROR_MESSAGE);
				Desktop desktop = Desktop.getDesktop();
				if (myObj.exists())
					desktop.open(myObj);
			} else
				JOptionPane.showMessageDialog(null, "File already exists.", "Error", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException error) {
			JOptionPane.showMessageDialog(null, "An error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
			error.printStackTrace();
		}
	}
}
