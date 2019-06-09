package com.atossyntel.connection;

import com.atossyntel.entities.EmployeeTakeModule;
import com.atossyntel.entities.InstructorTeachBatch;
import java.util.ArrayList;

public class InstructorTeachBatchDAO implements InstructorTeachBatchJDBCInterface {

    @Override
    public InstructorTeachBatch getInsTchBat(String userId, String batchId) {
        InstructorTeachBatchJDBCOps dbObj = new InstructorTeachBatchJDBCOps();
        try {
            InstructorTeachBatch temp = dbObj.getInsTchBat(userId, batchId);
            System.out.println("InstructorTeachBatch Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new InstructorTeachBatch();
        }
    }

    @Override
    public boolean deleteInsTchBat(String userId, String batchId) {
        InstructorTeachBatchJDBCOps dbObj = new InstructorTeachBatchJDBCOps();
        boolean deleted = dbObj.deleteInsTchBat(userId, batchId);
        if (deleted == true) {
            System.out.println("InstructorTeachBatch Successfully deleted");
        } else {
            System.out.println("InstructorTeachBatch not deleted...");
        }
        return deleted;
    }

    @Override
    public ArrayList<InstructorTeachBatch> getAllInsTchBat() {
        InstructorTeachBatchJDBCOps dbObj = new InstructorTeachBatchJDBCOps();
        try {
            ArrayList<InstructorTeachBatch> insTchBatList = dbObj.getAllInsTchBat();
            System.out.println("List of InstructorTeachBatch retrieved:" + insTchBatList);
            return insTchBatList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addInsTchBat(InstructorTeachBatch insTchBat) {
        InstructorTeachBatchJDBCOps dbObj = new InstructorTeachBatchJDBCOps();
        boolean added = dbObj.addInsTchBat(insTchBat);
        if (added == true) {
            System.out.println("InstructorTeachBatch Successfully added");
        } else {
            System.out.println("InstructorTeachBatch creation failed...");
        }
        return added;
    }
}
