
package com.atossyntel.connection;
import com.atossyntel.entities.EmployeeTakeModule;
import java.util.ArrayList;

/*treating employeeId, moduleId as primary key */
public interface EmployeeTakeModulesJDBCInterface {
    // Note the primary key is the whole 4 attributes in the table 
    public EmployeeTakeModule getEmployeeTakeModule(String moduleId,String empId,String batchId, String scores);
    public boolean deleteEmployeeTakeModule(String moduleId,String empId, String batchId);
    public boolean updateEmployeeTakeModule(EmployeeTakeModule empTakeMo);
    public ArrayList<EmployeeTakeModule> getAllEmployeeTakeModule();
    public boolean addEmployeeTakeModule(EmployeeTakeModule empTakeMo);
}
