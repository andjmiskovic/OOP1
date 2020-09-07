package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import fileHandler.AllDiscounts;
import fileHandler.SalaryCoefficients;
import fileHandler.Users;
import functions.JTextFieldCharLimit;
import models.CurrentUser;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField userNameField;
	JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users.readUsersFromCSV();
					AllDiscounts.readFromCSV();
					AllAnalysisTypes.readFromCSV();
					SalaryCoefficients.readFromCSV();
					AllAnalysisRequests.readRequestsFromCSV();
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

		setTitle("Login");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 970, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(0, 0, 442, 475);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Login.class.getResource("/pictures/girl.jpg")));
		icon.setBounds(0, -17, 463, 507);
		panel_1.add(icon);

		JLabel labelError = new JLabel("");
		labelError.setHorizontalAlignment(SwingConstants.CENTER);
		labelError.setForeground(Color.CYAN);
		labelError.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelError.setBounds(478, 293, 462, 32);
		contentPane.add(labelError);

		JButton login = new JButton("LOGIN");
		login.addActionListener(new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = userNameField.getText();
				String password = passwordField.getText();
				String classString = CurrentUser.setCurrentUser(userName, password);
				if (classString.equals("")) {
					labelError.setText("Wrong password or username.");
				} else {
					switch (classString) {
					case "Patient":
						frame.dispose();
						MenuPatient menuPatient = new MenuPatient();
						menuPatient.setVisible(true);
						break;
					case "Chemist":
						frame.dispose();
						MenuChemist menuChemist = new MenuChemist();
						menuChemist.setVisible(true);
						break;
					case "MedicalTechnicial":
						frame.dispose();
						MenuTechnicial menuTechnicial = new MenuTechnicial();
						menuTechnicial.setVisible(true);
						break;
					case "Admin":
						frame.dispose();
						MenuAdmin menuAdmin = new MenuAdmin();
						menuAdmin.setVisible(true);
						break;
					default:
						break;
					}
				}
			}
		});
		login.setFont(new Font("Dialog", Font.PLAIN, 16));
		login.setBackground(myBlue);
		login.setForeground(SystemColor.text);
		login.setBounds(552, 341, 302, 49);
		contentPane.getRootPane().setDefaultButton(login);
		contentPane.add(login);

		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setForeground(myBlue);
		labelUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelUsername.setBounds(552, 109, 179, 32);
		contentPane.add(labelUsername);

		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setForeground(myBlue);
		labelPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelPassword.setBounds(552, 195, 115, 32);
		contentPane.add(labelPassword);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(552, 238, 302, 32);
		passwordField.setDocument(new JTextFieldCharLimit(20));
		contentPane.add(passwordField);

		userNameField = new JTextField();
		userNameField.setColumns(10);
		userNameField.setBounds(552, 159, 302, 32);
		userNameField.setDocument(new JTextFieldCharLimit(20));
		contentPane.add(userNameField);

		JLabel labelWelcome = new JLabel("Welcome to our lab!");
		labelWelcome.setFont(new Font("Tahoma", Font.BOLD, 26));
		labelWelcome.setForeground(myBlue);
		labelWelcome.setBounds(552, 28, 442, 72);
		contentPane.add(labelWelcome);

		JLabel labelNoAccount = new JLabel("Don't have an account? ");
		labelNoAccount.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MenuClass newFrame = new MenuClass("Sign Up");
				PanelSignUp registracija = new PanelSignUp("Sign Up");
				newFrame.remove(newFrame.changingPanel);
				newFrame.getContentPane().add(registracija.changingPanel);
				newFrame.setVisible(true);
				frame.dispose();
			}
		});
		labelNoAccount.setForeground(new Color(6, 56, 74));
		labelNoAccount.setFont(new Font("Tahoma", Font.PLAIN, 22));
		labelNoAccount.setBounds(530, 396, 435, 68);
		contentPane.add(labelNoAccount);

		JLabel labelSignUp = new JLabel("Sign Up!");
		labelSignUp.setForeground(new Color(6, 56, 74));
		labelSignUp.setFont(new Font("Tahoma", Font.BOLD, 22));
		labelSignUp.setBounds(773, 394, 380, 72);
		contentPane.add(labelSignUp);
	}
}
