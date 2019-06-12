package com.atossyntel.connection;
import com.atossyntel.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EmployeeJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public EmployeeJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public Employee getEmployee(String empId) {
        String selectStmt = "SELECT * FROM employees WHERE employee_id= ?";
        Employee employee = new Employee();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, empId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return employee;
    }

    public boolean addEmployee(Employee emp) {
        String insertStmt = "INSERT INTO employees VALUES(?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, emp.getEmployeeId());
            st.setString(2, emp.getName());
            st.setString(3, emp.getEmail());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteEmployee(String empId) {
        String deleteStmt = "DELETE FROM employees WHERE employee_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, empId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateEmployee(Employee emp) {
        String updateStmt = "UPDATE employees SET name = ?, email = ? WHERE employee_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, emp.getName());
            st.setString(2, emp.getEmail());
            st.setString(3, emp.getEmployeeId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Employee> getAllEmployees() {
        String getAllStmt = "SELECT * FROM employees";
        ArrayList<Employee> employeeList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                Employee employee = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
                employeeList.add(employee);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return employeeList;
    }
}
