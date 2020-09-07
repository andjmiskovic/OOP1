package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

import fileHandler.Users;
import functions.JTextFieldCharLimit;
import functions.Validations;
import models.CurrentUser;
import models.Patient;

public class PanelSignUp extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;

	// namena moze biti "VIEW" - izmena sopstvenih podataka kod pacijenta
	// "REGISTER" - kod pacijenta ili kod med.teh.
	public PanelSignUp(String caseString) {
		setUp(caseString);

	}

	public static JPanel setUp(String caseString) {
		Color myBlue = new Color(6, 56, 74);
		Font font = new Font("Tahoma", Font.PLAIN, 20);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelSignUp = new JLabel();
		if (caseString.equals("VIEW"))
			labelSignUp.setText("MY DATA");
		else
			labelSignUp.setText("SIGN UP");
		labelSignUp.setForeground(Color.WHITE);
		labelSignUp.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelSignUp.setBounds(165, 51, 516, 59);
		changingPanel.add(labelSignUp);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelSignUp.class.getResource("/pictures/myDataBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel labelUserName = new JLabel("Username:");
		labelUserName.setForeground(Color.WHITE);
		labelUserName.setFont(font);
		labelUserName.setBounds(46, 183, 204, 33);
		changingPanel.add(labelUserName);

		JTextField userName = new JTextField();
		userName.setBounds(46, 227, 350, 33);
		userName.setDocument(new JTextFieldCharLimit(20));
		userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		changingPanel.add(userName);
		userName.setColumns(10);

		JTextField name = new JTextField();
		name.setColumns(10);
		name.setBounds(584, 325, 350, 33);
		name.setDocument(new JTextFieldCharLimit(20));
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
		changingPanel.add(name);

		JLabel labelName = new JLabel("Name:");
		labelName.setForeground(Color.WHITE);
		labelName.setFont(font);
		labelName.setBounds(584, 281, 204, 33);
		changingPanel.add(labelName);

		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setFont(font);
		labelPassword.setBounds(46, 282, 204, 33);
		changingPanel.add(labelPassword);

		JTextField lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(584, 227, 350, 33);
		lastName.setDocument(new JTextFieldCharLimit(20));
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
		changingPanel.add(lastName);

		JLabel labelLastName = new JLabel("Last Name:");
		labelLastName.setForeground(Color.WHITE);
		labelLastName.setFont(font);
		labelLastName.setBounds(584, 183, 204, 33);
		labelLastName.setVisible(true);
		changingPanel.add(labelLastName);

		JLabel labelPassword2 = new JLabel("Confirm Password:");
		labelPassword2.setForeground(Color.WHITE);
		labelPassword2.setFont(font);
		labelPassword2.setBounds(46, 380, 204, 33);
		changingPanel.add(labelPassword2);

		JTextField address = new JTextField();
		address.setColumns(10);
		address.setBounds(46, 618, 350, 33);
		address.setDocument(new JTextFieldCharLimit(30));
		address.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		changingPanel.add(address);

		JLabel labelAddress = new JLabel("Address:");
		labelAddress.setForeground(Color.WHITE);
		labelAddress.setFont(font);
		labelAddress.setBounds(46, 574, 204, 33);
		changingPanel.add(labelAddress);

		JPasswordField password = new JPasswordField();
		password.setBounds(46, 326, 350, 33);
		password.setDocument(new JTextFieldCharLimit(20));
		password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		changingPanel.add(password);

		JPasswordField password2 = new JPasswordField();
		password2.setBounds(46, 424, 350, 33);
		password2.setDocument(new JTextFieldCharLimit(20));
		password2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'Z')) || ((c >= 'a') && (c <= 'z'))
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		changingPanel.add(password2);

		JLabel labelaLBO = new JLabel("LBO:");
		labelaLBO.setForeground(Color.WHITE);
		labelaLBO.setFont(font);
		labelaLBO.setBounds(46, 474, 282, 33);
		changingPanel.add(labelaLBO);

		JTextField LBO = new JTextField();
		LBO.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		LBO.setColumns(10);
		LBO.setBounds(46, 518, 350, 33);
		LBO.setDocument(new JTextFieldCharLimit(11));
		changingPanel.add(LBO);

		JLabel labelPhone = new JLabel("Phone Number:");
		labelPhone.setForeground(Color.WHITE);
		labelPhone.setFont(font);
		labelPhone.setBounds(584, 475, 204, 33);
		changingPanel.add(labelPhone);

		JTextField phoneNumber = new JTextField();
		phoneNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(584, 518, 350, 33);
		phoneNumber.setDocument(new JTextFieldCharLimit(15));
		changingPanel.add(phoneNumber);

		JComboBox<String> gender = new JComboBox<String>();
		gender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gender.setModel(new DefaultComboBoxModel<String>(new String[] { "male", "female" }));
		gender.setSelectedIndex(0);
		gender.setBackground(Color.WHITE);
		gender.setBounds(584, 424, 350, 33);
		changingPanel.add(gender);

		JLabel labelGender = new JLabel("Gender:");
		labelGender.setForeground(Color.WHITE);
		labelGender.setFont(font);
		labelGender.setBounds(584, 375, 204, 33);
		changingPanel.add(labelGender);

		JLabel labelDateOfBirth = new JLabel("Date Of Birth:");
		labelDateOfBirth.setForeground(Color.WHITE);
		labelDateOfBirth.setFont(font);
		labelDateOfBirth.setBounds(584, 574, 204, 33);
		changingPanel.add(labelDateOfBirth);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(584, 618, 350, 33);
		dateChooser.setMaxSelectableDate(new Date());
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changingPanel.add(dateChooser);

		if (caseString.equals("VIEW")) {
			userName.setText(CurrentUser.getCurrentPatient().getUserName());
			name.setText(CurrentUser.getCurrentPatient().getName());
			lastName.setText(CurrentUser.getCurrentPatient().getLastName());
			password.setText(CurrentUser.getCurrentPatient().getPassword());
			password2.setText(CurrentUser.getCurrentPatient().getPassword());
			LBO.setText(CurrentUser.getCurrentPatient().getLBO());
			address.setText(CurrentUser.getCurrentPatient().getAddress());
			phoneNumber.setText(CurrentUser.getCurrentPatient().getPhoneNumber());
			gender.setSelectedItem(CurrentUser.getCurrentPatient().getGender());
			dateChooser.setDate(Date.from(
					CurrentUser.getCurrentPatient().getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		JLabel correctData = new JLabel("");
		correctData.setFont(new Font("Tahoma", Font.PLAIN, 25));
		correctData.setForeground(Color.CYAN);
		correctData.setBounds(46, 795, 888, 111);
		changingPanel.add(correctData);

		JButton buttonSave = new JButton("SAVE CHANGES");
		buttonSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userNameString = userName.getText();
				String passwordString = new String(password.getPassword());
				String password2String = new String(password2.getPassword());
				String LBOString = LBO.getText();
				String nameString = name.getText();
				String lastNameString = lastName.getText();
				String adressString = address.getText();
				String phoneNumberString = phoneNumber.getText();
				LocalDate dateOfBirth = dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String genderString = gender.getSelectedItem().toString();
				if (caseString.equals("VIEW")) {
					String validationString = Validations.validationTextForSignUp(false,
							CurrentUser.getCurrentPatient().getUserName(), CurrentUser.getCurrentPatient().getLBO(),
							userNameString, passwordString, password2String, LBOString, nameString, lastNameString,
							adressString, phoneNumberString, dateOfBirth);
					if (validationString.equals("")) {
						correctData.setText("Changes have been saved successfully!");
						Users.removePatient(CurrentUser.getCurrentPatient().getUserName());
						Users.addPatient(LBOString, userNameString, passwordString, nameString, lastNameString,
								phoneNumberString, adressString, dateOfBirth, genderString);
						CurrentUser.setCurrentPatient(Users.getPatientByLBO(LBOString));
					} else
						correctData.setText(validationString);
				} else {
					String validationString = Validations.validationTextForSignUp(true, "", "", userNameString,
							passwordString, password2String, LBOString, nameString, lastNameString, adressString,
							phoneNumberString, dateOfBirth);
					if (validationString.equals("")) {
						Users.addPatient(LBOString, userNameString, passwordString, nameString, lastNameString,
								phoneNumberString, adressString, dateOfBirth, genderString);
						CurrentUser.setClassOfTheCurrentUser("Patient");
						CurrentUser.setCurrentPatient(new Patient(LBOString, userNameString, passwordString, nameString,
								lastNameString, phoneNumberString, adressString, dateOfBirth, genderString, true));
						MenuPatient meniPatient = new MenuPatient();
						meniPatient.setVisible(true);
						SwingUtilities.getWindowAncestor(changingPanel).dispose();
					} else
						correctData.setText(validationString);
				}
			}
		});
		buttonSave.setBounds(46, 700, 888, 64);
		buttonSave.setFont(font);
		buttonSave.setBackground(myBlue);
		buttonSave.setForeground(SystemColor.text);
		changingPanel.add(buttonSave);

		return changingPanel;
	}
}
