package ru.hardy.udio.domain.api.recommendationspatient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class RecommendationsPatientResponse extends APIResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RecommendationsPatientResponseRecord> patients;
}
