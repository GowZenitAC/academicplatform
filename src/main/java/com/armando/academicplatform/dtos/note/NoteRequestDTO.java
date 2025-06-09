package com.armando.academicplatform.dtos.note;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteRequestDTO {
    private String studentId;

    private Long subjectId;

    private Long periodId;

    @NotNull
    private Double value;

    private String observations;
}
