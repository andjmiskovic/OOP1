package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;

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
import functions.Validations;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Registracija extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel panelKojiSeMenja;

	public Registracija() {
		setUp();

	}

	public static JPanel setUp() {
		Color myBlue = new Color(6, 56, 74);

		panelKojiSeMenja = new JPanel();
		panelKojiSeMenja.setBounds(324, 0, 720, 719);
		panelKojiSeMenja.setBackground(myBlue);
		panelKojiSeMenja.setLayout(null);

		JLabel labelaRegistracija = new JLabel("REGISTRACIJA");
		labelaRegistracija.setForeground(Color.WHITE);
		labelaRegistracija.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelaRegistracija.setBounds(46, 34, 308, 59);
		panelKojiSeMenja.add(labelaRegistracija);

		JLabel labelaKorisnickoIme = new JLabel("Korisničko Ime:");
		labelaKorisnickoIme.setForeground(Color.WHITE);
		labelaKorisnickoIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaKorisnickoIme.setBounds(46, 113, 204, 33);
		panelKojiSeMenja.add(labelaKorisnickoIme);

		JTextField korisnickoIme = new JTextField();
		korisnickoIme.setBounds(46, 157, 282, 33);
		panelKojiSeMenja.add(korisnickoIme);
		korisnickoIme.setColumns(10);

		JTextField ime = new JTextField();
		ime.setColumns(10);
		ime.setBounds(381, 157, 282, 33);
		panelKojiSeMenja.add(ime);

		JLabel labelIme = new JLabel("Ime:");
		labelIme.setForeground(Color.WHITE);
		labelIme.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIme.setBounds(381, 113, 204, 33);
		panelKojiSeMenja.add(labelIme);

		JLabel labelaLozinka = new JLabel("Lozinka:");
		labelaLozinka.setForeground(Color.WHITE);
		labelaLozinka.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaLozinka.setBounds(46, 212, 204, 33);
		panelKojiSeMenja.add(labelaLozinka);

		JTextField prezime = new JTextField();
		prezime.setColumns(10);
		prezime.setBounds(381, 256, 282, 33);
		panelKojiSeMenja.add(prezime);

		JLabel labelPrezime = new JLabel("Prezime:");
		labelPrezime.setForeground(Color.WHITE);
		labelPrezime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPrezime.setBounds(381, 212, 204, 33);
		labelPrezime.setVisible(true);
		panelKojiSeMenja.add(labelPrezime);

		JLabel labelaPotrvdaLozinke = new JLabel("Potvrdite Lozinku:");
		labelaPotrvdaLozinke.setForeground(Color.WHITE);
		labelaPotrvdaLozinke.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaPotrvdaLozinke.setBounds(46, 310, 204, 33);
		panelKojiSeMenja.add(labelaPotrvdaLozinke);

		JTextField adresa = new JTextField();
		adresa.setColumns(10);
		adresa.setBounds(381, 354, 282, 33);
		panelKojiSeMenja.add(adresa);

		JLabel labelAdresa = new JLabel("Adresa:");
		labelAdresa.setForeground(Color.WHITE);
		labelAdresa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAdresa.setBounds(381, 310, 204, 33);
		panelKojiSeMenja.add(labelAdresa);

		JPasswordField lozinka = new JPasswordField();
		lozinka.setBounds(46, 256, 282, 33);
		panelKojiSeMenja.add(lozinka);

		JPasswordField lozinkaOpet = new JPasswordField();
		lozinkaOpet.setBounds(46, 354, 282, 33);
		panelKojiSeMenja.add(lozinkaOpet);

		JLabel labelaLBO = new JLabel("LBO: (Lični Broj Osiguranja)");
		labelaLBO.setForeground(Color.WHITE);
		labelaLBO.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaLBO.setBounds(46, 404, 282, 33);
		panelKojiSeMenja.add(labelaLBO);

		JTextField LBO = new JTextField();
		LBO.setColumns(10);
		LBO.setBounds(46, 448, 282, 33);
		panelKojiSeMenja.add(LBO);

		JLabel labelBrojTelefona = new JLabel("Broj Telefona:");
		labelBrojTelefona.setForeground(Color.WHITE);
		labelBrojTelefona.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelBrojTelefona.setBounds(46, 498, 204, 33);
		panelKojiSeMenja.add(labelBrojTelefona);

		JTextField brojTelefona = new JTextField();
		brojTelefona.setColumns(10);
		brojTelefona.setBounds(46, 542, 282, 33);
		panelKojiSeMenja.add(brojTelefona);

		JComboBox<String> pol = new JComboBox<String>();
		pol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pol.setModel(new DefaultComboBoxModel<String>(new String[] { "Muški", "Ženski" }));
		pol.setSelectedIndex(0);
		pol.setBackground(Color.WHITE);
		pol.setBounds(381, 446, 282, 33);
		panelKojiSeMenja.add(pol);

		JLabel lblPol = new JLabel("Pol:");
		lblPol.setForeground(Color.WHITE);
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPol.setBounds(381, 404, 204, 33);
		panelKojiSeMenja.add(lblPol);

		JLabel labelaDatumRodjenja = new JLabel("Datum Rođenja:");
		labelaDatumRodjenja.setForeground(Color.WHITE);
		labelaDatumRodjenja.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaDatumRodjenja.setBounds(381, 498, 204, 33);
		panelKojiSeMenja.add(labelaDatumRodjenja);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(381, 542, 282, 153);
		panelKojiSeMenja.add(calendar);

		JLabel ispravnostPodataka = new JLabel("");
		ispravnostPodataka.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ispravnostPodataka.setForeground(Color.CYAN);
		ispravnostPodataka.setBounds(320, 34, 343, 59);
		panelKojiSeMenja.add(ispravnostPodataka);

		JPanel panelGotovo = new JPanel();
		panelGotovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userName = korisnickoIme.getText();
				String password = new String(lozinka.getPassword());
				String password2 = new String(lozinkaOpet.getPassword());
				String LBOString = LBO.getText();
				String name = ime.getText();
				String lastName = prezime.getText();
				String adress = adresa.getText();
				String phoneNumber = brojTelefona.getText();
				LocalDate dateOfBirth = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				String gender = pol.getSelectedItem().toString();
				String validationString = Validations.validationTextForSignUp(userName, password, password2, LBOString, name,
						lastName, adress, phoneNumber);
				if (validationString.equals("")) {
					Patient newPatient = new Patient(LBOString, userName, password, name, lastName, phoneNumber, adress,
							dateOfBirth, gender, true);
					Users.listOfPatients.add(newPatient);
					ispravnostPodataka.setText("Uspešno ste dodali pacijenta!");
					if (CurrentUser.getClassOfTheCurrentUser() == null) {
						CurrentUser.setClassOfTheCurrentUser("Patient");
						CurrentUser.setCurrentPatient(newPatient);
						ispravnostPodataka.setText("Uspešno ste registrovali pacijenta uraaa!");
						MeniPacijent meniPacijent = new MeniPacijent();
						meniPacijent.setVisible(true);
						SwingUtilities.getWindowAncestor(panelKojiSeMenja).dispose();
					} else
						ispravnostPodataka.setText("Uspešno ste registrovali pacijenta!");

				} else
					ispravnostPodataka.setText(validationString);
			}
		});
		panelGotovo.setBounds(46, 613, 282, 59);
		panelKojiSeMenja.add(panelGotovo);
		panelGotovo.setLayout(null);

		JLabel gotovo = new JLabel("GOTOVO");
		gotovo.setForeground(myBlue);
		gotovo.setFont(new Font("Tahoma", Font.BOLD, 26));
		gotovo.setHorizontalAlignment(SwingConstants.CENTER);
		gotovo.setBounds(0, 0, 282, 59);
		gotovo.setToolTipText("Kliknite za Potvrditu Registracije");
		panelGotovo.add(gotovo);

		return panelKojiSeMenja;
	}
}
