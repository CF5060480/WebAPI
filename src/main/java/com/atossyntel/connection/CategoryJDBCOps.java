package com.atossyntel.connection;
import com.atossyntel.pooling.ConnectionService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.atossyntel.entities.Category;

public class CategoryJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public CategoryJDBCOps() {
        cs = ConnectionService.getInstance();
        con = cs.createCon();
    }
    public Category getCategory(String categoryId) {
        String selectStmt = "SELECT * FROM category WHERE category_id= ?";
        Category category = new Category();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getString("CATEGORY_ID"), rs.getString("CATEGORY_NAME"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return category;
    }

    public boolean addCategory(Category category) {
        String insertStmt = "INSERT INTO category VALUES(?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, category.getCategoryId());
            st.setString(2, category.getCategoryName());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteCategory(String categoryId) {
        String deleteStmt = "DELETE FROM category WHERE category_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, categoryId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateCategory(Category category) {
        String updateStmt = "UPDATE category SET category_name = ? WHERE category_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, category.getCategoryName());
            st.setString(2, category.getCategoryId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Category> getAllCategories() {
        String getAllStmt = "SELECT * FROM category";
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                Category category = new Category(rs.getString("CATEGORY_ID"), rs.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return categoryList;
    }
}
