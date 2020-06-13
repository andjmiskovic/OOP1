package Interfaces;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MeniClass extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panelMeni;
	protected JPanel panelKojiSeMenja;
	JFrame frame;
	
	public JPanel getPanelKojiSeMenja() {
		return panelKojiSeMenja;
	}

	public void setPanelKojiSeMenja(JPanel panelKojiSeMenja) {
		this.panelKojiSeMenja = panelKojiSeMenja;
	}
	
	public MeniClass(String title) {
		super();
		Color myBlue = new Color(6, 56, 74);
		getContentPane().setBackground(myBlue);
		frame = this;
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1050, 750);
		setTitle(title);
		getContentPane().setLayout(null);
		
		panelMeni = new JPanel();
		panelMeni.setBounds(0, 0, 323, 719);
		panelMeni.setBackground(Color.WHITE);
		getContentPane().add(panelMeni);
		panelMeni.setLayout(null);
		
		JLabel lbllogo = new JLabel("New label");
		lbllogo.setIcon(new ImageIcon(MeniClass.class.getResource("/Interfaces/labi200.jpg")));
		lbllogo.setBounds(10, 11, 303, 200);
		panelMeni.add(lbllogo);
		
		panelKojiSeMenja = new JPanel();
		panelKojiSeMenja.setBounds(324, 0, 720, 719);
		getContentPane().add(panelKojiSeMenja);
		panelKojiSeMenja.setBackground(myBlue);
		panelKojiSeMenja.setLayout(null);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo300.jpg")));
	}
}
