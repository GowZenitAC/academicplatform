package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.entities.Period;
import com.armando.academicplatform.repositories.PeriodRepository;
import com.armando.academicplatform.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    PeriodRepository periodRepository;

    @Override
    public List<PeriodDTO> findAll() {
        return periodRepository.findAll().stream()
                .map(this::mapToPeriodDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PeriodDTO> findById(Long id) {
        return periodRepository.findById(id).map(this::mapToPeriodDTO);
    }

    @Override
    public PeriodDTO create(PeriodDTO periodDTO) {
        Period periodCreate = new Period();
        periodCreate.setName(periodDTO.getName());
        periodCreate.setStart_date(periodDTO.getStart_date());
        periodCreate.setFinish_date(periodDTO.getFinish_date());
        return mapToPeriodDTO(periodRepository.save(periodCreate));
    }

    private PeriodDTO mapToPeriodDTO(Period period){
        if (Objects.isNull(period))
            return null;
        return new PeriodDTO(period.getName(), period.getStart_date(), period.getFinish_date());
    }
}
