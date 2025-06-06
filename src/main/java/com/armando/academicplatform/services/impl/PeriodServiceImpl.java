package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.entities.Period;
import com.armando.academicplatform.repositories.PeriodRepository;
import com.armando.academicplatform.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public List<Period> findAll() {
        return periodRepository.findAll();
    }

    @Override
    public Optional<Period> findById(Long id) {
        return periodRepository.findById(id);
    }

    @Override
    public Period create(PeriodDTO periodDTO) {
        Period periodCreate = new Period();
        periodCreate.setName(periodDTO.getName());
        periodCreate.setStart_date(periodDTO.getStart_date());
        periodCreate.setFinish_date(periodDTO.getFinish_date());
        return periodRepository.save(periodCreate);
    }
}
