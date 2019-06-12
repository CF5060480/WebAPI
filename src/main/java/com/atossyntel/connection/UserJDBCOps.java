package com.atossyntel.connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.atossyntel.entities.User;

public class UserJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public UserJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public User getUser(String userId) {
        String selectStmt = "SELECT * FROM users WHERE user_id = ?";
        User user = new User();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, userId);
            System.out.println(userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("ISADMIN"));
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        cs.closeConnection(con);
        return user;
    }

    public boolean addUser(User user) {
        String insertStmt = "INSERT INTO USERS VALUES(?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, user.getUserId());
            st.setString(2, user.getPassword());
            st.setString(3, user.getIsAdmin());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteUser(String userId) {
        String deleteStmt = "DELETE FROM USERS WHERE user_id= ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, userId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateUser(User user) {
        String updateStmt = "UPDATE users SET password = ?, isadmin = ? WHERE user_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, user.getPassword());
            st.setString(2, user.getIsAdmin());
            st.setString(3, user.getUserId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<User> getAllUsers() {
        String getAllStmt = "SELECT * FROM users";
        ArrayList<User> userList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("USER_ID"), rs.getString("PASSWORD"), rs.getString("ISADMIN"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return userList;
    }
}
