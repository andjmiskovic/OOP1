package Interfaces;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class PanelPrices extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	private static JTable table;
	
	public PanelPrices() {
		setUp();

	}

	public static JPanel setUp() {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);
		
		JLabel labelPatients = new JLabel("PRICES");
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelPatients.setBounds(165, 51, 516, 59);
		changingPanel.add(labelPatients);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(75, 160, 1015, 650);
		changingPanel.add(scrollPane);
		
		String[] column = {"username", "name", "last name", "LBO", "date of birth", "address"};
		String[][] data = {{"ana", "ana", "ana", "1", "p", "a"}};
		table = new JTable(data, column);
		scrollPane.add(table);
		changingPanel.add(scrollPane);
		
		JLabel edit = new JLabel("");
		edit.setIcon(new ImageIcon(PanelPatients.class.getResource("/Interfaces/pen.jpg")));
		edit.setBounds(950, 51, 50, 50);
		changingPanel.add(edit);
		
		JLabel delete = new JLabel("");
		delete.setIcon(new ImageIcon(PanelPatients.class.getResource("/Interfaces/trash.jpg")));
		delete.setBounds(1000, 51, 50, 50);
		changingPanel.add(delete);
		
		JLabel plus = new JLabel("");
		plus.setIcon(new ImageIcon(PanelPrices.class.getResource("/Interfaces/plusBlue.jpg")));
		plus.setBounds(900, 53, 50, 50);
		changingPanel.add(plus);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/Interfaces/pricesBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);
		
		return changingPanel;
	}
}
