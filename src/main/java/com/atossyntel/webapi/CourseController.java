package com.atossyntel.webapi;

import com.atossyntel.connection.CourseDAO;
import com.atossyntel.entities.Course;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CourseController {

    @CrossOrigin
    @RequestMapping(value = "/getCourse", method = RequestMethod.POST)
    public String display(@RequestParam Map<String, String> data) throws JSONException {
        CourseDAO getCourse = new CourseDAO();
        Course course = getCourse.getCourse(data.get("courseId"));
        JSONObject json = new JSONObject();
        json.put("courseId", course.getCourseId());
        json.put("courseName", course.getCourseName());
        json.put("moduleId", course.getModuleId());
        System.out.println(json.toString());
        return json.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/createCourse", method = RequestMethod.POST)
    public String process(@RequestParam Map<String, String> data) {
        CourseDAO createCourse = new CourseDAO();
        Course course = new Course(data.get("courseId"), data.get("courseName"), data.get("moduleId"));
        boolean status = createCourse.addCourse(course);
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public String delete(@RequestParam Map<String, String> data) {
        CourseDAO deleteCourse = new CourseDAO();
        boolean status = deleteCourse.deleteCourse(data.get("courseId"));
        JSONObject jObj = new JSONObject();
        jObj.put("status", status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
    public String update(@RequestParam Map<String, String> data) {
        CourseDAO updateCourse = new CourseDAO();
        Course course = new Course(data.get("courseId"), data.get("courseName"), data.get("moduleId"));
        boolean status = updateCourse.updateCourse(course);
        JSONObject jObj = new JSONObject();
        jObj.put("status",status);
        return jObj.toString();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
    public String getAll() {      
        CourseDAO getAllCourses = new CourseDAO();
        ArrayList<Course> courseList = getAllCourses.getAllCourses();
        JSONArray jList = new JSONArray();
        for(Course c: courseList) {
            JSONObject jObj = new JSONObject();
            jObj.put("courseId", c.getCourseId());
            jObj.put("courseName", c.getCourseName());
            jObj.put("moduleId", c.getModuleId());
            jList.put(jObj);
        }
        System.out.println(jList.toString());
        return jList.toString();
    }
}
