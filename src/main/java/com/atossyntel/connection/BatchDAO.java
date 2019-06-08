package com.atossyntel.connection;
import com.atossyntel.entities.Batch;
import java.util.ArrayList;

public class BatchDAO implements BatchJDBCInterface {

    @Override
    public Batch getBatch(String batchId) {
        BatchJDBCOps dbObj = new BatchJDBCOps();
        try {
            Batch temp = dbObj.getBatch(batchId);
            System.out.println("Batch Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Batch();
        }
    }

    @Override
    public boolean deleteBatch(String batchId) {
        BatchJDBCOps dbObj = new BatchJDBCOps();
        boolean deleted = dbObj.deleteBatch(batchId);
        if (deleted == true) {
            System.out.println("Batch Successfully deleted");
        } else {
            System.out.println("Batch not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateBatch(Batch batch) {
        BatchJDBCOps dbObj = new BatchJDBCOps();
        boolean updated = dbObj.updateBatch(batch);
        if (updated == true) {
            System.out.println("Batch successfully updated");
        } else {
            System.out.println("Batch not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Batch> getAllBatches() {
        BatchJDBCOps dbObj = new BatchJDBCOps();
        try {
            ArrayList<Batch> batchList = dbObj.getAllBatches();
            System.out.println("List of Batches retrieved:" + batchList);
            return batchList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addBatch(Batch batch) {
        BatchJDBCOps dbObj = new BatchJDBCOps();
        boolean added = dbObj.addBatch(batch);
        if (added == true) {
            System.out.println("Batch Successfully added");
        } else {
            System.out.println("Batch creation failed...");
        }
        return added;
    }
}
