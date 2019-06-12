package com.atossyntel.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.atossyntel.entities.Course;

public class CourseJDBCOps {
    private ConnectionService cs;
    private Connection con;
    private PreparedStatement st;

    public CourseJDBCOps() {
        cs = new ConnectionService();
        con = cs.openConnection();
    }

    public Course getCourse(String courseId) {
        String selectStmt = "SELECT * FROM courses WHERE course_id = ?";
        Course course = new Course();
        try {
            st = con.prepareStatement(selectStmt);
            st.setString(1, courseId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                course = new Course(rs.getString("COURSE_ID"), rs.getString("COURSE_NAME"), rs.getString("MODULE_ID"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return course;
    }

    public boolean addCourse(Course course) {
        String insertStmt = "INSERT INTO courses VALUES(?, ?, ?)";
        int retval = 0;
        try {
            st = con.prepareStatement(insertStmt);
            st.setString(1, course.getCourseId());
            st.setString(2, course.getCourseName());
            st.setString(3, course.getModuleId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean deleteCourse(String courseId) {
        String deleteStmt = "DELETE FROM courses WHERE course_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(deleteStmt);
            st.setString(1, courseId);
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public boolean updateCourse(Course course) {
        String updateStmt = "UPDATE courses SET course_name = ?, module_id = ? WHERE course_id = ?";
        int retval = 0;
        try {
            st = con.prepareStatement(updateStmt);
            st.setString(1, course.getCourseName());
            st.setString(2, course.getModuleId());
            st.setString(3, course.getCourseId());
            retval = st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return (retval != 0);
    }

    public ArrayList<Course> getAllCourses() {
        String getAllStmt = "SELECT * FROM courses";
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            st = con.prepareStatement(getAllStmt);
            ResultSet rs = st.executeQuery();
            System.out.println(rs.toString());
            while (rs.next()) {
                Course course = new Course(rs.getString("COURSE_ID"), rs.getString("COURSE_NAME"), rs.getString("MODULE_ID"));
                courseList.add(course);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cs.closeConnection(con);
        return courseList;
    }
}
