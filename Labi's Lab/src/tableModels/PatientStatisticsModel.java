package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import models.PatientStatistics;

public class PatientStatisticsModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private static ArrayList<PatientStatistics> patients;
	private String[] columnNames = { "username", "name", "last name", "LBO", "number of requests", "total price" };

	public PatientStatisticsModel(ArrayList<PatientStatistics> patients) {
		PatientStatisticsModel.patients = patients;
	}

	@Override
	public int getRowCount() {
		return patients.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PatientStatistics patient = patients.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return patient.patient.getUserName();
		case 1:
			return patient.patient.getName();
		case 2:
			return patient.patient.getLastName();
		case 3:
			return patient.patient.getLBO();
		case 4:
			return patient.getNumberOfRequests();
		case 5:
			return patient.getTotalPriceByTime();
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
				put(1, 1);
				put(2, 1);
				put(3, 1);
			}
		};
		patients.sort(new Comparator<PatientStatistics>() {
			int retVal = 0;

			@Override
			public int compare(PatientStatistics o1, PatientStatistics o2) {
				switch (index) {
				case 0:
					retVal = o1.patient.getUserName().compareTo(o2.patient.getUserName());
					break;
				case 1:
					retVal = o1.patient.getName().compareTo(o2.patient.getName());
					break;
				case 2:
					retVal = o1.patient.getLastName().compareTo(o2.patient.getLastName());
					break;
				case 3:
					retVal = o1.patient.getLBO().compareTo(o2.patient.getLBO());
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
