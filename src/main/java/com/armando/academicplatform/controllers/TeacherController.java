package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import com.armando.academicplatform.dtos.teacher.TeacherRequestDTO;
import com.armando.academicplatform.dtos.teacher.TeacherSubjectSummaryDTO;
import com.armando.academicplatform.services.TeacherService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherInfoDTO> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherInfoDTO> findById(@PathVariable Long id){
            TeacherInfoDTO dto = teacherService.findById(id).orElseThrow();
            return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<TeacherSubjectSummaryDTO>> getTeacherSubjects(@PathVariable Long id){
         return ResponseEntity.ok(teacherService.getTeacherSubjectsDetail(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TeacherRequestDTO dto, BindingResult result){
        if (result.hasFieldErrors())
            ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.save(dto));
    }
}
