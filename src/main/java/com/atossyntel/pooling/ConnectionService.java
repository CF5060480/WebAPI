package com.atossyntel.pooling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionService {

    private static ConnectionService instance;
    private Connection con;
    private Statement st;

    private ConnectionService() {
        //default constructor
    }

    static {
        instance = new ConnectionService();
    }

    public static ConnectionService getInstance() {
        return instance;
    }

    public Connection createCon() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
    
    public void closeConnection(Connection c){
       try{c.close();}catch(Exception e){}
   }
}

