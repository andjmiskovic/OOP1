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

public class PanelSignUp extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;

	// namena moze biti "VIEW" - izmena sopstvenih podataka kod pacijenta ili med.teh., 
	//					"REGISTER" - kod pacijenta ili kod med.teh.
	public PanelSignUp(String caseString) {
		setUp(caseString);

	}

	public static JPanel setUp(String caseString) {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(324, 0, 1070, 719);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);
		
		JLabel labelSignUp = new JLabel(caseString);
		labelSignUp.setForeground(Color.WHITE);
		labelSignUp.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelSignUp.setBounds(46, 34, 308, 59);
		changingPanel.add(labelSignUp);

		JLabel labelUserName = new JLabel("Username:");
		labelUserName.setForeground(Color.WHITE);
		labelUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUserName.setBounds(46, 113, 204, 33);
		changingPanel.add(labelUserName);

		JTextField userName = new JTextField();
		userName.setBounds(46, 157, 282, 33);
		userName.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(userName);
		userName.setColumns(10);

		JTextField name = new JTextField();
		name.setColumns(10);
		name.setBounds(381, 157, 282, 33);
		name.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(name);

		JLabel labelName = new JLabel("Name:");
		labelName.setForeground(Color.WHITE);
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelName.setBounds(381, 113, 204, 33);
		changingPanel.add(labelName);

		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPassword.setBounds(46, 212, 204, 33);
		changingPanel.add(labelPassword);

		JTextField lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(381, 256, 282, 33);
		lastName.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(lastName);

		JLabel labelLastName = new JLabel("Last Name:");
		labelLastName.setForeground(Color.WHITE);
		labelLastName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelLastName.setBounds(381, 212, 204, 33);
		labelLastName.setVisible(true);
		changingPanel.add(labelLastName);

		JLabel labelPassword2 = new JLabel("Confirm Password:");
		labelPassword2.setForeground(Color.WHITE);
		labelPassword2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPassword2.setBounds(46, 310, 204, 33);
		changingPanel.add(labelPassword2);

		JTextField adresa = new JTextField();
		adresa.setColumns(10);
		adresa.setBounds(381, 354, 282, 33);
		adresa.setDocument(new JTextFieldCharLimit(30));
		changingPanel.add(adresa);

		JLabel labelAdresa = new JLabel("Address:");
		labelAdresa.setForeground(Color.WHITE);
		labelAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAdresa.setBounds(381, 310, 204, 33);
		changingPanel.add(labelAdresa);

		JPasswordField password = new JPasswordField();
		password.setBounds(46, 256, 282, 33);
		password.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(password);

		JPasswordField password2 = new JPasswordField();
		password2.setBounds(46, 354, 282, 33);
		password2.setDocument(new JTextFieldCharLimit(20));
		changingPanel.add(password2);

		JLabel labelaLBO = new JLabel("LBO:");
		labelaLBO.setForeground(Color.WHITE);
		labelaLBO.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaLBO.setBounds(46, 404, 282, 33);
		changingPanel.add(labelaLBO);

		JTextField LBO = new JTextField();
		LBO.setColumns(10);
		LBO.setBounds(46, 448, 282, 33);
		LBO.setDocument(new JTextFieldCharLimit(11));
		changingPanel.add(LBO);

		JLabel labelPhone = new JLabel("Phone Number:");
		labelPhone.setForeground(Color.WHITE);
		labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPhone.setBounds(718, 113, 204, 33);
		changingPanel.add(labelPhone);

		JTextField phoneNumber = new JTextField();
		phoneNumber.setColumns(10);
		phoneNumber.setBounds(718, 157, 282, 33);
		phoneNumber.setDocument(new JTextFieldCharLimit(15));
		changingPanel.add(phoneNumber);

		JComboBox<String> gender = new JComboBox<String>();
		gender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gender.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
		gender.setSelectedIndex(0);
		gender.setBackground(Color.WHITE);
		gender.setBounds(381, 446, 282, 33);
		changingPanel.add(gender);

		JLabel labelGender = new JLabel("Gender:");
		labelGender.setForeground(Color.WHITE);
		labelGender.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelGender.setBounds(381, 404, 204, 33);
		changingPanel.add(labelGender);

		JLabel labelDateOfBirth = new JLabel("Date Of Birth:");
		labelDateOfBirth.setForeground(Color.WHITE);
		labelDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDateOfBirth.setBounds(718, 215, 204, 33);
		changingPanel.add(labelDateOfBirth);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(718, 259, 282, 153);
		changingPanel.add(calendar);
		
		if (caseString.equals("VIEW")) {
			userName.setText(CurrentUser.getCurrentPatient().getUserName()); 
			name.setText(CurrentUser.getCurrentPatient().getName()); 
			lastName.setText(CurrentUser.getCurrentPatient().getLastName()); 
			password.setText(CurrentUser.getCurrentPatient().getPassword()); 
			password2.setText(CurrentUser.getCurrentPatient().getPassword()); 
			LBO.setText(CurrentUser.getCurrentPatient().getLBO()); 
			phoneNumber.setText(CurrentUser.getCurrentPatient().getPhoneNumber()); 
			gender.setSelectedItem(CurrentUser.getCurrentPatient().getGender());
			calendar.setDate(Date.from(CurrentUser.getCurrentPatient().getDateOfBirth().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		}

		JLabel correctData = new JLabel("");
		correctData.setFont(new Font("Tahoma", Font.PLAIN, 17));
		correctData.setForeground(Color.CYAN);
		correctData.setBounds(320, 34, 343, 59);
		changingPanel.add(correctData);

		JPanel panelFinish = new JPanel();
		panelFinish.setBounds(46, 613, 282, 59);
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
				String adressString = adresa.getText();
				String phoneNumberString = phoneNumber.getText();
				LocalDate dateOfBirth = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String genderString = gender.getSelectedItem().toString();
				String validationString = Validations.validationTextForSignUp(userNameString, passwordString, password2String, LBOString, nameString,
						lastNameString, adressString, phoneNumberString);
				if (validationString.equals("")) {
					Patient newPatient = new Patient(LBOString, userNameString, passwordString, nameString, lastNameString, phoneNumberString, adressString,
							dateOfBirth, genderString, true);
					Users.listOfPatients.add(newPatient);
					correctData.setText("You added a patient successfully!");
					if (CurrentUser.getClassOfTheCurrentUser() == null) {
						CurrentUser.setClassOfTheCurrentUser("Patient");
						CurrentUser.setCurrentPatient(newPatient);
						correctData.setText("You signed up successfully!");
						MenuPatient meniPacijent = new MenuPatient();
						meniPacijent.setVisible(true);
						SwingUtilities.getWindowAncestor(changingPanel).dispose();
					} else
						correctData.setText("You signed up a patient successfully!");

				} else
					correctData.setText(validationString);
			}
		});

		JLabel gotovo = new JLabel("FINISH");
		gotovo.setForeground(myBlue);
		gotovo.setFont(new Font("Tahoma", Font.BOLD, 26));
		gotovo.setHorizontalAlignment(SwingConstants.CENTER);
		gotovo.setBounds(0, 0, 282, 59);
		gotovo.setToolTipText("Click here to save registration");
		panelFinish.add(gotovo);
		
		JPanel panelSacuvaj = new JPanel();
		panelSacuvaj.setBounds(46, 613, 282, 59);
		panelSacuvaj.setLayout(null);
		panelSacuvaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userNameString = userName.getText();
				String passwordString = new String(password.getPassword());
				String password2String = new String(password2.getPassword());
				String LBOString = LBO.getText();
				String nameString = name.getText();
				String lastNameString = lastName.getText();
				String adressString = adresa.getText();
				String phoneNumberString = phoneNumber.getText();
				LocalDate dateOfBirth = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String genderString = gender.getSelectedItem().toString();
				String validationString = Validations.validationTextForSignUp(userNameString, passwordString, password2String, LBOString, nameString,
						lastNameString, adressString, phoneNumberString);
				if (validationString.equals("")) {
					correctData.setText("Changes have been saved successfully!");
					CurrentUser.getCurrentPatient().updateInfo(LBOString, userNameString, passwordString, nameString, lastNameString, 
							phoneNumberString, adressString, dateOfBirth, genderString, true);
				} else
					correctData.setText(validationString);
			}
		});
		
		JLabel sacuvaj = new JLabel("SAVE");
		sacuvaj.setForeground(myBlue);
		sacuvaj.setFont(new Font("Tahoma", Font.BOLD, 26));
		sacuvaj.setHorizontalAlignment(SwingConstants.CENTER);
		sacuvaj.setBounds(0, 0, 282, 59);
		sacuvaj.setToolTipText("Click here to save changes");
		panelSacuvaj.add(sacuvaj);
		
		if (caseString.equals("VIEW")) {
			changingPanel.add(panelSacuvaj);
		} else {
			changingPanel.add(panelFinish);
		}

		return changingPanel;
	}
}
