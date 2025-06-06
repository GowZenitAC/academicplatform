package com.armando.academicplatform.dtos.student;

import lombok.Getter;

@Getter
public class StudentResponseDTO {
    private String codigoMatricula;
    private String username;

    public StudentResponseDTO(String codigoMatricula, String username) {
        this.codigoMatricula = codigoMatricula;
        this.username = username;
    }

}
