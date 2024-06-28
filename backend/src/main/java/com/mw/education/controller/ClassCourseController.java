package com.mw.education.controller;

import com.mw.education.domain.ClassCourse;
import com.mw.education.service.ClassCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器类，负责处理与班级课程相关的HTTP请求。
 */
@RestController
@RequestMapping("/classCourse")
public class ClassCourseController {

    /**
     * 自动注入ClassCourseService实例，用于处理业务逻辑。
     */
    @Autowired
    private ClassCourseService classCourseService;

    /**
     * 添加一个新的班级课程。
     *
     * @param classCourse 包含新班级课程信息的实体。
     * @return 返回插入操作的影响行数。
     */
    @PostMapping("/add")
    public int addClassCourse(@RequestBody ClassCourse classCourse) {
        return classCourseService.insert(classCourse);
    }

    /**
     * 根据课程ID获取一个班级课程的详细信息。
     *
     * @param id 班级课程的唯一标识。
     * @return 返回对应的班级课程实体。
     */
    @GetMapping("/get/{id}")
    public ClassCourse getClassCourse(@PathVariable Integer id) {
        return classCourseService.selectByPrimaryKey(id);
    }

    /**
     * 获取所有班级课程的信息。
     *
     * @return 返回所有班级课程的列表。
     */
    @GetMapping("/all")
    public List<ClassCourse> getAllClassCourses() {
        return classCourseService.selectAll();
    }

    /**
     * 更新一个班级课程的信息。
     *
     * @param classCourse 包含更新后信息的班级课程实体。
     * @return 返回更新操作的影响行数。
     */
    @PutMapping("/update")
    public int updateClassCourse(@RequestBody ClassCourse classCourse) {
        return classCourseService.updateByPrimaryKey(classCourse);
    }

    /**
     * 根据课程ID删除一个班级课程。
     *
     * @param id 待删除班级课程的唯一标识。
     * @return 返回删除操作的影响行数。
     */
    @DeleteMapping("/delete/{id}")
    public int deleteClassCourse(@PathVariable Integer id) {
        return classCourseService.deleteByPrimaryKey(id);
    }

    /**
     * 根据班级ID获取该班级的所有课程信息。
     *
     * @param classId 班级的唯一标识。
     * @return 返回该班级对应的课程列表。
     */
    // 新增方法
    @GetMapping("/getByClassId/{classId}")
    public List<ClassCourse> getClassCoursesByClassId(@PathVariable Integer classId) {
        return classCourseService.selectByClassId(classId);
    }
}
