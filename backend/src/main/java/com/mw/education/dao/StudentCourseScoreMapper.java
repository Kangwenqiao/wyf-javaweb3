package com.mw.education.dao;

import com.mw.education.domain.StudentCourseScore;
import com.mw.education.dto.StudentScoreDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentCourseScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentCourseScore row);

    StudentCourseScore selectByPrimaryKey(Integer id);

    List<StudentCourseScore> selectAll();

    int updateByPrimaryKey(StudentCourseScore row);

    @Select("SELECT s.name AS studentName, s.code AS studentCode, scs.score AS score " +
            "FROM student_course_score scs " +
            "JOIN student s ON scs.student_id = s.id " +
            "WHERE scs.course_id = #{courseId}")
    List<StudentScoreDTO> getStudentsAndScoresByCourseId(@Param("courseId") int courseId);

    @Delete("DELETE FROM student_course_score WHERE student_id = #{studentId}")
    void deleteByStudentId(@Param("studentId") Integer studentId);

    @Select("SELECT * FROM student_course_score WHERE student_id = (SELECT id FROM student WHERE code = #{studentCode})")
    List<StudentCourseScore> selectScoresByStudentCode(@Param("studentCode") String studentCode);

}
