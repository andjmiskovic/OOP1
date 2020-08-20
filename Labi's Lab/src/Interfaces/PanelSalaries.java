package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import classes.Qualification;
import fileHandler.SalaryCoefficients;
import net.miginfocom.swing.MigLayout;

public class PanelSalaries extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;

	public PanelSalaries() {
		setUp();

	}

	public static JPanel setUp() {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelPatients = new JLabel("SALARY COEFFICIENTS");
		labelPatients.setBounds(165, 51, 516, 59);
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		changingPanel.add(labelPatients);

		JLabel icon = new JLabel("");
		icon.setBounds(75, 51, 70, 70);
		icon.setIcon(new ImageIcon(PanelSalaries.class.getResource("/Interfaces/walletBlue.jpg")));
		changingPanel.add(icon);

		JPanel qualificationPanel = new JPanel();
		qualificationPanel.setBounds(75, 240, 480, 500);
		qualificationPanel.setForeground(Color.WHITE);
		qualificationPanel.setBackground(myBlue);
		qualificationPanel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		qualificationPanel.setLayout(new MigLayout("", "[]", "[]"));
		changingPanel.add(qualificationPanel);

		JLabel labelQualification = new JLabel("Qualification coefficients:");
		labelQualification.setBounds(75, 180, 400, 70);
		labelQualification.setForeground(Color.WHITE);
		labelQualification.setFont(new Font("Tahoma", Font.PLAIN, 24));
		changingPanel.add(labelQualification);

		List<Qualification> coefficientKeys = SalaryCoefficients.salaryCoefficient.getQualificationCoefficients()
				.keySet().stream().sorted((q1, q2) -> q1.compareTo(q2)).collect(Collectors.toList());
		Map<Qualification, JTextField> qualificationTextFields = new HashMap<Qualification, JTextField>();

		for (Qualification qualification : coefficientKeys) {
			qualificationPanel.add(new Label(qualification.toString()));
			JTextField thresholdTextField = new JTextField(String
					.valueOf(SalaryCoefficients.salaryCoefficient.getQualificationCoefficients().get(qualification)));
			thresholdTextField.setColumns(7);
			qualificationTextFields.put(qualification, thresholdTextField);
			qualificationPanel.add(thresholdTextField, "wrap");
		}
		changingPanel.add(qualificationPanel, "cell 0 0, alignx left, aligny top");

		JPanel otherPanel = new JPanel();
		otherPanel.setForeground(Color.WHITE);
		otherPanel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		otherPanel.setBackground(new Color(6, 56, 74));
		otherPanel.setBounds(565, 239, 516, 500);
		changingPanel.add(otherPanel);
		otherPanel.setLayout(new MigLayout("", "[]", "[]"));

		JLabel labelOther = new JLabel("Other salary values:");
		labelOther.setBounds(560, 180, 400, 70);
		labelOther.setForeground(Color.WHITE);
		labelOther.setFont(new Font("Tahoma", Font.PLAIN, 24));
		changingPanel.add(labelOther);

		otherPanel.add(new Label("salary base"));
		JTextField base = new JTextField(String.valueOf(SalaryCoefficients.salaryCoefficient.getBase()));
		base.setColumns(7);
		otherPanel.add(base, "wrap");

		otherPanel.add(new Label("home visit price"));
		JTextField homeVisitPrice = new JTextField(String.valueOf(SalaryCoefficients.salaryCoefficient.getHomeVisit()));
		homeVisitPrice.setColumns(7);
		otherPanel.add(homeVisitPrice, "wrap");

		otherPanel.add(new Label("home visit with time price"));
		JTextField homeVisitWithTimePrice = new JTextField(
				String.valueOf(SalaryCoefficients.salaryCoefficient.getHomeVisit()));
		homeVisitWithTimePrice.setColumns(7);
		otherPanel.add(homeVisitWithTimePrice, "wrap");

		otherPanel.add(new Label("home visit bonus"));
		JTextField homeVisitBonus = new JTextField(String.valueOf(SalaryCoefficients.salaryCoefficient.getHomeVisit()));
		homeVisitBonus.setColumns(7);
		otherPanel.add(homeVisitBonus, "wrap");

		otherPanel.add(new Label("home visit with time bonus"));
		JTextField homeVisitWithTimeBonus = new JTextField(
				String.valueOf(SalaryCoefficients.salaryCoefficient.getHomeVisitWithTimeBonus()));
		homeVisitWithTimeBonus.setColumns(7);
		otherPanel.add(homeVisitWithTimeBonus, "wrap");

		otherPanel.add(new Label("specialization bonus"));
		JTextField specializationBonus = new JTextField(
				String.valueOf(SalaryCoefficients.salaryCoefficient.getSpecializationBonus()));
		specializationBonus.setColumns(7);
		otherPanel.add(specializationBonus, "wrap");

		otherPanel.add(new Label("holiday bonus"));
		JTextField holidayBonus = new JTextField(
				String.valueOf(SalaryCoefficients.salaryCoefficient.getHolidayBonus()));
		holidayBonus.setColumns(7);
		otherPanel.add(holidayBonus, "wrap");

		JLabel labelSavedChanges = new JLabel("");
		labelSavedChanges.setBounds(565, 745, 516, 70);
		labelSavedChanges.setForeground(Color.WHITE);
		labelSavedChanges.setFont(new Font("Tahoma", Font.BOLD, 20));
		changingPanel.add(labelSavedChanges);

		JPanel panelFinish = new JPanel();
		panelFinish.setBackground(Color.WHITE);
		panelFinish.setBounds(75, 745, 480, 70);
		changingPanel.add(panelFinish);
		panelFinish.setLayout(null);

		JLabel finish = new JLabel("SAVE CHANGES");
		finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Map.Entry<Qualification, JTextField> set : qualificationTextFields.entrySet()) {
						Double newValue = Double.parseDouble(set.getValue().getText().trim());
						Qualification qualification = set.getKey();
						SalaryCoefficients.salaryCoefficient.getQualificationCoefficients().replace(qualification,
								SalaryCoefficients.salaryCoefficient.getQualificationCoefficients().get(qualification),
								newValue);
					}
					SalaryCoefficients.salaryCoefficient.setBase(Double.valueOf(base.getText()));
					SalaryCoefficients.salaryCoefficient.setHomeVisit(Double.valueOf(homeVisitPrice.getText()));
					SalaryCoefficients.salaryCoefficient.setHomeVisitBonus(Double.valueOf(homeVisitBonus.getText()));
					SalaryCoefficients.salaryCoefficient
							.setHomeVisitWithTime(Double.valueOf(homeVisitWithTimePrice.getText()));
					SalaryCoefficients.salaryCoefficient
							.setHomeVisitWithTimeBonus(Double.valueOf(homeVisitWithTimeBonus.getText()));
					SalaryCoefficients.salaryCoefficient.setHolidayBonus(Double.valueOf(holidayBonus.getText()));
					SalaryCoefficients.salaryCoefficient
							.setSpecializationBonus(Double.valueOf(specializationBonus.getText()));

					SalaryCoefficients.saveData();

					labelSavedChanges.setText("Changes have been saved successfully.");

				} catch (Exception e0) {
					labelSavedChanges.setText("There is a problem, we can't save the changes.");
				}
			}
		});
		finish.setBounds(0, 0, 480, 70);
		panelFinish.add(finish);
		finish.setForeground(myBlue);
		finish.setFont(new Font("Tahoma", Font.BOLD, 26));
		finish.setHorizontalAlignment(SwingConstants.CENTER);
		finish.setToolTipText("Click here to save changes");

		return changingPanel;
	}
}
