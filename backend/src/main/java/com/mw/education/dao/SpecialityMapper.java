package com.mw.education.dao;

import com.mw.education.domain.Speciality;
import com.mw.education.domain.compose.SpecialityAndCollege;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpecialityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Speciality row);

    Speciality selectByPrimaryKey(Integer id);

    List<Speciality> selectAll();

    int updateByPrimaryKey(Speciality row);

    SpecialityAndCollege selectByPrimaryKeyJoinedCollege(@Param("id") Integer id);

    List<SpecialityAndCollege> selectAllJoinedCollege();
}
