package ru.hardy.udio.domain.api.recommendationspatient.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientSMOResponse extends APIResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private int codeMO;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RecommendationsPatientSMOResponseRecord> patients;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private RecommendationsPatientSMORequest request;
}