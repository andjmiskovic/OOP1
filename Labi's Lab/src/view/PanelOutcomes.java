package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.time.DateUtils;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.toedter.calendar.JDateChooser;

import fileHandler.Users;
import models.Employee;
import models.EmployeeStatistics;
import net.miginfocom.swing.MigLayout;
import tableModels.OutcomesEmployeeModel;

public class PanelOutcomes extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel, chartPanel, panelTable, pSearch;
	static JDateChooser startDate, endDate;
	static PieChart chart;
	static JTextField tfSearch;
	static JTable table;
	static ArrayList<EmployeeStatistics> employees;
	static ArrayList<JCheckBox> dayCheckboxs;
	static Color myBlue = new Color(6, 56, 74);

	public PanelOutcomes() {
		employees = new ArrayList<EmployeeStatistics>();
		for (Employee employee : Users.getEmployees())
			employees.add(new EmployeeStatistics(employee));
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("EXPENSES");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/outcomesBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblStartDate = new JLabel("Choose the date range");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStartDate.setBounds(39, 146, 213, 36);
		changingPanel.add(lblStartDate);

		startDate = new JDateChooser();
		startDate.setDateFormatString("dd.MM.yyyy.");
		startDate.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setBounds(39, 187, 105, 36);
		startDate.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refresh();
				endDate.setMinSelectableDate(startDate.getDate());
			}
		});
		changingPanel.add(startDate);

		endDate = new JDateChooser();
		endDate.setDateFormatString("dd.MM.yyyy.");
		endDate.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		endDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		endDate.setBounds(179, 187, 105, 36);
		endDate.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refresh();
			}
		});
		changingPanel.add(endDate);

		ChangeListener checkboxChangeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		};

		JPanel daysPanel = new JPanel(new MigLayout());
		daysPanel.setBounds(332, 187, 729, 36);
		daysPanel.setBackground(myBlue);
		dayCheckboxs = new ArrayList<>();
		for (DayOfWeek day : DayOfWeek.values()) {
			JCheckBox dayCheckbox = new JCheckBox(
					day.toString().substring(0, 1).toUpperCase() + day.toString().substring(1).toLowerCase());
			dayCheckbox.setBackground(myBlue);
			dayCheckbox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			dayCheckbox.setForeground(Color.WHITE);
			dayCheckbox.addChangeListener(checkboxChangeListener);
			dayCheckboxs.add(dayCheckbox);
			daysPanel.add(dayCheckbox);
		}
		changingPanel.add(daysPanel);

		JLabel lblSelectDaysOf = new JLabel("Select days of week");
		lblSelectDaysOf.setForeground(Color.WHITE);
		lblSelectDaysOf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectDaysOf.setBounds(332, 146, 213, 36);
		changingPanel.add(lblSelectDaysOf);

		chart = new PieChartBuilder().width(800).height(600).title("Costs by employee occupations").build();
		Color[] sliceColors = new Color[] { new Color(255, 215, 0), new Color(178, 34, 34), new Color(255, 69, 0) };
		chart.getStyler().setSeriesColors(sliceColors);
		chartPanel = new XChartPanel<PieChart>(chart);
		chartPanel.setBounds(39, 510, 1022, 414);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);

		chart.addSeries("Admin", 0);
		chart.addSeries("Chemist", 0);
		chart.addSeries("Medical Technicial", 0);
		changingPanel.add(chartPanel);

		JLabel line1 = new JLabel("-");
		line1.setForeground(Color.WHITE);
		line1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		line1.setBounds(154, 187, 10, 36);
		changingPanel.add(line1);

		panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(39, 274, 1022, 212);

		pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);

		table = new JTable(new OutcomesEmployeeModel(employees));
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);

		if (employees.size() > 0) {
			TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
			tableSorter.setModel((AbstractTableModel) table.getModel());
			table.setRowSorter(tableSorter);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
					OutcomesEmployeeModel.sort(index);
					OutcomesEmployeeModel employeeModel = (OutcomesEmployeeModel) table.getModel();
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
	}

	public static void refresh() {
		LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		ArrayList<DayOfWeek> daysOfWeeks = new ArrayList<DayOfWeek>();
		for (JCheckBox checkbox : dayCheckboxs) {
			if (checkbox.isSelected())
				daysOfWeeks.add(DayOfWeek.valueOf(checkbox.getText().toUpperCase()));
		}

		Map<String, Double> map = Users.incomesPerEmployeeOccupation(start, end, daysOfWeeks);
		chart.removeSeries("Admin");
		chart.addSeries("Admin", map.get("Admin"));

		chart.removeSeries("Medical Technicial");
		chart.addSeries("Medical Technicial", map.get("Medical Technicial"));

		chart.removeSeries("Chemist");
		chart.addSeries("Chemist", map.get("Chemist"));

		chartPanel.revalidate();
		chartPanel.repaint();

		for (EmployeeStatistics employeeStatistics : employees)
			employeeStatistics.update(start, end, daysOfWeeks);
		refreshTable();
	}

	public static void refreshTable() {
		OutcomesEmployeeModel patientModel = (OutcomesEmployeeModel) table.getModel();
		patientModel.fireTableDataChanged();
		table.repaint();
	}
}
