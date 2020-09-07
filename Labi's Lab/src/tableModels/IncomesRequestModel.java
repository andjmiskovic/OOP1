package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileHandler.AllAnalysisRequests;
import models.AnalysisRequest;

public class IncomesRequestModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<AnalysisRequest> analysisRequests;
	private String[] columnNames = { "id", "patient", "number of analysis", "price (RSD)" };

	public IncomesRequestModel(ArrayList<AnalysisRequest> analysisRequests) {
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
		AnalysisRequest request;
		try {
			request = analysisRequests.get(rowIndex);
		} catch (Exception e) {
			return null;
		}
		switch (columnIndex) {
		case 0:
			return request.getId();
		case 1:
			if (request.getPatient() != null)
				return request.getPatient().getLastName() + " " + request.getPatient().getName();
			return "missing patient";
		case 2:
			return request.getAnalyses().size();
		case 3:
			return request.getTotalPrice();
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
		if (this.getValueAt(0, columnIndex) != null) {
			return this.getValueAt(0, columnIndex).getClass();
		}
		return null;
	}

	public static void sort(int index) {
		Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put(0, 1);
				put(2, 1);
				put(3, 1);
			}
		};
		AllAnalysisRequests.listOfRequests.sort(new Comparator<AnalysisRequest>() {
			int retVal = 0;

			@Override
			public int compare(AnalysisRequest o1, AnalysisRequest o2) {
				switch (index) {
				case 0:
					retVal = o1.getId().compareTo(o2.getId());
					break;
				case 2:
					retVal = (int) o1.getAnalyses().size() - o2.getAnalyses().size();
					break;
				case 3:
					retVal = (int) (o1.getTotalPrice() - o2.getTotalPrice());
					break;
				default:
					System.exit(1);
					break;
				}
				return retVal * sortOrder.get(index);
			}
		});
		sortOrder.put(index, sortOrder.get(index) * -1);
	}

}
