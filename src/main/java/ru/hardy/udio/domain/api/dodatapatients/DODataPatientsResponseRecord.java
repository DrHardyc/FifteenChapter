package ru.hardy.udio.domain.api.dodatapatients;

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
public class DODataPatientsResponseRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private DODataPatientsResponse response;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public DODataPatientsResponseRecord(DODataPatientsRequestRecord doDataPatientsRequestRecord,
                                        DODataPatientsResponse doDataPatientsResponse, int errCode, String errMess) {
        this.setSurname(doDataPatientsRequestRecord.getSurname());
        this.setName(doDataPatientsRequestRecord.getName());
        this.setPatronymic(doDataPatientsRequestRecord.getPatronymic());
        this.setDateBirth(doDataPatientsRequestRecord.getDateBirth());
        this.setEnp(doDataPatientsRequestRecord.getEnp());
        this.setResponse(doDataPatientsResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setRespCode(errCode);
        this.setRespMessage(errMess);

    }

    public DODataPatientsResponseRecord(){

    }
}
