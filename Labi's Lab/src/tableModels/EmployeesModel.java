package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileHandler.Users;
import models.Employee;

public class EmployeesModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Employee> employees;
	private String[] columnNames = { "role", "username", "name", "last name", "phone number", "address" };

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
			return employee.getPhoneNumber();
		case 5:
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
		Users.getEmployees().sort(new Comparator<Employee>() {
			int retVal = 0;

			@Override
			public int compare(Employee o1, Employee o2) {
				switch (index) {
				case 0:
					retVal = o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
					break;
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
