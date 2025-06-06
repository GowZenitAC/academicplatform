package com.armando.academicplatform.repositories;

import com.armando.academicplatform.entities.Matricula;
import com.armando.academicplatform.utils.MatriculaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaId> {
}
