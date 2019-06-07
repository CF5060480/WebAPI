package com.atossyntel.webapi;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atossyntel.connection.UserDAO;
import com.atossyntel.entities.User;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    @CrossOrigin
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        UserDAO getuser = new UserDAO();
        User user = getuser.getUser(data.get("userId"));
        JSONObject json = new JSONObject();
        json.put("userId", user.getUserId());
        json.put("password", user.getPassword());
        json.put("isAdmin", user.getIsAdmin());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        UserDAO createuser = new UserDAO();
        User user = new User(data.get("userId"), data.get("password"), data.get("isAdmin"));
        boolean status = createuser.addUser(user);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        UserDAO deleteuser = new UserDAO();
        boolean status = deleteuser.deleteUser(data.get("userId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        UserDAO updateuser = new UserDAO();
        User user = new User(data.get("userId"), data.get("password"), data.get("isAdmin"));
        boolean status = updateuser.updateUser(user);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String getAll() {
        UserDAO getusers = new UserDAO();
        ArrayList<User> userList = getusers.getAllUsers();
        JSONArray jList = new JSONArray();
        for(User u: userList) {
            JSONObject jObj = new JSONObject();
            jObj.put("userId", u.getUserId());
            jObj.put("password", u.getPassword());
            jObj.put("isAdmin", u.getIsAdmin());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();

    }
}
