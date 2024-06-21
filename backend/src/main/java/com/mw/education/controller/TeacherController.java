package com.mw.education.controller;

import com.mw.education.domain.Teacher;
import com.mw.education.domain.compose.TeacherAndCollege;
import com.mw.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public String addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "Teacher added successfully!";
    }

    @PutMapping("/update")
    public String updateTeacher(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "Teacher updated successfully!";
    }

    @GetMapping("/get/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/getJoinedCollege/{id}")
    public TeacherAndCollege getTeacherByIdJoinedCollege(@PathVariable int id) {
        return teacherService.getTeacherByIdJoinedCollege(id);
    }

    @GetMapping("/all")
    public List<TeacherAndCollege> getAllTeachers() {
        return teacherService.getAllTeachersJoinedCollege();
    }

    @GetMapping("/getByCode/{code}")
    public Teacher getTeacherByCode(@PathVariable String code) {
        return teacherService.getTeacherByCode(code);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok("Teacher deleted successfully!");
    }
}
