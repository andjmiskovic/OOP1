package Interfaces;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import classes.Specialization;
import fileHandler.AllAnalysisTypes;
import fileHandler.SalaryCoefficients;
import models.AnalysisDiscountModel;
import net.miginfocom.swing.MigLayout;

public class PanelDiscounts extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel;
	private static JTable table;
	static Color myBlue = new Color(6, 56, 74);

	public PanelDiscounts() {
		setUp();

	}

	public static JPanel setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel labelDiscount = new JLabel("DISCOUNTS");
		labelDiscount.setForeground(Color.WHITE);
		labelDiscount.setFont(new Font("Tahoma", Font.PLAIN, 34));
		labelDiscount.setBounds(165, 51, 516, 59);
		changingPanel.add(labelDiscount);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelDiscounts.class.getResource("/Interfaces/discountblue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblLimit = new JLabel(convertToMultiline(
				"Minimum for single patient to spend in order to\nget 5% discount for chosen specialization group"));
		lblLimit.setBounds(75, 156, 440, 59);
		lblLimit.setForeground(Color.WHITE);
		lblLimit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		changingPanel.add(lblLimit);

		JPanel panelLimit = new JPanel();
		panelLimit.setBounds(75, 233, 440, 584);
		panelLimit.setForeground(Color.WHITE);
		panelLimit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panelLimit.setLayout(new MigLayout("", "[]", "[]"));
		panelLimit.setBackground(myBlue);
		changingPanel.add(panelLimit);

		JLabel labelAnalysisTypes = new JLabel(convertToMultiline(
				"Choose analysis type to add type discount to or \nchoose 3 or more for new group discount"));
		labelAnalysisTypes.setBounds(546, 156, 489, 59);
		labelAnalysisTypes.setForeground(Color.WHITE);
		labelAnalysisTypes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		changingPanel.add(labelAnalysisTypes);

		setUpTable(AllAnalysisTypes.listOfAnalysisTypes);

		List<Specialization> coefficientKeys = Specialization.stream().sorted((q1, q2) -> q1.compareTo(q2))
				.collect(Collectors.toList());
		Map<Specialization, JTextField> specializationTextFields = new HashMap<Specialization, JTextField>();

		for (Specialization specialization : coefficientKeys) {
			panelLimit.add(new Label(specialization.toString()));
			JTextField thresholdTextField = new JTextField(
					String.valueOf(SalaryCoefficients.groupDiscountLimits.get(specialization)));
			thresholdTextField.setColumns(7);
			specializationTextFields.put(specialization, thresholdTextField);
			panelLimit.add(thresholdTextField, "wrap");
		}

		JLabel labelSavedChanges = new JLabel("");
		labelSavedChanges.setBounds(75, 900, 440, 60);
		labelSavedChanges.setForeground(Color.WHITE);
		labelSavedChanges.setFont(new Font("Tahoma", Font.PLAIN, 20));
		changingPanel.add(labelSavedChanges);

		JButton buttonSave = new JButton("SAVE CHANGES");
		buttonSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Map.Entry<Specialization, JTextField> set : specializationTextFields.entrySet()) {
						Double newValue = Double.parseDouble(set.getValue().getText().trim());
						Specialization specialization = set.getKey();
						SalaryCoefficients.groupDiscountLimits.remove(specialization);
						SalaryCoefficients.groupDiscountLimits.put(specialization, newValue);
					}
					SalaryCoefficients.saveData();
					labelSavedChanges.setText("Changes have been saved successfully.");
				} catch (Exception e0) {
					labelSavedChanges.setText("There is a problem, we can't save the changes.");
				}
			}
		});
		buttonSave.setBounds(75, 828, 440, 64);
		buttonSave.setFont(new Font("Dialog", Font.PLAIN, 20));
		buttonSave.setBackground(myBlue);
		buttonSave.setForeground(SystemColor.text);
		changingPanel.add(buttonSave);

		JButton typeDiscount = new JButton("NEW TYPE DISCOUNT");
		typeDiscount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] lines = table.getSelectedRows();
				if (lines.length != 1) {
					JOptionPane.showMessageDialog(null, "You have to pick 1 analysis type for new type discount.",
							"Error", JOptionPane.WARNING_MESSAGE);
				} else {
					String name = table.getValueAt(lines[0], 0).toString();
					AnalysisType analysisType = AllAnalysisTypes.getAnalysisTypeByName(name);
					if (analysisType != null) {
						TypeDiscountAddEditDialog editDialog = new TypeDiscountAddEditDialog(analysisType,
								analysisType.getTypeDiscount());
						editDialog.setVisible(true);
						editDialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								refreshData();
							}
						});
					} else {
						JOptionPane.showMessageDialog(null, "We cannon find this analysis type!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		typeDiscount.setBounds(546, 828, 239, 64);
		typeDiscount.setForeground(SystemColor.text);
		typeDiscount.setFont(new Font("Dialog", Font.PLAIN, 17));
		typeDiscount.setBackground(myBlue);
		changingPanel.add(typeDiscount);

		JButton groupDiscount = new JButton("NEW GROUP DISCOUNT");
		groupDiscount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GroupDiscountAddEditDialog editDialog = new GroupDiscountAddEditDialog();
				editDialog.setVisible(true);
				editDialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						refreshData();
					}
				});
			}
		});
		groupDiscount.setForeground(Color.WHITE);
		groupDiscount.setFont(new Font("Dialog", Font.PLAIN, 17));
		groupDiscount.setBackground(new Color(6, 56, 74));
		groupDiscount.setBounds(796, 828, 239, 64);
		changingPanel.add(groupDiscount);

		return changingPanel;
	}

	public static void setUpTable(ArrayList<AnalysisType> analysisTypes) {
		table = new JTable(new AnalysisDiscountModel(analysisTypes));
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
				sort(index);
			}
		});

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setBounds(546, 233, 489, 584);
		panelTable.setLayout(new BorderLayout(0, 0));

		JPanel pSearch = new JPanel();
		pSearch.setBackground(Color.WHITE);
		pSearch.add(new JLabel("Search:"));
		JTextField tfSearch = new JTextField(20);
		pSearch.add(tfSearch);
		panelTable.add(pSearch, BorderLayout.SOUTH);
		panelTable.add(new JScrollPane(table), BorderLayout.CENTER);
		changingPanel.add(panelTable);

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
	}

	public static void sort(int index) {
		Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, 1);
				put(1, 1);
				put(2, 1);
				put(3, 1);
			}
		};
		AllAnalysisTypes.listOfAnalysisTypes.sort(new Comparator<AnalysisType>() {
			int retVal = 0;

			@Override
			public int compare(AnalysisType o1, AnalysisType o2) {
				switch (index) {
				case 0:
					retVal = o1.getName().compareTo(o2.getName());
					break;
				case 1:
					retVal = (int) (o1.getPrice() - o2.getPrice());
					break;
				case 2:
					retVal = (int) (o1.getTypeDiscount().getValue() - o2.getTypeDiscount().getValue());
					break;
				case 3:
					retVal = (int) (o1.getGroupDiscount().getValue() - o2.getGroupDiscount().getValue());
					break;
				default:
					System.exit(1);
					break;
				}
				return retVal * sortOrder.get(index);
			}
		});
		sortOrder.put(index, sortOrder.get(index) * -1);
		refreshData();
	}

	public static void refreshData() {
		AnalysisDiscountModel analysesModel = (AnalysisDiscountModel) table.getModel();
		analysesModel.fireTableDataChanged();
		table.repaint();
	}

	public static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>");
	}

	public static ArrayList<DayOfWeek> daysOfWeekFromCheckBoxes(ArrayList<Checkbox> checkboxs) {
		ArrayList<DayOfWeek> days = new ArrayList<DayOfWeek>();
		for (Checkbox dayCheckbox : checkboxs) {
			if (dayCheckbox.getState()) {
				switch (dayCheckbox.getLabel().toLowerCase()) {
				case "monday":
					days.add(DayOfWeek.MONDAY);
					break;
				case "tuesday":
					days.add(DayOfWeek.TUESDAY);
					break;
				case "wednesday":
					days.add(DayOfWeek.WEDNESDAY);
					break;
				case "thursday":
					days.add(DayOfWeek.THURSDAY);
					break;
				case "friday":
					days.add(DayOfWeek.FRIDAY);
					break;
				case "saturday":
					days.add(DayOfWeek.SATURDAY);
					break;
				case "sunday":
					days.add(DayOfWeek.SUNDAY);
					break;
				default:
					break;
				}
			}
		}
		return days;
	}
}
