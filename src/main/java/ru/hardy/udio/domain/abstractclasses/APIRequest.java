package ru.hardy.udio.domain.abstractclasses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class APIRequest {
    private String reqID;
    private int codeMO;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

}
