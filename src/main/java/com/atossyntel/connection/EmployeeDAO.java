/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atossyntel.connection;

import com.atossyntel.entities.Employee;
import java.util.ArrayList;

/**
 *
 * @author syntel
 */
public class EmployeeDAO implements EmployeeJDBCInterface {

    @Override
    public Employee getEmployee(String empId) {
        EmployeeJDBCOps dbObj = new EmployeeJDBCOps();
        try {
            Employee temp = dbObj.getEmployee(empId);
            System.out.println("Employee Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Employee();
        }
    }

    @Override
    public boolean deleteEmployee(String empId) {
        EmployeeJDBCOps dbObj = new EmployeeJDBCOps();
        boolean deleted = dbObj.deleteEmployee(empId);
        if (deleted == true) {
            System.out.println("Employee Successfully deleted");
        } else {
            System.out.println("Employee not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateEmployee(Employee emp) {
        EmployeeJDBCOps dbObj = new EmployeeJDBCOps();
        boolean updated = dbObj.updateEmployee(emp);
        if (updated == true) {
            System.out.println("Employee successfully updated");
        } else {
            System.out.println("Employee not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        EmployeeJDBCOps dbObj = new EmployeeJDBCOps();
        try {
            ArrayList<Employee> empList = dbObj.getAllEmployees();
            System.out.println("List of Employees retrieved:" + empList);
            return empList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addEmployee(Employee emp) {
               EmployeeJDBCOps dbObj = new EmployeeJDBCOps();
               boolean added = dbObj.addEmployee(emp);
               if(added == true)
                   System.out.println("Employee Successfully added");                 
               else
                   System.out.println("Employee creation failed...");
               return added;    }

}
