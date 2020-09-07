package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAdmin extends MenuClass {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuAdmin frame = new MenuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuAdmin() {
		super("Menu: Admin");
		Color myBlue = new Color(6, 56, 74);
		panelMenu.setLayout(null);
		Font myFont = new Font("Tahoma", Font.PLAIN, 20);

		JPanel panelAnalyses = new JPanel();
		panelAnalyses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelAnalyses();
				changingPanel = PanelAnalyses.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelAnalyses.setBackground(Color.WHITE);
		panelAnalyses.setBounds(0, 230, 350, 60);
		panelMenu.add(panelAnalyses);
		panelAnalyses.setLayout(null);

		JLabel iconAnalyses = new JLabel("");
		iconAnalyses.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/analize1.jpg")));
		iconAnalyses.setBounds(30, 0, 50, 60);
		panelAnalyses.add(iconAnalyses);

		JLabel labelAnalyses = new JLabel("ANALYSES");
		labelAnalyses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelAnalyses.setHorizontalAlignment(SwingConstants.LEFT);
		labelAnalyses.setBounds(120, 11, 129, 38);
		labelAnalyses.setForeground(myBlue);
		panelAnalyses.add(labelAnalyses);

		JPanel panelEmployees = new JPanel();
		panelEmployees.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelEmployees();
				changingPanel = PanelEmployees.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelEmployees.setBackground(Color.WHITE);
		panelEmployees.setBounds(0, 290, 350, 60);
		panelMenu.add(panelEmployees);
		panelEmployees.setLayout(null);

		JLabel iconEmployees = new JLabel("");
		iconEmployees.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/zaposleni1.jpg")));
		iconEmployees.setBounds(30, 0, 50, 60);
		panelEmployees.add(iconEmployees);

		JLabel labelEmployees = new JLabel("EMPLOYEES");
		labelEmployees.setBounds(120, 11, 141, 38);
		panelEmployees.add(labelEmployees);
		labelEmployees.setHorizontalAlignment(SwingConstants.LEFT);
		labelEmployees.setForeground(myBlue);
		labelEmployees.setFont(myFont);

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
		panelPatients.setBounds(0, 410, 350, 60);
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

		JPanel panelSalaries = new JPanel();
		panelSalaries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelSalaries();
				changingPanel = PanelSalaries.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelSalaries.setBackground(Color.WHITE);
		panelSalaries.setBounds(0, 530, 350, 60);
		panelMenu.add(panelSalaries);
		panelSalaries.setLayout(null);

		JLabel iconSalaries = new JLabel("");
		iconSalaries.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/plate.jpg")));
		iconSalaries.setBounds(30, 0, 50, 60);
		panelSalaries.add(iconSalaries);

		JLabel labelSalaries = new JLabel("SALARIES");
		labelSalaries.setBounds(120, 11, 104, 38);
		panelSalaries.add(labelSalaries);
		labelSalaries.setForeground(myBlue);
		labelSalaries.setHorizontalAlignment(SwingConstants.LEFT);
		labelSalaries.setFont(myFont);

		JPanel panelPatientsStatistics = new JPanel();
		panelPatientsStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelPatientsStatistics();
				changingPanel = PanelPatientsStatistics.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelPatientsStatistics.setBackground(Color.WHITE);
		panelPatientsStatistics.setBounds(0, 470, 350, 60);
		panelMenu.add(panelPatientsStatistics);
		panelPatientsStatistics.setLayout(null);

		JLabel iconPatientsStatistics = new JLabel("");
		iconPatientsStatistics.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/patientStatistics.jpg")));
		iconPatientsStatistics.setBounds(30, 0, 50, 60);
		panelPatientsStatistics.add(iconPatientsStatistics);

		JLabel labelPatientsStatistics = new JLabel("PATIENTS STATISTICS");
		labelPatientsStatistics.setBounds(120, 11, 220, 38);
		panelPatientsStatistics.add(labelPatientsStatistics);
		labelPatientsStatistics.setForeground(myBlue);
		labelPatientsStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		labelPatientsStatistics.setFont(myFont);

		JPanel panelIncomes = new JPanel();
		panelIncomes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelIncomes();
				changingPanel = PanelIncomes.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelIncomes.setBackground(Color.WHITE);
		panelIncomes.setBounds(0, 590, 350, 60);
		panelMenu.add(panelIncomes);
		panelIncomes.setLayout(null);

		JLabel iconIncomes = new JLabel("");
		iconIncomes.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/incomesNew.jpg")));
		iconIncomes.setBounds(30, 0, 50, 60);
		panelIncomes.add(iconIncomes);

		JLabel labelIncomes = new JLabel("INCOME");
		labelIncomes.setBounds(120, 11, 133, 38);
		panelIncomes.add(labelIncomes);
		labelIncomes.setForeground(myBlue);
		labelIncomes.setHorizontalAlignment(SwingConstants.LEFT);
		labelIncomes.setFont(myFont);

		JPanel panelOutcomes = new JPanel();
		panelOutcomes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelOutcomes();
				changingPanel = PanelOutcomes.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelOutcomes.setBackground(Color.WHITE);
		panelOutcomes.setBounds(0, 650, 350, 60);
		panelMenu.add(panelOutcomes);
		panelOutcomes.setLayout(null);

		JLabel labelOutomes = new JLabel("EXPENSES");
		labelOutomes.setBounds(120, 11, 230, 38);
		panelOutcomes.add(labelOutomes);
		labelOutomes.setForeground(myBlue);
		labelOutomes.setHorizontalAlignment(SwingConstants.LEFT);
		labelOutomes.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel iconOutcomes = new JLabel("");
		iconOutcomes.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/incomes.jpg")));
		iconOutcomes.setBounds(32, 0, 50, 60);
		panelOutcomes.add(iconOutcomes);

		JPanel panelDiscounts = new JPanel();
		panelDiscounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.remove(changingPanel);
				new PanelDiscounts();
				changingPanel = PanelDiscounts.changingPanel;
				frame.getContentPane().add(changingPanel);
				frame.validate();
				frame.repaint();
			}
		});
		panelDiscounts.setLayout(null);
		panelDiscounts.setBackground(Color.WHITE);
		panelDiscounts.setBounds(0, 350, 350, 60);
		panelMenu.add(panelDiscounts);

		JLabel labelDiscounts = new JLabel("DISCOUNTS");
		labelDiscounts.setHorizontalAlignment(SwingConstants.LEFT);
		labelDiscounts.setForeground(new Color(6, 56, 74));
		labelDiscounts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelDiscounts.setBounds(120, 11, 133, 38);
		panelDiscounts.add(labelDiscounts);

		JLabel iconDiscount = new JLabel("");
		iconDiscount.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/discount.jpg")));
		iconDiscount.setBounds(30, 0, 50, 60);
		panelDiscounts.add(iconDiscount);

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
		panelLogOut.setBounds(0, 710, 350, 60);
		panelMenu.add(panelLogOut);

		JLabel labelLogOut = new JLabel("LOG OUT");
		labelLogOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelLogOut.setForeground(new Color(6, 56, 74));
		labelLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelLogOut.setBounds(120, 11, 133, 38);
		panelLogOut.add(labelLogOut);

		JLabel iconLogOut = new JLabel("");
		iconLogOut.setBounds(30, 0, 50, 60);
		panelLogOut.add(iconLogOut);
		iconLogOut.setIcon(new ImageIcon(MenuAdmin.class.getResource("/pictures/odjava.jpg")));

	}
}
