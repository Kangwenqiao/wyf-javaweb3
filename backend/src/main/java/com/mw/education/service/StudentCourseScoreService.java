package com.mw.education.service;

import com.mw.education.dao.StudentCourseScoreMapper;
import com.mw.education.domain.StudentCourseScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mw.education.dto.StudentScoreDTO;
import java.util.List;

/**
 * 学生课程成绩服务类。
 *
 * 该类提供了对学生课程成绩进行增、删、改、查等操作的服务。
 * 通过与StudentCourseScoreMapper交互，实现对数据库中学生课程成绩数据的操作。
 */
@Service
public class StudentCourseScoreService {

    /**
     * 自动注入学生课程成绩Mapper接口。
     * 该Mapper负责具体的数据访问和操作。
     */
    @Autowired
    private StudentCourseScoreMapper studentCourseScoreMapper;

    /**
     * 根据主键删除学生课程成绩。
     *
     * @param id 学生课程成绩的主键ID。
     * @return 返回删除操作影响的行数。
     */
    public int deleteByPrimaryKey(Integer id) {
        return studentCourseScoreMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入新的学生课程成绩。
     *
     * @param studentCourseScore 学生课程成绩实体对象。
     * @return 返回插入操作影响的行数。
     */
    public int insert(StudentCourseScore studentCourseScore) {
        return studentCourseScoreMapper.insert(studentCourseScore);
    }

    /**
     * 根据主键查询学生课程成绩。
     *
     * @param id 学生课程成绩的主键ID。
     * @return 返回查询到的学生课程成绩实体对象。
     */
    public StudentCourseScore selectByPrimaryKey(Integer id) {
        return studentCourseScoreMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有学生课程成绩。
     *
     * @return 返回所有学生课程成绩的列表。
     */
    public List<StudentCourseScore> selectAll() {
        return studentCourseScoreMapper.selectAll();
    }

    /**
     * 根据主键更新学生课程成绩。
     *
     * @param studentCourseScore 学生课程成绩实体对象，包含待更新的数据。
     * @return 返回更新操作影响的行数。
     */
    public int updateByPrimaryKey(StudentCourseScore studentCourseScore) {
        return studentCourseScoreMapper.updateByPrimaryKey(studentCourseScore);
    }

    /**
     * 根据课程ID获取学生及其成绩列表。
     *
     * @param courseId 课程的ID。
     * @return 返回包含学生信息和成绩的DTO列表。
     */
    public List<StudentScoreDTO> getStudentsAndScoresByCourseId(int courseId) {
        return studentCourseScoreMapper.getStudentsAndScoresByCourseId(courseId);
    }

    /**
     * 根据学生学号获取其所有课程的成绩。
     *
     * @param studentCode 学生的学号。
     * @return 返回学生的所有课程成绩列表。
     */
    public List<StudentCourseScore> getStudentScoresByStudentCode(String studentCode) {
        return studentCourseScoreMapper.selectScoresByStudentCode(studentCode);
    }
}
