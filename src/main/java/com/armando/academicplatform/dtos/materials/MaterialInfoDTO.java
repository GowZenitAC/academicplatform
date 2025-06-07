package com.armando.academicplatform.dtos.materials;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaterialInfoDTO {
    private Long id;
    private String title;
    private String description;
    private String file_url;
}
