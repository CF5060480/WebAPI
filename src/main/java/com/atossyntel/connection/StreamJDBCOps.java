package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.atossyntel.entities.Stream;
import com.atossyntel.pooling.ConnectionPooling;

public class StreamJDBCOps {

    private Connection con;
    private Statement st;
    private ConnectionPooling conPool;

    public StreamJDBCOps() {
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

    public Stream getStream(String id) {
        try {
            System.out.println(id);
            ResultSet rs = st.executeQuery("SELECT * FROM stream WHERE stream_id= " + "'" + id + "'");
            Stream stream;
            while (rs.next()) {
                stream = new Stream(rs.getString("STREAM_ID"), rs.getString("STREAM_NAME"));
                return stream;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Stream();
        }
        return new Stream();
    }

    public boolean addStream(Stream stream) {
        try {
            st.executeQuery("INSERT INTO stream VALUES('" + stream.getStreamId() + "', '" + stream.getStreamName() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteStream(String id) {
        try {
            st.executeQuery("DELETE FROM stream WHERE stream_id='" + id + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateStream(Stream stream) {
        try {
            st.executeQuery("UPDATE stream SET stream_name='" + stream.getStreamName() + "' WHERE stream_id = '" + stream.getStreamId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Stream> getAllStreams() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM stream");
            ArrayList<Stream> streamList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                Stream stream = new Stream(rs.getString("STREAM_ID"), rs.getString("STREAM_NAME"));
                streamList.add(stream);
            }
            return streamList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
