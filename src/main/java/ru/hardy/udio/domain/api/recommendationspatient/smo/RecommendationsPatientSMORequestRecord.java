package ru.hardy.udio.domain.api.recommendationspatient.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequest;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientSMORequestRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private RecommendationsPatientSMORequest request;
}
