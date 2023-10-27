package ru.hardy.udio.domain.abstractclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import ru.hardy.udio.domain.struct.Sex;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class InsuredPerson {

    private String surname;

    private String name;

    private String patronymic;

    private Date dateBirth;

    private String enp;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
