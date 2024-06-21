package com.mw.education.dao;

import com.mw.education.domain.Exam;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam row);

    Exam selectByPrimaryKey(Integer id);

    List<Exam> selectAll();

    int updateByPrimaryKey(Exam row);


    @Select("SELECT e.* FROM exam e " +
            "JOIN course c ON e.course_id = c.id " +
            "JOIN class_course cc ON cc.course_id = c.id " +
            "JOIN class cl ON cl.id = cc.class_id " +
            "JOIN speciality s ON s.id = cl.speciality_id " +
            "JOIN term t ON c.term_id = t.id " +
            "WHERE t.id = #{termId} AND s.id = #{specialityId}")
    List<Exam> getExamsByTermAndSpeciality(@Param("termId") int termId, @Param("specialityId") int specialityId);


}