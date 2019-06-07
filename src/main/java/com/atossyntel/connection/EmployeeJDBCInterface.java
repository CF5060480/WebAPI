package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.util.ArrayList;

public interface EmployeeJDBCInterface {
	public Employee getEmployee(String empId);
	public boolean deleteEmployee(String empId);
	public boolean updateEmployee(Employee emp);
	public ArrayList<Employee> getAllEmployees();
	public boolean addEmployee(Employee emp);
}
