package com.armando.academicplatform.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "periods")
@Getter
@Setter
@NoArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate start_date;

    private LocalDate finish_date;

    @OneToMany(mappedBy = "period")
    private List<Matricula> matriculas;
}
