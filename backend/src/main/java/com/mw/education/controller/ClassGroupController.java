package com.mw.education.controller;

import com.mw.education.domain.ClassGroup;
import com.mw.education.domain.compose.ClassGroupAndSpecialityAndCollege;
import com.mw.education.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器类，负责处理与班级分组相关的HTTP请求。
 */
@RestController
@RequestMapping("/class")
public class ClassGroupController {

    /**
     * 自动注入班级分组服务类，用于处理业务逻辑。
     */
    @Autowired
    private ClassGroupService classGroupService;

    /**
     * 删除指定ID的班级分组。
     *
     * @param id 班级分组的唯一标识。
     * @return 返回影响的行数。
     */
    @DeleteMapping("/{id}")
    public int deleteClassGroup(@PathVariable Integer id) {
        return classGroupService.deleteByPrimaryKey(id);
    }

    /**
     * 插入一个新的班级分组。
     *
     * @param classGroup 包含新班级分组信息的实体对象。
     * @return 返回影响的行数。
     */
    @PostMapping
    public int insertClassGroup(@RequestBody ClassGroup classGroup) {
        return classGroupService.insert(classGroup);
    }

    /**
     * 根据ID查询班级分组信息。
     *
     * @param id 班级分组的唯一标识。
     * @return 返回对应的班级分组实体对象。
     */
    @GetMapping("/{id}")
    public ClassGroup selectClassGroup(@PathVariable Integer id) {
        return classGroupService.selectByPrimaryKey(id);
    }

    /**
     * 查询所有班级分组信息。
     *
     * @return 返回班级分组的列表。
     */
    @GetMapping("/all")
    public List<ClassGroup> selectAllClassGroups() {
        return classGroupService.selectAll();
    }

    /**
     * 更新指定ID的班级分组信息。
     *
     * @param classGroup 包含更新后信息的班级分组实体对象。
     * @return 返回影响的行数。
     */
    @PutMapping
    public int updateClassGroup(@RequestBody ClassGroup classGroup) {
        return classGroupService.updateByPrimaryKey(classGroup);
    }

    /**
     * 根据ID查询班级分组，并连带查询其关联的专业和学院信息。
     *
     * @param id 班级分组的唯一标识。
     * @return 返回包含班级分组、专业和学院信息的复合实体对象。
     */
    @GetMapping("/joined/{id}")
    public ClassGroupAndSpecialityAndCollege selectClassGroupJoinedSpecialityAndCollege(@PathVariable Integer id) {
        return classGroupService.selectByPrimaryKeyJoinedSpecialityAndCollege(id);
    }

    /**
     * 查询所有班级分组，并连带查询其关联的专业和学院信息。
     *
     * @return 返回包含所有班级分组、专业和学院信息的复合实体对象的列表。
     */
    @GetMapping("/joined/all")
    public List<ClassGroupAndSpecialityAndCollege> selectAllClassGroupsJoinedSpecialityAndCollege() {
        return classGroupService.selectAllJoinedSpecialityAndCollege();
    }
}
