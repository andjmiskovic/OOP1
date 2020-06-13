package Interfaces;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Prijava extends MeniClass {
	protected JPanel panelKojiSeMenja;
	
	public Prijava(JPanel pane) {
		super("Prijava Na Sistem");
		Color myBlue = new Color(6, 56, 74);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);

		panelKojiSeMenja = new JPanel();
		panelKojiSeMenja.setBounds(324, 0, 720, 719);
		pane.add(panelKojiSeMenja);
		panelKojiSeMenja.setBackground(myBlue);
		
		Button button = new Button("PRIJAVA");
		button.setFont(new Font("Dialog", Font.PLAIN, 16));
		button.setBackground(myBlue);
		button.setForeground(SystemColor.text);
		button.setBounds(552, 362, 302, 49);
		panelKojiSeMenja.add(button);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setForeground(myBlue);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(552, 122, 179, 32);
		panelKojiSeMenja.add(lblNewLabel);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setForeground(myBlue);
		lblLozinka.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLozinka.setBounds(552, 208, 115, 32);
		panelKojiSeMenja.add(lblLozinka);
		
		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(552, 251, 302, 32);
		panelKojiSeMenja.add(textField_1);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(552, 172, 302, 32);
		panelKojiSeMenja.add(textField);
		
		JLabel lblNewLabel_2 = new JLabel("Dobro do\u0161li u na\u0161u laboratoriju!");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setForeground(myBlue);
		lblNewLabel_2.setBounds(498, 29, 442, 72);
		panelKojiSeMenja.add(lblNewLabel_2);
		
		JLabel label = new JLabel("");
		label.setForeground(myBlue);
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(552, 305, 302, 32);
		panelKojiSeMenja.add(label);
		
		JLabel lblNemateNalog = new JLabel("Nemate nalog? ");
		lblNemateNalog.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				SignUp returnSignUp = new SignUp();
				returnSignUp.setVisible(true);
			}
		});
		lblNemateNalog.setForeground(new Color(6, 56, 74));
		lblNemateNalog.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNemateNalog.setBounds(543, 413, 442, 72);
		panelKojiSeMenja.add(lblNemateNalog);
		
		JLabel lblRegistrujteSe = new JLabel("Registrujte se!");
		lblRegistrujteSe.setForeground(new Color(6, 56, 74));
		lblRegistrujteSe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblRegistrujteSe.setBounds(706, 413, 442, 72);
		panelKojiSeMenja.add(lblRegistrujteSe);
	}

}
