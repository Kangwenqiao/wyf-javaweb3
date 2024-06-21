package com.mw.education.service;

import com.mw.education.dao.ClassCourseMapper;
import com.mw.education.domain.ClassCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassCourseService {

    @Autowired
    private ClassCourseMapper classCourseMapper;

    public int deleteByPrimaryKey(Integer id) {
        return classCourseMapper.deleteByPrimaryKey(id);
    }

    public int insert(ClassCourse classCourse) {
        return classCourseMapper.insert(classCourse);
    }

    public ClassCourse selectByPrimaryKey(Integer id) {
        return classCourseMapper.selectByPrimaryKey(id);
    }

    public List<ClassCourse> selectAll() {
        return classCourseMapper.selectAll();
    }

    public int updateByPrimaryKey(ClassCourse classCourse) {
        return classCourseMapper.updateByPrimaryKey(classCourse);
    }

    // 新增方法
    public List<ClassCourse> selectByClassId(Integer classId) {
        return classCourseMapper.selectByClassId(classId);
    }
}
