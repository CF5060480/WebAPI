package com.atossyntel.connection;

import com.atossyntel.entities.EmployeeTakeModule;
import java.util.ArrayList;

public class EmployeeTakeModulesDAO implements EmployeeTakeModulesJDBCInterface {

    @Override
    public EmployeeTakeModule getEmployeeTakeModule(String moduleId, String empId, String batchId, String scores) {
        EmployeeTakeModuleJDBCOps dbObj = new EmployeeTakeModuleJDBCOps();
        try {
            EmployeeTakeModule temp = dbObj.getEmployeeTakeModule(empId, moduleId, batchId, scores);
            System.out.println("EmployeeTakeModule Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new EmployeeTakeModule();
        }
    }

    @Override
    public boolean deleteEmployeeTakeModule(String moduleId, String empId, String batchId) {
        EmployeeTakeModuleJDBCOps dbObj = new EmployeeTakeModuleJDBCOps();
        boolean deleted = dbObj.deleteEmployeeTakeModule(moduleId, empId, batchId);
        if (deleted == true) {
            System.out.println("EmployeeTakeModule Successfully deleted");
        } else {
            System.out.println("EmployeeTakeModule not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        EmployeeTakeModuleJDBCOps dbObj = new EmployeeTakeModuleJDBCOps();
        boolean updated = dbObj.updateEmployeeTakeModule(empTakeMo);
        if (updated == true) {
            System.out.println("EmployeeTakeModule successfully updated");
        } else {
            System.out.println("EmployeeTakeModule not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<EmployeeTakeModule> getAllEmployeeTakeModule() {
        EmployeeTakeModuleJDBCOps dbObj = new EmployeeTakeModuleJDBCOps();
        try {
            ArrayList<EmployeeTakeModule> empTakeMoList = dbObj.getAllEmployeeTakeModules();
            System.out.println("List of EmployeeTakeModule retrieved:" + empTakeMoList);
            return empTakeMoList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addEmployeeTakeModule(EmployeeTakeModule empTakeMo) {
        EmployeeTakeModuleJDBCOps dbObj = new EmployeeTakeModuleJDBCOps();
        boolean added = dbObj.addEmployeeTakeModule(empTakeMo);
        if (added == true) {
            System.out.println("EmployeeTakeModule Successfully added");
        } else {
            System.out.println("EmployeeTakeModule creation failed...");
        }
        return added;
    }
}
