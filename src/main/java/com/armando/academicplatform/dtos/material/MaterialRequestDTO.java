package com.armando.academicplatform.dtos.material;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialRequestDTO {
    private String title;

    private String description;

    private String fileUrl;

    private Long subjectId;

    private Long teacherId;
}
