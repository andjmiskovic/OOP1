package view;

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

public class MenuTechnicial extends MenuClass {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTechnicial frame = new MenuTechnicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchPanel(JFrame frame, JPanel noviPanel) {
		frame.remove(changingPanel);
		frame.getContentPane().add(noviPanel);
		frame.validate();
	}

	public MenuTechnicial() {
		super("Menu: Medical Technicial");
		Color myBlue = new Color(6, 56, 74);
		panelMenu.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);

		JPanel panelRequests = new JPanel();
		panelRequests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelRequestsMT();
				changingPanel = PanelRequestsMT.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelRequests.setBackground(Color.WHITE);
		panelRequests.setBounds(0, 230, 350, 60);
		panelMenu.add(panelRequests);
		panelRequests.setLayout(null);

		JLabel iconRequests = new JLabel("");
		iconRequests.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/requestMT.jpg")));
		iconRequests.setBounds(30, 0, 50, 60);
		panelRequests.add(iconRequests);

		JLabel labelRequests = new JLabel("REQUESTS");
		labelRequests.setBounds(120, 11, 104, 38);
		panelRequests.add(labelRequests);
		labelRequests.setHorizontalAlignment(SwingConstants.LEFT);
		labelRequests.setForeground(myBlue);
		labelRequests.setFont(myFont);

		JPanel panelRequest = new JPanel();
		panelRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelNewRequestMT();
				changingPanel = PanelNewRequestMT.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelRequest.setBackground(Color.WHITE);
		panelRequest.setBounds(0, 290, 350, 60);
		panelMenu.add(panelRequest);
		panelRequest.setLayout(null);

		JLabel iconRequest = new JLabel("");
		iconRequest.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/sendARequest.jpg")));
		iconRequest.setBounds(30, 0, 50, 60);
		panelRequest.add(iconRequest);

		JLabel labelRequest = new JLabel("NEW REQUEST");
		labelRequest.setBounds(120, 11, 183, 38);
		panelRequest.add(labelRequest);
		labelRequest.setForeground(myBlue);
		labelRequest.setHorizontalAlignment(SwingConstants.LEFT);
		labelRequest.setFont(myFont);

		JPanel panelPatients = new JPanel();
		panelPatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelPatients();
				changingPanel = PanelPatients.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelPatients.setBackground(Color.WHITE);
		panelPatients.setBounds(0, 350, 350, 60);
		panelMenu.add(panelPatients);
		panelPatients.setLayout(null);

		JLabel iconPatients = new JLabel("");
		iconPatients.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/patient.jpg")));
		iconPatients.setBounds(30, 0, 50, 60);
		panelPatients.add(iconPatients);

		JLabel labelPatients = new JLabel("PATIENTS");
		labelPatients.setBounds(120, 11, 104, 38);
		labelPatients.setForeground(myBlue);
		panelPatients.add(labelPatients);
		labelPatients.setHorizontalAlignment(SwingConstants.LEFT);
		labelPatients.setFont(myFont);

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
		panelLogOut.setBounds(0, 410, 350, 60);
		panelMenu.add(panelLogOut);
		panelLogOut.setLayout(null);

		JLabel iconLogOut = new JLabel("");
		iconLogOut.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/odjava.jpg")));
		iconLogOut.setBounds(30, 0, 50, 60);
		panelLogOut.add(iconLogOut);

		JLabel labelLogOut = new JLabel("LOG OUT");
		labelLogOut.setBounds(120, 11, 133, 38);
		panelLogOut.add(labelLogOut);
		labelLogOut.setForeground(myBlue);
		labelLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelLogOut.setFont(myFont);

	}
}
