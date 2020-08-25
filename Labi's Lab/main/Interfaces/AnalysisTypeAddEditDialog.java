package Interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import classes.AnalysisType;
import classes.Specialization;
import fileHandler.AllAnalysisTypes;
import functions.JTextFieldCharLimit;
import net.miginfocom.swing.MigLayout;

public class AnalysisTypeAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private AnalysisType analysisType;

	public AnalysisTypeAddEditDialog(AnalysisType analysisType) {
		if (analysisType != null) {
			setTitle("Edit Analysis Type");
		} else {
			setTitle("Add new Analysis Type");
		}
		this.analysisType = analysisType;
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		setLayout(ml);

		add(new JLabel("Name"));
		JTextField txtName = new JTextField(20);
		txtName.setDocument(new JTextFieldCharLimit(20));
		add(txtName, "span 2");

		add(new JLabel("Description"));
		JTextField txtDescription = new JTextField(50);
		txtDescription.setDocument(new JTextFieldCharLimit(50));
		add(txtDescription, "span 2");

		add(new JLabel("Specialization group"));
		JComboBox<String> cmbSpecialization = new JComboBox<String>();
		for (Specialization specialization : Specialization.values()) {
			cmbSpecialization.addItem(specialization.toString());
		}
		add(cmbSpecialization, "span 2");

		add(new JLabel("Unit"));
		JTextField txtUnit = new JTextField(20);
		txtUnit.setDocument(new JTextFieldCharLimit(20));
		add(txtUnit, "span 2");

		add(new JLabel("Lower Bound"));
		JTextField txtLowerBound = new JTextField(20);
		txtLowerBound.setDocument(new JTextFieldCharLimit(10));
		add(txtLowerBound, "span 2");

		add(new JLabel("Upper Bound"));
		JTextField txtUpperBound = new JTextField(20);
		txtUpperBound.setDocument(new JTextFieldCharLimit(10));
		add(txtUpperBound, "span 2");

		add(new JLabel("Price (RSD)"));
		JTextField txtPrice = new JTextField(20);
		txtPrice.setDocument(new JTextFieldCharLimit(10));
		add(txtPrice, "span 2");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (analysisType != null) {
			txtName.setText(analysisType.getName() + "");
			txtUnit.setText(analysisType.getUnit());
			txtLowerBound.setText(String.valueOf(analysisType.getLowerBound()));
			txtUpperBound.setText(String.valueOf(analysisType.getUpperBound()));
			txtDescription.setText(analysisType.getDescription());
			txtPrice.setText(String.valueOf(analysisType.getPrice()));
			cmbSpecialization.setSelectedItem(analysisType.getGroup().toString());
		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = txtName.getText().trim();
					String unit = txtUnit.getText().trim();
					String description = txtDescription.getText().trim();
					System.out.println(cmbSpecialization.getSelectedItem().toString().trim());
					Specialization specialization = Specialization.valueOf(cmbSpecialization.getSelectedItem().toString().trim());
					double price = Double.valueOf(txtPrice.getText());
					double lowerBound = Double.valueOf(txtLowerBound.getText());
					double upperBound = Double.valueOf(txtUpperBound.getText());
					if (analysisType != null) {
						AllAnalysisTypes.removeAnalysisType(analysisType.getName());
						AllAnalysisTypes.addAnalysisType(name, description, unit, specialization, price, lowerBound,
								upperBound, analysisType.getTypeDiscount(), analysisType.getGroupDiscount());
						dispose();
					} else {
						if (functions.Unique.isUniqueAnalysisTypeName(name)) {
							AllAnalysisTypes.addAnalysisType(name, description, unit, specialization, price, lowerBound,
									upperBound, null, null);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Name is not unique.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You have to enter all data first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				PanelAnalyses.refreshData();
				AnalysisTypeAddEditDialog.this.dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnalysisTypeAddEditDialog.this.dispose();
			}
		});

	}
}
