package com.mw.education.dao;

import com.mw.education.domain.StudentCourseScore;
import com.mw.education.dto.StudentScoreDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学生课程成绩映射器接口，负责学生课程成绩的数据操作。
 */
public interface StudentCourseScoreMapper {
    /**
     * 根据主键删除学生课程成绩。
     *
     * @param id 主键ID，用于唯一标识一个学生课程成绩记录。
     * @return 影响的行数。
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一个新的学生课程成绩记录。
     *
     * @param row 学生课程成绩对象，包含所有需要插入的字段信息。
     * @return 影响的行数。
     */
    int insert(StudentCourseScore row);

    /**
     * 根据主键查询一个学生课程成绩记录。
     *
     * @param id 主键ID，用于唯一标识一个学生课程成绩记录。
     * @return 符合条件的学生课程成绩对象。
     */
    StudentCourseScore selectByPrimaryKey(Integer id);

    /**
     * 查询所有学生课程成绩记录。
     *
     * @return 学生课程成绩记录的列表。
     */
    List<StudentCourseScore> selectAll();

    /**
     * 根据主键更新一个学生课程成绩记录。
     *
     * @param row 学生课程成绩对象，包含所有需要更新的字段信息。
     * @return 影响的行数。
     */
    int updateByPrimaryKey(StudentCourseScore row);

    /**
     * 根据课程ID查询学生姓名、学号和成绩。
     *
     * @param courseId 课程ID，用于过滤学生课程成绩。
     * @return 包含学生姓名、学号和成绩的DTO列表。
     */
    @Select("SELECT s.name AS studentName, s.code AS studentCode, scs.score AS score " +
            "FROM student_course_score scs " +
            "JOIN student s ON scs.student_id = s.id " +
            "WHERE scs.course_id = #{courseId}")
    List<StudentScoreDTO> getStudentsAndScoresByCourseId(@Param("courseId") int courseId);

    /**
     * 根据学生ID删除学生的课程成绩记录。
     *
     * @param studentId 学生ID，用于删除该学生的课程成绩。
     */
    @Delete("DELETE FROM student_course_score WHERE student_id = #{studentId}")
    void deleteByStudentId(@Param("studentId") Integer studentId);

    /**
     * 根据学生学号查询学生的课程成绩记录。
     *
     * @param studentCode 学生学号，用于查询该学生的课程成绩。
     * @return 学生的课程成绩记录列表。
     */
    @Select("SELECT * FROM student_course_score WHERE student_id = (SELECT id FROM student WHERE code = #{studentCode})")
    List<StudentCourseScore> selectScoresByStudentCode(@Param("studentCode") String studentCode);

}
