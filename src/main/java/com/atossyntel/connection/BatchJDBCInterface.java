package com.atossyntel.connection;
import com.atossyntel.entities.Batch;
import java.util.ArrayList;

public interface BatchJDBCInterface {
    public Batch getBatch(String batchId);
    public boolean deleteBatch(String batchId);
    public boolean updateBatch(Batch batch);
    public ArrayList<Batch> getAllBatches();
    public boolean addBatch(Batch batch);
}
