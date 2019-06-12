package com.atossyntel.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.atossyntel.entities.Stream;
import com.atossyntel.pooling.ConnectionPooling;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StreamJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public StreamJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public Stream getStream(String streamId) {
        String selectStmt = "SELECT * FROM stream WHERE stream_id= ?";
        Stream stream = new Stream();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, streamId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                stream = new Stream(rs.getString("STREAM_ID"), rs.getString("STREAM_NAME"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return stream;
    }

    public boolean addStream(Stream stream) {
        String insertStmt = "INSERT INTO stream VALUES(?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, stream.getStreamId());
            st.setString(2, stream.getStreamName());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteStream(String streamId) {
        String deleteStmt = "DELETE FROM stream WHERE stream_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, streamId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateStream(Stream stream) {
        String updateStmt = "UPDATE stream SET stream_name = ? WHERE stream_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, stream.getStreamName());
            st.setString(2, stream.getStreamId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Stream> getAllStreams() {
        String getAllStmt = "SELECT * FROM stream";
        ArrayList<Stream> streamList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Stream stream = new Stream(rs.getString("STREAM_ID"), rs.getString("STREAM_NAME"));
                streamList.add(stream);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return streamList;
    }
}
