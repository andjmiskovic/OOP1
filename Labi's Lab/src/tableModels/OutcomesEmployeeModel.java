package tableModels;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import fileHandler.Users;
import models.Employee;
import models.EmployeeStatistics;

public class OutcomesEmployeeModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ArrayList<EmployeeStatistics> employees;
	private String[] columnNames = { "role", "username", "name", "last name", "number of requests/home visits" };

	public OutcomesEmployeeModel(ArrayList<EmployeeStatistics> employees) {
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
		EmployeeStatistics employee = employees.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return employee.getClassName();
		case 1:
			return employee.employee.getUserName();
		case 2:
			return employee.employee.getName();
		case 3:
			return employee.employee.getLastName();
		case 4:
			if (employee.getClassName().equals("Admin"))
				return " ";
			return employee.getNumberOfRequests();
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
		if (this.getValueAt(0, columnIndex) != null)
			return this.getValueAt(0, columnIndex).getClass();
		return null;
	}

	public static void sort(int index) {
		Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {
			private static final long serialVersionUID = 1L;

			{
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
