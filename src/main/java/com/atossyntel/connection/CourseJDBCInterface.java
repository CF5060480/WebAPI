package com.atossyntel.connection;
import com.atossyntel.entities.Course;
import java.util.ArrayList;

public interface CourseJDBCInterface {
    public Course getCourse(String courseId);
    public boolean deleteCourse(String courseId);
    public boolean updateCourse(Course course);
    public ArrayList<Course> getAllCourses();
    public boolean addCourse(Course course);
}
