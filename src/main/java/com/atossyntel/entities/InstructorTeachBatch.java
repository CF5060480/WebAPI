package com.atossyntel.entities;

public class InstructorTeachBatch {
    private String batchId;
    private String userId;

    public InstructorTeachBatch(String batchId, String userId) {
        this.batchId = batchId;
        this.userId = userId;
    }
    
    public InstructorTeachBatch() {
        this.batchId = "";
        this.userId = "";
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    
}
