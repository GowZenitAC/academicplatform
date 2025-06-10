package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.material.MaterialBySubjectDTO;
import com.armando.academicplatform.dtos.material.MaterialRequestDTO;
import com.armando.academicplatform.dtos.material.MaterialResponseDTO;
import com.armando.academicplatform.services.MaterialService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MaterialRequestDTO dto, BindingResult result){
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.save(dto));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<MaterialBySubjectDTO>> findBySubject(@PathVariable Long id){
        return ResponseEntity.ok(materialService.findBySubjects(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MaterialResponseDTO> delete(@PathVariable Long id){
        return materialService.delete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
