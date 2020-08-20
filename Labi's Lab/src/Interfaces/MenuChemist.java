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

public class MenuChemist extends MenuClass {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MenuChemist();
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
	public MenuChemist() {
		super("Menu: Chemist");
		Color myBlue = new Color(6, 56, 74);
		panelMenu.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);
		
		
		JPanel panelSubmittedSamples = new JPanel();
		panelSubmittedSamples.setBackground(Color.WHITE);
		panelSubmittedSamples.setBounds(0, 230, 350, 60);
		panelMenu.add(panelSubmittedSamples);
		panelSubmittedSamples.setLayout(null);
		
		JLabel iconSubmittedSamples = new JLabel("");
		iconSubmittedSamples.setIcon(new ImageIcon(MenuAdmin.class.getResource("/Interfaces/predati.jpg")));
		iconSubmittedSamples.setBounds(30, 0, 50, 60);
		panelSubmittedSamples.add(iconSubmittedSamples);
		
		JLabel labelSubmittedSamples = new JLabel("SUBMITTED SAMPLES");
		labelSubmittedSamples.setBounds(110, 11, 205, 38);
		panelSubmittedSamples.add(labelSubmittedSamples);
		labelSubmittedSamples.setHorizontalAlignment(SwingConstants.LEFT);
		labelSubmittedSamples.setForeground(myBlue);
		labelSubmittedSamples.setFont(myFont);
		
		JPanel panelProcessingSamples = new JPanel();
		panelProcessingSamples.setBackground(Color.WHITE);
		panelProcessingSamples.setBounds(0, 290, 350, 60);
		panelMenu.add(panelProcessingSamples);
		panelProcessingSamples.setLayout(null);
		
		JLabel iconProcessingSamples = new JLabel("");
		iconProcessingSamples.setIcon(new ImageIcon(MenuAdmin.class.getResource("/Interfaces/naobradi.jpg")));
		iconProcessingSamples.setBounds(30, 0, 50, 60);
		panelProcessingSamples.add(iconProcessingSamples);
		
		JLabel labelProcessingSamples = new JLabel("PROCESSING SAMPLES");
		labelProcessingSamples.setBounds(110, 11, 203, 38);
		panelProcessingSamples.add(labelProcessingSamples);
		labelProcessingSamples.setForeground(myBlue);
		labelProcessingSamples.setHorizontalAlignment(SwingConstants.LEFT);
		labelProcessingSamples.setFont(myFont);
		
		
		JPanel panelLogOut = new JPanel();
		panelLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Login returnLogin = new Login();
				returnLogin.setVisible(true);
			}
		});
		panelLogOut.setBackground(Color.WHITE);
		panelLogOut.setBounds(0, 350, 350, 60);
		panelMenu.add(panelLogOut);
		panelLogOut.setLayout(null);
		
		JLabel iconLogOut = new JLabel("");
		iconLogOut.setIcon(new ImageIcon(MenuAdmin.class.getResource("/Interfaces/odjava.jpg")));
		iconLogOut.setBounds(30, 0, 50, 60);
		panelLogOut.add(iconLogOut);
		
		JLabel labelLogOut = new JLabel("LOG OUT");
		labelLogOut.setBounds(110, 11, 133, 38);
		panelLogOut.add(labelLogOut);
		labelLogOut.setForeground(myBlue);
		labelLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelLogOut.setFont(myFont);
		
		JPanel changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		getContentPane().add(changingPanel);
		changingPanel.setBackground(myBlue);
	}

}
