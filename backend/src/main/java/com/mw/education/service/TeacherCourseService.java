package com.mw.education.service;

import com.mw.education.dao.TeacherCourseMapper;
import com.mw.education.domain.Course;
import com.mw.education.domain.Teacher;
import com.mw.education.domain.TeacherCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    public int deleteByPrimaryKey(Integer id) {
        return teacherCourseMapper.deleteByPrimaryKey(id);
    }

    public int insert(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insert(teacherCourse);
    }

    public TeacherCourse selectByPrimaryKey(Integer id) {
        return teacherCourseMapper.selectByPrimaryKey(id);
    }

    public List<TeacherCourse> selectAll() {
        return teacherCourseMapper.selectAll();
    }

    public int updateByPrimaryKey(TeacherCourse teacherCourse) {
        return teacherCourseMapper.updateByPrimaryKey(teacherCourse);
    }

    public List<Course> findCoursesByTeacherId(int teacherId) {
        return teacherCourseMapper.selectByTeacherId(teacherId);
    }

    public List<Teacher> getTeachersByCourseId(Integer courseId) {
        return teacherCourseMapper.selectTeachersByCourseId(courseId);
    }
}
