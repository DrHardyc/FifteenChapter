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
public class PADataPatientsResponseRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private PADataPatientsResponse response;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public PADataPatientsResponseRecord(PADataPatientsRequestRecord PADataPatientsRequestRecord,
                                        PADataPatientsResponse PADataPatientsResponse, int errCode, String errMess) {
        this.setSurname(PADataPatientsRequestRecord.getSurname());
        this.setName(PADataPatientsRequestRecord.getName());
        this.setPatronymic(PADataPatientsRequestRecord.getPatronymic());
        this.setDateBirth(PADataPatientsRequestRecord.getDateBirth());
        this.setEnp(PADataPatientsRequestRecord.getEnp());
        this.setResponse(PADataPatientsResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setRespCode(errCode);
        this.setRespMessage(errMess);

    }

    public PADataPatientsResponseRecord(){

    }
}
