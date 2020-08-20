package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import classes.AnalysisRequest;
import classes.AnalysisType;
import classes.Chemist;
import classes.CurrentStateOfRequest;
import classes.CurrentUser;
import classes.Patient;
import classes.Specialization;
import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.SalaryCoefficients;
import models.AnalysisRecommendationModel;
import models.AnalysisRequestModel;
import net.miginfocom.swing.MigLayout;

public class PanelSendARequest extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static double totalPrice;
	static JTable table, tableRecommendation;
	static ArrayList<AnalysisType> analysisTypes;
	static Color myBlue = new Color(6, 56, 74);
	static DecimalFormat priceFormat = new DecimalFormat("# RSD");

	public PanelSendARequest() {
		setUp();

	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("SEND A REQUEST");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/Interfaces/requestBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		table = new JTable(new AnalysisRequestModel(AllAnalysisTypes.listOfAnalysisTypes));
		table.setBackground(Color.WHITE);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setForeground(myBlue);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(50, 174, 1015, 300);
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		changingPanel.add(panelTable);

		// ne valja
		tableRecommendation = new JTable(
				new AnalysisRecommendationModel(new ArrayList<Map.Entry<AnalysisType, Double>>()));
		tableRecommendation.setBackground(Color.WHITE);
		tableRecommendation.setForeground(myBlue);

		JPanel panelTableRecommendation = new JPanel();
		panelTableRecommendation.setBackground(Color.WHITE);
		panelTableRecommendation.setLayout(new BorderLayout(0, 0));
		panelTableRecommendation.setBounds(50, 525, 1015, 171);
		panelTableRecommendation.add(new JScrollPane(tableRecommendation), BorderLayout.CENTER);
		changingPanel.add(panelTableRecommendation);

		JLabel lblNewLabel = new JLabel(
				"We recommend you to check these analysis again since your previous results were not well");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(50, 485, 777, 36);
		changingPanel.add(lblNewLabel);

		JLabel lblSelectAnalysisYou = new JLabel("Select analysis you want ");
		lblSelectAnalysisYou.setForeground(Color.WHITE);
		lblSelectAnalysisYou.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectAnalysisYou.setBounds(50, 132, 777, 36);
		changingPanel.add(lblSelectAnalysisYou);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(myBlue);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		panel.setBounds(50, 707, 1015, 233);
		changingPanel.add(panel);

		JLabel date = new JLabel("Date of request");
		date.setForeground(Color.WHITE);
		date.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel lbltotalPrice = new JLabel("Total price with discount\r\n");
		lbltotalPrice.setForeground(Color.WHITE);
		lbltotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JLabel labelHomeVisit = new JLabel("Home Visit");
		labelHomeVisit.setForeground(Color.WHITE);
		labelHomeVisit.setFont(new Font("Tahoma", Font.PLAIN, 17));

		JDateChooser startDate = new JDateChooser();
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setEnabled(false);
		panel.add(lbltotalPrice, "cell 0 4");
		panel.add(startDate, "cell 3 1, grow");

		SpinnerDateModel sm = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		JSpinner time = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(time, "HH:mm");
		time.setEditor(de);
		time.setEnabled(false);

		JCheckBox homeVisit = new JCheckBox();
		panel.add(homeVisit, "cell 3 0");
		panel.add(time, "cell 3 3,growx,aligny center");

		JLabel labelHomeVisitWithTime = new JLabel("Home Visit with time");
		labelHomeVisitWithTime.setForeground(Color.WHITE);
		labelHomeVisitWithTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(labelHomeVisitWithTime, "cell 0 2");

		JCheckBox homeVisitWithTime = new JCheckBox();
		homeVisitWithTime.setEnabled(false);
		panel.add(homeVisitWithTime, "cell 3 2");

		JLabel lblTime = new JLabel("Time of home visit");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblTime, "cell 0 3,growx,aligny center");

		JLabel price = new JLabel("0 RSD");
		price.setForeground(Color.WHITE);
		price.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(labelHomeVisit, "cell 0 0");
		panel.add(date, "cell 0 1, grow");
		panel.add(price, "flowx,cell 3 4,grow");

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					int[] lines = table.getSelectedRows();
					ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
					for (int line : lines)
						analysisTypes.add(AllAnalysisTypes
								.getAnalysisTypeByName(table.getModel().getValueAt(line, 0).toString()));
					LocalDate requestedDate = null;
					if (homeVisit.isSelected())
						requestedDate = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					totalPrice = AllAnalysisRequests.calculateRequestPriceWithtDiscount(analysisTypes, requestedDate,
							homeVisitWithTime.isSelected());
					price.setText(priceFormat.format(totalPrice));
				} else {
					price.setText("0 RSD");
				}
			}
		});

		homeVisitWithTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (homeVisitWithTime.isSelected()) {
					time.setEnabled(true);
					totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisit();
					totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
				} else {
					time.setEnabled(false);
					totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisit();
					totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
				}
				price.setText(priceFormat.format(totalPrice));
			}
		});

		homeVisit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (homeVisit.isSelected()) {
					startDate.setEnabled(true);
					homeVisitWithTime.setEnabled(true);
					if (homeVisitWithTime.isSelected())
						totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
					else
						totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisit();

				} else {
					startDate.setEnabled(false);
					time.setEnabled(false);
					homeVisitWithTime.setEnabled(false);
					homeVisitWithTime.setSelected(false);
					if (homeVisitWithTime.isSelected())
						totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
					else {
						totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisit();
					}
				}
				price.setText(priceFormat.format(totalPrice));
			}
		});

		JButton sendButton = new JButton("SEND");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] lines = table.getSelectedRows();
				if (lines.length == 0) {
					JOptionPane.showMessageDialog(null, "You have to choose at least 1 analysis type.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Map<AnalysisType, Double> analysisTypes = new HashMap<AnalysisType, Double>();
					for (int line : lines) {
						analysisTypes.put(AllAnalysisTypes.getAnalysisTypeByName(table.getValueAt(line, 0).toString()),
								null);
					}
					LocalTime homeVisitTime = null;
					LocalDate homeVisitDate = null;
					if (homeVisit.isSelected()) {
						homeVisitDate = LocalDate.parse(startDate.getDate().toString(), DateTimeFormatter.ofPattern("dd.MM.yyyy."));
						if (homeVisitWithTime.isSelected()) {
							homeVisitTime = LocalTime.parse(time.getValue().toString(), DateTimeFormatter.ofPattern("HH:MM"));
						}
					}
					int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to send this request?",
							"Confirmation", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						AnalysisRequest analysisRequest = new AnalysisRequest(
								String.valueOf(AllAnalysisRequests.listOfRequests.size() + 1),
								Double.valueOf(price.getText().replace(" RSD", "")), homeVisit.isSelected(),homeVisitDate,
								homeVisitWithTime.isSelected(), homeVisitTime, CurrentStateOfRequest.PROCESS_STARTED,
								analysisTypes, CurrentUser.getCurrentPatient(), LocalDate.now(), null, null,
								new HashMap<Specialization, Chemist>(), getAllSpecializations(analysisTypes));
						AllAnalysisRequests.addAnalysisRequest(analysisRequest);
						refreshTable();
					}
				}
			}
		});
		sendButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		sendButton.setBackground(myBlue);
		sendButton.setForeground(SystemColor.text);
		panel.add(sendButton, "cell 3 5, grow");
	}

	public static ArrayList<Specialization> getAllSpecializations(Map<AnalysisType, Double> analysisTypes) {
		ArrayList<Specialization> specializations = new ArrayList<Specialization>();
		for (Entry<AnalysisType, Double> analysisType : analysisTypes.entrySet())
			specializations.add(analysisType.getKey().getGroup());
		return removeDuplicates(specializations);
	}

	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
		ArrayList<T> newList = new ArrayList<T>();
		for (T element : list) {
			if (!newList.contains(element)) {
				newList.add(element);
			}
		}
		return newList;
	}

	public static ArrayList<Map.Entry<AnalysisType, Double>> recommendedAnaysisTypes(Patient patient) {
		ArrayList<Map.Entry<AnalysisType, Double>> list = new ArrayList<Map.Entry<AnalysisType, Double>>();
		ArrayList<AnalysisRequest> lastTwoAnalysisRequests = AllAnalysisRequests
				.getLastTwoAnalysisRequestsByPatient(patient);
		for (AnalysisRequest analysisRequest : lastTwoAnalysisRequests) {
			for (Map.Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet()) {
				if (entry.getValue() < entry.getKey().getLowerBound()
						|| entry.getValue() < entry.getKey().getUpperBound()) {
					list.add(entry);
				}
			}
		}
		return list;
	}

	public static void refreshTable() {
		AnalysisRequestModel analysisRequestModel = (AnalysisRequestModel) table.getModel();
		analysisRequestModel.fireTableDataChanged();
		table.repaint();
	}

	public static void refreshRecommendationTable() {
		AnalysisRecommendationModel analysisRequestModel = (AnalysisRecommendationModel) tableRecommendation.getModel();
		analysisRequestModel.fireTableDataChanged();
		tableRecommendation.repaint();
	}
}
