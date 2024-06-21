package com.mw.education.service;

import com.mw.education.dao.StudentCourseScoreMapper;
import com.mw.education.dao.StudentMapper;
import com.mw.education.domain.Student;
import com.mw.education.domain.compose.StudentAndClassAndSpecialityAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseScoreMapper studentCourseScoreMapper;

    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    public void save(Student student) {
        if (student.getId() == null) {
            studentMapper.insert(student);
        } else {
            studentMapper.updateByPrimaryKey(student);
        }
    }

    @Transactional
    public void delete(Integer id) {
        // 先删除与学生相关的成绩记录
        studentCourseScoreMapper.deleteByStudentId(id);
        // 再删除学生记录
        studentMapper.deleteByPrimaryKey(id);
    }

    public List<StudentAndClassAndSpecialityAndCollege> findAllWithDetails() {
        return studentMapper.selectAllWithDetails();
    }

    public StudentAndClassAndSpecialityAndCollege findByIdWithDetails(Integer id) {
        return studentMapper.selectByIdWithDetails(id);
    }

    public List<Student> findStudentsByClassId(int classId) {
        return studentMapper.selectByClassId(classId);
    }

    public StudentAndClassAndSpecialityAndCollege findByCodeWithDetails(String code) {
        return studentMapper.selectByCodeWithDetails(code);
    }

    public Student findByCode(String code) {
        return studentMapper.selectByCode(code);
    }
}
