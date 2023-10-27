package ru.hardy.udio.domain.abstractclasses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class APIRequest {
    private String reqID;
    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

}
