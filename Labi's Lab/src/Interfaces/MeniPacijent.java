package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MeniPacijent extends MeniClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniPacijent frame = new MeniPacijent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MeniPacijent() {
		super("Meni: Pacijent");
		Color myBlue = new Color(6, 56, 74);
		panelMeni.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		
		JPanel panelMojiPodaci = new JPanel();
		panelMojiPodaci.setBackground(Color.WHITE);
		panelMojiPodaci.setBounds(0, 230, 323, 60);
		panelMeni.add(panelMojiPodaci);
		panelMojiPodaci.setLayout(null);
		
		JLabel iconMojiPodaci = new JLabel("");
		iconMojiPodaci.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/mydata.jpg")));
		iconMojiPodaci.setBounds(30, 0, 50, 60);
		panelMojiPodaci.add(iconMojiPodaci);
		
		JLabel labelMojiPodaci = new JLabel("MOJI PODACI");
		labelMojiPodaci.setFont(myFont);
		labelMojiPodaci.setHorizontalAlignment(SwingConstants.LEFT);
		labelMojiPodaci.setBounds(120, 11, 177, 38);
		labelMojiPodaci.setForeground(myBlue);
		panelMojiPodaci.add(labelMojiPodaci);
		
		
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
		labelRezultati.setHorizontalAlignment(SwingConstants.CENTER);
		labelRezultati.setForeground(myBlue);
		labelRezultati.setFont(myFont);
		
		
		JPanel panelZakazivanje = new JPanel();
		panelZakazivanje.setBackground(Color.WHITE);
		panelZakazivanje.setBounds(0, 350, 323, 60);
		panelMeni.add(panelZakazivanje);
		panelZakazivanje.setLayout(null);
		
		JLabel iconZakazivanje = new JLabel("");
		iconZakazivanje.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/zakazivanje.jpg")));
		iconZakazivanje.setBounds(30, 0, 50, 60);
		panelZakazivanje.add(iconZakazivanje);
		
		JLabel labelZakazivanje = new JLabel("ZAKAZIVANJE");
		labelZakazivanje.setBounds(120, 11, 178, 38);
		panelZakazivanje.add(labelZakazivanje);
		labelZakazivanje.setForeground(myBlue);
		labelZakazivanje.setHorizontalAlignment(SwingConstants.LEFT);
		labelZakazivanje.setFont(myFont);
		
		
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
		panelOdjava.setBounds(0, 410, 323, 60);
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
