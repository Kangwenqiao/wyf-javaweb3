package com.mw.education.controller;

import com.mw.education.domain.ClassGroup;
import com.mw.education.domain.compose.ClassGroupAndSpecialityAndCollege;
import com.mw.education.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassGroupController {

    @Autowired
    private ClassGroupService classGroupService;

    @DeleteMapping("/{id}")
    public int deleteClassGroup(@PathVariable Integer id) {
        return classGroupService.deleteByPrimaryKey(id);
    }

    @PostMapping
    public int insertClassGroup(@RequestBody ClassGroup classGroup) {
        return classGroupService.insert(classGroup);
    }

    @GetMapping("/{id}")
    public ClassGroup selectClassGroup(@PathVariable Integer id) {
        return classGroupService.selectByPrimaryKey(id);
    }

    @GetMapping("/all")
    public List<ClassGroup> selectAllClassGroups() {
        return classGroupService.selectAll();
    }

    @PutMapping
    public int updateClassGroup(@RequestBody ClassGroup classGroup) {
        return classGroupService.updateByPrimaryKey(classGroup);
    }

    @GetMapping("/joined/{id}")
    public ClassGroupAndSpecialityAndCollege selectClassGroupJoinedSpecialityAndCollege(@PathVariable Integer id) {
        return classGroupService.selectByPrimaryKeyJoinedSpecialityAndCollege(id);
    }

    @GetMapping("/joined/all")
    public List<ClassGroupAndSpecialityAndCollege> selectAllClassGroupsJoinedSpecialityAndCollege() {
        return classGroupService.selectAllJoinedSpecialityAndCollege();
    }
}
