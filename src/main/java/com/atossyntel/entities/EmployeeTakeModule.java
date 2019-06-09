
package com.atossyntel.entities;

public class EmployeeTakeModule {
    private String mduleId;
    private String employeeId;
    private String batchId;
    private double scores;
    
    
    public EmployeeTakeModule() {
        this.mduleId = "N/A";
        this.employeeId = "N/A";
        this.batchId = "N/A";
        this.scores = 0;
    }

    public EmployeeTakeModule(String mduleId, String employeeId, String batchId, double scores) {
        this.mduleId = mduleId;
        this.employeeId = employeeId;
        this.batchId = batchId;
        this.scores = scores;
    }

    public String getMduleId() {
        return mduleId;
    }

    public void setMduleId(String mduleId) {
        this.mduleId = mduleId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "EmployeeTakeModule{" + "mduleId=" + mduleId + ", employeeId=" + employeeId + ", batchId=" + batchId + ", scores=" + scores + '}';
    } 

    
    
}
