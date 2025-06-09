package com.armando.academicplatform.services;

import com.armando.academicplatform.dtos.note.*;
import com.armando.academicplatform.entities.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    NoteResponseDTO save(NoteRequestDTO dto);

    List<NoteResponseBySubjectDTO> findBySubjectId(Long subjectId);

    List<NoteResponseByStudentDTO> findByStudent(String studentId);

    Optional<NoteResponseDTO> update(NoteRequestDTO dto);

    Optional<NoteResponseDTO> delete(NoteIdDTO dto);

}
