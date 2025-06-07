package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import com.armando.academicplatform.dtos.subject.SubjectRequestDTO;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<SubjectDetailDTO> findAll();
    Optional<SubjectDetailDTO> findById(Long id);
    SubjectDetailDTO save(SubjectRequestDTO dto);
    Optional<SubjectDetailDTO> update(Long id, SubjectRequestDTO dto);
    void delete(Long id);

}
