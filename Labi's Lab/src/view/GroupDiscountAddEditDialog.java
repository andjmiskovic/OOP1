package view;

import java.awt.Checkbox;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import models.AnalysisType;
import models.Discount;
import models.Specialization;
import net.miginfocom.swing.MigLayout;

public class GroupDiscountAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public GroupDiscountAddEditDialog() {
		setTitle("Add new group Discount");
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		initGUI();
		pack();
	}

	private void initGUI() {
		getContentPane().setLayout(new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]"));

		JPanel daysPanel = new JPanel(new MigLayout());
		ArrayList<Checkbox> dayCheckboxs = new ArrayList<>();
		add(new JLabel("Days of discount"), "wrap");
		for (DayOfWeek day : DayOfWeek.values()) {
			Checkbox dayCheckbox = new Checkbox(day.toString());
			dayCheckboxs.add(dayCheckbox);
			daysPanel.add(dayCheckbox, "wrap");
		}
		add(daysPanel, "wrap");

		add(new JLabel("Start date"), "wrap");
		JDateChooser endDate = new JDateChooser();
		JDateChooser startDate = new JDateChooser();
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		endDate.setDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		startDate.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				endDate.setMinSelectableDate(DateUtils.truncate(startDate.getDate(), Calendar.DAY_OF_MONTH));
				endDate.setDate(DateUtils.truncate(startDate.getDate(), Calendar.DAY_OF_MONTH));
			}
		});
		startDate.setMinSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		getContentPane().add(startDate, "wrap,grow");

		getContentPane().add(new JLabel("End date"), "wrap");
		getContentPane().add(endDate, "wrap,grow");

		add(new JLabel("Value (%)"), "wrap");
		JSpinner valueSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		add(valueSpinner, "wrap");

		add(new JLabel("Specialization group"), "wrap");
		JComboBox<String> specializations = new JComboBox<String>();
		for (Specialization specialization : Specialization.values()) {
			specializations.addItem(specialization.getName());
		}
		add(specializations, "wrap");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double value = 1 - Double.valueOf(valueSpinner.getValue().toString()) / 100;
					LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					ArrayList<DayOfWeek> days = PanelDiscounts.daysOfWeekFromCheckBoxes(dayCheckboxs);
					if (days.size() == 0) {
						JOptionPane.showMessageDialog(null, "You have to choose at least 1 day of week!", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Specialization specialization = Specialization
								.fromString(specializations.getSelectedItem().toString());
						String id = String.valueOf(AllDiscounts.listOfDiscounts.size() + 1);
						Discount newDiscount = new Discount(id, value, days, start, end);
						ArrayList<AnalysisType> analysisTypes = AllAnalysisTypes.listOfAnalysisTypes;
						for (AnalysisType analysisType : analysisTypes) {
							if (analysisType.getGroup() == specialization) {
								AllAnalysisTypes.getAnalysisTypeByName(analysisType.getName())
										.setGroupDiscount(newDiscount);
							}
						}
						AllAnalysisTypes.saveData();
						AllDiscounts.addDiscount(newDiscount);
						dispose();
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Data you entered is not valid!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
//				PanelDiscounts.refreshData();
//				GroupDiscountAddEditDialog.this.dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GroupDiscountAddEditDialog.this.dispose();
			}
		});

	}
}
