package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import classes.Patient;
import fileHandler.Users;
import models.PatientModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ImageIcon;

public class PanelPatients extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JLabel edit;
	static JLabel delete;
	static JLabel plus;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelPatients() {
		setUp();
	}

	public static void setUpTable(ArrayList<Patient> patients) {
		table = new JTable(new PatientModel(patients));
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
				PatientModel.sort(index);
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
		changingPanel.add(panelTable);

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

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelPatients = new JLabel("PATIENTS");
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelPatients.setBounds(165, 51, 516, 59);
		changingPanel.add(labelPatients);

		edit = new JLabel("");
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to choose a line first.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String username = table.getValueAt(line, 0).toString();
					Patient patient = Users.getPatientByUsername(username);
					if (patient != null) {
						PatientAddEditDialog editDialog = new PatientAddEditDialog(patient);
						editDialog.setVisible(true);
						editDialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								refreshData();
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "We can't find a patient you're looking for!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		edit.setIcon(new ImageIcon(PanelPatients.class.getResource("/Interfaces/pen.jpg")));
		edit.setBounds(935, 830, 60, 60);
		changingPanel.add(edit);

		delete = new JLabel("");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to choose a line first.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String username = table.getValueAt(line, 0).toString();
					Patient patient = Users.getPatientByUsername(username);
					if (patient != null) {
						int choice = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete this patient?",
								patient.getName() + " " + patient.getLastName() + " - Delete",
								JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							Users.removePatient(patient.getUserName());
							refreshData();
						}
					} else {
						JOptionPane.showMessageDialog(null, "We can't find a patient you're looking for!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		delete.setIcon(new ImageIcon(PanelPatients.class.getResource("/Interfaces/trash.jpg")));
		delete.setBounds(1000, 830, 60, 60);
		changingPanel.add(delete);

		plus = new JLabel("");
		plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PatientAddEditDialog editDialog = new PatientAddEditDialog(null);
				editDialog.setVisible(true);
				editDialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						refreshData();
					}
				});
			}
		});
		plus.setIcon(new ImageIcon(PanelPrices.class.getResource("/Interfaces/plusBlue.jpg")));
		plus.setBounds(870, 830, 60, 60);
		changingPanel.add(plus);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/Interfaces/patientsBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		setUpTable(Users.listOfPatients);

		return changingPanel;
	}

	public static void refreshData() {
		PatientModel patientModel = (PatientModel) table.getModel();
		patientModel.fireTableDataChanged();
		table.repaint();
	}
}
