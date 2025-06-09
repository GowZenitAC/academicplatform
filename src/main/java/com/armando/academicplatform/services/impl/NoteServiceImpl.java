package com.armando.academicplatform.services.impl;

import com.armando.academicplatform.dtos.note.*;
import com.armando.academicplatform.dtos.period.*;
import com.armando.academicplatform.dtos.student.*;
import com.armando.academicplatform.entities.Note;
import com.armando.academicplatform.repositories.*;
import com.armando.academicplatform.services.NoteService;
import com.armando.academicplatform.utils.NoteId;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public NoteResponseDTO save(NoteRequestDTO dto) {
        var student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el estudiante con el id: " + dto.getStudentId()));
        var subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra la asignatura con el id: " + dto.getSubjectId()));
        var period = periodRepository.findById(dto.getPeriodId())
                .orElseThrow(() -> new EntityNotFoundException("No se encuentra el periodo con el id: " + dto.getPeriodId()));

        Note note = new Note();
        note.setStudent(student);
        note.setSubject(subject);
        note.setPeriod(period);
        note.setValue(dto.getValue());
        note.setObservations(dto.getObservations());
        return mapToNoteResponseDTO(noteRepository.save(note));
    }

    @Override
    public List<NoteResponseBySubjectDTO> findBySubjectId(Long subjectId) {
        var subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("No se encontro una asignatura con el id: " + subjectId));
        var notes = noteRepository.findBySubject(subject);

        return notes.stream()
                .map(this::mapToNoteResponseBySubject)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteResponseByStudentDTO> findByStudent(String studentId) {
        var student  = studentRepository.findById(studentId)
                .orElseThrow(() ->  new RuntimeException("No se encontro una asignatura con el id: " + studentId));
        var notes = noteRepository.findByStudent(student);

        return notes.stream()
                .map(this::mapToNoteResponseByStudent)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NoteResponseDTO> update(NoteRequestDTO dto) {
        NoteId noteId = new NoteId(dto.getStudentId(), dto.getSubjectId(), dto.getPeriodId());

        Optional<Note> noteOptional = noteRepository.findById(noteId);

        if (noteOptional.isEmpty()) return Optional.empty();

        Note note = noteOptional.get();

        note.setValue(dto.getValue());
        note.setObservations(dto.getObservations());

        return Optional.of(mapToNoteResponseDTO(note));
    }

    @Override
    public Optional<NoteResponseDTO> delete(NoteIdDTO dto) {
        NoteId noteId = new NoteId(dto.getStudentId(), dto.getSubjectId(), dto.getPeriodId());
        Optional<Note> optionalNote = noteRepository.findById(noteId);
        if (optionalNote.isEmpty()) {
            return Optional.empty(); // No existe, no se elimina
        }
        noteRepository.delete(optionalNote.get());

        return Optional.of(mapToNoteResponseDTO(optionalNote.get()));
    }

    private NoteResponseDTO mapToNoteResponseDTO(Note note){
        NoteResponseDTO dto = new NoteResponseDTO();
        dto.setStudentName(note.getStudent().getUser().getName());
        dto.setSubjectName(note.getSubject().getName());
        dto.setPeriod(mapToPeriodDTO(note));
        dto.setValue(note.getValue());
        dto.setObservations(Objects.isNull(note.getObservations()) ? null : note.getObservations());
        return dto;
    }

    private NoteResponseBySubjectDTO mapToNoteResponseBySubject(Note note){
        NoteResponseBySubjectDTO dto = new NoteResponseBySubjectDTO();
        dto.setSubjectName(note.getSubject().getName());
        dto.setStudent(mapToStudentResponse(note));
        dto.setPeriod(mapToPeriodDTO(note));
        dto.setValue(note.getValue());
        dto.setObservations(Objects.isNull(note.getObservations()) ? null : note.getObservations());
        return dto;
    }
    private NoteResponseByStudentDTO mapToNoteResponseByStudent(Note note){
        NoteResponseByStudentDTO dto = new NoteResponseByStudentDTO();
        dto.setSubjectName(note.getSubject().getName());
        dto.setStudentName(note.getStudent().getUser().getName());
        dto.setPeriod(mapToPeriodDTO(note));
        dto.setValue(note.getValue());
        return dto;
    }

    private PeriodDTO mapToPeriodDTO(Note note){
        if (Objects.isNull(note))
            return null;
        return new PeriodDTO(note.getPeriod().getName(), note.getPeriod().getStart_date(), note.getPeriod().getFinish_date());
    }

    private StudentResponseDTO mapToStudentResponse(Note note){
        return new StudentResponseDTO(note.getStudent().getCodigoMatricula(), note.getStudent().getUser().getName());
    }
}
