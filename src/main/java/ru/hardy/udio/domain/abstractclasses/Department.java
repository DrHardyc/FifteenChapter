package ru.hardy.udio.domain.abstractclasses;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import ru.hardy.udio.domain.api.numberavailableseats.DateNumberVacantPlaces;

import java.util.List;

@Data
@MappedSuperclass
public abstract class Department {
    private int codeMO;
    private int codeDep;
    private String nameDep;
    private int numberPlacesCurrentDay;
    private int numberPlacesNext10Days;
}
