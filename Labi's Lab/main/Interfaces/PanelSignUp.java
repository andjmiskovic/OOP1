package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import classes.CurrentUser;
import classes.Patient;
import fileHandler.Users;
import functions.JTextFieldCharLimit;
import functions.Validations;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;

public class PanelSignUp extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;

	// namena moze biti "VIEW" - izmena sopstvenih podataka kod pacijenta ili
	// med.teh.,
	// "REGISTER" - kod pacijenta ili kod med.teh.
	public PanelSignUp(String caseString) {
		setUp(caseString);

	}

	public static JPanel setUp(String caseString) {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelSignUp = new JLabel(caseString);
		labelSignUp.setForeground(Color.WHITE);
		labelSignUp.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelSignUp.setBounds(165, 51, 516, 59);
		changingPanel.add(labelSignUp);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelSignUp.class.getResource("/Interfaces/signUpBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel labelUserName = new JLabel("Username:");
		labelUserName.setForeground(Color.WHITE);
		labelUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUserName.setBounds(46, 183, 204, 33);
		changingPanel.add(labelUserName);

		JTextField userName = new JTextField();
		userName.setBounds(46, 227, 350, 33);
		userName.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(userName);
		userName.setColumns(10);

		JTextField name = new JTextField();
		name.setColumns(10);
		name.setBounds(46, 618, 350, 33);
		name.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(name);

		JLabel labelName = new JLabel("Name:");
		labelName.setForeground(Color.WHITE);
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelName.setBounds(46, 574, 204, 33);
		changingPanel.add(labelName);

		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPassword.setBounds(46, 282, 204, 33);
		changingPanel.add(labelPassword);

		JTextField lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(584, 227, 350, 33);
		lastName.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(lastName);

		JLabel labelLastName = new JLabel("Last Name:");
		labelLastName.setForeground(Color.WHITE);
		labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelLastName.setBounds(584, 183, 204, 33);
		labelLastName.setVisible(true);
		changingPanel.add(labelLastName);

		JLabel labelPassword2 = new JLabel("Confirm Password:");
		labelPassword2.setForeground(Color.WHITE);
		labelPassword2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPassword2.setBounds(46, 380, 204, 33);
		changingPanel.add(labelPassword2);

		JTextField address = new JTextField();
		address.setColumns(10);
		address.setBounds(584, 325, 350, 33);
		address.setDocument(new JTextFieldCharLimit(30));
		changingPanel.add(address);

		JLabel labelAddress = new JLabel("Address:");
		labelAddress.setForeground(Color.WHITE);
		labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAddress.setBounds(584, 281, 204, 33);
		changingPanel.add(labelAddress);

		JPasswordField password = new JPasswordField();
		password.setBounds(46, 326, 350, 33);
		password.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(password);

		JPasswordField password2 = new JPasswordField();
		password2.setBounds(46, 424, 350, 33);
		password2.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(password2);

		JLabel labelaLBO = new JLabel("LBO:");
		labelaLBO.setForeground(Color.WHITE);
		labelaLBO.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaLBO.setBounds(46, 474, 282, 33);
		changingPanel.add(labelaLBO);

		JTextField LBO = new JTextField();
		LBO.setColumns(10);
		LBO.setBounds(46, 518, 350, 33);
		LBO.setDocument(new JTextFieldCharLimit(11));
		changingPanel.add(LBO);

		JLabel labelPhone = new JLabel("Phone Number:");
		labelPhone.setForeground(Color.WHITE);
		labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPhone.setBounds(584, 475, 204, 33);
		changingPanel.add(labelPhone);

		JTextField phoneNumber = new JTextField();
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
		labelGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelGender.setBounds(584, 375, 204, 33);
		changingPanel.add(labelGender);

		JLabel labelDateOfBirth = new JLabel("Date Of Birth:");
		labelDateOfBirth.setForeground(Color.WHITE);
		labelDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDateOfBirth.setBounds(584, 574, 204, 33);
		changingPanel.add(labelDateOfBirth);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(584, 618, 350, 153);
		changingPanel.add(calendar);

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
			calendar.setDate(Date.from(
					CurrentUser.getCurrentPatient().getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		JLabel correctData = new JLabel("");
		correctData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		correctData.setForeground(Color.CYAN);
		correctData.setBounds(46, 795, 888, 111);
		changingPanel.add(correctData);

		JPanel panelFinish = new JPanel();
		panelFinish.setBounds(46, 704, 350, 67);
		panelFinish.setLayout(null);
		panelFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userNameString = userName.getText();
				String passwordString = new String(password.getPassword());
				String password2String = new String(password2.getPassword());
				String LBOString = LBO.getText();
				String nameString = name.getText();
				String lastNameString = lastName.getText();
				String adressString = address.getText();
				String phoneNumberString = phoneNumber.getText();
				LocalDate dateOfBirth = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String genderString = gender.getSelectedItem().toString();
				if (caseString.equals("VIEW")) {
					correctData.setText("Changes have been saved successfully!");
					Users.removePatient(CurrentUser.getCurrentPatient().getUserName());
					Users.addPatient(LBOString, userNameString, passwordString, nameString,
							lastNameString, phoneNumberString, adressString, dateOfBirth, genderString);
				} else {
					String validationString = Validations.validationTextForSignUp(userNameString, passwordString,
							password2String, LBOString, nameString, lastNameString, adressString, phoneNumberString);
					if (validationString.equals("")) {
						Users.addPatient(LBOString, userNameString, passwordString, nameString, lastNameString,
								phoneNumberString, adressString, dateOfBirth, genderString);
						CurrentUser.setClassOfTheCurrentUser("Patient");
						CurrentUser.setCurrentPatient(new Patient(LBOString, userNameString, passwordString, nameString,
								lastNameString, phoneNumberString, adressString, dateOfBirth, genderString, true));
						correctData.setText("You signed up successfully!");
						MenuPatient meniPatient = new MenuPatient();
						meniPatient.setVisible(true);
						SwingUtilities.getWindowAncestor(changingPanel).dispose();
					} else
						correctData.setText(validationString);
				}
			}
		});

		JLabel finish = new JLabel();
		finish.setForeground(myBlue);
		finish.setFont(new Font("Tahoma", Font.BOLD, 26));
		finish.setHorizontalAlignment(SwingConstants.CENTER);
		finish.setBounds(0, 0, 350, 67);
		if (caseString.equals("VIEW")) {
			finish.setText("SAVE CHANGES");
		} else {
			finish.setText("FINISH");
		}
		panelFinish.add(finish);
		changingPanel.add(panelFinish);

		return changingPanel;
	}
}
