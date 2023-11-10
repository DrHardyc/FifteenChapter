package ru.hardy.udio.domain.api.recommendationspatient.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIDepartmentResponseRecord;
import ru.hardy.udio.domain.abstractclasses.APIInsuredPersonResponseRecord;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequestRecord;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientResponse;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientSMOResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private RecommendationsPatientSMOResponse response;

    private Date dateRecommendation;
    private String recommendation;
    private String mainDiagnosis;
    private String possibilityHolding;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "responseRecord_id")
    private List<ParticipantSMO> participants;

    public RecommendationsPatientSMOResponseRecord(RecommendationsPatientRequestRecord requestRecord,
                                                   RecommendationsPatientSMOResponse recommendationsPatientSMOResponse,
                                                   List<ParticipantSMO> participantSMOS) {
        this.setResponse(recommendationsPatientSMOResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setSurname(requestRecord.getSurname());
        this.setName(requestRecord.getName());
        this.setPatronymic(requestRecord.getPatronymic());
        this.setDateBirth(requestRecord.getDateBirth());
        this.setEnp(requestRecord.getEnp());
        this.setDateRecommendation(requestRecord.getDateRecommendation());
        this.setRecommendation(requestRecord.getRecommendation());
        this.setMainDiagnosis(requestRecord.getMainDiagnosis());
        this.setPossibilityHolding(requestRecord.getPossibilityHolding());
        this.setParticipants(participantSMOS);
    }

    public RecommendationsPatientSMOResponseRecord() {

    }
}
