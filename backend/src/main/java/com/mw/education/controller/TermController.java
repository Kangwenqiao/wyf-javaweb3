package com.mw.education.controller;

import com.mw.education.domain.Term;
import com.mw.education.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private TermService termService;

    @PostMapping
    public ResponseEntity<Term> createTerm(@RequestBody Term term) {
        termService.insert(term);
        return ResponseEntity.ok(term);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Term> getTermById(@PathVariable Integer id) {
        Term term = termService.selectById(id);
        if (term == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(term);
    }

    @GetMapping
    public ResponseEntity<List<Term>> getAllTerms() {
        List<Term> terms = termService.selectAll();
        return ResponseEntity.ok(terms);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Term> updateTerm(@PathVariable Integer id, @RequestBody Term term) {
        term.setId(id);
        termService.update(term);
        return ResponseEntity.ok(term);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable Integer id) {
        termService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
