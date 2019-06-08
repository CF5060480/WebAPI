package com.atossyntel.connection;
import com.atossyntel.entities.Category;
import java.util.ArrayList;

public interface CategoryJDBCInterface {
    public Category getCategory(String categoryId);
    public boolean deleteCategory(String categoryId);
    public boolean updateCategory(Category category);
    public ArrayList<Category> getAllCategories();
    public boolean addCategory(Category category);
}
