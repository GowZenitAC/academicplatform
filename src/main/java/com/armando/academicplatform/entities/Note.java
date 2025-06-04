package com.armando.academicplatform.entities;

import com.armando.academicplatform.utils.NotaId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(NotaId.class)
public class Note {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    private Double value;
    private String observations;

}
