package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.course.CourseRequestDTO;
import com.armando.academicplatform.dtos.course.CourseResponseDTO;
import com.armando.academicplatform.services.CourseService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> findAll(){
        List<CourseResponseDTO> dto = courseService.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id){
        CourseResponseDTO dto = courseService.findById(id).orElseThrow();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CourseRequestDTO courseRequestDTO, BindingResult result){
        if (result.hasFieldErrors())
            ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(courseRequestDTO));
    }
}
