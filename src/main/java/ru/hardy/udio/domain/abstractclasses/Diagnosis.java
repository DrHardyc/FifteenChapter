package ru.hardy.udio.domain.abstractclasses;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Diagnosis {
    private String codeDiagnosis;
    private String nameDiagnosis;
}
