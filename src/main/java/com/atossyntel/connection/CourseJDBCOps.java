package com.atossyntel.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.atossyntel.entities.Course;

public class CourseJDBCOps {
    private Connection con;
    private Statement st;

    public CourseJDBCOps() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
            st = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Course getCourse(String courseId) {
        try {
            System.out.println(courseId);
            ResultSet rs = st.executeQuery("SELECT * FROM courses WHERE course_id= " + "'" + courseId + "'");
            Course course;
            while (rs.next()) {
                course = new Course(rs.getString("COURSE_ID"), rs.getString("COURSE_NAME"), rs.getString("MODULE_ID"));
                return course;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new Course();
        }
        return new Course();
    }

    public boolean addCourse(Course course) {
        try {
            st.executeQuery("INSERT INTO courses VALUES('" + course.getCourseId() + "', '" + course.getCourseName() + "', '" + course.getModuleId() + "')");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteCourse(String courseId) {
        try {
            st.executeQuery("DELETE FROM courses WHERE course_id='" + courseId + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateCourse(Course course) {
        try {
            st.executeQuery("UPDATE courses SET course_name='" + course.getCourseName() + "', module_id='" + course.getModuleId() + "' WHERE course_id = '" + course.getCourseId() + "'");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public ArrayList<Course> getAllCourses() {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM courses");
            ArrayList<Course> courseList = new ArrayList<>();
            System.out.println(rs.toString());
            while (rs.next()) {
                Course course = new Course(rs.getString("COURSE_ID"), rs.getString("COURSE_NAME"), rs.getString("MODULE_ID"));
                courseList.add(course);
            }
            return courseList;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }
}
