package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import com.armando.academicplatform.dtos.subject.SubjectRequestDTO;
import com.armando.academicplatform.services.SubjectService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDetailDTO> findAll(){
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDetailDTO> findById(Long id){
            var subject = subjectService.findById(id).orElseThrow();
        return ResponseEntity.ok(subject);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SubjectRequestDTO dto, BindingResult result){
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SubjectRequestDTO dto, BindingResult result){
        var optionalSubject = subjectService.findById(id);
        if (optionalSubject.isPresent())
            return ResponseEntity.ok(subjectService.update(id, dto));
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        var optionalSubject = subjectService.delete(id);
        if (optionalSubject.isPresent())
            return ResponseEntity.ok(optionalSubject.orElseThrow());
        return ResponseEntity.notFound().build();
    }
}
