package com.mw.education.controller;

import com.mw.education.domain.StudentCourseScore;
import com.mw.education.dto.StudentScoreDTO;
import com.mw.education.service.StudentCourseScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生课程成绩控制器，负责处理与学生课程成绩相关的HTTP请求。
 */
@RestController
@RequestMapping("/studentCourseScore")
public class StudentCourseScoreController {

    @Autowired
    private StudentCourseScoreService studentCourseScoreService;

    /**
     * 添加学生课程成绩。
     * @param studentCourseScore 学生课程成绩对象，包含需要添加的数据。
     * @return 返回受影响的行数，通常为1表示成功添加。
     */
    @PostMapping("/add")
    public int addStudentCourseScore(@RequestBody StudentCourseScore studentCourseScore) {
        return studentCourseScoreService.insert(studentCourseScore);
    }

    /**
     * 根据ID获取学生课程成绩。
     * @param id 学生课程成绩的唯一标识ID。
     * @return 返回匹配ID的学生课程成绩对象。
     */
    @GetMapping("/get/{id}")
    public StudentCourseScore getStudentCourseScore(@PathVariable Integer id) {
        return studentCourseScoreService.selectByPrimaryKey(id);
    }

    /**
     * 获取所有学生课程成绩。
     * @return 返回所有学生课程成绩的列表。
     */
    @GetMapping("/all")
    public List<StudentCourseScore> getAllStudentCourseScores() {
        return studentCourseScoreService.selectAll();
    }

    /**
     * 更新学生课程成绩。
     * @param studentCourseScore 学生课程成绩对象，包含需要更新的数据。
     * @return 返回受影响的行数，表示更新的成功程度。
     */
    @PutMapping("/update")
    public int updateStudentCourseScore(@RequestBody StudentCourseScore studentCourseScore) {
        return studentCourseScoreService.updateByPrimaryKey(studentCourseScore);
    }

    /**
     * 根据ID删除学生课程成绩。
     * @param id 学生课程成绩的唯一标识ID。
     * @return 返回受影响的行数，通常为1表示成功删除。
     */
    @DeleteMapping("/delete/{id}")
    public int deleteStudentCourseScore(@PathVariable Integer id) {
        return studentCourseScoreService.deleteByPrimaryKey(id);
    }

    /**
     * 根据课程ID获取学生及其成绩列表。
     * @param courseId 课程的唯一标识ID。
     * @return 返回包含学生信息和对应成绩的DTO列表。
     */
    @GetMapping("/course/{courseId}/students")
    public List<StudentScoreDTO> getStudentsAndScoresByCourseId(@PathVariable int courseId) {
        return studentCourseScoreService.getStudentsAndScoresByCourseId(courseId);
    }

    /**
     * 根据学生代码获取学生的课程成绩列表。
     * @param studentCode 学生的唯一标识代码。
     * @return 返回包含学生课程成绩的列表。
     */
    @GetMapping("/student/{studentCode}")
    public List<StudentCourseScore> getStudentScoresByStudentCode(@PathVariable String studentCode) {
        return studentCourseScoreService.getStudentScoresByStudentCode(studentCode);
    }
}
