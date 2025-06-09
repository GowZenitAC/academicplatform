package com.armando.academicplatform.dtos.note;

import com.armando.academicplatform.dtos.period.PeriodDTO;
import com.armando.academicplatform.dtos.subject.SubjectDetailDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteResponseByStudentDTO {
    private String studentName;

    private String subjectName;

    private PeriodDTO period;

    private Double value;

}
