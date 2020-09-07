package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fileHandler.AllAnalysisTypes;
import functions.JTextFieldCharLimit;
import functions.Validations;
import models.AnalysisType;
import models.Discount;
import models.Specialization;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		getContentPane().setLayout(ml);

		getContentPane().add(new JLabel("Name"));
		JTextField txtName = new JTextField(20);
		txtName.setDocument(new JTextFieldCharLimit(20));
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == ' ')
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		if (analysisType != null) 
			txtName.setEnabled(false);
		getContentPane().add(txtName, "span 2");

		getContentPane().add(new JLabel("Description"));
		JTextField txtDescription = new JTextField(20);
		txtDescription.setDocument(new JTextFieldCharLimit(50));
		txtDescription.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == ' ') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtDescription, "span 2");

		getContentPane().add(new JLabel("Specialization group"));
		JComboBox<String> cmbSpecialization = new JComboBox<String>();
		for (Specialization specialization : Specialization.values()) {
			cmbSpecialization.addItem(specialization.toString());
		}
		getContentPane().add(cmbSpecialization, "spanx 2,grow");

		getContentPane().add(new JLabel("Unit"));
		JTextField txtUnit = new JTextField(20);
		txtUnit.setDocument(new JTextFieldCharLimit(20));
		txtUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == '/') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtUnit, "span 2");

		getContentPane().add(new JLabel("Lower Bound"));
		JTextField txtLowerBound = new JTextField(20);
		txtLowerBound.setDocument(new JTextFieldCharLimit(10));
		txtLowerBound.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtLowerBound, "span 2");

		getContentPane().add(new JLabel("Upper Bound"));
		JTextField txtUpperBound = new JTextField(20);
		txtUpperBound.setDocument(new JTextFieldCharLimit(10));
		txtUpperBound.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtUpperBound, "span 2");

		getContentPane().add(new JLabel("Price (RSD)"));
		JTextField txtPrice = new JTextField(20);
		txtPrice.setDocument(new JTextFieldCharLimit(10));
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtPrice, "span 2");

		JButton btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel);

		JButton btnOK = new JButton("OK");
		getContentPane().add(btnOK);

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
					Specialization specialization = Specialization
							.valueOf(cmbSpecialization.getSelectedItem().toString().trim());
					double price = Double.valueOf(txtPrice.getText());
					double lowerBound = Double.valueOf(txtLowerBound.getText());
					double upperBound = Double.valueOf(txtUpperBound.getText());
					if (analysisType != null) {
						AllAnalysisTypes.removeAnalysisType(analysisType.getName());
						Discount groupDiscount = null;
						if (analysisType.getGroup() == specialization) {
							groupDiscount = analysisType.getGroupDiscount();
						}
						AllAnalysisTypes.addAnalysisType(name, description, unit, specialization, price, lowerBound,
								upperBound, analysisType.getTypeDiscount(), groupDiscount);
						dispose();
					} else {
						if (Validations.isUniqueAnalysisTypeName(name)) {
							AllAnalysisTypes.addAnalysisType(name, description, unit, specialization, price, lowerBound,
									upperBound, null, null);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Name is not unique.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Data you have entered is not correct. Please try again.",
							"Error", JOptionPane.ERROR_MESSAGE);
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
