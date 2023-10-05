package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInformingResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryInformingResponse response;

    @OneToOne(fetch = FetchType.LAZY)
    private IndividualHistoryInforming requestRecord;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;


}
