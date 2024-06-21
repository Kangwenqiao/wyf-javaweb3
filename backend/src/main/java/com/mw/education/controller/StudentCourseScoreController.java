package com.mw.education.controller;

import com.mw.education.domain.StudentCourseScore;
import com.mw.education.dto.StudentScoreDTO;
import com.mw.education.service.StudentCourseScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentCourseScore")
public class StudentCourseScoreController {

    @Autowired
    private StudentCourseScoreService studentCourseScoreService;

    @PostMapping("/add")
    public int addStudentCourseScore(@RequestBody StudentCourseScore studentCourseScore) {
        return studentCourseScoreService.insert(studentCourseScore);
    }

    @GetMapping("/get/{id}")
    public StudentCourseScore getStudentCourseScore(@PathVariable Integer id) {
        return studentCourseScoreService.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<StudentCourseScore> getAllStudentCourseScores() {
        return studentCourseScoreService.selectAll();
    }

    @PutMapping("/update")
    public int updateStudentCourseScore(@RequestBody StudentCourseScore studentCourseScore) {
        return studentCourseScoreService.updateByPrimaryKey(studentCourseScore);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteStudentCourseScore(@PathVariable Integer id) {
        return studentCourseScoreService.deleteByPrimaryKey(id);
    }

    @GetMapping("/course/{courseId}/students")
    public List<StudentScoreDTO> getStudentsAndScoresByCourseId(@PathVariable int courseId) {
        return studentCourseScoreService.getStudentsAndScoresByCourseId(courseId);
    }

    @GetMapping("/student/{studentCode}")
    public List<StudentCourseScore> getStudentScoresByStudentCode(@PathVariable String studentCode) {
        return studentCourseScoreService.getStudentScoresByStudentCode(studentCode);
    }
}
