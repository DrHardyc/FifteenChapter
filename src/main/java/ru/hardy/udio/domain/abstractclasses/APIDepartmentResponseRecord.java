package ru.hardy.udio.domain.abstractclasses;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class APIDepartmentResponseRecord extends Department {
    private int respCode;
    private String respMessage;
}
