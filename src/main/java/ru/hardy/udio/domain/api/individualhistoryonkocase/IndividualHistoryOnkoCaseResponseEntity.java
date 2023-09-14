package ru.hardy.udio.domain.api.individualhistoryonkocase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int resultRequestCode;

    @OneToOne(fetch = FetchType.LAZY)
    private IndividualHistoryOnkoCaseRequest patientRequest;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public IndividualHistoryOnkoCaseResponseEntity(){

    }
}
