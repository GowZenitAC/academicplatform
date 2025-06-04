package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
