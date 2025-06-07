package com.armando.academicplatform.dtos.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long courseId;

    private Long professorId;
}
