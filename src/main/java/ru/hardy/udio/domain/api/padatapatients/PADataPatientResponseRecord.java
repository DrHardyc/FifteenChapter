package ru.hardy.udio.domain.api.padatapatients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientResponseRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private PADataPatientResponse response;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public PADataPatientResponseRecord(PADataPatientRequestRecord PADataPatientRequestRecord,
                                       PADataPatientResponse PADataPatientResponse, int errCode, String errMess) {
        this.setSurname(PADataPatientRequestRecord.getSurname());
        this.setName(PADataPatientRequestRecord.getName());
        this.setPatronymic(PADataPatientRequestRecord.getPatronymic());
        this.setDateBirth(PADataPatientRequestRecord.getDateBirth());
        this.setEnp(PADataPatientRequestRecord.getEnp());
        this.setResponse(PADataPatientResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setRespCode(errCode);
        this.setRespMessage(errMess);

    }

    public PADataPatientResponseRecord(){

    }
}
