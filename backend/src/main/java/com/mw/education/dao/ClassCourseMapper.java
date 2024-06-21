package com.mw.education.dao;

import com.mw.education.domain.ClassCourse;
import java.util.List;

public interface ClassCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassCourse row);

    ClassCourse selectByPrimaryKey(Integer id);

    List<ClassCourse> selectAll();

    int updateByPrimaryKey(ClassCourse row);

    // 新增方法
    List<ClassCourse> selectByClassId(Integer classId);

    int deleteByClassId(Integer classId);
}
