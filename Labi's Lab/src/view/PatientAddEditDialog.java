package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.time.DateUtils;

import com.toedter.calendar.JDateChooser;

import fileHandler.Users;
import functions.JTextFieldCharLimit;
import functions.Validations;
import models.Patient;
import net.miginfocom.swing.MigLayout;

public class PatientAddEditDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Patient patient;

	public PatientAddEditDialog(Patient patient) {
		if (patient != null) {
			setTitle("Edit Patient");
		} else {
			setTitle("Add new Patient");
		}
		this.patient = patient;
		setLocation(300, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout ml = new MigLayout("wrap 3", "[][][]", "[]10[]10[]10[]20[]");
		setLayout(ml);

		add(new JLabel("Username"));
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
		if (patient != null)
			txtUsername.setEnabled(false);
		add(txtUsername, "span 2");

		add(new JLabel("Password"));
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
		add(txtPassword, "span 2");

		add(new JLabel("Name"));
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
		add(txtName, "span 2");

		add(new JLabel("Last Name"));
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
		add(txtLastName, "span 2");

		add(new JLabel("Phone Number"));
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
		add(txtPhoneNumber, "span 2");

		add(new JLabel("Address"));
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
		add(txtAddress, "span 2");

		add(new JLabel("Date of Birth"));
		JDateChooser dateOfBirth = new JDateChooser();
		dateOfBirth.setMaxSelectableDate(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
		add(dateOfBirth, "span 2, grow");

		add(new JLabel("LBO"));
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
		add(txtLBO, "span 2");

		add(new JLabel("Gender"));
		JComboBox<String> genderBox = new JComboBox<String>();
		genderBox.addItem("male");
		genderBox.addItem("female");
		add(genderBox, "span 2");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (patient != null) {
			txtUsername.setText(patient.getUserName() + "");
			txtPassword.setText(patient.getPassword());
			txtName.setText(patient.getName());
			txtLastName.setText(patient.getLastName());
			txtPhoneNumber.setText(patient.getPhoneNumber());
			txtAddress.setText(patient.getAddress());
			try {
				dateOfBirth
						.setDate(Date.from(patient.getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			} catch (Exception e) {
			}
			txtLBO.setText(patient.getLBO());
			genderBox.setSelectedItem(patient.getGender());
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
					LocalDate dateOfBirthString = dateOfBirth.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();
					String LBO = txtLBO.getText();
					String genderString = genderBox.getSelectedItem().toString();
					String allowedUserName = null;
					String allowedLBO = null;
					if (patient != null) {
						allowedLBO = patient.getLBO();
						allowedUserName = patient.getUserName();
					}
					String validationText = Validations.validationTextForSignUp(false, allowedUserName, allowedLBO,
							usernameString, passwordString, passwordString, LBO, nameString, lastnameString,
							addressString, phoneNumberString, dateOfBirthString);
					if (validationText.equals("")) {
						if (patient != null) {
							Users.removePatient(patient.getUserName());
							Users.addPatient(LBO, usernameString, passwordString, nameString, lastnameString,
									phoneNumberString, addressString, dateOfBirthString, genderString);
							dispose();
						} else {
							if (Validations.isUniqueUserName(usernameString)) {
								if (Validations.isUniqueLBO(LBO)) {
									Users.addPatient(LBO, usernameString, passwordString, nameString, lastnameString,
											phoneNumberString, addressString, dateOfBirthString, genderString);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "LBO is not unique.", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Username is not unique.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "You have to enter all data first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PatientAddEditDialog.this.dispose();
			}
		});

	}
}
