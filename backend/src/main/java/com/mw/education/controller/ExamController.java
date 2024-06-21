package com.mw.education.controller;

import com.mw.education.domain.Exam;
import com.mw.education.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/add")
    public int addExam(@RequestBody Exam exam) {
        return examService.insert(exam);
    }

    @GetMapping("/get/{id}")
    public Exam getExam(@PathVariable Integer id) {
        return examService.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<Exam> getAllExams() {
        return examService.selectAll();
    }

    @PutMapping("/update")
    public int updateExam(@RequestBody Exam exam) {
        return examService.updateByPrimaryKey(exam);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteExam(@PathVariable Integer id) {
        return examService.deleteByPrimaryKey(id);
    }


}
