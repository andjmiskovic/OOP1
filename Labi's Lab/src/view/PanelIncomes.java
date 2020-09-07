package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
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

import fileHandler.AllAnalysisRequests;
import models.AnalysisRequest;
import models.AnalysisType;
import models.CurrentStateOfRequest;
import models.Specialization;
import net.miginfocom.swing.MigLayout;
import tableModels.IncomesRequestModel;

public class PanelIncomes extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel, chartPanel, panelTable, pSearch;
	static JCheckBox homeVisitCheckBox;
	static JDateChooser startDate, endDate;
	static JSpinner numberOfAnalysisTypes;
	static PieChart chart;
	static JTextField tfSearch;
	static JComboBox<CurrentStateOfRequest> stateOfTheRequest;
	static ArrayList<JCheckBox> specializationCheckBoxes, dayCheckboxs;
	static ArrayList<AnalysisType> analysisTypes;
	static ArrayList<AnalysisRequest> analysisRequests;
	static Color myBlue = new Color(6, 56, 74);

	public PanelIncomes() {
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("INCOME");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/incomesBlue.jpg")));
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

		JLabel lowerAge = new JLabel("Requests with more than               analysis types");
		lowerAge.setForeground(Color.WHITE);
		lowerAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lowerAge.setBounds(332, 146, 424, 36);
		changingPanel.add(lowerAge);

		numberOfAnalysisTypes = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		numberOfAnalysisTypes.setBounds(531, 146, 55, 37);
		numberOfAnalysisTypes.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});
		changingPanel.add(numberOfAnalysisTypes);
		
		ChangeListener checkboxChangeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		};

		stateOfTheRequest = new JComboBox<CurrentStateOfRequest>();
		for (CurrentStateOfRequest currentStateOfRequest : CurrentStateOfRequest.values()) {
			stateOfTheRequest.addItem(currentStateOfRequest);
		}
		stateOfTheRequest.setBounds(511, 212, 245, 30);
		stateOfTheRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		changingPanel.add(stateOfTheRequest);

		JPanel daysPanel = new JPanel(new MigLayout());
		daysPanel.setBounds(39, 274, 245, 236);
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
			daysPanel.add(dayCheckbox, "wrap");
		}
		changingPanel.add(daysPanel);

		JLabel lblSelectDaysOf = new JLabel("Select days of week");
		lblSelectDaysOf.setForeground(Color.WHITE);
		lblSelectDaysOf.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectDaysOf.setBounds(39, 240, 213, 36);
		changingPanel.add(lblSelectDaysOf);

		JPanel specializationsPanel = new JPanel(new MigLayout("", "[grow][grow]", "[grow][grow][grow][grow]"));
		specializationsPanel.setBackground(myBlue);
		specializationsPanel.setBounds(39, 544, 257, 410);
		specializationCheckBoxes = new ArrayList<>();

		for (Specialization specialization : Specialization.values()) {
			JCheckBox specializationCheckBox = new JCheckBox(specialization.toString());
			specializationCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
			specializationCheckBox.setForeground(Color.WHITE);
			specializationCheckBox.setBackground(myBlue);
			specializationCheckBox.addChangeListener(checkboxChangeListener);
			specializationCheckBoxes.add(specializationCheckBox);
			specializationsPanel.add(specializationCheckBox, "wrap");
		}
		changingPanel.add(specializationsPanel);

		homeVisitCheckBox = new JCheckBox("");
		homeVisitCheckBox.addChangeListener(checkboxChangeListener);
		homeVisitCheckBox.setBackground(myBlue);
		homeVisitCheckBox.setBounds(902, 146, 27, 30);
		changingPanel.add(homeVisitCheckBox);

		chart = new PieChartBuilder().width(800).height(600).title("Incomes per specialization group").build();
		Color[] sliceColors = new Color[] { new Color(100, 210, 237), new Color(224, 120, 86), new Color(60, 95, 90), new Color(183, 196, 207),
				new Color(89, 154, 208), new Color(33, 154, 216), new Color(15, 154, 8), new Color(246, 166, 21), new Color(232, 42, 55) };
		chart.getStyler().setSeriesColors(sliceColors);
		chartPanel = new XChartPanel<PieChart>(chart);
		chartPanel.setBounds(332, 510, 767, 436);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);

		for (Specialization specialization : Specialization.values())
			chart.addSeries(specialization.toString(), 0);
		changingPanel.add(chartPanel);

		JLabel lblHomeVisit = new JLabel("Home visit");
		lblHomeVisit.setForeground(Color.WHITE);
		lblHomeVisit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHomeVisit.setBounds(804, 146, 105, 36);
		changingPanel.add(lblHomeVisit);

		JLabel lblState = new JLabel("State of the request");
		lblState.setForeground(Color.WHITE);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblState.setBounds(332, 206, 232, 36);
		changingPanel.add(lblState);

		JLabel lblChooseGroup = new JLabel("Choose specialization group");
		lblChooseGroup.setForeground(Color.WHITE);
		lblChooseGroup.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChooseGroup.setBounds(39, 510, 213, 36);
		changingPanel.add(lblChooseGroup);

		JLabel line1 = new JLabel("-");
		line1.setForeground(Color.WHITE);
		line1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		line1.setBounds(154, 187, 10, 36);
		changingPanel.add(line1);

		panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(332, 274, 767, 212);

		pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);

		panelTable.add(new JScrollPane(setupTable(AllAnalysisRequests.listOfRequests)), BorderLayout.CENTER);
		changingPanel.add(panelTable);

	}

	public static void refresh() {
		LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		ArrayList<Specialization> selectedSpecialization = new ArrayList<Specialization>();
		for (JCheckBox checkbox : specializationCheckBoxes) {
			if (checkbox.isSelected())
				selectedSpecialization.add(Specialization.fromString(checkbox.getText()));
		}
		ArrayList<DayOfWeek> daysOfWeeks = new ArrayList<DayOfWeek>();
		for (JCheckBox checkbox : dayCheckboxs) {
			if (checkbox.isSelected())
				daysOfWeeks.add(DayOfWeek.valueOf(checkbox.getText().toUpperCase()));
		}
		boolean homeVisit = homeVisitCheckBox.isSelected();
		int numberOfAnalysis = (int) numberOfAnalysisTypes.getValue();
		CurrentStateOfRequest currentStateOfRequest = (CurrentStateOfRequest) stateOfTheRequest.getSelectedItem();

		Map<Specialization, Double> map = AllAnalysisRequests.incomesPerSpecialization(start, end,
				selectedSpecialization, daysOfWeeks, homeVisit, numberOfAnalysis, currentStateOfRequest);
		for (Specialization specialization : Specialization.values()) {
			chart.removeSeries(specialization.toString());
			chart.addSeries(specialization.toString(), map.get(specialization));
		}
		panelTable.removeAll();
		panelTable.add(pSearch, BorderLayout.SOUTH);
		panelTable.add(
				new JScrollPane(setupTable(AllAnalysisRequests.filterAnalysisRequests(start, end,
						selectedSpecialization, daysOfWeeks, homeVisit, numberOfAnalysis, currentStateOfRequest))),
				BorderLayout.CENTER);
		chartPanel.revalidate();
		chartPanel.repaint();
	}

	public static JTable setupTable(ArrayList<AnalysisRequest> list) {
		JTable table = new JTable(new IncomesRequestModel(list));
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);

		if (list.size() > 0) {
			TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
			tableSorter.setModel((AbstractTableModel) table.getModel());
			table.setRowSorter(tableSorter);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
					IncomesRequestModel.sort(index);
					IncomesRequestModel analysisRequestModel = (IncomesRequestModel) table.getModel();
					analysisRequestModel.fireTableDataChanged();
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
		return table;
	}
}
