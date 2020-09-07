package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fileHandler.AllAnalysisRequests;
import fileHandler.Users;
import functions.GeneralFunctions;
import models.AnalysisRequest;
import models.AnalysisType;
import models.Chemist;
import models.CurrentStateOfRequest;
import models.CurrentUser;
import models.Specialization;
import net.miginfocom.swing.MigLayout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class EnterValue extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public EnterValue(String requestId, AnalysisType analysisType) {
		setBounds(100, 100, 450, 116);
		setTitle("Value by hand");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/logo300.jpg")));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[1px][424px]", "[1px][218px]"));
		{
			JLabel lblNewLabel = new JLabel("Enter analysis type value:");
			contentPanel.add(lblNewLabel, "cell 0 0,grow");
		}
		{
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!(((c >= '0') && (c <= '9')) || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
							|| (c == KeyEvent.VK_DELETE))) {
						e.consume();
					}
				}
			});
			contentPanel.add(textField, "cell 1 0,grow");
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							double value = GeneralFunctions.round(Double.valueOf(textField.getText()), 2);
							Map<AnalysisType, Double> analyses = AllAnalysisRequests.getAnalysisRequestById(requestId)
									.getAnalyses();
							analyses.put(analysisType, value);
							AnalysisRequest analysisRequest = AllAnalysisRequests.getAnalysisRequestById(requestId);
							AllAnalysisRequests.listOfRequests.remove(analysisRequest);
							analysisRequest.setAnalyses(analyses);
							if (GeneralFunctions.isRequestFinished(analysisRequest)) {
								analysisRequest.setCurrentState(CurrentStateOfRequest.FINISHED_REPORT);
								analysisRequest.setProcessedDate(LocalDate.now());
							}
							if (GeneralFunctions.isGroupProccessed(analysisRequest, analysisType)) {
								Map<Specialization, Chemist> processedGroups = analysisRequest.getProcessedGroups();
								processedGroups.put(analysisType.getGroup(), CurrentUser.getCurrentChemist());
								analysisRequest.setProcessedGroups(processedGroups);
								ArrayList<Specialization> unprocessedGroups = analysisRequest.getUnprocessedGroups();
								unprocessedGroups.remove(analysisType.getGroup());
								analysisRequest.setUnprocessedGroups(unprocessedGroups);
							}
							AllAnalysisRequests.addAnalysisRequest(analysisRequest);
							Users.saveData();
							dispose();
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "You have to enter decimal value.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
