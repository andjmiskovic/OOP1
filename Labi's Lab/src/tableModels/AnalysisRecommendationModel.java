package tableModels;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import models.AnalysisType;

public class AnalysisRecommendationModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Map.Entry<AnalysisType, Double>> analysisRequests;
	private String[] columnNames = { "name", "group", "lower bound", "upper bound", "value", "unit" };

	public AnalysisRecommendationModel(ArrayList<Map.Entry<AnalysisType, Double>> analysisRequests) {
		this.analysisRequests = analysisRequests;
	}

	@Override
	public int getRowCount() {
		return analysisRequests.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Map.Entry<AnalysisType, Double> analysisTypeWithResult = analysisRequests.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return analysisTypeWithResult.getKey().getName();
		case 1:
			return analysisTypeWithResult.getKey().getGroup();
		case 2:
			return String.valueOf(analysisTypeWithResult.getKey().getLowerBound());
		case 3:
			return String.valueOf(analysisTypeWithResult.getKey().getUpperBound());
		case 4:
			if (analysisTypeWithResult.getValue() == null)
				return "processing";
			return String.valueOf(analysisTypeWithResult.getValue());
		case 5:
			return analysisTypeWithResult.getKey().getUnit();
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

}
