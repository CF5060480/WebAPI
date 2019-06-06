package com.atossyntel.entities;

public class User {
	private String userId;
	private String password;
	private String isAdmin;
		
	public User() {
		super();
		this.userId = "";
		this.password="";
		this.isAdmin="";
	}
	public User(String userId, String password, String isAdmin) {
		super();
		this.userId = userId;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
