package com.mw.education.dao;

import com.mw.education.domain.ClassGroup;
import com.mw.education.domain.compose.ClassGroupAndSpecialityAndCollege;

import java.util.List;

public interface ClassGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassGroup row);

    ClassGroup selectByPrimaryKey(Integer id);

    List<ClassGroup> selectAll();

    int updateByPrimaryKey(ClassGroup row);

    ClassGroupAndSpecialityAndCollege selectByPrimaryKeyJoinedSpecialityAndCollege(Integer id);

    List<ClassGroupAndSpecialityAndCollege> selectAllJoinedSpecialityAndCollege();
}
