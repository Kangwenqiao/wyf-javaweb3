package com.mw.education.service;

import com.mw.education.domain.Dean;
import com.mw.education.dao.DeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeanService {

    @Autowired
    private DeanMapper deanMapper;

    public void addDean(Dean dean) {
        deanMapper.insert(dean);
    }

    public void updateDean(Dean dean) {
        deanMapper.update(dean);
    }

    public void deleteDean(int id) {
        deanMapper.delete(id);
    }

    public Dean getDeanById(int id) {
        return deanMapper.findById(id);
    }

    public List<Dean> getAllDeans() {
        return deanMapper.findAll();
    }
}
