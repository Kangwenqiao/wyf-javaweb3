package com.mw.education.service;

import com.mw.education.dao.SpecialityMapper;
import com.mw.education.domain.Speciality;
import com.mw.education.domain.compose.SpecialityAndCollege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityMapper specialityMapper;

    public void addSpeciality(Speciality speciality) {
        specialityMapper.insert(speciality);
    }

    public void updateSpeciality(Speciality speciality) {
        specialityMapper.updateByPrimaryKey(speciality);
    }

    public void deleteSpeciality(int id) {
        specialityMapper.deleteByPrimaryKey(id);
    }

    public Speciality getSpecialityById(int id) {
        return specialityMapper.selectByPrimaryKey(id);
    }

    public List<Speciality> getAllSpecialities() {
        return specialityMapper.selectAll();
    }

    public SpecialityAndCollege getSpecialityByIdJoinedCollege(int id) {
        return specialityMapper.selectByPrimaryKeyJoinedCollege(id);
    }

    public List<SpecialityAndCollege> getAllSpecialitiesJoinedCollege() {
        return specialityMapper.selectAllJoinedCollege();
    }
}
