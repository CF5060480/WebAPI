package com.atossyntel.connection;

import com.atossyntel.pooling.ConnectionService;
import com.atossyntel.entities.InstructorTeachBatch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstructorTeachBatchJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public InstructorTeachBatchJDBCOps() {
        cs= ConnectionService.getInstance();
        con = cs.createCon();
    }
    
    public InstructorTeachBatch getInsTchBat(String userId, String batchId) throws SQLException {
        String selectStmt = "SELECT * FROM instructors_teach_batches WHERE user_id = ? AND batch_id = ?";
        InstructorTeachBatch insTchBat = new InstructorTeachBatch();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, userId);
            st.setString(2, batchId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                insTchBat = new InstructorTeachBatch(rs.getString("USER_ID"), rs.getString("BATCH_ID"));
            }
            return insTchBat;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return new InstructorTeachBatch();
    }

    public boolean addInsTchBat(InstructorTeachBatch insTchBat) {
        String insertStmt = "INSERT INTO instructors_teach_batches VALUES(?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, insTchBat.getUserId());
            st.setString(2, insTchBat.getBatchId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteInsTchBat(String userId,String batchId) {
        String deleteStmt = "DELETE FROM instructors_teach_batches WHERE user_id = ? AND batch_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, userId);
            st.setString(2, batchId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<InstructorTeachBatch> getAllInsTchBat() {
        String getAllStmt = "SELECT * FROM instructors_teach_batches";
        ArrayList<InstructorTeachBatch> insTchBatList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                InstructorTeachBatch insTchBat = new InstructorTeachBatch(rs.getString("USER_ID"), rs.getString("BATCH_ID"));
                insTchBatList.add(insTchBat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con); 
        return insTchBatList;

    }
}
