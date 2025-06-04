package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
