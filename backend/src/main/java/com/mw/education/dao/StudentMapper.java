package com.mw.education.dao;

import com.mw.education.domain.Student;
import com.mw.education.domain.compose.StudentAndClassAndSpecialityAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 学生数据访问对象接口。
 * 该接口提供了对学生表的CRUD操作以及一些特定条件下的查询操作。
 */
public interface StudentMapper {
    /**
     * 根据主键删除学生记录。
     *
     * @param id 学生的唯一标识符。
     * @return 影响的行数。
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一个新的学生记录。
     *
     * @param row 要插入的学生对象。
     * @return 影响的行数。
     */
    int insert(Student row);

    /**
     * 根据主键查询学生详细信息。
     *
     * @param id 学生的唯一标识符。
     * @return 学生对象，如果找不到则为null。
     */
    Student selectByPrimaryKey(Integer id);

    /**
     * 查询所有学生记录。
     *
     * @return 学生列表。
     */
    List<Student> selectAll();

    /**
     * 根据主键更新学生记录。
     *
     * @param row 要更新的学生对象。
     * @return 影响的行数。
     */
    int updateByPrimaryKey(Student row);

    /**
     * 根据学号和密码查询学生记录数量。
     * 用于验证登录。
     *
     * @param code 学生的学号。
     * @param password 学生的密码。
     * @return 符合条件的学生数量。
     */
    @Select("select count(id) from student where code = #{code} and password = #{password}")
    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);

    /**
     * 查询所有学生记录，包括班级、专业和学院等详细信息。
     *
     * @return 包含详细信息的学生列表。
     */
    List<StudentAndClassAndSpecialityAndCollege> selectAllWithDetails();

    /**
     * 根据主键查询学生详细信息，包括班级、专业和学院等。
     *
     * @param id 学生的唯一标识符。
     * @return 包含详细信息的学生对象，如果找不到则为null。
     */
    StudentAndClassAndSpecialityAndCollege selectByIdWithDetails(@Param("id") Integer id);

    /**
     * 根据班级ID查询所有属于该班级的学生。
     *
     * @param classId 班级的唯一标识符。
     * @return 属于该班级的学生列表。
     */
    @Select("SELECT * FROM student WHERE class_id = #{classId}")
    List<Student> selectByClassId(int classId);

    /**
     * 根据学号查询学生详细信息，包括班级、专业和学院等。
     *
     * @param code 学生的学号。
     * @return 包含详细信息的学生对象，如果找不到则为null。
     */
    StudentAndClassAndSpecialityAndCollege selectByCodeWithDetails(@Param("code") String code);

    /**
     * 根据学号查询学生信息。
     *
     * @param code 学生的学号。
     * @return 学生对象，如果找不到则为null。
     */
    @Select("SELECT * FROM student WHERE code = #{code}")
    Student selectByCode(@Param("code") String code);
}
