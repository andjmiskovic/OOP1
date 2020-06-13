package Interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUp() {
		Color myBlue = new Color(6, 56, 74);

		setTitle("Registracija Novih Pacijenata");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 614);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 449, 575);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SignUp.class.getResource("/Interfaces/labb.jpg")));
		lblNewLabel_1.setBounds(0, 0, 449, 575);
		panel_1.add(lblNewLabel_1);
		
		Button button = new Button("REGISTRACIJA");
		button.setFont(new Font("Dialog", Font.PLAIN, 16));
		button.setBackground(myBlue);
		button.setForeground(SystemColor.text);
		button.setBounds(477, 372, 225, 60);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Korisničko ime:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(myBlue);
		lblNewLabel.setBounds(477, 27, 115, 32);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(477, 57, 225, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLozinka.setForeground(myBlue);
		lblLozinka.setBounds(477, 91, 115, 32);
		contentPane.add(lblLozinka);
		
		JLabel lblPotvrdaLozinke = new JLabel("Potvrdite Lozinku:");
		lblPotvrdaLozinke.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPotvrdaLozinke.setForeground(myBlue);
		lblPotvrdaLozinke.setBounds(477, 154, 149, 32);
		contentPane.add(lblPotvrdaLozinke);
		
		JLabel lblLboliniBroj = new JLabel("LBO: ");
		lblLboliniBroj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLboliniBroj.setForeground(myBlue);
		lblLboliniBroj.setBounds(477, 217, 225, 32);
		contentPane.add(lblLboliniBroj);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(477, 247, 225, 27);
		contentPane.add(textField_3);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIme.setForeground(myBlue);
		lblIme.setBounds(719, 27, 115, 32);
		contentPane.add(lblIme);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(719, 57, 225, 27);
		contentPane.add(textField_4);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrezime.setForeground(myBlue);
		lblPrezime.setBounds(719, 91, 115, 32);
		contentPane.add(lblPrezime);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(719, 121, 225, 27);
		contentPane.add(textField_5);
		
		JLabel lblBrojTelefona = new JLabel("Broj Telefona:");
		lblBrojTelefona.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBrojTelefona.setBounds(719, 154, 115, 32);
		lblBrojTelefona.setForeground(myBlue);
		contentPane.add(lblBrojTelefona);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(719, 184, 225, 27);
		contentPane.add(textField_6);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdresa.setForeground(myBlue);
		lblAdresa.setBounds(719, 217, 115, 32);
		contentPane.add(lblAdresa);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(719, 247, 225, 27);
		contentPane.add(textField_7);
		
		JLabel lblPol = new JLabel("Pol:");
		lblPol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPol.setForeground(myBlue);
		lblPol.setBounds(477, 282, 225, 32);
		contentPane.add(lblPol);
		
		JLabel lblDatumRoenja = new JLabel("Datum Rođenja:");
		lblDatumRoenja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDatumRoenja.setForeground(myBlue);
		lblDatumRoenja.setBounds(719, 282, 225, 32);
		contentPane.add(lblDatumRoenja);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Muški", "Ženski"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(477, 313, 225, 27);
		contentPane.add(comboBox);
				
		passwordField = new JPasswordField();
		passwordField.setBounds(477, 121, 225, 27);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(477, 184, 225, 27);
		contentPane.add(passwordField_1);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(477, 495, 467, 51);
		contentPane.add(label);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(719, 313, 225, 153);
		contentPane.add(calendar);
		
		JLabel label_1 = new JLabel("");		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo300.jpg")));
	}
}
