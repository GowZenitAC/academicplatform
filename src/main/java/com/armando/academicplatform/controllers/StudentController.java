package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.student.StudentRequestDTO;
import com.armando.academicplatform.dtos.student.StudentResponseDTO;
import com.armando.academicplatform.entities.Student;
import com.armando.academicplatform.services.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentResponseDTO> findAll(){
        return studentService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody StudentRequestDTO student, BindingResult result){
        if(result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.save(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> findById(@PathVariable String id){
        return studentService.findById(id)
                .map( student -> {
                    StudentResponseDTO dto = new StudentResponseDTO(
                        student.getCodigoMatricula(),
                        student.getUser().getName(),
                        student.getUser().getUsername()
                    );
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}
