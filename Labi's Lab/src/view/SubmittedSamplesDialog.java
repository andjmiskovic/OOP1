package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
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

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import functions.GeneralFunctions;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentStateOfRequest;
import models.CurrentUser;
import models.Specialization;
import net.miginfocom.swing.MigLayout;
import tableModels.AnalysisRecommendationModel;

public class SubmittedSamplesDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	public static ArrayList<Entry<AnalysisType, Double>> analysisTypes;
	public static AnalysisRequest analysisRequest;
	public static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public SubmittedSamplesDialog(AnalysisRequest analysisRequest,
			ArrayList<Entry<AnalysisType, Double>> analysisTypes) {
		setTitle(analysisRequest.getId() + "- Proccessing");
		SubmittedSamplesDialog.analysisTypes = analysisTypes;
		SubmittedSamplesDialog.analysisRequest = analysisRequest;
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		initGUI();
		pack();
	}

	private void initGUI() {
		try {
			table = new JTable(new AnalysisRecommendationModel(analysisTypes));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error. We cannot open this table", "Error", JOptionPane.ERROR_MESSAGE);
		}
		table = new JTable(new AnalysisRecommendationModel(analysisTypes));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.WHITE);
		panelButtons.setLayout(new MigLayout("", "[][][grow]", "[][]"));

		JButton randomValue = new JButton("GET VALUE BY MACHINE");
		randomValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to select an analysis type first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						AnalysisType analysisType = AllAnalysisTypes
								.getAnalysisTypeByName(table.getValueAt(line, 0).toString());
						Map<AnalysisType, Double> analyses = analysisRequest.getAnalyses();
						analyses.put(analysisType, GeneralFunctions.getRandomValue(analysisType));
						AnalysisRequest newAnalysisRequest = AllAnalysisRequests
								.getAnalysisRequestById(analysisRequest.getId());
						newAnalysisRequest.setAnalyses(analyses);
						if (GeneralFunctions.isRequestFinished(newAnalysisRequest)) {
							newAnalysisRequest.setCurrentState(CurrentStateOfRequest.FINISHED_REPORT);
							newAnalysisRequest.setProcessedDate(LocalDate.now());
						}
						if (GeneralFunctions.isGroupProccessed(newAnalysisRequest, analysisType)) {
							Map<Specialization, Chemist> processedGroups = newAnalysisRequest.getProcessedGroups();
							processedGroups.put(analysisType.getGroup(), CurrentUser.getCurrentChemist());
							newAnalysisRequest.setProcessedGroups(processedGroups);
							ArrayList<Specialization> unprocessedGroups = newAnalysisRequest.getUnprocessedGroups();
							unprocessedGroups.remove(analysisType.getGroup());
							newAnalysisRequest.setUnprocessedGroups(unprocessedGroups);
						}
						AllAnalysisRequests.listOfRequests.remove(analysisRequest);
						AllAnalysisRequests.addAnalysisRequest(newAnalysisRequest);
						refreshData();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "We have a problem. Try again later.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		randomValue.setForeground(Color.WHITE);
		randomValue.setBackground(myBlue);
		randomValue.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelButtons.add(randomValue, "cell 0 0,alignx center,grow");

		JButton enterValue = new JButton("ENTER VALUE BY HAND");
		enterValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to select an analysis type first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String analysisName = table.getValueAt(line, 0).toString();
					AnalysisType analysisType = AllAnalysisTypes.getAnalysisTypeByName(analysisName);
					EnterValue enterValue = new EnterValue(analysisRequest.getId(), analysisType);
					enterValue.setVisible(true);
					enterValue.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							refreshData();
						}
					});
				}
			}
		});
		enterValue.setForeground(Color.WHITE);
		enterValue.setBackground(myBlue);
		enterValue.setFont(new Font("Dialog", Font.PLAIN, 20));
		panelButtons.add(enterValue, "cell 0 1,alignx center,grow");
		panelTable.add(panelButtons, BorderLayout.SOUTH);

		getContentPane().add(panelTable);
	}

	public static void refreshData() {
		AnalysisRecommendationModel requestModel = (AnalysisRecommendationModel) table.getModel();
		requestModel.fireTableDataChanged();
		table.repaint();
	}

}
