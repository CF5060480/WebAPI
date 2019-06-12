package com.atossyntel.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.atossyntel.entities.User;
import com.atossyntel.pooling.ConnectionPooling;

public class UserJDBCOps {

    private Connection con;
    private Statement st;
    private ConnectionPooling conPool;

    public UserJDBCOps() {
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

    public User getUser(String userId) {
        try {
            System.out.println(userId);
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE USER_ID= " + "'" + userId + "'");
            User user;
            while (rs.next()) {
                user = new User(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("ISADMIN"));
                return user;
            }
        } catch (SQLException ex) {
            ex.getMessage();
            return new User();
        }
        return new User();
    }

    public boolean addUser(User user) {
        try {
            st.executeQuery("INSERT INTO USERS VALUES('" + user.getUserId() + "' , '" + user.getPassword() + "', '" + user.getIsAdmin() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String userId) {
        try {
            st.executeQuery("DELETE FROM USERS WHERE user_id='" + userId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            System.out.println("UPDATE users SET(password= '" + user.getPassword() + "', isadmin='" + user.getIsAdmin() + "') WHERE user_id = '" + user.getUserId() + "'");
            st.executeQuery("UPDATE users SET password= '" + user.getPassword() + "', isadmin='" + user.getIsAdmin() + "' WHERE user_id = '" + user.getUserId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            ArrayList<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("ISADMIN"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
