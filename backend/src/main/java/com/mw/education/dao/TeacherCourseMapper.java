package com.mw.education.dao;

import com.mw.education.domain.Course;
import com.mw.education.domain.Teacher;
import com.mw.education.domain.TeacherCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse row);

    TeacherCourse selectByPrimaryKey(Integer id);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse row);

    List<Course> selectByTeacherId(@Param("teacherId") int teacherId);

    List<Teacher> selectTeachersByCourseId(@Param("courseId") Integer courseId);

    int countByTeacherId(@Param("teacherId") int teacherId);

    void deleteByTeacherId(@Param("teacherId") int teacherId);

    List<TeacherCourse> selectTeacherCoursesByTeacherId(@Param("teacherId") int teacherId);

}
