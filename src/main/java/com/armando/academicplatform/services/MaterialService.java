package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.material.MaterialBySubjectDTO;
import com.armando.academicplatform.dtos.material.MaterialRequestDTO;
import com.armando.academicplatform.dtos.material.MaterialResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MaterialService {

    MaterialResponseDTO save(MaterialRequestDTO dto);

    Optional<MaterialResponseDTO> delete (Long id);

    List<MaterialBySubjectDTO> findBySubjects(Long subjectId);


}
