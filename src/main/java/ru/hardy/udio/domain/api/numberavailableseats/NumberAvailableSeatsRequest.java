package ru.hardy.udio.domain.api.numberavailableseats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.patientonkocase.PatientOnkoCaseRequestRecord;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reqID;
    private int codeMO;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private List<NumberAvailableSeatsRequestRecord> departments;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
