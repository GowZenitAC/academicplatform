package com.armando.academicplatform.controllers;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.entities.Period;
import com.armando.academicplatform.services.PeriodService;
import com.armando.academicplatform.utils.ValidationErrors;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periods")
public class PeriodController {

    @Autowired
    PeriodService periodService;

    @GetMapping
    public ResponseEntity<List<PeriodDTO>> findAll(){
        return ResponseEntity.ok(periodService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PeriodDTO periodDTO, BindingResult result){
        if (result.hasFieldErrors())
            return ValidationErrors.validation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodService.create(periodDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodDTO> findById(@Valid @PathVariable Long id){
        return periodService.findById(id)
                .map(period -> {
                    PeriodDTO dto = new PeriodDTO(
                            period.getName(),
                            period.getStart_date(),
                            period.getFinish_date()
                    );
                    return ResponseEntity.status(HttpStatus.OK).body(dto);
                }).orElse(ResponseEntity.notFound().build());
    }
    }
