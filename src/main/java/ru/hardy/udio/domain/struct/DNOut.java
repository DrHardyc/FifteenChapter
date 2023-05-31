package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.struct.DNGet;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "udio_tfoms")
public class DNOut extends DNGet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reason_dereg; //причина снятия с учета
}
