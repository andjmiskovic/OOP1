package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import models.Patient;
import fileHandler.Users;
import models.PatientStatistics;
import models.Specialization;
import net.miginfocom.swing.MigLayout;
import tableModels.PatientStatisticsModel;

public class PanelPatientsStatistics extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel, panelTable, pSearch;
	static JDateChooser startDate, endDate;
	static JTextField tfSearch;
	static ArrayList<JCheckBox> specializationCheckBoxes, dayCheckboxs;
	static ArrayList<PatientStatistics> patientStatistics;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelPatientsStatistics() {
		patientStatistics = new ArrayList<PatientStatistics>();
		for (Patient patient : Users.listOfPatients)
			patientStatistics.add(new PatientStatistics(patient));
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("PATIENTS STATISTICS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelPatientsStatistics.class.getResource("/pictures/patientStatisticsBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblStartDate = new JLabel("Choose the date range");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStartDate.setBounds(39, 168, 213, 36);
		changingPanel.add(lblStartDate);

		startDate = new JDateChooser();
		startDate.setDateFormatString("dd.MM.yyyy.");
		startDate.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setBounds(39, 209, 105, 36);
		changingPanel.add(startDate);

		endDate = new JDateChooser();
		endDate.setDateFormatString("dd.MM.yyyy.");
		endDate.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		endDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		endDate.setBounds(179, 209, 105, 36);
		endDate.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refresh();
			}
		});
		changingPanel.add(endDate);
		startDate.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refresh();
				endDate.setMinSelectableDate(startDate.getDate());
			}
		});

		ItemListener checkboxChangeListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				refresh();
			}
		};

		JPanel daysPanel = new JPanel(new MigLayout());
		daysPanel.setBounds(332, 209, 729, 36);
		daysPanel.setBackground(myBlue);
		dayCheckboxs = new ArrayList<>();
		for (DayOfWeek day : DayOfWeek.values()) {
			JCheckBox dayCheckbox = new JCheckBox(
					day.toString().substring(0, 1).toUpperCase() + day.toString().substring(1).toLowerCase());
			dayCheckbox.setBackground(myBlue);
			dayCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			dayCheckbox.setForeground(Color.WHITE);
			dayCheckbox.addItemListener(checkboxChangeListener);
			dayCheckboxs.add(dayCheckbox);
			daysPanel.add(dayCheckbox);
		}
		changingPanel.add(daysPanel);

		JLabel lblSelectDaysOf = new JLabel("Select days of week");
		lblSelectDaysOf.setForeground(Color.WHITE);
		lblSelectDaysOf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectDaysOf.setBounds(332, 168, 213, 36);
		changingPanel.add(lblSelectDaysOf);

		JLabel line1 = new JLabel("-");
		line1.setForeground(Color.WHITE);
		line1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		line1.setBounds(154, 209, 10, 36);
		changingPanel.add(line1);

		panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(39, 420, 1022, 487);

		pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);

		table = new JTable(new PatientStatisticsModel(patientStatistics));
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);

		if (patientStatistics.size() > 0) {
			TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
			tableSorter.setModel((AbstractTableModel) table.getModel());
			table.setRowSorter(tableSorter);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
					PatientStatisticsModel.sort(index);
					PatientStatisticsModel employeeModel = (PatientStatisticsModel) table.getModel();
					employeeModel.fireTableDataChanged();
					table.repaint();
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

		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		changingPanel.add(panelTable);

		JPanel panelSpecializations = new JPanel();
		panelSpecializations.setBackground(new Color(6, 56, 74));
		panelSpecializations.setBounds(39, 307, 735, 124);
		panelSpecializations.setLayout(new FlowLayout(FlowLayout.LEFT));
		specializationCheckBoxes = new ArrayList<JCheckBox>();
		for (Specialization specialization : Specialization.values()) {
			JCheckBox specializationCheckBox = new JCheckBox(specialization.toString());
			specializationCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			specializationCheckBox.setForeground(Color.WHITE);
			specializationCheckBox.setBackground(myBlue);
			specializationCheckBox.addItemListener(checkboxChangeListener);
			specializationCheckBoxes.add(specializationCheckBox);
			panelSpecializations.add(specializationCheckBox);
		}
		changingPanel.add(panelSpecializations);

		JLabel lblSelectSpecia = new JLabel("Select specialization groups");
		lblSelectSpecia.setForeground(Color.WHITE);
		lblSelectSpecia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectSpecia.setBounds(39, 271, 213, 36);
		changingPanel.add(lblSelectSpecia);
	}

	public static void refresh() {
		LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		ArrayList<DayOfWeek> daysOfWeeks = new ArrayList<DayOfWeek>();
		for (JCheckBox checkbox : dayCheckboxs) {
			if (checkbox.isSelected()) {
				daysOfWeeks.add(DayOfWeek.valueOf(checkbox.getText().toUpperCase()));

			}
		}
		ArrayList<Specialization> selectedSpecialization = new ArrayList<Specialization>();
		for (JCheckBox checkbox : specializationCheckBoxes) {
			if (checkbox.isSelected()) {
				selectedSpecialization.add(Specialization.fromString(checkbox.getText()));
			}
		}
		for (PatientStatistics patientStatistics : patientStatistics)
			patientStatistics.update(start, end, daysOfWeeks, selectedSpecialization);
		refreshData();
	}

	public static void refreshData() {
		PatientStatisticsModel patientModel = (PatientStatisticsModel) table.getModel();
		patientModel.fireTableDataChanged();
		table.repaint();
	}
}
