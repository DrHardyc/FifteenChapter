package ru.hardy.udio.domain.api.recommendationspatient.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientRequestRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private RecommendationsPatientRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private RecommendationsPatient patient;

    private Date dateRecommendation;
    private String recommendation;
    private String mainDiagnosis;
    private String possibilityHolding;

    @OneToMany(mappedBy = "requestRecord", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Participant> participants;
}
