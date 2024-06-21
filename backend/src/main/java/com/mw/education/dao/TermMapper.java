package com.mw.education.dao;

import com.mw.education.domain.Term;

import java.util.List;

public interface TermMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Term row);

    Term selectByPrimaryKey(Integer id);

    List<Term> selectAll();

    int updateByPrimaryKey(Term row);
}