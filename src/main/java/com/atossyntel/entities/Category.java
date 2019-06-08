package com.atossyntel.entities;

public class Category {
    private String categoryId;
    private String categoryName;
    
    public Category(){
        super();
        this.categoryId = "";
        this.categoryName = "";
    }
    
    public Category(String id, String n){
        super();
        this.categoryId = id;
        this.categoryName = n;
    }
    
    public void setCategoryId(String id){
        this.categoryId = id;
    }
    
    public void setCategoryName(String n){
        this.categoryName = n;
    }
    
    public String getCategoryId(){
        return this.categoryId;
    }
    
    public String getCategoryName(){
        return this.categoryName;
    }
}