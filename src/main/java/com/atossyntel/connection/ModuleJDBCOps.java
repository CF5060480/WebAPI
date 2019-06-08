package com.atossyntel.connection;

import com.atossyntel.entities.Module;
import com.atossyntel.entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModuleJDBCOps {

    private Connection con;
    private Statement st;

    public ModuleJDBCOps() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
            st = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Module getModule(String moduleId) {
        try {
            System.out.println(moduleId);
            ResultSet rs = st.executeQuery("SELECT * FROM modules WHERE MODULE_ID= " + "'" + moduleId + "'");
            Module module;
            while (rs.next()) {
                module = new Module(rs.getString("MODULE_ID"), rs.getString("MODULE_NAME"), rs.getString("CATEGORY_ID"),rs.getString("STREAM_ID"));
                return module;
            }
        } catch (SQLException ex) {
            ex.getMessage();
            return new Module();
        }
        return new Module();
    }

    public boolean addModule(Module module) {
        try {
            st.executeQuery("INSERT INTO MODULES VALUES('" + module.getModuleId() + "' , '" 
                    + module.getModuleName() + "', '" + module.getCategoryId()+ "', '" + module.getStreamId() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteModule(String moduleId) {
        try {
            st.executeQuery("DELETE FROM MODULES WHERE MODULE_ID='" + moduleId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateModule(Module module) {
        try {
            System.out.println("UPDATE modules SET(MODULE_NAME= '" + module.getModuleName()
                    + "', CATEGORY_ID='" + module.getCategoryId()
                    + "', STREAM_ID='" + module.getStreamId()
                    + "') WHERE MODULE_ID = '" + module.getModuleId() + "'");
            st.executeQuery("UPDATE modules SET MODULE_NAME= '" + module.getModuleName()
                    + "', CATEGORY_ID='" + module.getCategoryId()
                    + "', STREAM_ID='" + module.getStreamId()
                    + "' WHERE MODULE_ID = '" + module.getModuleId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Module> getAllModules() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM modules");
            ArrayList<Module> moduleList = new ArrayList<>();
            while (rs.next()) {
                Module module = new Module(rs.getString("MODULE_ID"), rs.getString("MODULE_NAME"), rs.getString("CATEGORY_ID"),rs.getString("STREAM_ID"));
                System.out.println("####"+module);
                moduleList.add(module);
            }
            return moduleList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
