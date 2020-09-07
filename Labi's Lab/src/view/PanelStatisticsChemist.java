package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.time.DateUtils;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.toedter.calendar.JDateChooser;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import models.AnalysisType;
import models.Specialization;

public class PanelStatisticsChemist extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel, chartPanel;
	static JCheckBox homeVisitCheckBox;
	static JDateChooser startDate, endDate;
	static JSpinner lowerAgeSpinner, upperAgeSpinner;
	static XYChart chart;
	static JComboBox<String> analysisTypesCombobox;
	static JComboBox<Specialization> specializations;
	static Color myBlue = new Color(6, 56, 74);

	public PanelStatisticsChemist() {
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("STATISTICS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/statisticsBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblSelectAnalysisYou = new JLabel("Choose the analysis type in order to see statistics");
		lblSelectAnalysisYou.setForeground(Color.WHITE);
		lblSelectAnalysisYou.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectAnalysisYou.setBounds(115, 150, 777, 36);
		changingPanel.add(lblSelectAnalysisYou);

		JLabel lblStartDate = new JLabel("Choose the date range");
		lblStartDate.setForeground(Color.WHITE);
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStartDate.setBounds(113, 283, 213, 36);
		changingPanel.add(lblStartDate);

		chart = new XYChartBuilder().width(800).height(600).title("Number of analyses per gender in date range")
				.xAxisTitle("X").yAxisTitle("Y").build();
		chartPanel = new XChartPanel<XYChart>(chart);
		chartPanel.setBounds(70, 403, 822, 522);

		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setAxisTitlesVisible(false);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

		chart.addSeries("Male", new double[] { 0 }, new double[] { 0 });
		chart.addSeries("Female", new double[] { 0 }, new double[] { 0 });
		chart.addSeries("Total", new double[] { 0 }, new double[] { 0 });

		changingPanel.add(chartPanel);

		analysisTypesCombobox = new JComboBox<String>();
		analysisTypesCombobox.setBounds(508, 202, 384, 36);
		changingPanel.add(analysisTypesCombobox);

		startDate = new JDateChooser();
		startDate.setDateFormatString("dd.MM.yyyy.");
		startDate.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setBounds(70, 330, 105, 36);
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
		endDate.setBounds(210, 330, 105, 36);
		endDate.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				refresh();
			}
		});
		changingPanel.add(endDate);

		JLabel lowerAge = new JLabel("Choose the age range");
		lowerAge.setForeground(Color.WHITE);
		lowerAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lowerAge.setBounds(378, 283, 179, 36);
		changingPanel.add(lowerAge);

		lowerAgeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 120, 5));
		lowerAgeSpinner.setBounds(388, 330, 55, 37);
		lowerAgeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
				int min = (int) lowerAgeSpinner.getValue();
				int max = (int) upperAgeSpinner.getValue();
				upperAgeSpinner.setModel(new SpinnerNumberModel(0, min, 120, 5));
				System.out.println(min);
				System.out.println(max);

			}
		});
		changingPanel.add(lowerAgeSpinner);

		upperAgeSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 120, 5));
		upperAgeSpinner.setBounds(476, 329, 55, 37);
		upperAgeSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});
		changingPanel.add(upperAgeSpinner);

		JLabel line1 = new JLabel("-");
		line1.setForeground(Color.WHITE);
		line1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		line1.setBounds(190, 330, 10, 36);
		changingPanel.add(line1);

		JLabel line2 = new JLabel("-");
		line2.setForeground(Color.WHITE);
		line2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		line2.setBounds(456, 330, 10, 36);
		changingPanel.add(line2);

		analysisTypesCombobox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		specializations = new JComboBox<Specialization>();
		for (Specialization specialization : Specialization.values()) {
			specializations.addItem(specialization);
		}
		Specialization currentSpecialization = (Specialization) specializations.getSelectedItem();
		for (AnalysisType analysisType : AllAnalysisTypes.listOfAnalysisTypes) {
			if (analysisType.getGroup() == currentSpecialization)
				analysisTypesCombobox.addItem(analysisType.getName());
		}
		specializations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analysisTypesCombobox.removeAllItems();
				Specialization currentSpecialization = (Specialization) specializations.getSelectedItem();
				for (AnalysisType analysisType : AllAnalysisTypes.listOfAnalysisTypes) {
					if (analysisType.getGroup() == currentSpecialization)
						analysisTypesCombobox.addItem(analysisType.getName());
				}
				specializations.revalidate();
				specializations.repaint();
				refresh();
			}
		});
		specializations.setBounds(70, 202, 384, 36);
		changingPanel.add(specializations);
	}

	public static void refresh() {
		int startAge = (int) lowerAgeSpinner.getValue();
		int endAge = (int) upperAgeSpinner.getValue();
		LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		AnalysisType analysis = null;
		if (analysisTypesCombobox.getItemCount() > 0) {
			if (analysisTypesCombobox.getSelectedItem().toString().trim() != "") {
				analysis = AllAnalysisTypes.getAnalysisTypeByName(analysisTypesCombobox.getSelectedItem().toString());
				ArrayList<Date> dates = new ArrayList<>();
				ArrayList<Integer> males = new ArrayList<>();
				ArrayList<Integer> females = new ArrayList<>();
				ArrayList<Integer> totals = new ArrayList<>();

				for (LocalDate date = start; date.isBefore(end); date = date.plusDays(14)) {
					Map<String, Integer> map = AllAnalysisRequests.numberOfAnalysesByTypeAndSex(analysis, date,
							date.plusDays(14), startAge, endAge);
					dates.add(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
					males.add(map.get("male"));
					females.add(map.get("female"));
					totals.add(map.get("male") + map.get("female"));
				}
				chart.removeSeries("Male");
				if (males.size() > 0)
					chart.addSeries("Male", dates, males);
				else
					chart.addSeries("Male", new double[] { 0 }, new double[] { 0 });

				chart.removeSeries("Female");
				if (females.size() > 0)
					chart.addSeries("Female", dates, females);
				else
					chart.addSeries("Female", new double[] { 0 }, new double[] { 0 });

				chart.removeSeries("Total");
				if (totals.size() > 0)
					chart.addSeries("Total", dates, totals);
				else
					chart.addSeries("Total", new double[] { 0 }, new double[] { 0 });
			}
		} else {
			chart.removeSeries("Male");
			chart.removeSeries("Female");
			chart.removeSeries("Total");

			chart.addSeries("Male", new double[] { 0 }, new double[] { 0 });
			chart.addSeries("Female", new double[] { 0 }, new double[] { 0 });
			chart.addSeries("Total", new double[] { 0 }, new double[] { 0 });
		}
		chartPanel.revalidate();
		chartPanel.repaint();
	}
}
