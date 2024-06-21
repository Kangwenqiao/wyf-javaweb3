package com.mw.education.controller;

import com.mw.education.domain.Course;
import com.mw.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public int addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public int updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        course.setId(id);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/{id}")
    public int deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourseById(id);
    }

    @GetMapping("/details/{courseId}")
    public Course getCourseDetailsById(@PathVariable Integer courseId) {
        return courseService.getCourseById(courseId);
    }
}
