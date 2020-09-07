package view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuClass extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelMenu;
	public static JPanel changingPanel;
	JFrame frame;
	
	public JPanel getChangingPanel() {
		return changingPanel;
	}

	public static void setChangingPanel(JPanel panel) {
		changingPanel = panel;
	}
	
	public MenuClass(String title) {
		super();
		Color myBlue = new Color(6, 56, 74);
		getContentPane().setBackground(myBlue);
		frame = this;
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 20, 1500, 1000);
		setTitle(title);
		getContentPane().setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 350, 1000);
		panelMenu.setBackground(Color.WHITE);
		getContentPane().add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setIcon(new ImageIcon(MenuClass.class.getResource("/pictures/labi200.jpg")));
		lbllogo.setBounds(10, 11, 303, 200);
		panelMenu.add(lbllogo);
		
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		getContentPane().add(changingPanel);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
	}
}
