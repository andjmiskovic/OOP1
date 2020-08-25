package Interfaces;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import com.toedter.calendar.JCalendar;

public class PanelSchedulingPatient extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	
	public PanelSchedulingPatient() {
		setUp();

	}

	public static JPanel setUp() {
		Color myBlue = new Color(6, 56, 74);

		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);
		
		JLabel labelPatients = new JLabel("YOUR CALENDAR");
		labelPatients.setForeground(Color.WHITE);
		labelPatients.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelPatients.setBounds(165, 51, 516, 59);
		changingPanel.add(labelPatients);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/Interfaces/calendarBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(75, 160, 905, 289);
		changingPanel.add(calendar);
		
		JLabel lblListOfYour = new JLabel("List of your appointments for selected date:");
		lblListOfYour.setForeground(Color.WHITE);
		lblListOfYour.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblListOfYour.setBounds(75, 471, 516, 59);
		changingPanel.add(lblListOfYour);
		
		JLabel labelAppointments = new JLabel("");
		labelAppointments.setForeground(Color.WHITE);
		labelAppointments.setFont(new Font("Tahoma", Font.PLAIN, 24));
		labelAppointments.setBounds(75, 541, 905, 400);
		changingPanel.add(labelAppointments);
		
		return changingPanel;
	}
}
