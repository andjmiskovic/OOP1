package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import classes.AnalysisRequest;
import classes.AnalysisType;
import models.AnalysisRecommendationModel;

public class RequestDetailsDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private AnalysisRequest analysisRequest;
	public static JTable table;

	public RequestDetailsDialog(AnalysisRequest analysisRequest) {
		setTitle(analysisRequest.getId() + "- Details");
		this.setAnalysisRequest(analysisRequest);
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		pack();
	}

	private void initGUI() {
		ArrayList<Map.Entry<AnalysisType, Double>> results = new ArrayList<Map.Entry<AnalysisType, Double>>();
		for (Map.Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet()) {
			results.add(entry);
		}
		add(setUpTable(results));
	}
	
	public static JPanel setUpTable(ArrayList<Map.Entry<AnalysisType, Double>> results) {
		table = new JTable(new AnalysisRecommendationModel(results));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		panelTable.add(new JButton("click here to print the results"), BorderLayout.SOUTH);
		return panelTable;
	}

	public AnalysisRequest getAnalysisRequest() {
		return analysisRequest;
	}

	public void setAnalysisRequest(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}
}
