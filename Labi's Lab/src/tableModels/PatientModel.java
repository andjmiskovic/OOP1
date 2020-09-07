package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileHandler.Users;
import models.Patient;


public class PatientModel extends AbstractTableModel {
	private static final long serialVersionUID = 173122351138550735L;
	private ArrayList<Patient> patients;
	private String[] columnNames = { "username", "name", "last name", "LBO", "phone number", "address"};

	public PatientModel(ArrayList<Patient> patients) {
		this.patients = patients;
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
		Patient patient = patients.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return patient.getUserName();
		case 1:
			return patient.getName();
		case 2:
			return patient.getLastName();
		case 3:
			return patient.getLBO();
		case 4:
			return patient.getPhoneNumber();
		case 5:
			return patient.getAddress();
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
		Users.listOfPatients.sort(new Comparator<Patient>() {
			int retVal = 0;

			@Override
			public int compare(Patient o1, Patient o2) {
				switch (index) {
				case 0:
					retVal = o1.getUserName().compareTo(o2.getUserName());
					break;
				case 1:
					retVal = o1.getName().compareTo(o2.getName());
					break;
				case 2:
					retVal = o1.getLastName().compareTo(o2.getLastName());
					break;
				case 3:
					retVal = o1.getLBO().compareTo(o2.getLBO());
					break;
				case 4:
					retVal = o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
					break;
				case 5:
					retVal = o1.getAddress().compareTo(o2.getAddress());
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
