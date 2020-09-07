package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.Users;
import functions.GeneralFunctions;
import functions.JTextFieldCharLimit;
import functions.Validations;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentStateOfRequest;
import models.Patient;
import models.Specialization;
import net.miginfocom.swing.MigLayout;
import tableModels.AnalysisTypeModel;

public class PanelNewRequestMT extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static double totalPrice;
	static JButton findPatientByLBO, sendButton;
	static JTable table, tableRecommendation;
	static ArrayList<AnalysisType> analysisTypes;
	static Color myBlue = new Color(6, 56, 74);
	static Patient patient = null;
	static DecimalFormat priceFormat = new DecimalFormat("# RSD");

	public PanelNewRequestMT() {
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("NEW REQUEST");
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
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setForeground(myBlue);

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(50, 175, 1000, 400);
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		changingPanel.add(panelTable);

		JLabel lblSelectAnalysisYou = new JLabel("Choose the analyses you want (press and hold Ctrl for more) ");
		lblSelectAnalysisYou.setForeground(Color.WHITE);
		lblSelectAnalysisYou.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectAnalysisYou.setBounds(50, 132, 777, 36);
		changingPanel.add(lblSelectAnalysisYou);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(myBlue);
		panel.setLayout(new MigLayout("", "[grow][grow]", "[grow][grow]"));
		panel.setBounds(50, 600, 800, 300);
		changingPanel.add(panel);

		JLabel lbltotalPrice = new JLabel("Total price with discount\r\n");
		lbltotalPrice.setForeground(Color.WHITE);
		lbltotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel patientName = new JLabel("Patient's Name");
		patientName.setForeground(Color.WHITE);
		patientName.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JTextField name = new JTextField();
		name.setColumns(20);
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		name.setDocument(new JTextFieldCharLimit(20));

		JLabel patientLastName = new JLabel("Patient's Last Name");
		patientLastName.setForeground(Color.WHITE);
		patientLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JTextField lastName = new JTextField();
		lastName.setColumns(20);
		lastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		lastName.setDocument(new JTextFieldCharLimit(20));

		JLabel patientLBO = new JLabel("Patient's LBO");
		patientLBO.setForeground(Color.WHITE);
		patientLBO.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JTextField LBO = new JTextField();
		LBO.setColumns(20);
		LBO.setDocument(new JTextFieldCharLimit(11));
		LBO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		LBO.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel price = new JLabel("0 RSD");
		price.setForeground(Color.WHITE);
		price.setFont(new Font("Tahoma", Font.PLAIN, 20));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (table.getSelectedRow() > -1) {
					int[] lines = table.getSelectedRows();
					ArrayList<AnalysisType> analysisTypes = new ArrayList<AnalysisType>();
					for (int line : lines)
						analysisTypes.add(AllAnalysisTypes
								.getAnalysisTypeByName(table.getModel().getValueAt(line, 0).toString()));
					totalPrice = AllAnalysisRequests.calculateRequestPriceWithDiscount(analysisTypes, false, false);
					price.setText(priceFormat.format(totalPrice));
				} else {
					price.setText("0 RSD");
				}
			}
		});

		findPatientByLBO = new JButton("FIND A PATIENT BY LBO");
		findPatientByLBO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String LBOString = LBO.getText();
				if (!functions.Validations.isNumeric(LBOString))
					JOptionPane.showMessageDialog(null, "LBO is only allowed to have numbers.", "Error",
							JOptionPane.WARNING_MESSAGE);
				else {
					if (LBOString.length() != 11)
						JOptionPane.showMessageDialog(null, "LBO shoud have 11 caracters.", "Error",
								JOptionPane.WARNING_MESSAGE);
					else {
						if (!Validations.isUniqueLBO(LBOString)) {
							patient = Users.getPatientByLBO(LBOString);
							name.setText(patient.getName());
							lastName.setText(patient.getLastName());
						} else
							JOptionPane.showMessageDialog(null, "None of the patients has this LBO.", "Error",
									JOptionPane.WARNING_MESSAGE);

					}
				}
			}
		});
		findPatientByLBO.setFont(new Font("Dialog", Font.PLAIN, 18));
		findPatientByLBO.setBackground(myBlue);
		findPatientByLBO.setForeground(SystemColor.text);

		sendButton = new JButton("ADD NEW REQUEST");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] lines = table.getSelectedRows();
				if (lines.length == 0) {
					JOptionPane.showMessageDialog(null, "You have to choose at least 1 analysis type.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					Map<AnalysisType, Double> analysisTypes = new HashMap<AnalysisType, Double>();
					for (int line : lines)
						analysisTypes.put(AllAnalysisTypes.getAnalysisTypeByName(table.getValueAt(line, 0).toString()),
								null);
					int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to send this request?",
							"Confirmation", JOptionPane.YES_NO_OPTION);
					if (patient == null)
						patient = new Patient(LBO.getText(), Users.newPatientUserName(), "", name.getText(),
								lastName.getText(), "", "", null, "", false);
					if (choice == JOptionPane.YES_OPTION) {
						AnalysisRequest analysisRequest = new AnalysisRequest(AllAnalysisRequests.uniqueRequestId(),
								Double.valueOf(price.getText().replace(" RSD", "")), false, null, false, null,
								CurrentStateOfRequest.PROCESS_STARTED, analysisTypes, patient, LocalDate.now(), null,
								null, new HashMap<Specialization, Chemist>(),
								GeneralFunctions.getAllSpecializations(analysisTypes));
						AllAnalysisRequests.addAnalysisRequest(analysisRequest);
						Users.listOfPatients.add(patient);
						Users.saveData();
						name.setText("");
						lastName.setText("");
						LBO.setText("");
					}
				}
			}
		});
		sendButton.setFont(new Font("Dialog", Font.PLAIN, 18));
		sendButton.setBackground(myBlue);
		sendButton.setForeground(SystemColor.text);

		panel.add(patientLastName, "cell 0 0");
		panel.add(lastName, "cell 2 0");
		panel.add(patientName, "cell 0 1");
		panel.add(name, "cell 2 1");
		panel.add(patientLBO, "cell 0 2");
		panel.add(LBO, "cell 2 2");
		panel.add(findPatientByLBO, "cell 0 4, grow");
		panel.add(lbltotalPrice, "cell 0 3");
		panel.add(price, "flowx,cell 2 3");
		panel.add(sendButton, "cell 2 4, grow");
	}
}
