package com.mw.education.controller;

import com.mw.education.domain.Course;
import com.mw.education.domain.Teacher;
import com.mw.education.domain.TeacherCourse;
import com.mw.education.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师课程控制器，负责处理与教师课程相关的HTTP请求。
 */
@RestController
@RequestMapping("/teacherCourse")
public class TeacherCourseController {

    @Autowired
    private TeacherCourseService teacherCourseService;

    /**
     * 添加教师课程关系。
     *
     * @param teacherCourse 包含教师和课程信息的数据对象。
     * @return 返回插入操作的影响行数。
     */
    @PostMapping("/add")
    public int addTeacherCourse(@RequestBody TeacherCourse teacherCourse) {
        return teacherCourseService.insert(teacherCourse);
    }

    /**
     * 根据ID获取教师课程关系。
     *
     * @param id 教师课程的唯一标识。
     * @return 返回匹配ID的教师课程对象。
     */
    @GetMapping("/get/{id}")
    public TeacherCourse getTeacherCourse(@PathVariable Integer id) {
        return teacherCourseService.selectByPrimaryKey(id);
    }

    /**
     * 获取所有教师课程关系。
     *
     * @return 返回所有教师课程的数据列表。
     */
    @GetMapping("/all")
    public List<TeacherCourse> getAllTeacherCourses() {
        return teacherCourseService.selectAll();
    }

    /**
     * 更新教师课程关系。
     *
     * @param teacherCourse 包含待更新教师课程信息的数据对象。
     * @return 返回更新操作的影响行数。
     */
    @PutMapping("/update")
    public int updateTeacherCourse(@RequestBody TeacherCourse teacherCourse) {
        return teacherCourseService.updateByPrimaryKey(teacherCourse);
    }

    /**
     * 根据ID删除教师课程关系。
     *
     * @param id 教师课程的唯一标识。
     * @return 返回删除操作的影响行数。
     */
    @DeleteMapping("/delete/{id}")
    public int deleteTeacherCourse(@PathVariable Integer id) {
        return teacherCourseService.deleteByPrimaryKey(id);
    }

    /**
     * 根据教师ID获取其教授的所有课程。
     *
     * @param teacherId 教师的唯一标识。
     * @return 返回匹配教师ID的课程列表。
     */
    @GetMapping("/teacher/{teacherId}/courses")
    public List<Course> getCoursesByTeacherId(@PathVariable int teacherId) {
        return teacherCourseService.findCoursesByTeacherId(teacherId);
    }

    /**
     * 根据课程ID获取教授该课程的所有教师。
     *
     * @param courseId 课程的唯一标识。
     * @return 返回匹配课程ID的教师列表。
     */
    @GetMapping("/course/{courseId}/teachers")
    public List<Teacher> getTeachersByCourseId(@PathVariable Integer courseId) {
        return teacherCourseService.getTeachersByCourseId(courseId);
    }

    /**
     * 根据教师ID获取其所有的教师课程关系。
     *
     * @param teacherId 教师的唯一标识。
     * @return 返回匹配教师ID的教师课程列表。
     */
    @GetMapping("/teacher/{teacherId}/teacherCourses")
    public List<TeacherCourse> getTeacherCoursesByTeacherId(@PathVariable int teacherId) {
        return teacherCourseService.getByTeacherId(teacherId);
    }
}
