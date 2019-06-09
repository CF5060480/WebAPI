
package com.atossyntel.connection;
import com.atossyntel.entities.EmployeeTakeModule;
import com.atossyntel.entities.InstructorTeachBatch;
import java.util.ArrayList;

/*treating employeeId, moduleId as primary key */
public interface InstructorTeachBatchJDBCInterface {
    // Note the primary key is the whole 4 attributes in the table 
    public InstructorTeachBatch getInsTchBat(String userId, String batchId);
    public boolean deleteInsTchBat(String userId, String batchId);
    public ArrayList<InstructorTeachBatch> getAllInsTchBat();
    public boolean addInsTchBat(InstructorTeachBatch InsTchBat);
}
