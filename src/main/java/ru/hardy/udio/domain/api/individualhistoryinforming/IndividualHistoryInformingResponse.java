package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;

@Getter
@Setter
public class IndividualHistoryInformingResponse extends InsuredPerson{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int resultRequestCode;
    private String reqID;
    private int codeMO;
    private int numberRecordsProcessed;



    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
