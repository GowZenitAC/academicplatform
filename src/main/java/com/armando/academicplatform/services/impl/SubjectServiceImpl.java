package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import com.armando.academicplatform.dtos.subject.SubjectRequestDTO;
import com.armando.academicplatform.services.SubjectService;

import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public List<SubjectDetailDTO> findAll() {
        return List.of();
    }

    @Override
    public Optional<SubjectDetailDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public SubjectDetailDTO save(SubjectRequestDTO dto) {
        return null;
    }

    @Override
    public Optional<SubjectDetailDTO> update(Long id, SubjectRequestDTO dto) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
