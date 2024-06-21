package com.mw.education.controller;

import com.mw.education.domain.Dean;
import com.mw.education.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dean")
public class DeanController {

    @Autowired
    private DeanService deanService;

    @PostMapping("/add")
    public String addDean(@RequestBody Dean dean) {
        deanService.addDean(dean);
        return "Dean added successfully!";
    }

    @PutMapping("/update")
    public String updateDean(@RequestBody Dean dean) {
        deanService.updateDean(dean);
        return "Dean updated successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDean(@PathVariable int id) {
        deanService.deleteDean(id);
        return "Dean deleted successfully!";
    }

    @GetMapping("/get/{id}")
    public Dean getDeanById(@PathVariable int id) {
        return deanService.getDeanById(id);
    }

    @GetMapping("/all")
    public List<Dean> getAllDeans() {
        return deanService.getAllDeans();
    }
}
