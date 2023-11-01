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

    public MedicalOrganization(int codeMO, String fullName, String shortName){
        this.setCodeMO(codeMO);
        this.setFullName(fullName);
        this.setShortName(shortName);

    }

    public MedicalOrganization() {

    }
}
