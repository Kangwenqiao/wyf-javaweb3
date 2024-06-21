package com.mw.education.service;

import com.mw.education.domain.College;
import com.mw.education.dao.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    public void addCollege(College college) {
        collegeMapper.insert(college);
    }

    public void updateCollege(College college) {
        collegeMapper.updateByPrimaryKey(college);
    }

    public void deleteCollege(int id) {
        collegeMapper.deleteByPrimaryKey(id);
    }

    public College getCollegeById(int id) {
        return collegeMapper.selectByPrimaryKey(id);
    }

    public List<College> getAllColleges() {
        return collegeMapper.selectAll();
    }
}
