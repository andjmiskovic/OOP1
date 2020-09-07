package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileHandler.AllAnalysisTypes;
import models.AnalysisType;

public class AnalysisMyStatisticsModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<AnalysisType> analysisTypes;
	private String[] columnNames = { "name", "group", "description", "lower bound", "upper bound", "unit" };

	public AnalysisMyStatisticsModel(ArrayList<AnalysisType> analysisTypes) {
		this.analysisTypes = analysisTypes;
	}

	@Override
	public int getRowCount() {
		return analysisTypes.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		AnalysisType analysisType = analysisTypes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return analysisType.getName();
		case 1:
			return analysisType.getGroup();
		case 2:
			return analysisType.getDescription();
		case 3:
			return analysisType.getLowerBound();
		case 4:
			return analysisType.getUpperBound();
		case 5:
			return analysisType.getUnit();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (this.getValueAt(0, columnIndex) == null)
			return null;
		return this.getValueAt(0, columnIndex).getClass();
	}

	public static void sort(int index) {
		Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, 1);
				put(1, 1);
				put(2, 1);
				put(3, 1);
				put(4, 1);
				put(5, 1);
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
					retVal = o1.getGroup().compareTo(o2.getGroup());
					break;
				case 2:
					retVal = o1.getDescription().compareTo(o2.getDescription());
					break;
				case 3:
					retVal = (int) (o1.getLowerBound() - o2.getLowerBound());
					break;
				case 4:
					retVal = (int) (o1.getUpperBound() - o2.getUpperBound());
					break;
				case 5:
					retVal = o1.getUnit().compareTo(o2.getUnit());
					break;
				default:
					break;
				}
				return retVal * sortOrder.get(index);
			}
		});
		sortOrder.put(index, sortOrder.get(index) * -1);
	}

}
