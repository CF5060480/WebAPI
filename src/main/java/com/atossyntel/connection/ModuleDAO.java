package com.atossyntel.connection;

import com.atossyntel.entities.Module;
import java.util.ArrayList;

public class ModuleDAO implements ModuleJDBCInterface {

    @Override
    public Module getModule(String moduleId) {
        ModuleJDBCOps dbObj = new ModuleJDBCOps();
        try {
            Module temp = dbObj.getModule(moduleId);
            System.out.println("Module Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Module();
        }
    }

    @Override
    public boolean deleteModule(String moduleId) {
        ModuleJDBCOps dbObj = new ModuleJDBCOps();
        boolean deleted = dbObj.deleteModule(moduleId);
        if (deleted == true) {
            System.out.println("module Successfully deleted");
        } else {
            System.out.println("module not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateModule(Module module) {
        ModuleJDBCOps dbObj = new ModuleJDBCOps();
        boolean updated = dbObj.updateModule(module);
        if (updated == true) {
            System.out.println("Module successfully updated");
        } else {
            System.out.println("Module not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Module> getAllModules() {
        ModuleJDBCOps dbObj = new ModuleJDBCOps();
        try {
            ArrayList<Module> moduleList = dbObj.getAllModules();
            System.out.println("List of module retrieved:" + moduleList);
            return moduleList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addModule(Module module) {
        ModuleJDBCOps dbObj = new ModuleJDBCOps();
        boolean added = dbObj.addModule(module);
        if (added == true) {
            System.out.println("Module Successfully added");
        } else {
            System.out.println("Module creation failed...");
        }
        return added;
    }
}
