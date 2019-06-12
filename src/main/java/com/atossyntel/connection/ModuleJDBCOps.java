package com.atossyntel.connection;
import com.atossyntel.entities.Module;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public ModuleJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public Module getModule(String moduleId) {
        String selectStmt = "SELECT * FROM modules WHERE module_id = ?";
        Module module = new Module();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, moduleId);
            System.out.println(moduleId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                module = new Module(rs.getString("MODULE_ID"), rs.getString("MODULE_NAME"), rs.getString("CATEGORY_ID"),rs.getString("STREAM_ID"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return module;
    }

    public boolean addModule(Module module) {
        String insertStmt = "INSERT INTO MODULES VALUES(? , ?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, module.getModuleId());
            st.setString(2, module.getModuleName());
            st.setString(3, module.getCategoryId());
            st.setString(4, module.getStreamId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteModule(String moduleId) {
        String deleteStmt = "DELETE FROM MODULES WHERE module_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, moduleId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateModule(Module module) {
        String updateStmt = "UPDATE modules SET module_name = ?, category_id = ?, stream_id = ? WHERE module_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, module.getModuleName());
            st.setString(2, module.getCategoryId());
            st.setString(3, module.getStreamId());
            st.setString(4, module.getModuleId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Module> getAllModules() {
        String getAllStmt = "SELECT * FROM modules";
        ArrayList<Module> moduleList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Module module = new Module(rs.getString("MODULE_ID"), rs.getString("MODULE_NAME"), rs.getString("CATEGORY_ID"), rs.getString("STREAM_ID"));
                moduleList.add(module);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return moduleList;
    }
}
