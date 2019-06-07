package com.atossyntel.connection;

import com.atossyntel.entities.User;
import java.util.ArrayList;

public class UserDAO implements UserJDBCInterface{

	@Override
	public User getUser(String userId) {
		UserJDBCOps dbObj = new UserJDBCOps();
                try {
		User temp = dbObj.getUser(userId);
                System.out.println("User Retrieved: " + temp);
		return temp;
                }catch(Exception e) {
                    System.out.println(e.getMessage());
                    return new User();
                }
	}

	@Override
	public boolean deleteUser(String userId) {
            UserJDBCOps dbObj = new UserJDBCOps();
            boolean deleted = dbObj.deleteUser(userId);
            if(deleted==true)
                System.out.println("User Successfully deleted");
            else
                System.out.println("User not deleted...");
            return deleted;
	}

	@Override
	public boolean updateUser(User user) {
            UserJDBCOps dbObj = new UserJDBCOps();
            boolean updated = dbObj.updateUser(user);
            if(updated==true) 
                System.out.println("User successfully updated");
            else
                System.out.println("User not updated...");
            return updated;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		UserJDBCOps dbObj = new UserJDBCOps();
                try {
                ArrayList<User> userList = dbObj.getAllUsers();
                System.out.println("List of users retrieved:" + userList);
                return userList;
                }catch(Exception e) {
                 System.out.println(e.getMessage());
                 return new ArrayList<>();
                }
	}

	@Override
	public boolean addUser(User user) {
               UserJDBCOps dbObj = new UserJDBCOps();
               boolean added = dbObj.addUser(user);
               if(added == true)
                   System.out.println("User Successfully added");                 
               else
                   System.out.println("User creation failed...");
               return added;
	}

}
