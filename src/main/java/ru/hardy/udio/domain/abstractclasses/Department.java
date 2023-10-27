package ru.hardy.udio.domain.abstractclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class Department {
    private int codeDep;
    private String nameDep;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
