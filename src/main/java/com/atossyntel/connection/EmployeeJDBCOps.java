package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.atossyntel.entities.User;

public class EmployeeJDBCOps {
	private Connection con;
	private Statement st;
	
	public EmployeeJDBCOps() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
			st = con.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 				             
	}
	
	public Employee getEmployee(String empId) {
		try{
                System.out.println(empId);
		ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employee_id= " +  "'" + empId + "'");
                Employee emp;
                while(rs.next()) {
                    emp = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
                    return emp;
                }
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return new Employee();
		}
                return new Employee();
	}
	
	public boolean addEmployee(Employee emp) {
		try {
                        st.executeQuery("INSERT INTO employees VALUES('" + emp.getEmployeeId() + "', '" + emp.getName() + "', '" + emp.getEmail() + "')");
			return true;
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public boolean deleteEmployee(String empId) {
		try {
			st.executeQuery("DELETE FROM employees WHERE employee_id='" + empId+"'");
			return true;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public boolean updateEmployee(Employee emp) {
		try {
			st.executeQuery("UPDATE employees SET name='" + emp.getName() + "', email ='" + emp.getEmail() + "' WHERE employee_id = '" + emp.getEmployeeId() + "'");
			return true;
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}
	
	public ArrayList<Employee> getAllEmployees(){
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM employees");
                        ArrayList<Employee> empList = new ArrayList<>();
                        System.out.println(rs.toString());
                        while(rs.next())
                        {
                            Employee emp = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
                            empList.add(emp);
                        }                      
			return empList;
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
			return new ArrayList<>();
		}
	}
}