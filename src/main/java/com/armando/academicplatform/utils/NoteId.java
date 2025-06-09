package com.armando.academicplatform.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteId implements Serializable {
    private String student;
    private Long subject;
    private Long period;
}
