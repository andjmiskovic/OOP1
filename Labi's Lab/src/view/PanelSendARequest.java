package view;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.SalaryCoefficients;
import functions.GeneralFunctions;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentStateOfRequest;
import models.CurrentUser;
import models.Patient;
import models.Specialization;
import tableModels.AnalysisRecommendationModel;
import tableModels.AnalysisTypeModel;

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
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/requestBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		table = new JTable(new AnalysisTypeModel(AllAnalysisTypes.listOfAnalysisTypes));
		table.setBackground(Color.WHITE);
		table.setRowSelectionAllowed(true);
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setForeground(myBlue);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
				AnalysisTypeModel.sort(index);
				refreshTable();
			}
		});

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(50, 174, 1015, 300);

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

		tableRecommendation = new JTable(
				new AnalysisRecommendationModel(recommendedAnaysisTypes(CurrentUser.getCurrentPatient())));
		tableRecommendation.setBackground(Color.WHITE);
		tableRecommendation.setForeground(myBlue);
		tableRecommendation.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				int row = tableRecommendation.rowAtPoint(evt.getPoint());
				String name = tableRecommendation.getModel().getValueAt(row, 0).toString();
				for (int i = 0; i < table.getRowCount(); i++) {
					if (table.isRowSelected(i) | table.getModel().getValueAt(i, 0).toString().equals(name)) {
						table.getSelectionModel().addSelectionInterval(i, i);
					}
				}
			}
		});

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

		JLabel lblSelectAnalysisYou = new JLabel("Choose the analyses you want (press and hold Ctrl for more) ");
		lblSelectAnalysisYou.setForeground(Color.WHITE);
		lblSelectAnalysisYou.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSelectAnalysisYou.setBounds(50, 132, 777, 36);
		changingPanel.add(lblSelectAnalysisYou);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(myBlue);
		panel.setBounds(50, 707, 1015, 233);
		changingPanel.add(panel);

		JLabel date = new JLabel("Date of request");
		date.setBounds(699, 7, 199, 31);
		date.setForeground(Color.WHITE);
		date.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lbltotalPrice = new JLabel("Total price with discount\r\n");
		lbltotalPrice.setBounds(446, 119, 252, 21);
		lbltotalPrice.setForeground(Color.WHITE);
		lbltotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel labelHomeVisit = new JLabel("Home Visit");
		labelHomeVisit.setBounds(446, 14, 124, 21);
		labelHomeVisit.setForeground(Color.WHITE);
		labelHomeVisit.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JDateChooser startDate = new JDateChooser();
		startDate.setDateFormatString("dd.MM.yyyy.");
		startDate.setBounds(908, 7, 107, 31);
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setEnabled(false);
		panel.setLayout(null);
		panel.add(lbltotalPrice);
		panel.add(startDate);

		SpinnerDateModel sm = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
		JSpinner time = new javax.swing.JSpinner(sm);
		JSpinner.DateEditor te = new JSpinner.DateEditor(time, "HH:mm");
		time.setEditor(te);
		time.setEnabled(false);
		time.setBounds(908, 67, 107, 20);

		JCheckBox homeVisit = new JCheckBox();
		homeVisit.setBounds(668, 14, 21, 21);
		panel.add(homeVisit);
		panel.add(time);

		JLabel labelHomeVisitWithTime = new JLabel("Home Visit with time");
		labelHomeVisitWithTime.setBounds(446, 66, 195, 21);
		labelHomeVisitWithTime.setForeground(Color.WHITE);
		labelHomeVisitWithTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(labelHomeVisitWithTime);

		JCheckBox homeVisitWithTime = new JCheckBox();
		homeVisitWithTime.setBounds(668, 66, 21, 21);
		homeVisitWithTime.setEnabled(false);
		panel.add(homeVisitWithTime);

		JLabel lblTime = new JLabel("Time of home visit");
		lblTime.setBounds(699, 66, 199, 21);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblTime);

		JLabel price = new JLabel("0 RSD");
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setBounds(832, 112, 183, 31);
		price.setForeground(Color.WHITE);
		price.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel.add(labelHomeVisit);
		panel.add(date);
		panel.add(price);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					int[] lines = table.getSelectedRows();
					ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
					for (int line : lines)
						analysisTypes.add(AllAnalysisTypes
								.getAnalysisTypeByName(table.getModel().getValueAt(line, 0).toString()));
					totalPrice = AllAnalysisRequests.calculateRequestPriceWithDiscount(analysisTypes,
							homeVisit.isSelected(), homeVisitWithTime.isSelected());
					price.setText(priceFormat.format(totalPrice));
				} else
					price.setText("0 RSD");
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
					if (homeVisitWithTime.isSelected())
						totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
					else
						totalPrice += SalaryCoefficients.salaryCoefficient.getHomeVisit();
					startDate.setEnabled(true);
					homeVisitWithTime.setEnabled(true);
				} else {
					if (homeVisitWithTime.isSelected())
						totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisitWithTime();
					else 
						totalPrice -= SalaryCoefficients.salaryCoefficient.getHomeVisit();
					startDate.setEnabled(false);
					time.setEnabled(false);
					homeVisitWithTime.setEnabled(false);
					homeVisitWithTime.setSelected(false);
				}
				price.setText(priceFormat.format(totalPrice));
			}
		});

		JButton sendButton = new JButton("SEND");
		sendButton.setBounds(446, 166, 569, 45);
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
						homeVisitDate = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						if (homeVisitWithTime.isSelected()) {
							Date date = (Date) time.getValue();
							homeVisitTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
									.toLocalTime();
						}
					}
					int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to send this request?",
							"Confirmation", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						AnalysisRequest analysisRequest = new AnalysisRequest(AllAnalysisRequests.uniqueRequestId(),
								Double.valueOf(price.getText().replace(" RSD", "")), homeVisit.isSelected(),
								homeVisitDate, homeVisitWithTime.isSelected(), homeVisitTime,
								CurrentStateOfRequest.PROCESS_STARTED, analysisTypes, CurrentUser.getCurrentPatient(),
								LocalDate.now(), null, null, new HashMap<Specialization, Chemist>(),
								GeneralFunctions.getAllSpecializations(analysisTypes));
						AllAnalysisRequests.addAnalysisRequest(analysisRequest);
						refreshTable();
					}
				}
			}
		});
		sendButton.setFont(new Font("Dialog", Font.PLAIN, 20));
		sendButton.setBackground(myBlue);
		sendButton.setForeground(SystemColor.text);
		panel.add(sendButton);
	}

	public static ArrayList<Map.Entry<AnalysisType, Double>> recommendedAnaysisTypes(Patient patient) {
		ArrayList<Map.Entry<AnalysisType, Double>> list = new ArrayList<Map.Entry<AnalysisType, Double>>();
		ArrayList<AnalysisRequest> lastTwoAnalysisRequests = AllAnalysisRequests
				.getLastTwoAnalysisRequestsByPatient(patient);
		for (AnalysisRequest analysisRequest : lastTwoAnalysisRequests) {
			for (Map.Entry<AnalysisType, Double> entry : analysisRequest.getAnalyses().entrySet()) {
				if (entry.getValue() != null) {
					if (entry.getValue() < entry.getKey().getLowerBound()
							| entry.getValue() > entry.getKey().getUpperBound())
						list.add(entry);
				}
			}
		}
		return list;
	}

	public static void refreshTable() {
		AnalysisTypeModel analysisRequestModel = (AnalysisTypeModel) table.getModel();
		analysisRequestModel.fireTableDataChanged();
		table.repaint();
	}

}
