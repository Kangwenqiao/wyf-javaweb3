package com.mw.education.service;

import com.mw.education.dao.StudentCourseScoreMapper;
import com.mw.education.domain.StudentCourseScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mw.education.dto.StudentScoreDTO;
import java.util.List;

@Service
public class StudentCourseScoreService {

    @Autowired
    private StudentCourseScoreMapper studentCourseScoreMapper;

    public int deleteByPrimaryKey(Integer id) {
        return studentCourseScoreMapper.deleteByPrimaryKey(id);
    }

    public int insert(StudentCourseScore studentCourseScore) {
        return studentCourseScoreMapper.insert(studentCourseScore);
    }

    public StudentCourseScore selectByPrimaryKey(Integer id) {
        return studentCourseScoreMapper.selectByPrimaryKey(id);
    }

    public List<StudentCourseScore> selectAll() {
        return studentCourseScoreMapper.selectAll();
    }

    public int updateByPrimaryKey(StudentCourseScore studentCourseScore) {
        return studentCourseScoreMapper.updateByPrimaryKey(studentCourseScore);
    }

    public List<StudentScoreDTO> getStudentsAndScoresByCourseId(int courseId) {
        return studentCourseScoreMapper.getStudentsAndScoresByCourseId(courseId);
    }

    public List<StudentCourseScore> getStudentScoresByStudentCode(String studentCode) {
        return studentCourseScoreMapper.selectScoresByStudentCode(studentCode);
    }

}
