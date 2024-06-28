package com.mw.education.controller;

import com.mw.education.domain.College;
import com.mw.education.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CollegeController负责处理与学院相关的HTTP请求。
 * 它充当控制器角色，将请求转发给相应的服务层进行处理，并返回处理结果。
 */
@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    /**
     * 通过POST请求添加一个新的学院。
     * @param college 包含新学院信息的请求体。
     * @return 添加成功后的提示信息。
     */
    @PostMapping("/add")
    public String addCollege(@RequestBody College college) {
        collegeService.addCollege(college);
        return "College added successfully!";
    }

    /**
     * 通过PUT请求更新一个已存在的学院信息。
     * @param college 包含更新后学院信息的请求体。
     * @return 更新成功后的提示信息。
     */
    @PutMapping("/update")
    public String updateCollege(@RequestBody College college) {
        collegeService.updateCollege(college);
        return "College updated successfully!";
    }

    /**
     * 通过DELETE请求删除一个学院。
     * @param id 待删除学院的唯一标识。
     * @return 删除成功后的提示信息。
     */
    @DeleteMapping("/delete/{id}")
    public String deleteCollege(@PathVariable int id) {
        collegeService.deleteCollege(id);
        return "College deleted successfully!";
    }

    /**
     * 通过GET请求获取指定ID的学院信息。
     * @param id 指定学院的唯一标识。
     * @return 包含指定学院信息的响应体。
     */
    @GetMapping("/get/{id}")
    public College getCollegeById(@PathVariable int id) {
        return collegeService.getCollegeById(id);
    }

    /**
     * 通过GET请求获取所有学院的信息。
     * @return 包含所有学院信息的列表。
     */
    @GetMapping("/all")
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }
}
