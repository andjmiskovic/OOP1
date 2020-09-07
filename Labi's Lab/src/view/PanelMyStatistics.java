package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

import fileHandler.AllAnalysisRequests;
import fileHandler.AllAnalysisTypes;
import models.AnalysisType;
import models.CurrentUser;
import tableModels.AnalysisMyStatisticsModel;

public class PanelMyStatistics extends JPanel {
	private static final long serialVersionUID = 1L;
	static JPanel changingPanel, chartPanel;
	static JTable table;
	static ArrayList<AnalysisType> analysisTypes;
	static Color myBlue = new Color(6, 56, 74);

	public PanelMyStatistics() {
		setUp();
	}

	public static void setUp() {
		changingPanel = new JPanel();
		changingPanel.setBounds(350, 0, 1150, 1000);
		changingPanel.setBackground(myBlue);
		changingPanel.setLayout(null);

		JLabel title = new JLabel("MY STATISTICS");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Tahoma", Font.PLAIN, 34));
		title.setBounds(165, 51, 662, 59);
		changingPanel.add(title);

		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(PanelEmployees.class.getResource("/pictures/myStatisticsBlue.jpg")));
		icon.setBounds(75, 51, 70, 70);
		changingPanel.add(icon);

		JLabel lblChooseGroup = new JLabel("Choose analysis type in order to see statistics of your prevoius results");
		lblChooseGroup.setForeground(Color.WHITE);
		lblChooseGroup.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChooseGroup.setBounds(50, 132, 558, 36);
		changingPanel.add(lblChooseGroup);

		XYChart chart = new XYChartBuilder().width(800).height(600).title("Previous results per analysis type")
				.xAxisTitle("Dates").yAxisTitle("Measured Values").build();
		chart.getStyler().setChartTitleVisible(true);
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);

		XChartPanel<XYChart> chartPanel = new XChartPanel<XYChart>(chart);
		chartPanel.setBounds(50, 514, 1015, 419);
		changingPanel.add(chartPanel);

		table = new JTable(new AnalysisMyStatisticsModel(AllAnalysisTypes.listOfAnalysisTypes));
		table.setBackground(Color.WHITE);
		table.setRowSelectionAllowed(true);
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(myBlue);
		table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getTableHeader().columnAtPoint(arg0.getPoint());
				AnalysisMyStatisticsModel.sort(index);
				refreshTable();
			}
		});

		JPanel panelTable = new JPanel();
		panelTable.setBackground(Color.WHITE);
		panelTable.setLayout(new BorderLayout(0, 0));
		panelTable.setBounds(50, 174, 1015, 300);

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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				int line = table.getSelectedRow();
				if (line != -1) {
					AnalysisType analysisTypes = AllAnalysisTypes
							.getAnalysisTypeByName(table.getModel().getValueAt(line, 0).toString());
					Map<LocalDate, Double> resultMap = AllAnalysisRequests.calculateStatisticsPerAnalysisTypeByPatient(
							CurrentUser.getCurrentPatient(), analysisTypes);
					if (resultMap.size() == 0)
						JOptionPane.showMessageDialog(null, "You have never done this analysis or results are not ready yet.", "Error",
								JOptionPane.WARNING_MESSAGE);
					else {
						chart.removeSeries("analysis");
						ArrayList<Date> xData = new ArrayList<Date>();
						ArrayList<Double> yData = new ArrayList<Double>();
						for (Map.Entry<LocalDate, Double> entry : resultMap.entrySet()) {
							xData.add(Date.from(entry.getKey().atStartOfDay(ZoneId.systemDefault()).toInstant()));
							yData.add(entry.getValue());
						}
						chart.addSeries("analysis", xData, yData);
						chartPanel.revalidate();
						chartPanel.repaint();
					}
				}
			}
		});
	}

	public static void refreshTable() {
		AnalysisMyStatisticsModel analysisRequestModel = (AnalysisMyStatisticsModel) table.getModel();
		analysisRequestModel.fireTableDataChanged();
		table.repaint();
	}
}
