package com.atossyntel.connection;
import com.atossyntel.entities.Category;
import java.util.ArrayList;

public class CategoryDAO implements CategoryJDBCInterface {

    @Override
    public Category getCategory(String categoryId) {
        CategoryJDBCOps dbObj = new CategoryJDBCOps();
        try {
            Category temp = dbObj.getCategory(categoryId);
            System.out.println("Category Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Category();
        }
    }

    @Override
    public boolean deleteCategory(String categoryId) {
        CategoryJDBCOps dbObj = new CategoryJDBCOps();
        boolean deleted = dbObj.deleteCategory(categoryId);
        if (deleted == true) {
            System.out.println("Category Successfully deleted");
        } else {
            System.out.println("Category not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateCategory(Category category) {
        CategoryJDBCOps dbObj = new CategoryJDBCOps();
        boolean updated = dbObj.updateCategory(category);
        if (updated == true) {
            System.out.println("Category successfully updated");
        } else {
            System.out.println("Category not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        CategoryJDBCOps dbObj = new CategoryJDBCOps();
        try {
            ArrayList<Category> categoryList = dbObj.getAllCategories();
            System.out.println("List of Categories retrieved:" + categoryList);
            return categoryList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addCategory(Category category) {
        CategoryJDBCOps dbObj = new CategoryJDBCOps();
        boolean added = dbObj.addCategory(category);
        if (added == true) {
            System.out.println("Category Successfully added");
        } else {
            System.out.println("Category creation failed...");
        }
        return added;
    }
}
