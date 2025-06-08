package com.armando.academicplatform.dtos.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StudentRequestDTO {
    @NotBlank
    private String codigoMatricula;
    @NotNull
    private Long userId;
}
