package com.atossyntel.connection;
import com.atossyntel.pooling.ConnectionService;
import com.atossyntel.entities.Batch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class BatchJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public BatchJDBCOps(){
        cs = ConnectionService.getInstance();
        con = cs.createCon();
    }
    
    public Batch getBatch(String batchId) {
        Batch batch = new Batch();
        String selectStmt = "SELECT * FROM batches WHERE batch_id = ?";
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, batchId);
            System.out.println(batchId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                batch = new Batch(rs.getString("BATCH_ID"), rs.getString("START_DATE"), rs.getString("END_DATE"), 
                        rs.getString("STREAM_ID"), rs.getString("COUNTRY"), rs.getString("CITY"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return batch;
    }

    public boolean addBatch(Batch batch) {
        String insertStmt = "INSERT INTO batches VALUES(?, ?, ?, ?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, batch.getBatchId());
            st.setString(2, batch.getStartDate());
            st.setString(3, batch.getEndDate());
            st.setString(4, batch.getStreamId());
            st.setString(5, batch.getCountry());
            st.setString(6, batch.getCity());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteBatch(String batchId) {
        String deleteStmt = "DELETE FROM batches WHERE batch_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, batchId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateBatch(Batch batch) {
        String updateStmt = "UPDATE batches SET start_date = ?, end_date = ?, stream_id = ?, country = ?, city = ? WHERE batch_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, batch.getStartDate());
            st.setString(2, batch.getEndDate());
            st.setString(3, batch.getStreamId());
            st.setString(4, batch.getCountry());
            st.setString(5, batch.getCity());
            st.setString(6, batch.getBatchId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Batch> getAllBatches() {
        ArrayList<Batch> batchList = new ArrayList<>();
        String getAllStmt = "SELECT * FROM batches";
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                Batch batch = new Batch(rs.getString("BATCH_ID"), rs.getString("START_DATE"), rs.getString("END_DATE"), 
                        rs.getString("STREAM_ID"), rs.getString("COUNTRY"), rs.getString("CITY"));
                batchList.add(batch);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return batchList;
    }
}
