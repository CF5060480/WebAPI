package com.atossyntel.connection;
import com.atossyntel.entities.InstructorTeachBatch;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InstructorTeachBatchJDBCOps {

    private Connection con;
    private Statement st;

    public InstructorTeachBatchJDBCOps() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
            st = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public InstructorTeachBatch getInsTchBat(String userId, String batchId) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM instructors_teach_batches WHERE user_id= " + "'" + userId+"'"
                                                                                +" AND batch_id= " + "'" + batchId+"'");
            InstructorTeachBatch insTchBat;
            while (rs.next()) {
                insTchBat = new InstructorTeachBatch(rs.getString("USER_ID"), rs.getString("BATCH_ID"));
                return insTchBat;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new InstructorTeachBatch();
        }
        return new InstructorTeachBatch();
    }

    public boolean addInsTchBat(InstructorTeachBatch insTchBat) {
        try {
            st.executeQuery("INSERT INTO instructors_teach_batches VALUES('" 
                    + insTchBat.getUserId() + "', '" 
                    + insTchBat.getBatchId() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteInsTchBat(String userId,String batchId) {
        try {
            System.out.println(userId);
            System.out.println(batchId);
            st.executeQuery("DELETE FROM instructors_teach_batches WHERE USER_ID='" + userId 
                    + "' AND BATCH_ID='" + batchId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<InstructorTeachBatch> getAllInsTchBat() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM instructors_teach_batches");
            ArrayList<InstructorTeachBatch> insTchBatList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                InstructorTeachBatch insTchBat = new InstructorTeachBatch(rs.getString("USER_ID"), rs.getString("BATCH_ID"));
                insTchBatList.add(insTchBat);
            }
            return insTchBatList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
