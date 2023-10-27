package ru.hardy.udio.domain.abstractclasses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class APIResponse {
    private int resultResponseCode;
    @JsonIgnore
    private String resultRequestMess;
    private String reqID;
    private int codeMO;
    private int numberRecordsProcessed;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
