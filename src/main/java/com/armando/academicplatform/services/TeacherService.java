package com.armando.academicplatform.services;


import com.armando.academicplatform.dtos.teacher.TeacherInfoDTO;
import com.armando.academicplatform.dtos.teacher.TeacherRequestDTO;
import com.armando.academicplatform.dtos.teacher.TeacherSubjectSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<TeacherInfoDTO> findAll();
    Optional<TeacherInfoDTO> findById(Long id);
    TeacherInfoDTO save(TeacherRequestDTO dto);
    Optional<TeacherInfoDTO> update(Long id, TeacherRequestDTO dto);
    List<TeacherSubjectSummaryDTO> getTeacherSubjectsDetail(Long id);
}
