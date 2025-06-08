package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.entities.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    List<PeriodDTO> findAll();

    Optional<PeriodDTO> findById(Long id);

    PeriodDTO create(PeriodDTO periodDTO);
}
