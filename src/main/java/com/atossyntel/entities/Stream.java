package com.atossyntel.entities;

public class Stream {
    private String streamId;
    private String streamName;
    
    public Stream(){
        super();
        this.streamId = "";
        this.streamName = "";
    }
    
    public Stream(String id, String n){
        super();
        this.streamId = id;
        this.streamName = n;
    }
    
    public void setStreamId(String id){
        this.streamId = id;
    }
    
    public void setStreamName(String n){
        this.streamName = n;
    }
    
    public String getStreamId(){
        return this.streamId;
    }
    
    public String getStreamName(){
        return this.streamName;
    }
}
