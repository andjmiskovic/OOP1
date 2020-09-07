package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPatient extends MenuClass {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPatient frame = new MenuPatient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPatient() {
		super("Menu: Patient");
		Color myBlue = new Color(6, 56, 74);
		panelMenu.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);

		JPanel panelMyData = new JPanel();
		panelMyData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelSignUp("VIEW");
				changingPanel = PanelSignUp.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelMyData.setBackground(Color.WHITE);
		panelMyData.setBounds(0, 230, 350, 60);
		panelMenu.add(panelMyData);
		panelMyData.setLayout(null);

		JLabel iconMyData = new JLabel("");
		iconMyData.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/mydata.jpg")));
		iconMyData.setBounds(30, 0, 50, 60);
		panelMyData.add(iconMyData);

		JLabel labelMyData = new JLabel("MY DATA");
		labelMyData.setFont(myFont);
		labelMyData.setHorizontalAlignment(SwingConstants.LEFT);
		labelMyData.setBounds(120, 11, 177, 38);
		labelMyData.setForeground(myBlue);
		panelMyData.add(labelMyData);

		JPanel panelResults = new JPanel();
		panelResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelResultsPatient();
				changingPanel = PanelResultsPatient.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelResults.setBackground(Color.WHITE);
		panelResults.setBounds(0, 290, 350, 60);
		panelMenu.add(panelResults);
		panelResults.setLayout(null);

		JLabel iconResults = new JLabel("");
		iconResults.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/results.jpg")));
		iconResults.setBounds(30, 0, 50, 60);
		panelResults.add(iconResults);

		JLabel labelResults = new JLabel("RESULTS");
		labelResults.setBounds(120, 11, 104, 38);
		panelResults.add(labelResults);
		labelResults.setHorizontalAlignment(SwingConstants.LEFT);
		labelResults.setForeground(myBlue);
		labelResults.setFont(myFont);

		JPanel panelRequest = new JPanel();
		panelRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelSendARequest();
				changingPanel = PanelSendARequest.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelRequest.setBackground(Color.WHITE);
		panelRequest.setBounds(0, 350, 350, 60);
		panelMenu.add(panelRequest);
		panelRequest.setLayout(null);

		JLabel labelRequest = new JLabel("SEND A REQUEST");
		labelRequest.setBounds(120, 11, 220, 38);
		panelRequest.add(labelRequest);
		labelRequest.setForeground(myBlue);
		labelRequest.setHorizontalAlignment(SwingConstants.LEFT);
		labelRequest.setFont(myFont);

		JLabel iconRequest = new JLabel("");
		iconRequest.setIcon(new ImageIcon(MenuPatient.class.getResource("/pictures/sendARequest.jpg")));
		iconRequest.setBounds(32, 0, 50, 60);
		panelRequest.add(iconRequest);
		
		JPanel panelMyStatistics = new JPanel();
		panelMyStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelMyStatistics();
				changingPanel = PanelMyStatistics.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelMyStatistics.setLayout(null);
		panelMyStatistics.setBackground(Color.WHITE);
		panelMyStatistics.setBounds(0, 410, 350, 60);
		panelMenu.add(panelMyStatistics);
		
		JLabel labelMyStatistics = new JLabel("MY STATISTICS");
		labelMyStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		labelMyStatistics.setForeground(new Color(6, 56, 74));
		labelMyStatistics.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelMyStatistics.setBounds(120, 11, 188, 38);
		panelMyStatistics.add(labelMyStatistics);
		
		JLabel iconMyStatistics = new JLabel("");
		iconMyStatistics.setIcon(new ImageIcon(MenuPatient.class.getResource("/pictures/myStatistics.jpg")));
		iconMyStatistics.setBounds(32, 0, 50, 60);
		panelMyStatistics.add(iconMyStatistics);

		JPanel panelLogOut = new JPanel();
		panelLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Login returnLogin = new Login();
				returnLogin.setVisible(true);
			}
		});
		panelLogOut.setLayout(null);
		panelLogOut.setBackground(Color.WHITE);
		panelLogOut.setBounds(0, 470, 350, 60);
		panelMenu.add(panelLogOut);

		JLabel labelLogOut = new JLabel("LOG OUT");
		labelLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelLogOut.setForeground(new Color(6, 56, 74));
		labelLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelLogOut.setBounds(120, 11, 220, 38);
		panelLogOut.add(labelLogOut);

		JLabel iconLogOut = new JLabel("");
		iconLogOut.setBounds(31, 0, 50, 60);
		panelLogOut.add(iconLogOut);
		iconLogOut.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/odjava.jpg")));
		
	}
}
