package com.atossyntel.webapi;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atossyntel.connection.UserDAO;
import com.atossyntel.connection.EmployeeDAO;
import com.atossyntel.entities.Employee;
import com.atossyntel.entities.User;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class EmployeeController {

    @CrossOrigin
    @RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        EmployeeDAO getEmp = new EmployeeDAO();
        Employee emp = getEmp.getEmployee(data.get("employeeId"));
        JSONObject json = new JSONObject();
        json.put("employeeId", emp.getEmployeeId());
        json.put("name", emp.getName());
        json.put("email", emp.getEmail());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        EmployeeDAO createEmp = new EmployeeDAO();
        Employee emp = new Employee(data.get("employeeId"), data.get("name"), data.get("email"));
        boolean status = createEmp.addEmployee(emp);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        EmployeeDAO deleteEmp = new EmployeeDAO();
        boolean status = deleteEmp.deleteEmployee(data.get("employeeId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        EmployeeDAO updateEmp = new EmployeeDAO();
        Employee emp = new Employee(data.get("employeeId"), data.get("name"), data.get("email"));
        boolean status = updateEmp.updateEmployee(emp);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
    public String getAll() {
        EmployeeDAO getemps = new EmployeeDAO();
        ArrayList<Employee> empList = getemps.getAllEmployees();
        System.out.println(empList.toString());
        JSONArray jList = new JSONArray();
        for(Employee e: empList) {
            JSONObject jObj = new JSONObject();
            jObj.put("employeeId", e.getEmployeeId());
            jObj.put("name", e.getName());
            jObj.put("email", e.getEmail());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();

    }
}
