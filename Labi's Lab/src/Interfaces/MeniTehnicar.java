package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MeniTehnicar extends MeniClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniTehnicar frame = new MeniTehnicar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void switchPanel(JFrame frame, JPanel noviPanel) {
		frame.remove(panelKojiSeMenja);
		frame.add(noviPanel);
		frame.validate();
	}

	/**
	 * Create the frame.
	 */
	public MeniTehnicar() {		
		super("Meni: Medicinski Tehničar");
		Color myBlue = new Color(6, 56, 74);
		panelMeni.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		JPanel panelZahtevi = new JPanel();
		panelZahtevi.setBackground(Color.WHITE);
		panelZahtevi.setBounds(0, 230, 323, 60);
		panelMeni.add(panelZahtevi);
		panelZahtevi.setLayout(null);
		
		JLabel iconZahtevi = new JLabel("");
		iconZahtevi.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/request.jpg")));
		iconZahtevi.setBounds(30, 0, 50, 60);
		panelZahtevi.add(iconZahtevi);
		
		JLabel labelZahtevi = new JLabel("ZAHTEVI");
		labelZahtevi.setBounds(120, 11, 104, 38);
		panelZahtevi.add(labelZahtevi);
		labelZahtevi.setHorizontalAlignment(SwingConstants.LEFT);
		labelZahtevi.setForeground(myBlue);
		labelZahtevi.setFont(myFont);
		
		
		JPanel panelRezultati = new JPanel();
		panelRezultati.setBackground(Color.WHITE);
		panelRezultati.setBounds(0, 290, 323, 60);
		panelMeni.add(panelRezultati);
		panelRezultati.setLayout(null);
		
		JLabel iconRezultati = new JLabel("");
		iconRezultati.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/results.jpg")));
		iconRezultati.setBounds(30, 0, 50, 60);
		panelRezultati.add(iconRezultati);
		
		JLabel labelRezultati = new JLabel("REZULTATI");
		labelRezultati.setBounds(120, 11, 104, 38);
		panelRezultati.add(labelRezultati);
		labelRezultati.setForeground(myBlue);
		labelRezultati.setHorizontalAlignment(SwingConstants.LEFT);
		labelRezultati.setFont(myFont);
		
		
		JPanel panelPacijenti = new JPanel();
		panelPacijenti.setBackground(Color.WHITE);
		panelPacijenti.setBounds(0, 350, 323, 60);
		panelMeni.add(panelPacijenti);
		panelPacijenti.setLayout(null);
		
		JLabel iconPacijenti = new JLabel("");
		iconPacijenti.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/patient.jpg")));
		iconPacijenti.setBounds(30, 0, 50, 60);
		panelPacijenti.add(iconPacijenti);
		
		JLabel labelPacijenti = new JLabel("PACIJENTI");
		labelPacijenti.setBounds(120, 11, 104, 38);
		labelPacijenti.setForeground(myBlue);
		panelPacijenti.add(labelPacijenti);
		labelPacijenti.setHorizontalAlignment(SwingConstants.LEFT);
		labelPacijenti.setFont(myFont);
		
		
		JPanel panelRNP = new JPanel();
		panelRNP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Registracija registracija = new Registracija();
				frame.remove(panelKojiSeMenja);
				frame.add(registracija.panelKojiSeMenja);
				frame.validate();
				frame.repaint();
			}
		});
		panelRNP.setBackground(Color.WHITE);
		panelRNP.setBounds(0, 410, 323, 60);
		panelMeni.add(panelRNP);
		panelRNP.setLayout(null);
		
		JLabel iconRNP = new JLabel("");
		iconRNP.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/new.jpg")));
		iconRNP.setBounds(30, 0, 50, 60);
		panelRNP.add(iconRNP);
		
		JLabel labelRNP = new JLabel("NOVI PACIJENTI");
		labelRNP.setFont(myFont);
		labelRNP.setHorizontalAlignment(SwingConstants.LEFT);
		labelRNP.setBounds(120, 11, 193, 38);
		labelRNP.setForeground(myBlue);
		panelRNP.add(labelRNP);
		
		
		JPanel panelOdjava = new JPanel();
		panelOdjava.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Login returnLogin = new Login();
				returnLogin.setVisible(true);
			}
		});
		panelOdjava.setBackground(Color.WHITE);
		panelOdjava.setBounds(0, 470, 323, 60);
		panelMeni.add(panelOdjava);
		panelOdjava.setLayout(null);
		
		JLabel iconOdjava = new JLabel("");
		iconOdjava.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/odjava.jpg")));
		iconOdjava.setBounds(30, 0, 50, 60);
		panelOdjava.add(iconOdjava);
		
		JLabel labelOdjava = new JLabel("ODJAVA");
		labelOdjava.setBounds(120, 11, 133, 38);
		panelOdjava.add(labelOdjava);
		labelOdjava.setForeground(myBlue);
		labelOdjava.setHorizontalAlignment(SwingConstants.LEFT);
		labelOdjava.setFont(myFont);
	}
}
