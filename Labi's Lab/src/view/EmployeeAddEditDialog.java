package view;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import fileHandler.Users;
import functions.JTextFieldCharLimit;
import functions.Validations;
import models.Admin;
import models.Chemist;
import models.Employee;
import models.MedicalTechnicial;
import models.Qualification;
import models.Specialization;
import net.miginfocom.swing.MigLayout;

public class EmployeeAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Employee employee;

	public EmployeeAddEditDialog(Employee employee) {
		String roleString;
		if (employee != null) {
			setTitle("Edit Employee");
			roleString = employee.getClass().toString();
		} else {
			setTitle("Add new Employee");
			roleString = null;
		}
		this.employee = employee;
		setLocation(300, 50);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI(roleString);
		pack();
	}

	private void initGUI(String role) {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		getContentPane().setLayout(ml);

		ArrayList<Checkbox> specializationCheckBoxes = new ArrayList<>();
		JComboBox<String> cmbQualification = new JComboBox<String>();

		getContentPane().add(new JLabel("Role"));
		JComboBox<String> cmbRole = new JComboBox<String>();
		cmbRole.addItem("Admin");
		cmbRole.addItem("Chemist");
		cmbRole.addItem("Medical Technicial");
		if (employee != null) {
			cmbRole.setEnabled(false);
		}
		cmbRole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cmbRole.getSelectedItem().equals("Chemist")) {
					for (Checkbox specializationCheckBox : specializationCheckBoxes) {
						specializationCheckBox.setEnabled(true);
					}
					cmbQualification.setEnabled(true);
				} else if (cmbRole.getSelectedItem().equals("Medical Technicial")) {
					for (Checkbox specializationCheckBox : specializationCheckBoxes) {
						specializationCheckBox.setEnabled(false);
					}
					cmbQualification.setEnabled(true);

				} else {
					for (Checkbox specializationCheckBox : specializationCheckBoxes) {
						specializationCheckBox.setEnabled(false);
					}
					cmbQualification.setEnabled(false);
				}
			}
		});
		getContentPane().add(cmbRole, "span 2, grow");

		getContentPane().add(new JLabel("Username"));
		JTextField txtUsername = new JTextField(20);
		txtUsername.setDocument(new JTextFieldCharLimit(20));
		txtUsername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		if (employee != null)
			txtUsername.setEnabled(false);
		getContentPane().add(txtUsername, "span 2");

		getContentPane().add(new JLabel("Password"));
		JTextField txtPassword = new JTextField(20);
		txtPassword.setDocument(new JTextFieldCharLimit(20));
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtPassword, "span 2");

		getContentPane().add(new JLabel("Name"));
		JTextField txtName = new JTextField(20);
		txtName.setDocument(new JTextFieldCharLimit(20));
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtName, "span 2");

		getContentPane().add(new JLabel("Last Name"));
		JTextField txtLastName = new JTextField(20);
		txtLastName.setDocument(new JTextFieldCharLimit(20));
		txtLastName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z')) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtLastName, "span 2");

		getContentPane().add(new JLabel("Qualification"));
		for (Qualification qualification : Qualification.values()) {
			cmbQualification.addItem(qualification.getName());
		}
		cmbQualification.setEnabled(false);
		getContentPane().add(cmbQualification, "span 2, grow");

		getContentPane().add(new JLabel("Phone Number"));
		JTextField txtPhoneNumber = new JTextField(20);
		txtPhoneNumber.setDocument(new JTextFieldCharLimit(20));
		txtPhoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtPhoneNumber, "span 2");

		getContentPane().add(new JLabel("Date of Birth"));
		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		getContentPane().add(dateOfBirth, "span 2, grow");

		getContentPane().add(new JLabel("Address"));
		JTextField txtAddress = new JTextField(20);
		txtAddress.setDocument(new JTextFieldCharLimit(20));
		txtAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == ' ') || (c == '\\') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtAddress, "span 2");

		getContentPane().add(new JLabel("Gender"));
		JComboBox<String> genderBox = new JComboBox<String>();
		genderBox.addItem("male");
		genderBox.addItem("female");
		getContentPane().add(genderBox, "span 2, grow");

		getContentPane().add(new JLabel("LBO"));
		JTextField txtLBO = new JTextField(20);
		txtLBO.setDocument(new JTextFieldCharLimit(11));
		txtLBO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		getContentPane().add(txtLBO, "span 2");

		getContentPane().add(new JLabel("Years of Service"));
		JSpinner yearsOfService = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		getContentPane().add(yearsOfService, "spanx 2,grow");

		JPanel specializationsPanel = new JPanel(new MigLayout());
		getContentPane().add(new JLabel("Specializations"), "span 2");

		for (models.Specialization specialization : models.Specialization.values()) {
			Checkbox specializationCheckBox = new Checkbox(specialization.toString());
			specializationCheckBox.setEnabled(false);
			specializationCheckBoxes.add(specializationCheckBox);
			specializationsPanel.add(specializationCheckBox, "wrap");
		}
		getContentPane().add(specializationsPanel, "wrap");
		getContentPane().add(new JLabel());

		JButton btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel);

		JButton btnOK = new JButton("OK");
		getContentPane().add(btnOK);

		if (employee != null) {
			txtUsername.setText(employee.getUserName() + "");
			txtPassword.setText(employee.getPassword());
			txtName.setText(employee.getName());
			txtLastName.setText(employee.getLastName());
			txtPhoneNumber.setText(employee.getPhoneNumber());
			txtAddress.setText(employee.getAddress());
			cmbRole.setSelectedItem(employee.getClass().getSimpleName().toString());
			dateOfBirth.setDate(Date.from(employee.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			txtLBO.setText(employee.getLBO());
			if (cmbRole.getSelectedItem().toString().equals("Chemist")) {
				cmbQualification.setSelectedItem(((Chemist) employee).getQualification().toString());
				for (Checkbox specializationCheckBox : specializationCheckBoxes) {
					for (models.Specialization specialization : ((Chemist) employee).getListOfSpecializations()) {
						if (specializationCheckBox.getLabel() == specialization.toString())
							specializationCheckBox.setState(true);
					}
				}
			} else if (cmbRole.getSelectedItem().toString().equals("Medical Technicial")) {
				cmbQualification.setSelectedItem(((Chemist) employee).getQualification().toString());
				for (Checkbox specializationCheckBox : specializationCheckBoxes)
					specializationCheckBox.setEnabled(false);
			} else {
				cmbQualification.setEditable(false);
				for (Checkbox specializationCheckBox : specializationCheckBoxes)
					specializationCheckBox.setEnabled(false);
			}
		}

		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String usernameString = txtUsername.getText().trim();
					String passwordString = txtPassword.getText().trim();
					String nameString = txtName.getText().trim();
					String lastnameString = txtLastName.getText().trim();
					String addressString = txtAddress.getText().trim();
					String phoneNumberString = txtPhoneNumber.getText().trim();
					String roleString = cmbRole.getSelectedItem().toString().trim();
					Qualification qualificationString = Qualification
							.fromString(cmbQualification.getSelectedItem().toString().trim());
					LocalDate dateOfBirthString = dateOfBirth.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					String LBO = txtLBO.getText();
					int yos = (Integer) yearsOfService.getValue();
					String genderString = genderBox.getSelectedItem().toString();
					String allowedUserName = null;
					String allowedLBO = null;
					if (employee != null) {
						allowedLBO = employee.getLBO();
						allowedUserName = employee.getUserName();
					}
					String validationText = Validations.validationTextForSignUp(false, allowedUserName, allowedLBO,
							usernameString, passwordString, passwordString, LBO, nameString, lastnameString,
							addressString, phoneNumberString, dateOfBirthString);
					if (validationText.equals("")) {
						if (employee != null) {
							switch (roleString.toLowerCase()) {
							case "admin":
								Users.removeAdmin(employee.getUserName());
								Users.addAdmin(LBO, usernameString, passwordString, nameString, lastnameString,
										phoneNumberString, addressString, dateOfBirthString, genderString, yos);
								PanelEmployees.employees.remove(employee);
								PanelEmployees.employees.add(new Admin(LBO, usernameString, passwordString, nameString,
										lastnameString, phoneNumberString, addressString, dateOfBirthString,
										genderString, true, yos));
								dispose();
								break;
							case "chemist":
								ArrayList<Specialization> specializations = new ArrayList<Specialization>();
								for (Checkbox specializationCheckBox : specializationCheckBoxes) {
									if (specializationCheckBox.getState()) {
										specializations
												.add(Specialization.fromString(specializationCheckBox.getLabel()));
									}
								}
								Users.removeChemist(employee.getUserName());
								Users.addChemist(LBO, usernameString, passwordString, nameString, lastnameString,
										phoneNumberString, addressString, dateOfBirthString, genderString, yos,
										qualificationString, specializations);
								PanelEmployees.employees.remove(employee);
								PanelEmployees.employees.add(new Chemist(LBO, usernameString, passwordString,
										nameString, lastnameString, phoneNumberString, addressString, dateOfBirthString,
										genderString, true, yos, qualificationString, specializations));
								dispose();
								break;
							case "medical technicial":
								Users.removeMedicalTechnicial(employee.getUserName());
								Users.addMedicalTechnicial(LBO, usernameString, passwordString, nameString,
										lastnameString, phoneNumberString, addressString, dateOfBirthString,
										genderString, yos, qualificationString);
								PanelEmployees.employees.remove(employee);
								PanelEmployees.employees.add(new MedicalTechnicial(LBO, usernameString, passwordString,
										nameString, lastnameString, phoneNumberString, addressString, dateOfBirthString,
										genderString, true, yos, qualificationString));
								dispose();
								break;
							default:
								break;
							}
						} else {
							if (Validations.isUniqueUserName(usernameString)) {
								if (Validations.isUniqueLBO(LBO)) {
									switch (roleString.toLowerCase()) {
									case "admin":
										Users.addAdmin(LBO, usernameString, passwordString, nameString, lastnameString,
												phoneNumberString, addressString, dateOfBirthString, genderString, yos);
										PanelEmployees.employees.add(new Admin(LBO, usernameString, passwordString,
												nameString, lastnameString, phoneNumberString, addressString,
												dateOfBirthString, genderString, true, yos));
										dispose();
										break;
									case "chemist":
										ArrayList<models.Specialization> specializations = new ArrayList<models.Specialization>();
										for (Checkbox specializationCheckBox : specializationCheckBoxes) {
											if (specializationCheckBox.getState()) {
												specializations.add(
														Specialization.fromString(specializationCheckBox.getLabel()));
											}
										}
										Users.addChemist(LBO, usernameString, passwordString, nameString,
												lastnameString, phoneNumberString, addressString, dateOfBirthString,
												genderString, yos, qualificationString, specializations);
										PanelEmployees.employees.add(new Chemist(LBO, usernameString, passwordString,
												nameString, lastnameString, phoneNumberString, addressString,
												dateOfBirthString, genderString, true, yos, qualificationString,
												specializations));
										dispose();
										break;
									case "medical technicial":
										Users.addMedicalTechnicial(LBO, usernameString, passwordString, nameString,
												lastnameString, phoneNumberString, addressString, dateOfBirthString,
												genderString, yos, qualificationString);
										PanelEmployees.employees.add(new MedicalTechnicial(LBO, usernameString,
												passwordString, nameString, lastnameString, phoneNumberString,
												addressString, dateOfBirthString, genderString, true, yos,
												qualificationString));
										dispose();
										break;
									default:
										break;
									}
								} else {
									JOptionPane.showMessageDialog(null, "LBO is not unique.", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Username is not unique.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else
						JOptionPane.showMessageDialog(null, validationText, "Error", JOptionPane.ERROR_MESSAGE);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error occured. Please try again later.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeAddEditDialog.this.dispose();
			}
		});
	}
}
