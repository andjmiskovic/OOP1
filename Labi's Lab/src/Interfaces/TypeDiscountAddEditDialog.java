package Interfaces;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
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
import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import net.miginfocom.swing.MigLayout;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TypeDiscountAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Discount discount;
	private AnalysisType analysisType;

	public TypeDiscountAddEditDialog(AnalysisType analysisType, Discount discount) {
		if (discount != null) {
			setTitle("Edit Discount");
		} else {
			setTitle("Add new Discount");
		}
		this.discount = discount;
		this.analysisType = analysisType;
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

		getContentPane().add(new JLabel("Value (%)"));
		JSpinner valueSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		getContentPane().add(valueSpinner, "span 2");

		JPanel daysPanel = new JPanel(new MigLayout());
		ArrayList<Checkbox> dayCheckboxs = new ArrayList<>();
		getContentPane().add(new JLabel("Days of discount"));
		for (DayOfWeek day : DayOfWeek.values()) {
			Checkbox dayCheckbox = new Checkbox(day.toString());
			dayCheckboxs.add(dayCheckbox);
			daysPanel.add(dayCheckbox, "wrap");
		}
		getContentPane().add(daysPanel, "span 2");

		JButton btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel);

		JButton btnOK = new JButton("OK");
		getContentPane().add(btnOK);

		if (discount != null) {
			startDate.setDate(Date.from(discount.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			endDate.setDate(Date.from(discount.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			valueSpinner.setValue(100 * (1 - analysisType.getTypeDiscount().getValue()));
			for (Checkbox dayCheckbox : dayCheckboxs) {
				for (DayOfWeek day : discount.getDaysOfDiscount()) {
					if (dayCheckbox.getLabel() == day.toString()) {
						dayCheckbox.setState(true);
					}
				}
			}
		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double value = 1 - Double.valueOf(valueSpinner.getValue().toString()) / 100;
					LocalDate start = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate end = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					ArrayList<DayOfWeek> days = PanelDiscounts.daysOfWeekFromCheckBoxes(dayCheckboxs);
					String id = String.valueOf(AllDiscounts.listOfDiscounts.size() + 1);
					if (discount != null) {
						id = discount.getId();
					}
					Discount newDiscount = new Discount(id, value, days, start, end);
					AllAnalysisTypes.listOfAnalysisTypes.remove(analysisType);
					analysisType.setTypeDiscount(newDiscount);
					AllAnalysisTypes.listOfAnalysisTypes.add(analysisType);
					AllDiscounts.listOfDiscounts.remove(discount);
					AllDiscounts.listOfDiscounts.add(newDiscount);
					dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You have to enter all data first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				PanelDiscounts.refreshData();
				TypeDiscountAddEditDialog.this.dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TypeDiscountAddEditDialog.this.dispose();
			}
		});

	}
}
