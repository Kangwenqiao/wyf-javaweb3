package com.mw.education.dao;

import com.mw.education.domain.College;
import java.util.List;

public interface CollegeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(College row);

    College selectByPrimaryKey(Integer id);

    List<College> selectAll();

    int updateByPrimaryKey(College row);
}