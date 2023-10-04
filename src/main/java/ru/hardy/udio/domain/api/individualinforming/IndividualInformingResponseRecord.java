package ru.hardy.udio.domain.api.individualinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualInformingResponseRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualInformingResponse response;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;


    public IndividualInformingResponseRecord(IndividualInformingRequestRecord individualInformingRequestRecord,
                                             IndividualInformingResponse individualInformingResponse,
                                             int errCode, String errMess) {
        this.setSurname(individualInformingRequestRecord.getSurname());
        this.setName(individualInformingRequestRecord.getName());
        this.setPatronymic(individualInformingRequestRecord.getPatronymic());
        this.setDateBirth(individualInformingRequestRecord.getDateBirth());
        this.setEnp(individualInformingRequestRecord.getEnp());
        this.setResponse(individualInformingResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
    }

    public IndividualInformingResponseRecord() {

    }
}
