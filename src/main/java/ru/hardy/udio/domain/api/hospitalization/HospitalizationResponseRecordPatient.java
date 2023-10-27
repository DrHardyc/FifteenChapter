package ru.hardy.udio.domain.api.hospitalization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.struct.People;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema ="udio_datacontrol")
public class HospitalizationResponseRecordPatient extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "responseRecord_id", nullable = false)
    @JsonIgnore
    private HospitalizationResponseRecord responseRecord;

    private int respCode;
    private String respMess;

    public HospitalizationResponseRecordPatient(HospitalizationRequestRecordPatient hospitalizationRequestRecordPatient,
                                                int errCode, String errMess) {
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setSurname(hospitalizationRequestRecordPatient.getSurname());
        this.setName(hospitalizationRequestRecordPatient.getName());
        this.setPatronymic(hospitalizationRequestRecordPatient.getPatronymic());
        this.setDateBirth(hospitalizationRequestRecordPatient.getDateBirth());
        this.setEnp(hospitalizationRequestRecordPatient.getEnp());
        this.setRespCode(errCode);
        this.setRespMess(errMess);
    }
    public HospitalizationResponseRecordPatient() {

    }
}
