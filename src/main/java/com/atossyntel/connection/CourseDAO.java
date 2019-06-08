package com.atossyntel.connection;
import com.atossyntel.entities.Category;
import com.atossyntel.entities.Course;
import java.util.ArrayList;

public class CourseDAO implements CourseJDBCInterface {

    @Override
    public Course getCourse(String courseId) {
        CourseJDBCOps dbObj = new CourseJDBCOps();
        try {
            Course temp = dbObj.getCourse(courseId);
            System.out.println("Course Retrieved: " + temp);
            return temp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Course();
        }
    }

    @Override
    public boolean deleteCourse(String courseId) {
        CourseJDBCOps dbObj = new CourseJDBCOps();
        boolean deleted = dbObj.deleteCourse(courseId);
        if (deleted == true) {
            System.out.println("Course Successfully deleted");
        } else {
            System.out.println("Course not deleted...");
        }
        return deleted;
    }

    @Override
    public boolean updateCourse(Course course) {
        CourseJDBCOps dbObj = new CourseJDBCOps();
        boolean updated = dbObj.updateCourse(course);
        if (updated == true) {
            System.out.println("Course successfully updated");
        } else {
            System.out.println("Course not updated...");
        }
        return updated;
    }

    @Override
    public ArrayList<Course> getAllCourses() {
        CourseJDBCOps dbObj = new CourseJDBCOps();
        try {
            ArrayList<Course> courseList = dbObj.getAllCourses();
            System.out.println("List of Courses retrieved:" + courseList);
            return courseList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addCourse(Course course) {
        CourseJDBCOps dbObj = new CourseJDBCOps();
        boolean added = dbObj.addCourse(course);
        if (added == true) {
            System.out.println("Course Successfully added");
        } else {
            System.out.println("Course creation failed...");
        }
        return added;
    }     
}
