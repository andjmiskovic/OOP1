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

import fileHandler.Users;
import models.Employee;
import tableModels.EmployeesModel;
import tableModels.MTRequestModel;

public class PanelEmployees extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JTable table;
	static JButton edit;
	static JButton plus;
	static JLabel delete;
	static ArrayList<Employee> employees;
	static Color myBlue = new Color(6, 56, 74);
	static Font font = new Font("Tahoma", Font.PLAIN, 20);

	public PanelEmployees() {
		employees = Users.getEmployees();
		setUp();

	}

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelPatients = new JLabel("EMPLOYEES");
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelPatients.setBounds(165, 51, 516, 59);
		changingPanel.add(labelPatients);

		table = new JTable(new EmployeesModel(employees));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);

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
		if (employees.size() > 0) {
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
		changingPanel.add(panelTable);

		edit = new JButton("EDIT");
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to pick someone.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String username = table.getValueAt(line, 1).toString();
					String role = table.getValueAt(line, 0).toString();
					Employee employee = Users.getEmployeeByUserName(role, username);
					if (employee != null) {
						EmployeeAddEditDialog editDialog = new EmployeeAddEditDialog(employee);
						editDialog.setVisible(true);
						editDialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								refreshData();
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "We cannon find this employee!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		edit.setBounds(865, 828, 200, 64);
		edit.setFont(font);
		edit.setBackground(myBlue);
		edit.setForeground(SystemColor.text);
		changingPanel.add(edit);

//		delete = new JLabel("");
//		delete.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int line = table.getSelectedRow();
//				if (line == -1) {
//					JOptionPane.showMessageDialog(null, "You have to pick someone.", "Error",
//							JOptionPane.WARNING_MESSAGE);
//				} else {
//					String username = table.getValueAt(line, 1).toString();
//					String role = table.getValueAt(line, 0).toString();
//					Employee employee = Users.getEmployeeByUserName(role, username);
//					if (employee != null) {
//						int choice = JOptionPane.showConfirmDialog(null,
//								"Are you sure you want to delete this employee?",
//								employee.getName() + " " + employee.getLastName() + " - delete confirmation",
//								JOptionPane.YES_NO_OPTION);
//						if (choice == JOptionPane.YES_OPTION) {
//							Users.removeEmployee(role, username);
//							employees.remove(Users.getEmployeeByUserName(username));
//							refreshData();
//						}
//					} else {
//						JOptionPane.showMessageDialog(null, "We cannon delete this employee!", "Error",
//								JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		});
//		delete.setIcon(new ImageIcon(PanelPatients.class.getResource("/pictures/trash.jpg")));
//		delete.setBounds(1000, 830, 60, 60);
//		changingPanel.add(delete);

		plus = new JButton("ADD");
		plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				EmployeeAddEditDialog editDialog = new EmployeeAddEditDialog(null);
				editDialog.setVisible(true);
				refreshData();
				editDialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						refreshData();
					}
				});
			}
		});
		plus.setFont(font);
		plus.setBackground(myBlue);
		plus.setForeground(SystemColor.text);
		plus.setBounds(650, 828, 200, 64);
		changingPanel.add(plus);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/employeesBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		return changingPanel;
	}

	public static void refreshData() {
		EmployeesModel employeesModel = (EmployeesModel) table.getModel();
		employeesModel.fireTableDataChanged();
		table.repaint();
	}
}