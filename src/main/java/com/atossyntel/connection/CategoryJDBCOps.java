package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.atossyntel.entities.Category;
import com.atossyntel.pooling.ConnectionPooling;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryJDBCOps {

    private Connection con;
    private Statement st;
    ConnectionPooling conPool;

    public CategoryJDBCOps() {
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

    public Category getCategory(String categoryId) {
        try {
            System.out.println(categoryId);
            ResultSet rs = st.executeQuery("SELECT * FROM category WHERE category_id= " + "'" + categoryId + "'");
            Category category;
            while (rs.next()) {
                category = new Category(rs.getString("CATEGORY_ID"), rs.getString("CATEGORY_NAME"));
                return category;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Category();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Category();
    }

    public boolean addCategory(Category category) {
        try {
            st.executeQuery("INSERT INTO category VALUES('" + category.getCategoryId() + "', '" + category.getCategoryName() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean deleteCategory(String categoryId) {
        try {
            st.executeQuery("DELETE FROM category WHERE category_id='" + categoryId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean updateCategory(Category category) {
        try {
            st.executeQuery("UPDATE category SET category_name='" + category.getCategoryName() + "' WHERE category_id = '" + category.getCategoryId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Category> getAllCategories() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM category");
            ArrayList<Category> categoryList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                Category category = new Category(rs.getString("CATEGORY_ID"), rs.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CategoryJDBCOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
