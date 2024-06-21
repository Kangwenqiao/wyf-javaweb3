package com.mw.education.dao;

import com.mw.education.domain.Teacher;
import com.mw.education.domain.compose.TeacherAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher row);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher row);

    TeacherAndCollege selectByPrimaryKeyJoinedCollege(Integer id);

    List<TeacherAndCollege> selectAllJoinedCollege();


    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);


    Teacher selectByCode(@Param("code") String code);

}
