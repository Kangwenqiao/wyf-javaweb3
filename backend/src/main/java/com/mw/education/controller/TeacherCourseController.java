package com.mw.education.controller;

import com.mw.education.domain.Course;
import com.mw.education.domain.Teacher;
import com.mw.education.domain.TeacherCourse;
import com.mw.education.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacherCourse")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    @PostMapping("/add")
    public int addTeacherCourse(@RequestBody TeacherCourse teacherCourse) {
        return teacherCourseService.insert(teacherCourse);
    }

    @GetMapping("/get/{id}")
    public TeacherCourse getTeacherCourse(@PathVariable Integer id) {
        return teacherCourseService.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<TeacherCourse> getAllTeacherCourses() {
        return teacherCourseService.selectAll();
    }

    @PutMapping("/update")
    public int updateTeacherCourse(@RequestBody TeacherCourse teacherCourse) {
        return teacherCourseService.updateByPrimaryKey(teacherCourse);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteTeacherCourse(@PathVariable Integer id) {
        return teacherCourseService.deleteByPrimaryKey(id);
    }
    @GetMapping("/teacher/{teacherId}/courses")
    public List<Course> getCoursesByTeacherId(@PathVariable int teacherId) {
        return teacherCourseService.findCoursesByTeacherId(teacherId);
    }

    @GetMapping("/course/{courseId}/teachers")
    public List<Teacher> getTeachersByCourseId(@PathVariable Integer courseId) {
        return teacherCourseService.getTeachersByCourseId(courseId);
    }

    // 新添加的方法
    @GetMapping("/teacher/{teacherId}/teacherCourses")
    public List<TeacherCourse> getTeacherCoursesByTeacherId(@PathVariable int teacherId) {
        return teacherCourseService.getByTeacherId(teacherId);
    }
}
