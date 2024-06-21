package com.mw.education.dao;

import com.mw.education.domain.Dean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeanMapper {



    List<Dean> findAll();

    Dean findById(int id);

    void insert(Dean dean);

    void update(Dean dean);

    void delete(int id);

    @Select("SELECT COUNT(*) FROM dean WHERE code = #{code} AND password = #{password}")
    int countByCodeAndPassword(@Param("code") String code, @Param("password") String password);


}
