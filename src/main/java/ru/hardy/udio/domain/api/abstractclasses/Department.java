package ru.hardy.udio.domain.api.abstractclasses;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Department {
    private int codeMO;
    private int codeDep;
    private String nameDep;
    private int numberPlacesCurrentDay;
    private int numberPlacesNext10Days;
}
