package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import fileHandler.AllAnalysisRequests;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentUser;
import tableModels.ChemistRequestsModel;
import tableModels.MTRequestModel;

public class PanelSubmittedSamples extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JButton sampleTaken;
	static JButton acceptProccessing;
	static JButton printTheRequest;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelSubmittedSamples() {
		setUp();
	}

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelAnalysisRequests = new JLabel("SUBMITTED SAMPLES");
		labelAnalysisRequests.setForeground(Color.WHITE);
		labelAnalysisRequests.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelAnalysisRequests.setBounds(165, 51, 608, 59);
		changingPanel.add(labelAnalysisRequests);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/bloodSamplesBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		ArrayList<AnalysisRequest> requests = AllAnalysisRequests.getRequestsWithTakenSamples();
		table = new JTable(new ChemistRequestsModel(requests));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setBounds(50, 160, 1015, 650);
		panelTable.setLayout(new BorderLayout(0, 0));

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		JTextField tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		if (requests.size() > 0) {
			TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
			tableSorter.setModel((AbstractTableModel) table.getModel());
			table.setRowSorter(tableSorter);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
					MTRequestModel.sort(index);
					refreshData();
				}
			});
			tfSearch.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void removeUpdate(DocumentEvent e) {
					changedUpdate(e);
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					changedUpdate(e);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					if (tfSearch.getText().trim().length() == 0) {
						tableSorter.setRowFilter(null);
					} else {
						tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
					}
				}
			});
		}
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);

		changingPanel.add(panelTable);

		acceptProccessing = new JButton("ACCEPT PROCCESSING");
		acceptProccessing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line > -1) {
					AnalysisRequest analysisRequest = AllAnalysisRequests
							.getAnalysisRequestById(table.getValueAt(line, 0).toString());
					ArrayList<Map.Entry<AnalysisType, Double>> results = new ArrayList<Map.Entry<AnalysisType, Double>>();
					for (Map.Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet())
						results.add(entry);
					ArrayList<Entry<AnalysisType, Double>> analysisTypes = filteredAnalysisTypesByCurrentChemistsSpecialization(
							results);
					if (analysisTypes.size() == 0)
						JOptionPane.showMessageDialog(null,
								"You are not specialized in any of fields required for proccessing this request!",
								"Error", JOptionPane.ERROR_MESSAGE);
					else {
						acceptProccessing.setEnabled(false);
						SubmittedSamplesDialog editDialog = new SubmittedSamplesDialog(analysisRequest, analysisTypes);
						editDialog.setVisible(true);
						editDialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								refreshData();
							}
						});
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"You have to select a request first!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		acceptProccessing.setBounds(711, 829, 354, 64);
		acceptProccessing.setForeground(SystemColor.text);
		acceptProccessing.setFont(new Font("Dialog", Font.PLAIN, 17));
		acceptProccessing.setEnabled(false);
		acceptProccessing.setBackground(myBlue);
		changingPanel.add(acceptProccessing);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1)
					acceptProccessing.setEnabled(true);
			}
		});

		return changingPanel;
	}

	private static ArrayList<Map.Entry<AnalysisType, Double>> filteredAnalysisTypesByCurrentChemistsSpecialization(
			ArrayList<Map.Entry<AnalysisType, Double>> results) {
		Chemist currentChemist = CurrentUser.getCurrentChemist();
		ArrayList<Map.Entry<AnalysisType, Double>> analyses = new ArrayList<Map.Entry<AnalysisType, Double>>();
		for (Map.Entry<AnalysisType, Double> entry : results) {
			if (currentChemist.getListOfSpecializations().contains(entry.getKey().getGroup()))
				analyses.add(entry);
		}
		return analyses;
	}

	public static void refreshData() {
		ChemistRequestsModel requestModel = (ChemistRequestsModel) table.getModel();
		requestModel.fireTableDataChanged();
		table.repaint();
	}
}
