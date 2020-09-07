package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
import models.CurrentStateOfRequest;
import models.CurrentUser;
import tableModels.MTRequestModel;

public class PanelRequestsMT extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JButton sampleTaken;
	static JButton acceptSampling;
	static JButton printTheRequest;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelRequestsMT() {
		setUp();
	}

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelAnalysisRequests = new JLabel("ANALYSIS REQUESTS");
		labelAnalysisRequests.setForeground(Color.WHITE);
		labelAnalysisRequests.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelAnalysisRequests.setBounds(165, 51, 608, 59);
		changingPanel.add(labelAnalysisRequests);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/requestMTBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		table = new JTable(new MTRequestModel(AllAnalysisRequests.listOfRequests));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
				MTRequestModel.sort(index);
				refreshData();
			}
		});

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
		changingPanel.add(panelTable);

		acceptSampling = new JButton("ACCEPT SAMPLING");
		acceptSampling.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				AllAnalysisRequests.setMedicalTechnicial(table.getModel().getValueAt(line, 0).toString(),
						CurrentUser.getCurrentMedicalTechnicial());
				acceptSampling.setEnabled(false);
				refreshData();
			}
		});
		acceptSampling.setBounds(826, 829, 239, 64);
		acceptSampling.setForeground(SystemColor.text);
		acceptSampling.setFont(new Font("Dialog", Font.PLAIN, 17));
		acceptSampling.setEnabled(false);
		acceptSampling.setBackground(myBlue);
		changingPanel.add(acceptSampling);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					int line = table.getSelectedRow();
					AnalysisRequest analysisRequest = AllAnalysisRequests
							.getAnalysisRequestById(table.getModel().getValueAt(line, 0).toString());
					if (analysisRequest.getMedicalTechnicial() == null)
						acceptSampling.setEnabled(true);
					if (analysisRequest.getMedicalTechnicial() == CurrentUser.getCurrentMedicalTechnicial())
						sampleTaken.setEnabled(true);
					if (analysisRequest.getCurrentState().equals(CurrentStateOfRequest.PROCESSING))
						sampleTaken.setEnabled(false);
					printTheRequest.setEnabled(true);
				}
			}
		});

		sampleTaken = new JButton("SAMPLE TAKEN");
		sampleTaken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				AllAnalysisRequests.setSampleTaken(table.getModel().getValueAt(line, 0).toString());
				sampleTaken.setEnabled(false);
				refreshData();
			}
		});
		sampleTaken.setForeground(Color.WHITE);
		sampleTaken.setEnabled(false);
		sampleTaken.setFont(new Font("Dialog", Font.PLAIN, 17));
		sampleTaken.setBackground(new Color(6, 56, 74));
		sampleTaken.setBounds(571, 829, 239, 64);
		changingPanel.add(sampleTaken);

		printTheRequest = new JButton("PRINT THE REQUEST");
		printTheRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AnalysisRequest analysisRequest = AllAnalysisRequests
						.getAnalysisRequestById(table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
				RequestDetailsDialog.printResults(analysisRequest);
			}
		});
		printTheRequest.setForeground(Color.WHITE);
		printTheRequest.setEnabled(false);
		printTheRequest.setFont(new Font("Dialog", Font.PLAIN, 17));
		printTheRequest.setBackground(new Color(6, 56, 74));
		printTheRequest.setBounds(317, 829, 239, 64);
		changingPanel.add(printTheRequest);

		return changingPanel;
	}

	public static void refreshData() {
		MTRequestModel requestModel = (MTRequestModel) table.getModel();
		requestModel.fireTableDataChanged();
		table.repaint();
	}
}
