package com.mw.education.service;

import com.mw.education.dao.CourseMapper;
import com.mw.education.domain.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;

    public int deleteCourseById(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    public int addCourse(Course course) {
        return courseMapper.insert(course);
    }

    public Course getCourseById(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    public List<Course> getAllCourses() {
        return courseMapper.selectAll();
    }

    public int updateCourse(Course course) {
        return courseMapper.updateByPrimaryKey(course);
    }
}
