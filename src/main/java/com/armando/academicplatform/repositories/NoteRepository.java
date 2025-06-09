package com.armando.academicplatform.repositories;

import com.armando.academicplatform.dtos.note.NoteResponseBySubjectDTO;
import com.armando.academicplatform.entities.Note;
import com.armando.academicplatform.entities.Student;
import com.armando.academicplatform.entities.Subject;
import com.armando.academicplatform.utils.NoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, NoteId> {
    List<Note> findBySubject(Subject subject);

    List<Note> findByStudent(Student student);
}
