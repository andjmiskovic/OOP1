package models;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import classes.AnalysisRequest;
import fileHandler.AllAnalysisRequests;

public class RequestModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<AnalysisRequest> analysisRequests;
	private String[] columnNames = { "id", "requested date", "home visit time", "state of request",
			"medical technicial", "price (RSD)" };

	public RequestModel(ArrayList<AnalysisRequest> analysisRequests) {
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
		AnalysisRequest request = analysisRequests.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return request.getId();
		case 1:
			return request.getRequestedDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		case 2:
			if (request.getHomeVisitDate() == null)
				return " ";
			if (request.getHomeVisitTime() == null)
				return request.getHomeVisitDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
			return request.getHomeVisitDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy."))
					+ " " + request.getHomeVisitTime().format(DateTimeFormatter.ofPattern("HH:mm"));
		case 3:
			return request.getCurrentState().getName();
		case 4:
			if (request.getMedicalTechnicial() != null)
				return request.getMedicalTechnicial().getName() + request.getMedicalTechnicial().getLastName();
			return " ";
		case 5:
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
		AllAnalysisRequests.listOfRequests.sort(new Comparator<AnalysisRequest>() {
			int retVal = 0;

			@Override
			public int compare(AnalysisRequest o1, AnalysisRequest o2) {
				switch (index) {
				case 0:
					retVal = o1.getId().compareTo(o2.getId());
					break;
				case 1:
					retVal = o1.getRequestedDate().compareTo(o2.getRequestedDate());
					break;
				case 3:
					retVal = o1.getCurrentState().toString().compareTo(o2.getCurrentState().toString());
					break;
				case 5:
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
