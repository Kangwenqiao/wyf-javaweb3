package com.mw.education.dao;

import com.mw.education.domain.Student;
import com.mw.education.domain.compose.StudentAndClassAndSpecialityAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student row);

    Student selectByPrimaryKey(Integer id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student row);

    @Select("select count(id) from student where code = #{code} and password = #{password}")
    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);

    List<StudentAndClassAndSpecialityAndCollege> selectAllWithDetails();

    StudentAndClassAndSpecialityAndCollege selectByIdWithDetails(@Param("id") Integer id);

    @Select("SELECT * FROM student WHERE class_id = #{classId}")
    List<Student> selectByClassId(int classId);

    StudentAndClassAndSpecialityAndCollege selectByCodeWithDetails(@Param("code") String code);

    @Select("SELECT * FROM student WHERE code = #{code}")
    Student selectByCode(@Param("code") String code);
}
