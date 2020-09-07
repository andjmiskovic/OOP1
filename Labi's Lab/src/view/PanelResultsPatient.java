package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import fileHandler.AllAnalysisRequests;
import models.AnalysisRequest;
import models.CurrentUser;
import tableModels.RequestModel;

public class PanelResultsPatient extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelResultsPatient() {
		setUp();

	}

	public static JPanel setUp() {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelPatients = new JLabel("HISTORY OF YOUR RESULTS");
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelPatients.setBounds(165, 51, 516, 59);
		changingPanel.add(labelPatients);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/resultsBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblChooseOneOf = new JLabel("Choose one of your previous results to see the details:");
		lblChooseOneOf.setForeground(Color.WHITE);
		lblChooseOneOf.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblChooseOneOf.setBounds(92, 158, 516, 59);
		changingPanel.add(lblChooseOneOf);

		changingPanel.add(setUpTable(AllAnalysisRequests.getAnalysisRequestsByPatient(CurrentUser.getCurrentPatient()),
				92, 213, 957, 476));

		JButton detailsButton = new JButton("SHOW ME DETAILS");
		detailsButton.setBounds(92, 714, 957, 59);
		detailsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick 1 request.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String id = table.getValueAt(line, 0).toString();
					AnalysisRequest analysisRequest = AllAnalysisRequests.getAnalysisRequestById(id);
					if (analysisRequest != null) {
						RequestDetailsDialog editDialog = new RequestDetailsDialog(analysisRequest);
						editDialog.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "We cannon find this request!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		detailsButton.setForeground(SystemColor.text);
		detailsButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		detailsButton.setBackground(myBlue);
		changingPanel.add(detailsButton);

		return changingPanel;
	}

	public static JPanel setUpTable(ArrayList<AnalysisRequest> requests, int x, int y, int w, int l) {
		table = new JTable(new RequestModel(requests));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setBounds(x, y, w, l);
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
			table.setBackground(Color.WHITE);
			table.setForeground(myBlue);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
					RequestModel.sort(index);
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
		return panelTable;
	}

	public static void refreshData() {
		RequestModel requestModel = (RequestModel) table.getModel();
		requestModel.fireTableDataChanged();
		table.repaint();
	}
}
