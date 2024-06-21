package com.mw.education.controller;

import com.mw.education.domain.ClassCourse;
import com.mw.education.service.ClassCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classCourse")
public class ClassCourseController {

    @Autowired
    private ClassCourseService classCourseService;

    @PostMapping("/add")
    public int addClassCourse(@RequestBody ClassCourse classCourse) {
        return classCourseService.insert(classCourse);
    }

    @GetMapping("/get/{id}")
    public ClassCourse getClassCourse(@PathVariable Integer id) {
        return classCourseService.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<ClassCourse> getAllClassCourses() {
        return classCourseService.selectAll();
    }

    @PutMapping("/update")
    public int updateClassCourse(@RequestBody ClassCourse classCourse) {
        return classCourseService.updateByPrimaryKey(classCourse);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteClassCourse(@PathVariable Integer id) {
        return classCourseService.deleteByPrimaryKey(id);
    }

    // 新增方法
    @GetMapping("/getByClassId/{classId}")
    public List<ClassCourse> getClassCoursesByClassId(@PathVariable Integer classId) {
        return classCourseService.selectByClassId(classId);
    }
}
