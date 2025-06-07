package com.armando.academicplatform.dtos.course;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String academicYear;
}
