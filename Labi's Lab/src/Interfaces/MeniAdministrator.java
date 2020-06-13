package Interfaces;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MeniAdministrator extends MeniClass {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniAdministrator frame = new MeniAdministrator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MeniAdministrator() {
		super("Meni: Administrator");
		Color myBlue = new Color(6, 56, 74);
		panelMeni.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		
		JPanel panelAnalize = new JPanel();
		panelAnalize.setBackground(Color.WHITE);
		panelAnalize.setBounds(0, 230, 323, 60);
		panelMeni.add(panelAnalize);
		panelAnalize.setLayout(null);
		
		JLabel iconAnalize = new JLabel("");
		iconAnalize.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/analize1.jpg")));
		iconAnalize.setBounds(30, 0, 50, 60);
		panelAnalize.add(iconAnalize);
		
		JLabel labelAnalize = new JLabel("ANALIZE");
		labelAnalize.setFont(myFont);
		labelAnalize.setHorizontalAlignment(SwingConstants.LEFT);
		labelAnalize.setBounds(120, 11, 104, 38);
		labelAnalize.setForeground(myBlue);
		panelAnalize.add(labelAnalize);
		
		
		JPanel panelZaposleni = new JPanel();
		panelZaposleni.setBackground(Color.WHITE);
		panelZaposleni.setBounds(0, 290, 323, 60);
		panelMeni.add(panelZaposleni);
		panelZaposleni.setLayout(null);
		
		JLabel iconZaposleni = new JLabel("");
		iconZaposleni.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/zaposleni1.jpg")));
		iconZaposleni.setBounds(30, 0, 50, 60);
		panelZaposleni.add(iconZaposleni);
		
		JLabel labelZaposleni = new JLabel("ZAPOSLENI");
		labelZaposleni.setBounds(120, 11, 104, 38);
		panelZaposleni.add(labelZaposleni);
		labelZaposleni.setHorizontalAlignment(SwingConstants.CENTER);
		labelZaposleni.setForeground(myBlue);
		labelZaposleni.setFont(myFont);
		
		
		JPanel panelCenovnik = new JPanel();
		panelCenovnik.setBackground(Color.WHITE);
		panelCenovnik.setBounds(0, 350, 323, 60);
		panelMeni.add(panelCenovnik);
		panelCenovnik.setLayout(null);
		
		JLabel iconCenovnik = new JLabel("");
		iconCenovnik.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/cenovnik.jpg")));
		iconCenovnik.setBounds(30, 0, 50, 60);
		panelCenovnik.add(iconCenovnik);
		
		JLabel labelCenovnik = new JLabel("CENOVNIK");
		labelCenovnik.setBounds(120, 11, 104, 38);
		panelCenovnik.add(labelCenovnik);
		labelCenovnik.setForeground(myBlue);
		labelCenovnik.setHorizontalAlignment(SwingConstants.LEFT);
		labelCenovnik.setFont(myFont);
		
		
		JPanel panelPacijenti = new JPanel();
		panelPacijenti.setBackground(Color.WHITE);
		panelPacijenti.setBounds(0, 410, 323, 60);
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
		
		
		JPanel panelPlate = new JPanel();
		panelPlate.setBackground(Color.WHITE);
		panelPlate.setBounds(0, 470, 323, 60);
		panelMeni.add(panelPlate);
		panelPlate.setLayout(null);
		
		JLabel iconPlate = new JLabel("");
		iconPlate.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/plate.jpg")));
		iconPlate.setBounds(30, 0, 50, 60);
		panelPlate.add(iconPlate);
		
		JLabel labelPlate = new JLabel("PLATE");
		labelPlate.setBounds(120, 11, 70, 38);
		panelPlate.add(labelPlate);
		labelPlate.setForeground(myBlue);
		labelPlate.setHorizontalAlignment(SwingConstants.LEFT);
		labelPlate.setFont(myFont);
		
		
		JPanel panelIzvestaji = new JPanel();
		panelIzvestaji.setBackground(Color.WHITE);
		panelIzvestaji.setBounds(0, 530, 323, 60);
		panelMeni.add(panelIzvestaji);
		panelIzvestaji.setLayout(null);
		
		JLabel iconIzvestaji = new JLabel("");
		iconIzvestaji.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/izvestaji.jpg")));
		iconIzvestaji.setBounds(30, 0, 50, 60);
		panelIzvestaji.add(iconIzvestaji);
		
		JLabel labelIzvestaji = new JLabel("IZVEŠTAJI");
		labelIzvestaji.setBounds(120, 11, 104, 38);
		panelIzvestaji.add(labelIzvestaji);
		labelIzvestaji.setForeground(myBlue);
		labelIzvestaji.setHorizontalAlignment(SwingConstants.LEFT);
		labelIzvestaji.setFont(myFont);

		
		JPanel panelStatistika = new JPanel();
		panelStatistika.setBackground(Color.WHITE);
		panelStatistika.setBounds(0, 590, 323, 60);
		panelMeni.add(panelStatistika);
		panelStatistika.setLayout(null);
		
		JLabel iconStatistika = new JLabel("");
		iconStatistika.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/statistics.png")));
		iconStatistika.setBounds(30, 0, 50, 60);
		panelStatistika.add(iconStatistika);
		
		JLabel labelStatistika = new JLabel("STATISTIKA");
		labelStatistika.setBounds(120, 11, 133, 38);
		panelStatistika.add(labelStatistika);
		labelStatistika.setForeground(myBlue);
		labelStatistika.setHorizontalAlignment(SwingConstants.LEFT);
		labelStatistika.setFont(myFont);
		
		
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
		panelOdjava.setBounds(0, 650, 323, 60);
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
		
		JPanel panelKojiSeMenja = new JPanel();
		panelKojiSeMenja.setBounds(322, 0, 653, 719);
		getContentPane().add(panelKojiSeMenja);
		panelKojiSeMenja.setBackground(myBlue);
	}

}
