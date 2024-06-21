package com.mw.education.service;

import com.mw.education.dao.ExamMapper;
import com.mw.education.domain.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamMapper examMapper;

    public int deleteByPrimaryKey(Integer id) {
        return examMapper.deleteByPrimaryKey(id);
    }

    public int insert(Exam exam) {
        return examMapper.insert(exam);
    }

    public Exam selectByPrimaryKey(Integer id) {
        return examMapper.selectByPrimaryKey(id);
    }

    public List<Exam> selectAll() {
        return examMapper.selectAll();
    }

    public int updateByPrimaryKey(Exam exam) {
        return examMapper.updateByPrimaryKey(exam);
    }

}
