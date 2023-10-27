package ru.hardy.udio.domain.api.recommendationspatient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;

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
}
