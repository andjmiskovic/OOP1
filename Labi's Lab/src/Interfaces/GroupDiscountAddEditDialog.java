package Interfaces;

import java.awt.Checkbox;
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

import classes.AnalysisType;
import classes.Discount;
import classes.Specialization;
import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import net.miginfocom.swing.MigLayout;

public class GroupDiscountAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public GroupDiscountAddEditDialog() {
		setTitle("Add new group Discount");
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		getContentPane().setLayout(new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]"));
		
		getContentPane().add(new JLabel("Start date"));
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
		getContentPane().add(startDate, "span 2, grow");

		getContentPane().add(new JLabel("End date"));
		getContentPane().add(endDate, "span 2, grow");

		add(new JLabel("Value (%)"));
		JSpinner valueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		add(valueSpinner, "span 2");

		add(new JLabel("Specialization group"));
		JComboBox<String> specializations = new JComboBox<String>();
		for (Specialization specialization : Specialization.values()) {
			specializations.addItem(specialization.getName());
		}
		add(specializations, "span 2");

		JPanel daysPanel = new JPanel(new MigLayout());
		ArrayList<Checkbox> dayCheckboxs = new ArrayList<>();
		add(new JLabel("Days of discount"));
		for (DayOfWeek day : DayOfWeek.values()) {
			Checkbox dayCheckbox = new Checkbox(day.toString());
			dayCheckboxs.add(dayCheckbox);
			daysPanel.add(dayCheckbox, "wrap");
		}
		add(daysPanel, "span 2");

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
					Specialization specialization = Specialization
							.valueOf(specializations.getSelectedItem().toString());
					String id = String.valueOf(AllDiscounts.listOfDiscounts.size() + 1);
					Discount newDiscount = new Discount(id, value, days, start, end);
					for (AnalysisType analysisType : AllAnalysisTypes.listOfAnalysisTypes) {
						if (analysisType.getGroup() == specialization) {
							AllAnalysisTypes.listOfAnalysisTypes.remove(analysisType);
							analysisType.setGroupDiscount(newDiscount);
							AllAnalysisTypes.listOfAnalysisTypes.add(analysisType);
						}
					}
					AllDiscounts.listOfDiscounts.add(newDiscount);
					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You have to enter all data first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				PanelDiscounts.refreshData();
				GroupDiscountAddEditDialog.this.dispose();
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
