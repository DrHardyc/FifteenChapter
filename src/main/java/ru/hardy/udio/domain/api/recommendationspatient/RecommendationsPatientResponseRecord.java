package ru.hardy.udio.domain.api.recommendationspatient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIInsuredPersonResponseRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientResponseRecord extends APIInsuredPersonResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private RecommendationsPatientResponse response;


    public RecommendationsPatientResponseRecord (RecommendationsPatientRequestRecord patient, RecommendationsPatientResponse response,
                                     int respCode, String respMessage) {
        this.setSurname(patient.getSurname());
        this.setName(patient.getName());
        this.setPatronymic(patient.getPatronymic());
        this.setEnp(patient.getEnp());
        this.setDateBirth(patient.getDateBirth());
        this.setResponse(response);
        this.setRespCode(respCode);
        this.setRespMessage(respMessage);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public RecommendationsPatientResponseRecord() {

    }
}
