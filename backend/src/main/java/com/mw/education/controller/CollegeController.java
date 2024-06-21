package com.mw.education.controller;

import com.mw.education.domain.College;
import com.mw.education.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @PostMapping("/add")
    public String addCollege(@RequestBody College college) {
        collegeService.addCollege(college);
        return "College added successfully!";
    }

    @PutMapping("/update")
    public String updateCollege(@RequestBody College college) {
        collegeService.updateCollege(college);
        return "College updated successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCollege(@PathVariable int id) {
        collegeService.deleteCollege(id);
        return "College deleted successfully!";
    }

    @GetMapping("/get/{id}")
    public College getCollegeById(@PathVariable int id) {
        return collegeService.getCollegeById(id);
    }

    @GetMapping("/all")
    public List<College> getAllColleges() {
        return collegeService.getAllColleges();
    }
}
