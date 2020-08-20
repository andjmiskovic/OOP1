package models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import classes.AnalysisType;

public class AnalysisDiscountModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<AnalysisType> analysisTypes;
	private String[] columnNames = { "name", "price", "type discount", "group discount" };

	public AnalysisDiscountModel(ArrayList<AnalysisType> analysisTypes) {
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
			return analysisType.getPrice();
		case 2:
			if (analysisType.getTypeDiscount() != null)
				return analysisType.getTypeDiscount().getValue();
			return 0;
		case 3:
			if (analysisType.getGroupDiscount() != null)
				return analysisType.getGroupDiscount().getValue();
			return 0;
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
