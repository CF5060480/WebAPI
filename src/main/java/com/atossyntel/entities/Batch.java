package com.atossyntel.entities;

public class Batch {
    private String batchId;
    private String startDate;
    private String endDate;
    private String streamId;
    private String city;
    private String country;
    
    public Batch(){
        super();
        this.batchId = "";
        this.startDate = "";
        this.endDate = "";
        this.streamId = "";
        this.city = "";
        this.country = "";
    }
    
    public Batch(String id, String start, String end, String stream, String country, String city){
        super();
        this.batchId = id;
        this.startDate = start;
        this.endDate = end;
        this.streamId = stream;
        this.city = city;
        this.country = country;
    }

    public void setBatchId(String id){
        this.batchId = id;
    }

    public void setStartDate(String start){
        this.startDate = start;
    }

    public void setEndDate(String end){
        this.endDate = end;
    }
    
    public void setStreamid(String stream){
        this.streamId = stream;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public String getBatchId(){
        return this.batchId;
    }
    
    public String getStartDate(){
        return this.startDate;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
    
    public String getStreamId(){
        return this.streamId;
    }
    
    public String getCity(){
        return this.city;
    }
     
    public String getCountry(){
        return this.country;
    }
}
