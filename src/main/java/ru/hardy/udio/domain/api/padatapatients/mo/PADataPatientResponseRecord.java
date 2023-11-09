package ru.hardy.udio.domain.api.padatapatients.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIInsuredPersonResponseRecord;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientResponseRecord extends APIInsuredPersonResponseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private PADataPatientResponse response;

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
