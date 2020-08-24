package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import classes.AnalysisType;
import fileHandler.AllAnalysisTypes;
import models.AnalysisModel;

public class PanelAnalyses extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	static JLabel edit;
	static JLabel delete;
	static JLabel plus;
	static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelAnalyses() {
		setUp();
	}

	public static JPanel setUpTable(ArrayList<AnalysisType> analysisTypes, int x, int y, int w, int l) {
		table = new JTable(new AnalysisModel(analysisTypes));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
		table.setBackground(Color.WHITE);
		table.setForeground(myBlue);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
				AnalysisModel.sort(index);
				refreshData();
			}
		});

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setBounds(x, y, w, l);
		panelTable.setLayout(new BorderLayout(0, 0));

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		JTextField tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if (tfSearch.getText().trim().length() == 0) {
					tableSorter.setRowFilter(null);
				} else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
				}
			}
		});
		return panelTable;
	}

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelAnalysisTypes = new JLabel("ANALYSIS TYPES");
		labelAnalysisTypes.setForeground(Color.WHITE);
		labelAnalysisTypes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelAnalysisTypes.setBounds(165, 51, 516, 59);
		changingPanel.add(labelAnalysisTypes);

		edit = new JLabel("");
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to choose a line first.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String name = table.getValueAt(line, 0).toString();
					AnalysisType analysisType = AllAnalysisTypes.getAnalysisTypeByName(name);
					if (analysisType != null) {
						AnalysisTypeAddEditDialog editDialog = new AnalysisTypeAddEditDialog(analysisType);
						editDialog.setVisible(true);
						refreshData();
					} else {
						JOptionPane.showMessageDialog(null, "We can't find analysis you're looking for!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		edit.setIcon(new ImageIcon(PanelAnalyses.class.getResource("/Interfaces/pen.jpg")));
		edit.setBounds(935, 830, 60, 60);
		changingPanel.add(edit);

		delete = new JLabel("");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int line = table.getSelectedRow();
				if (line == -1) {
					JOptionPane.showMessageDialog(null, "You have to choose a line first.", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					String name = table.getValueAt(line, 0).toString();
					AnalysisType analysisType = AllAnalysisTypes.getAnalysisTypeByName(name);
					if (analysisType != null) {
						int choice = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to delete this analysis type?",
								analysisType.getName() + " - Delete",
								JOptionPane.YES_NO_OPTION);
						if (choice == JOptionPane.YES_OPTION) {
							AllAnalysisTypes.removeAnalysisType(analysisType.getName());;
							refreshData();
						} else {
							JOptionPane.showMessageDialog(null, "We can't find analysis you're looking for!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}

			}
		});
		delete.setIcon(new ImageIcon(PanelAnalyses.class.getResource("/Interfaces/trash.jpg")));
		delete.setBounds(1000, 830, 60, 60);
		changingPanel.add(delete);

		plus = new JLabel("");
		plus.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AnalysisTypeAddEditDialog editDialog = new AnalysisTypeAddEditDialog(null);
				editDialog.setVisible(true);
				refreshData();
			}
		});
		plus.setIcon(new ImageIcon(PanelPrices.class.getResource("/Interfaces/plusBlue.jpg")));
		plus.setBounds(870, 830, 60, 60);
		changingPanel.add(plus);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/Interfaces/analysisBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		changingPanel.add(setUpTable(AllAnalysisTypes.listOfAnalysisTypes, 50, 160, 1015, 650));

		return changingPanel;
	}


	public static void refreshData() {
		AnalysisModel analysesModel = (AnalysisModel) table.getModel();
		analysesModel.fireTableDataChanged();
		table.repaint();
	}
}
