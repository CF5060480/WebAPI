package com.atossyntel.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {
   public Connection openConnection(){
       Connection con = null;
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
