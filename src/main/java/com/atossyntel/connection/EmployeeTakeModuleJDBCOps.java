package com.atossyntel.connection;
import com.atossyntel.entities.EmployeeTakeModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EmployeeTakeModuleJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public EmployeeTakeModuleJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public ArrayList<EmployeeTakeModule> getEmployeeTakeModule(String batchId) {
        String selectStmt = "SELECT * FROM employees_take_modules WHERE batch_id = ?";
        ArrayList<EmployeeTakeModule> empTakeMoList = new ArrayList<>();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, batchId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EmployeeTakeModule empTakeMo = new EmployeeTakeModule(rs.getString("MODULE_ID"), rs.getString("EMPLOYEE_ID"), rs.getString("BATCH_ID"), rs.getInt("SCORES"));
                empTakeMoList.add(empTakeMo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return empTakeMoList;
    }

    public boolean addEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        String insertStmt = "INSERT INTO employees_take_modules VALUES(?, ?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, empTakeMo.getMduleId());
            st.setString(2, empTakeMo.getEmployeeId());
            st.setString(3, empTakeMo.getBatchId());
            st.setDouble(4, empTakeMo.getScores());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteEmployeeTakeModule(String moduleId,String empId,String batchId) {
        String deleteStmt = "DELETE FROM employees_take_modules WHERE module_id = ? AND employee_id = ? AND batch_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, moduleId);
            st.setString(2, empId);
            st.setString(3, batchId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        String updateStmt = "UPDATE employees_take_modules SET scores = ? WHERE module_id = ? AND employee_id = ? AND batch_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setDouble(1, empTakeMo.getScores());
            st.setString(2, empTakeMo.getMduleId());
            st.setString(3, empTakeMo.getEmployeeId());
            st.setString(4, empTakeMo.getBatchId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<EmployeeTakeModule> getAllEmployeeTakeModules() {
        String getAllStmt = "SELECT * FROM employees_take_modules";
        ArrayList<EmployeeTakeModule> empTakeMoList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EmployeeTakeModule empTakeMo = new EmployeeTakeModule(rs.getString("MODULE_ID"), 
                        rs.getString("EMPLOYEE_ID"), rs.getString("BATCH_ID"), rs.getInt("SCORES"));
                empTakeMoList.add(empTakeMo);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return empTakeMoList;
    }
}
