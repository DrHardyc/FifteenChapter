package ru.hardy.udio.domain.nsi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "udio_nsi")
public class MedicalOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeMO;
    private String fullName;
    private String shortName;

}
