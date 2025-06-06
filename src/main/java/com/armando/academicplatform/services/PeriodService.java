package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.entities.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    List<Period> findAll();

    Optional<Period> findById(Long id);

    Period create(PeriodDTO periodDTO);
}
