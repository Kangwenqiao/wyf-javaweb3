package com.mw.education.service;

import com.mw.education.dao.TeacherCourseMapper;
import com.mw.education.dao.TeacherMapper;
import com.mw.education.domain.Teacher;
import com.mw.education.domain.compose.TeacherAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    public void addTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    public Teacher getTeacherById(int id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    public List<Teacher> getAllTeachers() {
        return teacherMapper.selectAll();
    }

    public TeacherAndCollege getTeacherByIdJoinedCollege(int id) {
        return teacherMapper.selectByPrimaryKeyJoinedCollege(id);
    }

    public List<TeacherAndCollege> getAllTeachersJoinedCollege() {
        return teacherMapper.selectAllJoinedCollege();
    }

    public boolean hasCourses(int teacherId) {
        return teacherCourseMapper.countByTeacherId(teacherId) > 0;
    }

    public void deleteTeacher(int id) {
        // 删除所有与该教师相关的课程记录
        teacherCourseMapper.deleteByTeacherId(id);

        // 然后再删除教师记录
        teacherMapper.deleteByPrimaryKey(id);
    }

    public Teacher getTeacherByCode(String code) {
        return teacherMapper.selectByCode(code);
    }
}
