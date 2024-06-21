package com.mw.education.dao;

import com.mw.education.domain.Course;
import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course row);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course row);
}
