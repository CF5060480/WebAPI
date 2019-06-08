package com.atossyntel.webapi;

import com.atossyntel.connection.CategoryDAO;
import com.atossyntel.entities.Category;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CategoryController {

    @CrossOrigin
    @RequestMapping(value = "/getCategory", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        CategoryDAO getcategory = new CategoryDAO();
        Category category = getcategory.getCategory(data.get("categoryId"));
        JSONObject json = new JSONObject();
        json.put("categoryId", category.getCategoryId());
        json.put("categoryName", category.getCategoryName());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        CategoryDAO createcategory = new CategoryDAO();
        Category category = new Category(data.get("categoryId"), data.get("categoryName"));
        boolean status = createcategory.addCategory(category);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        CategoryDAO deletecategory = new CategoryDAO();
        boolean status = deletecategory.deleteCategory(data.get("categoryId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        CategoryDAO updatecategory = new CategoryDAO();
        Category category = new Category(data.get("categoryId"), data.get("categoryName"));
        boolean status = updatecategory.updateCategory(category);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCategories", method = RequestMethod.GET)
    public String getAll() {
        CategoryDAO getcategories = new CategoryDAO();
        ArrayList<Category> categoryList = getcategories.getAllCategories();
        JSONArray jList = new JSONArray();
        for(Category c: categoryList) {
            JSONObject jObj = new JSONObject();
            jObj.put("categoryId", c.getCategoryId());
            jObj.put("categoryName", c.getCategoryName());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();
    }
}
