package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class Sex {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
