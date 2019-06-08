package com.atossyntel.entities;

public class Course {
    private String courseId;
    private String courseName;
    private String moduleId;

    public Course(String courseId, String courseName, String moduleId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.moduleId = moduleId;
    }
    
    public Course() {
        this.courseId = "";
        this.courseName = "";
        this.moduleId = "";
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }    
}
