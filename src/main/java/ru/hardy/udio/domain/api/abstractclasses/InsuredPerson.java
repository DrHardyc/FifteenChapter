package ru.hardy.udio.domain.api.abstractclasses;

import jakarta.persistence.MappedSuperclass;
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
}
