package com.mw.education.service;

import com.mw.education.dao.TermMapper;
import com.mw.education.domain.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private TermMapper termMapper;

    /**
     * 根据主键删除学期记录
     * @param id 学期ID
     * @return 受影响的行数
     */
    public int deleteById(Integer id) {
        return termMapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入新的学期记录
     * @param term 学期对象
     * @return 受影响的行数
     */
    public int insert(Term term) {
        return termMapper.insert(term);
    }

    /**
     * 根据主键查找学期记录
     * @param id 学期ID
     * @return 学期对象
     */
    public Term selectById(Integer id) {
        return termMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有学期记录
     * @return 学期对象列表
     */
    public List<Term> selectAll() {
        return termMapper.selectAll();
    }

    /**
     * 更新学期记录
     * @param term 学期对象
     * @return 受影响的行数
     */
    public int update(Term term) {
        return termMapper.updateByPrimaryKey(term);
    }
}
