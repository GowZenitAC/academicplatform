package com.armando.academicplatform.dtos.period;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PeriodDTO {
    private String name;
    private LocalDate start_date;
    private LocalDate finish_date;
}
