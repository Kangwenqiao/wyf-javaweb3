package com.mw.education.controller;

import com.mw.education.domain.Course;
import com.mw.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器，负责处理与课程相关的HTTP请求。
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    /**
     * 自动注入课程服务，用于处理课程相关的业务逻辑。
     */
    @Autowired
    private CourseService courseService;

    /**
     * 根据课程ID获取课程信息。
     *
     * @param id 课程ID
     * @return 对应ID的课程信息
     */
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Integer id) {
        return courseService.getCourseById(id);
    }

    /**
     * 获取所有课程信息。
     *
     * @return 所有课程的列表
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * 添加新课程。
     *
     * @param course 新课程的信息
     * @return 添加课程后的ID
     */
    @PostMapping
    public int addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    /**
     * 根据课程ID更新课程信息。
     *
     * @param id      课程ID
     * @param course  更新后的课程信息
     * @return 更新操作的影响行数
     */
    @PutMapping("/{id}")
    public int updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        course.setId(id);
        return courseService.updateCourse(course);
    }

    /**
     * 根据课程ID删除课程。
     *
     * @param id 课程ID
     * @return 删除操作的影响行数
     */
    @DeleteMapping("/{id}")
    public int deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourseById(id);
    }

    /**
     * 根据课程ID获取课程详细信息。
     *
     * @param courseId 课程ID
     * @return 对应ID的课程详细信息
     */
    @GetMapping("/details/{courseId}")
    public Course getCourseDetailsById(@PathVariable Integer courseId) {
        return courseService.getCourseById(courseId);
    }
}
