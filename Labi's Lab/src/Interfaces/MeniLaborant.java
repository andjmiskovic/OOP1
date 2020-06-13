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

public class MeniLaborant extends MeniClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MeniLaborant();
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
	public MeniLaborant() {
		super("Meni: Laborant");
		Color myBlue = new Color(6, 56, 74);
		panelMeni.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		
		JPanel panelPredatiUzorci = new JPanel();
		panelPredatiUzorci.setBackground(Color.WHITE);
		panelPredatiUzorci.setBounds(0, 230, 323, 60);
		panelMeni.add(panelPredatiUzorci);
		panelPredatiUzorci.setLayout(null);
		
		JLabel iconPredatiUzorci = new JLabel("");
		iconPredatiUzorci.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/predati.jpg")));
		iconPredatiUzorci.setBounds(30, 0, 50, 60);
		panelPredatiUzorci.add(iconPredatiUzorci);
		
		JLabel labelPredatiUzorci = new JLabel("PREDATI UZORCI");
		labelPredatiUzorci.setBounds(120, 11, 193, 38);
		panelPredatiUzorci.add(labelPredatiUzorci);
		labelPredatiUzorci.setHorizontalAlignment(SwingConstants.LEFT);
		labelPredatiUzorci.setForeground(myBlue);
		labelPredatiUzorci.setFont(myFont);
		
		
		JPanel panelUzorciNaObradi = new JPanel();
		panelUzorciNaObradi.setBackground(Color.WHITE);
		panelUzorciNaObradi.setBounds(0, 290, 323, 60);
		panelMeni.add(panelUzorciNaObradi);
		panelUzorciNaObradi.setLayout(null);
		
		JLabel iconUzorciNaObradi = new JLabel("");
		iconUzorciNaObradi.setIcon(new ImageIcon(MeniAdministrator.class.getResource("/Interfaces/naobradi.jpg")));
		iconUzorciNaObradi.setBounds(30, 0, 50, 60);
		panelUzorciNaObradi.add(iconUzorciNaObradi);
		
		JLabel labelUzorciNaObradi = new JLabel("UZORCI NA OBRADI");
		labelUzorciNaObradi.setBounds(120, 11, 182, 38);
		panelUzorciNaObradi.add(labelUzorciNaObradi);
		labelUzorciNaObradi.setForeground(myBlue);
		labelUzorciNaObradi.setHorizontalAlignment(SwingConstants.LEFT);
		labelUzorciNaObradi.setFont(myFont);
		
		
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
		panelOdjava.setBounds(0, 350, 323, 60);
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
