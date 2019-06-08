/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atossyntel.entities;

public class Module {
  	
        private String moduleId;
	private String moduleName; 
        private String categoryId;
	private String streamId;
        
    public Module() {
        this.moduleId = "N/A";
        this.moduleName = "N/A";
        this.categoryId = "N/A";
        this.streamId = "N/A";
    }

    public Module(String moduleId, String moduleName, String categoryId, String streamId) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.categoryId = categoryId;
        this.streamId = streamId;
    }
        
        

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    @Override
    public String toString() {
        return "Module{" + "moduleId=" + moduleId + ", moduleName=" + moduleName + ", categoryId=" + categoryId + ", streamId=" + streamId + '}';
    }
        
        
	
	
}

