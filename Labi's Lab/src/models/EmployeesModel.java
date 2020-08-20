package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import classes.Employee;
import fileHandler.Users;

public class EmployeesModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> employees;
	private String[] columnNames = { "role", "username", "name", "last name", "qualification", "phone number", "address" };

	public EmployeesModel(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int getRowCount() {
		return employees.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = employees.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return employee.getClass().getSimpleName();
		case 1:
			return employee.getUserName();
		case 2:
			return employee.getName();
		case 3:
			return employee.getLastName();
		case 4:
			return employee.getQualification().getName();
		case 5:
			return employee.getPhoneNumber();
		case 6:
			return employee.getAddress();
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
			}
		};
		Users.getEmployees().sort(new Comparator<Employee>() {
			int retVal = 0;

			@Override
			public int compare(Employee o1, Employee o2) {
				switch (index) {
				case 1:
					retVal = o1.getUserName().compareTo(o2.getUserName());
					break;
				case 2:
					retVal = o1.getName().compareTo(o2.getName());
					break;
				case 3:
					retVal = o1.getLastName().compareTo(o2.getLastName());
					break;
				case 4:
					retVal = o1.getQualification().compareTo(o2.getQualification());
					break;
				case 5:
					retVal = o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
					break;
				case 6:
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
