package com.armando.academicplatform.dtos.note;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteIdDTO {
    private String studentId;

    private Long subjectId;

    private Long periodId;
}
