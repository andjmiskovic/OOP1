package Interfaces;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import classes.CurrentUser;
import fileHandler.Users;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField lozinka;
	private JTextField korisnickoIme;
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users.readUsersFromCSV("C:\\Users\\Lenovo\\eclipse-workspace\\Labi's Lab\\src\\Files\\Users.csv");
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		super();
		frame = this;
		Color myBlue = new Color(6, 56, 74);

		setTitle("Prijava na sistem");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 981, 514);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo300.jpg")));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 442, 475);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Interfaces/girl.jpg")));
		lblNewLabel_1.setBounds(0, -17, 463, 507);
		panel_1.add(lblNewLabel_1);

		JLabel labelaZaIspisGreske = new JLabel("");
		labelaZaIspisGreske.setHorizontalAlignment(SwingConstants.CENTER);
		labelaZaIspisGreske.setForeground(Color.CYAN);
		labelaZaIspisGreske.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelaZaIspisGreske.setBounds(478, 305, 462, 32);
		contentPane.add(labelaZaIspisGreske);

		Button prijava = new Button("PRIJAVA");
		prijava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String userName = korisnickoIme.getText();
				String password = lozinka.getText();
				String classString = CurrentUser.setCurrentUser(userName, password);
				if (classString.equals("")) {
					labelaZaIspisGreske.setText("Pogrešno korisničko ime ili pogrešna lozinka.");
				} else {
					switch (classString) {
					case "Patient":
						frame.dispose();
						MeniPacijent meniPacijent = new MeniPacijent();
						meniPacijent.setVisible(true);
						break;
					case "Chemist":
						frame.dispose();
						MeniLaborant meniLaborant = new MeniLaborant();
						meniLaborant.setVisible(true);
						break;
					case "MedicalTechnicial":
						frame.dispose();
						MeniTehnicar meniTehnicar = new MeniTehnicar();
						meniTehnicar.setVisible(true);
						break;
					case "Admin":
						frame.dispose();
						MeniAdministrator meniAdministrator = new MeniAdministrator();
						meniAdministrator.setVisible(true);
						break;
					default:
						break;
					}
				}
			}
		});
		prijava.setFont(new Font("Dialog", Font.PLAIN, 16));
		prijava.setBackground(myBlue);
		prijava.setForeground(SystemColor.text);
		prijava.setBounds(552, 362, 302, 49);
		contentPane.add(prijava);

		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setForeground(myBlue);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(552, 122, 179, 32);
		contentPane.add(lblNewLabel);

		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setForeground(myBlue);
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLozinka.setBounds(552, 208, 115, 32);
		contentPane.add(lblLozinka);

		lozinka = new JPasswordField();
		lozinka.setColumns(10);
		lozinka.setBounds(552, 251, 302, 32);
		contentPane.add(lozinka);

		korisnickoIme = new JTextField();
		korisnickoIme.setColumns(10);
		korisnickoIme.setBounds(552, 172, 302, 32);
		contentPane.add(korisnickoIme);

		JLabel lblNewLabel_2 = new JLabel("Dobro do\u0161li u na\u0161u laboratoriju!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setForeground(myBlue);
		lblNewLabel_2.setBounds(498, 29, 442, 72);
		contentPane.add(lblNewLabel_2);

		JLabel lblNemateNalog = new JLabel("Nemate nalog? ");
		lblNemateNalog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MeniClass newFrame = new MeniClass("Registracija");
				Registracija registracija = new Registracija();
				newFrame.remove(newFrame.panelKojiSeMenja);
				newFrame.getContentPane().add(registracija.panelKojiSeMenja);
				newFrame.setVisible(true);
				frame.dispose();
			}
		});
		lblNemateNalog.setForeground(new Color(6, 56, 74));
		lblNemateNalog.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNemateNalog.setBounds(543, 413, 442, 72);
		contentPane.add(lblNemateNalog);

		JLabel lblRegistrujteSe = new JLabel("Registrujte se!");
		lblRegistrujteSe.setForeground(new Color(6, 56, 74));
		lblRegistrujteSe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRegistrujteSe.setBounds(706, 413, 442, 72);
		contentPane.add(lblRegistrujteSe);
	}
}
