package com.armando.academicplatform.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //-> Genera getters, setters, toString(), equals() y hashCode().
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaId implements Serializable {
    private Long student;
    private Long course;
    private Long period;
}
