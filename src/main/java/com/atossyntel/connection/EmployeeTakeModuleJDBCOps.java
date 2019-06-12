package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import com.atossyntel.entities.EmployeeTakeModule;
import com.atossyntel.pooling.ConnectionPooling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeTakeModuleJDBCOps {

    private Connection con;
    private Statement st;
    private ConnectionPooling conPool;

    public EmployeeTakeModuleJDBCOps() {
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

    public ArrayList<EmployeeTakeModule> getEmployeeTakeModule(String batchId) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM employees_take_modules WHERE batch_id='" + batchId + "'");
            ArrayList<EmployeeTakeModule> empTakeMoList = new ArrayList<>();
            while (rs.next()) {
                EmployeeTakeModule empTakeMo = new EmployeeTakeModule(rs.getString("MODULE_ID"), rs.getString("EMPLOYEE_ID"), rs.getString("BATCH_ID"), rs.getInt("SCORES"));
                empTakeMoList.add(empTakeMo);
            }
            return empTakeMoList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTakeModuleJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        try {
            st.executeQuery("INSERT INTO employees_take_modules VALUES('"
                    + empTakeMo.getMduleId() + "', '"
                    + empTakeMo.getEmployeeId() + "', '"
                    + empTakeMo.getBatchId() + "', '"
                    + empTakeMo.getScores() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTakeModuleJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean deleteEmployeeTakeModule(String moduleId, String empId, String batchId) {
        try {
            st.executeQuery("DELETE FROM employees_take_modules WHERE "
                    + "MODULE_ID='" + moduleId + "' AND "
                    + "EMPLOYEE_ID='" + empId + "' AND "
                    + "BATCH_ID='" + batchId
                    + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTakeModuleJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        try {
            st.executeQuery("UPDATE employees_take_modules SET "
                    + "SCORES='" + empTakeMo.getScores() + "' WHERE MODULE_ID = '" + empTakeMo.getMduleId()
                    + "' AND EMPLOYEE_ID = '" + empTakeMo.getEmployeeId()
                    + "' AND BATCH_ID = '" + empTakeMo.getBatchId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTakeModuleJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<EmployeeTakeModule> getAllEmployeeTakeModules() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM employees_take_modules");
            ArrayList<EmployeeTakeModule> empTakeMoList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                EmployeeTakeModule empTakeMo = new EmployeeTakeModule(rs.getString("MODULE_ID"),
                        rs.getString("EMPLOYEE_ID"), rs.getString("BATCH_ID"), rs.getInt("SCORES"));
                empTakeMoList.add(empTakeMo);
            }
            return empTakeMoList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeTakeModuleJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
