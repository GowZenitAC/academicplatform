package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.note.*;
import com.armando.academicplatform.services.NoteService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody NoteRequestDTO dto, BindingResult result) {
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.save(dto));
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody NoteRequestDTO dto, BindingResult result) {
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return noteService.update(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<NoteResponseDTO> delete(@RequestBody NoteIdDTO dto) {
        return noteService.delete(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<NoteResponseByStudentDTO>> findByStudent(@PathVariable String studentId) {
        List<NoteResponseByStudentDTO> response = noteService.findByStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<NoteResponseBySubjectDTO>> findBySubject(@PathVariable Long subjectId) {
        List<NoteResponseBySubjectDTO> response = noteService.findBySubjectId(subjectId);
        return ResponseEntity.ok(response);
    }


}
