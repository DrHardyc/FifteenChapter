package ru.hardy.udio.domain.abstractclasses;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class APIInsuredPersonResponseRecord extends InsuredPerson{
    private int respCode;
    private String respMessage;
}
