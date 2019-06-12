package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.atossyntel.entities.User;
import com.atossyntel.pooling.ConnectionPooling;

public class EmployeeJDBCOps {

    private Connection con;
    private Statement st;
    private ConnectionPooling conPool;

    public EmployeeJDBCOps() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conPool = ConnectionPooling.create("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
            con = conPool.getConnection();
            st = con.createStatement();
            System.out.println("Connection Pool: " + conPool);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployee(String empId) throws SQLException {
        try {
            System.out.println(empId);
            ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employee_id= " + "'" + empId + "'");
            Employee emp;
            while (rs.next()) {
                emp = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
                return emp;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Employee();
        } finally {
        con.close();
    }
        return new Employee();
    }

    public boolean addEmployee(Employee emp) throws SQLException {
        try {
            st.executeQuery("INSERT INTO employees VALUES('" + emp.getEmployeeId() + "', '" + emp.getName() + "', '" + emp.getEmail() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            con.close();
        }
    }

    public boolean deleteEmployee(String empId) throws SQLException {
        try {
            st.executeQuery("DELETE FROM employees WHERE employee_id='" + empId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            con.close();
        }
    }

    public boolean updateEmployee(Employee emp) throws SQLException {
        try {
            st.executeQuery("UPDATE employees SET name='" + emp.getName() + "', email ='" + emp.getEmail() + "' WHERE employee_id = '" + emp.getEmployeeId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            con.close();
        }
    }

    public ArrayList<Employee> getAllEmployees() throws SQLException {
        try {
            System.out.println("getting all employees...");
            ResultSet rs = st.executeQuery("SELECT * FROM employees");
            ArrayList<Employee> empList = new ArrayList<>();
            System.out.println("Result Set: " + rs.toString());
            while (rs.next()) {
                Employee emp = new Employee(rs.getString("EMPLOYEE_ID"), rs.getString("NAME"), rs.getString("EMAIL"));
                empList.add(emp);
            }
            return empList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        } finally {
            con.close();
        }
    }
}
