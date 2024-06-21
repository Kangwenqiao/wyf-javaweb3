package com.mw.education.service;

import com.mw.education.dao.ClassCourseMapper;
import com.mw.education.dao.ClassGroupMapper;
import com.mw.education.domain.ClassGroup;
import com.mw.education.domain.compose.ClassGroupAndSpecialityAndCollege;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassGroupService {

    @Resource
    private ClassGroupMapper classGroupMapper;

    @Resource
    private ClassCourseMapper classCourseMapper;

    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        classCourseMapper.deleteByClassId(id);
        return classGroupMapper.deleteByPrimaryKey(id);
    }

    public int insert(ClassGroup row) {
        return classGroupMapper.insert(row);
    }

    public ClassGroup selectByPrimaryKey(Integer id) {
        return classGroupMapper.selectByPrimaryKey(id);
    }

    public List<ClassGroup> selectAll() {
        return classGroupMapper.selectAll();
    }

    public int updateByPrimaryKey(ClassGroup row) {
        return classGroupMapper.updateByPrimaryKey(row);
    }

    public ClassGroupAndSpecialityAndCollege selectByPrimaryKeyJoinedSpecialityAndCollege(Integer id) {
        return classGroupMapper.selectByPrimaryKeyJoinedSpecialityAndCollege(id);
    }

    public List<ClassGroupAndSpecialityAndCollege> selectAllJoinedSpecialityAndCollege() {
        return classGroupMapper.selectAllJoinedSpecialityAndCollege();
    }
}
