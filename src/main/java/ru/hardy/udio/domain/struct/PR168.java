package ru.hardy.udio.domain.struct;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(schema = "udio_nsi")
public class PR168 {

    @Id
    private Long id;

    private String diag;

    @Enumerated(EnumType.STRING)
    private PeriodPR168 periodpr168;

    @Enumerated(EnumType.STRING)
    private Duration duration;

    private Integer spec; //в соответствии с V021 (idspec)
}
