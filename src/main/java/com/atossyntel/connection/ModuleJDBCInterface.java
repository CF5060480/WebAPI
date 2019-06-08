
package com.atossyntel.connection;
import com.atossyntel.entities.Module;
import java.util.ArrayList;

public interface ModuleJDBCInterface {
        public Module getModule(String moduleId);
	public boolean deleteModule(String moduleId);
	public boolean updateModule(Module module);
	public ArrayList<Module> getAllModules();
	public boolean addModule(Module module);
}
