package com.mw.education.controller;

import com.mw.education.domain.Speciality;
import com.mw.education.domain.compose.SpecialityAndCollege;
import com.mw.education.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @PostMapping("/add")
    public String addSpeciality(@RequestBody Speciality speciality) {
        specialityService.addSpeciality(speciality);
        return "Speciality added successfully!";
    }

    @PutMapping("/update")
    public String updateSpeciality(@RequestBody Speciality speciality) {
        specialityService.updateSpeciality(speciality);
        return "Speciality updated successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSpeciality(@PathVariable int id) {
        specialityService.deleteSpeciality(id);
        return "Speciality deleted successfully!";
    }

    @GetMapping("/get/{id}")
    public Speciality getSpecialityById(@PathVariable int id) {
        return specialityService.getSpecialityById(id);
    }

    @GetMapping("/all")
    public List<Speciality> getAllSpecialities() {
        return specialityService.getAllSpecialities();
    }

    @GetMapping("/getJoinedCollege/{id}")
    public SpecialityAndCollege getSpecialityByIdJoinedCollege(@PathVariable int id) {
        return specialityService.getSpecialityByIdJoinedCollege(id);
    }

    @GetMapping("/allJoinedCollege")
    public List<SpecialityAndCollege> getAllSpecialitiesJoinedCollege() {
        return specialityService.getAllSpecialitiesJoinedCollege();
    }
}
