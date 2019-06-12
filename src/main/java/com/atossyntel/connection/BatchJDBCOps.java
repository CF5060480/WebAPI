package com.atossyntel.connection;

import com.atossyntel.entities.Batch;
import com.atossyntel.pooling.ConnectionPooling;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BatchJDBCOps {

    private Connection con;
    private Statement st;
    ConnectionPooling conPool;

    public BatchJDBCOps() {
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

    public Batch getBatch(String batchId) {
        try {
            System.out.println(batchId);
            ResultSet rs = st.executeQuery("SELECT * FROM batches WHERE batch_id = " + "'" + batchId + "'");
            Batch batch;
            while (rs.next()) {
                batch = new Batch(rs.getString("BATCH_ID"), rs.getString("START_DATE"), rs.getString("END_DATE"), rs.getString("STREAM_ID"), rs.getString("COUNTRY"), rs.getString("CITY"));
                return batch;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Batch();
        }
        return new Batch();
    }

    public boolean addBatch(Batch batch) {
        try {
            st.executeQuery("INSERT INTO batches VALUES('" + batch.getBatchId() + "', '"
                    + batch.getStartDate() + "', '" + batch.getEndDate() + "', '" + batch.getStreamId()
                    + "', '" + batch.getCountry() + "', '" + batch.getCity() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteBatch(String batchId) {
        try {
            st.executeQuery("DELETE FROM batches WHERE batch_id='" + batchId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateBatch(Batch batch) {
        try {
            st.executeQuery("UPDATE batches SET start_date = '" + batch.getStartDate() + "', end_date = '"
                    + batch.getEndDate() + "', stream_id = '" + batch.getStreamId() + "', country = '"
                    + batch.getCountry() + "', city = '" + batch.getCity() + "' WHERE batch_id = '"
                    + batch.getBatchId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Batch> getAllBatches() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM batches");
            ArrayList<Batch> batchList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                Batch batch = new Batch(rs.getString("BATCH_ID"), rs.getString("START_DATE"), rs.getString("END_DATE"), rs.getString("STREAM_ID"), rs.getString("COUNTRY"), rs.getString("CITY"));
                batchList.add(batch);
            }
            return batchList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
