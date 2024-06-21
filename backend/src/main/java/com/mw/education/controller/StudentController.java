package com.mw.education.controller;

import com.mw.education.domain.compose.StudentAndClassAndSpecialityAndCollege;
import com.mw.education.domain.Student;
import com.mw.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/{code}")
    public StudentAndClassAndSpecialityAndCollege getStudentByCode(@PathVariable String code) {
        StudentAndClassAndSpecialityAndCollege byCodeWithDetails = studentService.findByCodeWithDetails(code);
        return byCodeWithDetails;
    }

    @GetMapping
    public List<StudentAndClassAndSpecialityAndCollege> getAllStudents() {
        return studentService.findAllWithDetails();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentService.save(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        student.setId(id);
        studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.delete(id);
    }

    @GetMapping("/class/{classId}")
    public List<Student> getStudentsByClassId(@PathVariable int classId) {
        return studentService.findStudentsByClassId(classId);
    }

    @GetMapping("/classId/{studentCode}")
    public Integer getClassIdByStudentCode(@PathVariable String studentCode) {
        Student student = studentService.findByCode(studentCode);
        if (student != null) {
            return student.getClassId();
        }
        return null;
    }
}
